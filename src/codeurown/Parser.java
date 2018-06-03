/*
 * ANTI-JAVA SOURCE CODE PLAGIARSIM
 * USING STRING MATCHING AND
 * SEQUENTIAL SEARCH ALGORITHMS
 */
package codeurown;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;
/**
 * 
 * @authors
 * Castañeda, Joshua Robert B.
 * Militar, Jessica Justine E.
 * Zubiri, Ceasar Jem Rick D.
 * Fiestada, Angelo F.
 */
public class Parser {
    
        
    private String codeToken = "";
    private String formattedCodeToken = "";
    private Stack<String> className = new Stack<>();
    private Keywords keywords = new Keywords();
    private int index = 0;
    private String token = "";
    private CodeBlock block = new CodeBlock("Java", CodeBlock.BlockType.PROGRAM);
    private boolean escapeLoop = false;
    private boolean inCase = false;
    private int stackN = 0;
    private ArrayList<Integer> x = new ArrayList<>(); 
    
    public String getToken(ArrayList<String> tokenList){
        className.push("");
        token = tokenList.get(0);
        while(!token.equals("class"))
            token = tokenList.get(++index);
        parseCode(tokenList, block);
        System.out.println("DONE PARSING");
        collectToken(block);
        return codeToken;
    }
    
    /**
     * tokens are put in the code block tree
     * recursive call happens when new block of code is created.
     * when '{' is met as a token
     * @param tokenList
     * @param block 
     */
    private void parseCode(ArrayList<String> tokenList, CodeBlock block){
        do{
            if(token.equals("package") || token.equals("import")){
                while(!token.equals(";"))
                    token = tokenList.get(++index);
                token = tokenList.get(++index);
            }
            //CLASS
            if(token.equals("class") || token.equals("interface")){
                parseClassBlock(tokenList, block);
            }
            
            //METHODS
            else if(token.equals("(")){
                parseMethodBlock(tokenList, block);
            }
            
            //DECLARATIONS AND ASSIGNMENTS
            if(token.equals(";") || token.equals("=") || token.equals(",") || token.equals("new")){
                parseDeclarationAssignment(tokenList, block);
            }
            
            //METHOD CALLS
            if(token.equals("(") && 
                    (tokenList.get(index-2).equals(".") || tokenList.get(index-2).equals(";") || tokenList.get(index-2).equals("{") || tokenList.get(index-2).equals("}") || tokenList.get(index-2).equals("=") || tokenList.get(index-2).equals(")") || tokenList.get(index-2).equals(":"))
                    && !tokenList.get(index-1).equals(className.peek())
                    &&!tokenList.get(index-1).equals("if") && !tokenList.get(index-1).equals("else")){
                ArrayList<String> methodCalls = new ArrayList<>();
                parseMethodCall(tokenList, methodCalls);
                String methodName = "";
                for(String s : methodCalls){
                    methodName += s;
                }
                block.addCode(methodName);
                //System.out.println(methodName + "\tADDED TO\t" + block.getName());
            }
                        
            //OPERATIONS
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%")){
                parseOperation(tokenList, block);
            }
            
            //CONDITIONAL AND LOOP BLOCKS
            if(token.equals("if") || token.equals("else") || token.equals("for") || token.equals("while") || token.equals("do") || token.equals("switch") || token.equals("case") || token.equals("try") || token.equals("catch")){
                parseBlock(tokenList, block);
            }
            
            //Local Blocks Bracket Check
            if(token.equals("{")){
                escapeLoop = false;
            }
            else if(token.equals("}") && stackN != 0)
                escapeLoop = true;
            if((token.equals(";") || token.equals("}")) && escapeLoop){
                stackN--;
                token = tokenList.get(--index);
                escapeLoop = true;
                if(stackN == 0)
                    escapeLoop = false;
                break; 
            }
            
            if(token.equals("return")){
                while(!token.equals(";"))
                    token = tokenList.get(++index);
                block.addCode("R");
            }
            
            if(token.equals("<")){
                int stack = 0;
                while(!token.equals(">") && stack != 0){
                    token = tokenList.get(++index);
                }
            }
            
            if(token.equals("{") && tokenList.get(index-1).equals("=")){
                int stack = 1;
                while(!token.equals("}") && stack != 0){
                    token = tokenList.get(++index);
                    if(token.equals("{"))
                        stack++;
                    else if(token.equals("}"))
                        stack--;
                }
            }
            
            if(index != tokenList.size()-1)
                token = tokenList.get(++index);
            
            if(token.equals("case") && inCase){
                inCase = false;
                break;
            }
            
        }while(!token.equals("}") && (!tokenList.get(index+1).equals(";") || !tokenList.get(index+1).equals(",")));
        
        if(index != tokenList.size()-1 && !token.equals("}") && !token.equals("case"))
            token = tokenList.get(++index);
        if(token.equals("case"))
            token = tokenList.get(--index);
        if(!className.isEmpty())
            className.pop();
    }
    
    //CLASS
    private void parseClassBlock(ArrayList<String> tokenList, CodeBlock block){
        className.push(tokenList.get(index+1));
        String blockName = "C";
        String tempSuffix = "";
        index++;
        while(!token.equals("{")){
            token = tokenList.get(++index);
            if(token.equals("extends"))
                tempSuffix += "e";
            if(token.equals("implements"))
                tempSuffix += "p";
            if(token.equals(","))
                tempSuffix += "p";
        }
        char[] tmpCharArray = tempSuffix.toCharArray();
        Arrays.sort(tmpCharArray);
        blockName += new String(tmpCharArray);

        //RECURSIVE CALL
        CodeBlock tempBlock = new CodeBlock(blockName, CodeBlock.BlockType.CLASS);
        block.addBlock(tempBlock);
        //System.out.println(blockName + "\tADDED TO\t" + block.getName());
        parseCode(tokenList, tempBlock);
    }
    //METHODS
    private void parseMethodBlock(ArrayList<String> tokenList, CodeBlock block){
        if(tokenList.get(index-3).equals("static") 
            || tokenList.get(index-3).equals("public") 
            || tokenList.get(index-3).equals("private") 
            || tokenList.get(index-3).equals("protected")
            || tokenList.get(index-1).equals(className.peek())){

            String methodName = "";
            if(!tokenList.get(index-1).equals(className.peek()))
                methodName += (keywords.get(tokenList.get(index-2)) == null ? "o" : keywords.get(tokenList.get(index-2)));
            methodName += "M";

            String tmpSuffix = "";
            boolean jump = false;
            while(!token.equals(")")){
                token = tokenList.get(++index);
                if(!jump && !token.equals(")")){
                    tmpSuffix += (keywords.get(token) == null ? "o" : keywords.get(token));
                    jump = true;
                }
                if(token.equals(","))
                    jump = false;
            }

            char[] tmpCharArr = tmpSuffix.toCharArray();
            Arrays.sort(tmpCharArr);
            methodName += new String(tmpCharArr);

            while(!token.equals("{") && !token.equals(";")){
                token = tokenList.get(++index);
                if(token.equals("throws") || token.equals(","))
                    methodName += 't';
            }
            if(token.equals("{")){
                //RECURSIVE CALL
                className.push("METHOD");
                CodeBlock tmpBlock = new CodeBlock(methodName, CodeBlock.BlockType.METHOD);
                block.addBlock(tmpBlock);
                //System.out.println(methodName + "\tADDED TO\t" + block.getName());
                parseCode(tokenList, tmpBlock);
            }
            else if(token.equals(";")){
                block.addCode(methodName);
                //System.out.println(methodName + "\tADDED TO\t" + block.getName());
            }
        }
    }
    //METHOD CALLS
    private void parseMethodCall(ArrayList<String> tokenList, ArrayList<String> methodCalls){
        String codeName = "F";
        String suffix = "";
        boolean skip = false;
        boolean skipExit = false;
        while(!token.equals(")") || skipExit){
            skipExit = false;
            token = tokenList.get(++index);
            if(tokenList.get(index+1).equals("(") || tokenList.get(index+1).equals("."))
                skip = true;
            if(!skip && !token.equals(",") && !token.equals(")"))
                suffix += (keywords.get(token) == null ? "o" : keywords.get(token));
            else if(token.equals(","))
                skip = false;
            if(token.equals("(")){
                parseMethodCall(tokenList, methodCalls);
                suffix += "f";
                skipExit = true;
            }
        }
        char[] tmpCharArray = suffix.toCharArray();
        Arrays.sort(tmpCharArray);
        codeName += new String(tmpCharArray);
        methodCalls.add(codeName);
    }
    //DECLARATIONS AND ASSIGNMENTS
    private void parseDeclarationAssignment(ArrayList<String> tokenList, CodeBlock block){
        if((token.equals(";") || token.equals("=") || token.equals(",")) && 
                ((tokenList.get(index-3).equals(";") && !tokenList.get(index-2).equals("}")) 
                || tokenList.get(index-3).equals("private") || tokenList.get(index-3).equals("public") || tokenList.get(index-3).equals("static")
                || tokenList.get(index-3).equals("{") || tokenList.get(index-3).equals("}") 
                ||tokenList.get(index-2).equals(",") 
                || (tokenList.get(index-3).equals(")") && !tokenList.get(index-2).equals(";") && !tokenList.get(index-2).equals("{") && !token.equals(";"))) 
                && !tokenList.get(index-1).equals("break")){
            block.addCode("D" + (keywords.get(tokenList.get(index-2)) == null ? "o" : keywords.get(tokenList.get(index-2))));
            if(token.equals("="))
                block.addCode("A");
            //System.out.println("DECASS\tADDED TO\t" + block.getName());
        }
        else if(token.equals("new")){
            block.addCode("D" + (keywords.get(tokenList.get(index+1)) == null ? "o" : keywords.get(tokenList.get(index+1))));
            block.addCode("A");
            //System.out.println("DECASS\tADDED TO\t" + block.getName());
        }
        if(token.equals("=") && (tokenList.get(index-2).equals(";") || tokenList.get(index-2).equals("{") || tokenList.get(index-2).equals("}"))){
            block.addCode("A");
            //System.out.println("ASS\tADDED TO\t" + block.getName());
        }
    }
    //OPERATIONS
    private void parseOperation(ArrayList<String> tokenList, CodeBlock block){
        do{
            token = tokenList.get(--index);
            if(token.equals("+=") || token.equals("-=") || token.equals("*=") || token.equals("/="))
                break;
        }while(!token.equals("="));
        ArrayList<String> expression = new ArrayList<>();
        do{
            token = tokenList.get(++index);
            expression.add(token);
        }while(!tokenList.get(index+1).equals(";"));
        expression = toPostfix(expression);
        String opName = "";
        boolean skip = false;
        for(String s : expression){
            if(!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/") && !s.equals("%") && !skip){
                opName += "n";
                skip = true;
            }
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%")){
                opName += s;
                skip = false;
            }
        }
        block.addCode("O" + opName);
        //System.out.println("O" + opName + "\tADDED TO\t" + block.getName());
    }
    //CONDITIONAL AND LOOP BLOCKS
    private void parseBlock(ArrayList<String> tokenList, CodeBlock block){
        if(token.equals("if") || token.equals("for") || token.equals("while") || token.equals("catch") || (token.equals("else") && tokenList.get(index+1).equals("if"))){
            CodeBlock.BlockType blockType = null;
            if(token.equals("if") || token.equals("else"))
                blockType = CodeBlock.BlockType.CONDITION;
            else if(token.equals("for") || token.equals("while"))
                blockType = CodeBlock.BlockType.LOOP;
            else if(token.equals("switch"))
                blockType = CodeBlock.BlockType.SWITCH;
            
            int stackP = 0;
            while(!token.equals(")") || stackP != 0){
                token = tokenList.get(++index);
                if(token.equals("("))
                    stackP++;
                else if(token.equals(")"))
                    stackP--;
            }
            if(!tokenList.get(index+1).equals("{")){
                stackN++;
                escapeLoop = true;
                //System.out.println("ASDASDASDASDASDASDASD");
            }
            if(!tokenList.get(index+1).equals(";")){
                className.push("BLOCK");
                CodeBlock tempBlock = new CodeBlock("B", blockType);
                block.addBlock(tempBlock);
                //System.out.println("B" + "\tADDED TO\t" + block.getName());
                parseCode(tokenList, tempBlock);
            }
        }
        if((token.equals("else") && !tokenList.get(index+1).equals("if")) || token.equals("try")){
            CodeBlock.BlockType blockType = null;
            if(token.equals("else"))
                blockType = CodeBlock.BlockType.CONDITION;
            else if(token.equals("try"))
                blockType = CodeBlock.BlockType.TRY_CATCH;
            token = tokenList.get(++index);
            if(!token.equals("{")){
                stackN++;
                escapeLoop = true;
                //System.out.println("ASDASDASDASDASDASDASD");
            }
            if(!token.equals(";")){
                className.push("BLOCK");
                CodeBlock tempBlock = new CodeBlock("B", blockType);
                block.addBlock(tempBlock);
                //System.out.println("B" + "\tADDED TO\t" + block.getName());
                parseCode(tokenList, tempBlock);
            }
        }
        else if(token.equals("do")){
            token = tokenList.get(++index);
            className.push("BLOCK");
            CodeBlock tempBlock = new CodeBlock("B", CodeBlock.BlockType.CONDITION);
            block.addBlock(tempBlock);
            //System.out.println("B" + "\tADDED TO\t" + block.getName());
            parseCode(tokenList, tempBlock);
            while(!token.equals(";"))
                token = tokenList.get(++index);
        }
        if(token.equals("switch")){
            while(!token.equals("{"))
                token = tokenList.get(++index);
            className.push("SWITCH");
            CodeBlock tempBlock = new CodeBlock("S", CodeBlock.BlockType.SWITCH);
            block.addBlock(tempBlock);
            //System.out.println("S" + "\tADDED TO\t" + block.getName());
            parseCode(tokenList, tempBlock);
        }
        if(token.equals("case")){
            while(!token.equals(":"))
                token = tokenList.get(++index);
            className.push("CASE");
            if(!tokenList.get(index+1).equals("{"))
                inCase = true;
            CodeBlock tempBlock = new CodeBlock("W", CodeBlock.BlockType.SWITCH);
            block.addBlock(tempBlock);
            //System.out.println("W" + "\tADDED TO\t" + block.getName());
            parseCode(tokenList, tempBlock);
        }
    }
    
    private int indentCount = 0;
    private void collectToken(CodeBlock block){
        for(CodeBlock b : block.getBlocks()){
            int blockValue = 0;
            Collections.sort(b.getCodes());
            Collections.sort(b.getBlocks());
            int count = 0;
            for(int i = 0; i < indentCount; i++){
                formattedCodeToken += "―";
                count++;
            }
            codeToken += count;
            codeToken += b.getName() + ",";
            formattedCodeToken += b.getName() + "\n";    
            indentCount++;
            for(String c : b.getCodes()){
                count = 0;
                for(int i = 0; i < indentCount; i++){
                    formattedCodeToken += "―";
                    count++;
                }
                codeToken += count;
                codeToken += c + ",";
                formattedCodeToken += c + "\n";
                if(c.charAt(0) == 'D')
                    blockValue += 2;
                else if(c.charAt(0) == 'A')
                    blockValue += 1;
                else
                    blockValue += 3;
            }
            
            for(CodeBlock bl : b.getBlocks()){
                if(bl.getType() == CodeBlock.BlockType.CLASS)
                    blockValue += 15;
                else if(bl.getType() == CodeBlock.BlockType.METHOD)
                    blockValue += 10;
                else if(bl.getType() == CodeBlock.BlockType.LOOP)
                    blockValue += 7;
                else if(bl.getType() == CodeBlock.BlockType.TRY_CATCH)
                    blockValue += 1;
                else if(bl.getType() == CodeBlock.BlockType.CONDITION)
                    blockValue += 3;
                else if(bl.getType() == CodeBlock.BlockType.SWITCH)
                    blockValue += 5;
            }
            x.add(block.getBlocks().size() + block.getCodes().size()); //modify this
            collectToken(b);
            indentCount--;
        }
    }
    
    public String getFormattedToken(){
        return formattedCodeToken;
    }
    
    public ArrayList<String> toPostfix(ArrayList<String> expression){
        Stack<String> operators = new Stack<>();
        ArrayList<String> postfix = new ArrayList();
        for(String token : expression){
            if(token.equals("("))
                operators.push(token);
            else if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("%")){
                if(operators.isEmpty() || toInt(token) > toInt(operators.peek())){
                    operators.push(token);
                }
                else{
                    while(!operators.isEmpty() && toInt(token) < toInt(operators.peek())){
                        postfix.add(operators.pop());
                    }
                    operators.push(token);
                }
            }
            else if(token.equals(")")){
                while(!operators.peek().equals("(")){
                    postfix.add(operators.pop());
                }
                operators.pop();
            }
            else
                postfix.add(token);
        }
        while(!operators.isEmpty()){
            postfix.add(operators.pop());
        }
        
        return postfix;
    }
    public int toInt(String operator){
        switch (operator.toCharArray()[0]){
            case '-' : 
            case '+' : { return 1; }
            case '/' : 
            case '*' : { return 2; }
            case '%' : { return 3; }
        }
        return 0;
    }
    
    public ArrayList<String> tokenize(File file) throws IOException{
        
        FileReader fileReader = new FileReader(file);
        StreamTokenizer tokenizer = new StreamTokenizer(fileReader);
        tokenizer.parseNumbers();
        tokenizer.wordChars('_', '_');
        
        tokenizer.eolIsSignificant(true);
        tokenizer.ordinaryChars(0, ' ');
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        tokenizer.ordinaryChar('.');
        tokenizer.ordinaryChar(',');
        tokenizer.ordinaryChar('+');
        tokenizer.ordinaryChar('-');
        tokenizer.ordinaryChar('/');
        tokenizer.ordinaryChar('*');
        tokenizer.slashSlashComments(true);
        tokenizer.slashStarComments(true);
        
        ArrayList<String> tokenList = new ArrayList<>();
        String all = "";
        int token = 0;
        while(token != StreamTokenizer.TT_EOF){
            token = tokenizer.nextToken();
            switch(token){
                case StreamTokenizer.TT_NUMBER: {
                    double number = tokenizer.nval;
                    tokenList.add(number + "");
                    all += number + "\n";
                    break;
                }
                case StreamTokenizer.TT_WORD: {
                    String word = tokenizer.sval;
                    tokenList.add(word);
                    all += word + "\n";
                    break;
                }
                case '"': {
                    String quote = tokenizer.sval;
                    tokenList.add("String");
                    all += quote + "\n";
                    break;
                }
                case 39 : {
                    String aposthrophe = tokenizer.sval;
                    tokenList.add("Charac");
                    all += aposthrophe + "\n";
                    break;
                }
                case StreamTokenizer.TT_EOL: {
                    break;
                }
                case StreamTokenizer.TT_EOF: {
                    break;
                }
                default : {
                    char symbol = (char)tokenizer.ttype;
                    if(symbol == '{' || symbol == '}' 
                            || symbol == '(' || symbol == ')' 
                            || symbol == '[' || symbol == ']' 
                            || symbol == '+' || symbol == '-' 
                            || symbol == '*' || symbol == '/' 
                            || symbol == '%' || symbol == '=' 
                            || symbol == '>' || symbol == '<' 
                            || symbol == '?' || symbol == ';' 
                            || symbol == ':' || symbol == '|'
                            || symbol == '&' || symbol == ','
                            || symbol == '.'){
                        if(symbol == '='){
                            if(tokenList.get(tokenList.size()-1).equals("+")
                                    || tokenList.get(tokenList.size()-1).equals("-")
                                    || tokenList.get(tokenList.size()-1).equals("/")
                                    || tokenList.get(tokenList.size()-1).equals("*")
                                    || tokenList.get(tokenList.size()-1).equals("%")
                                    || tokenList.get(tokenList.size()-1).equals("=")
                                    || tokenList.get(tokenList.size()-1).equals("<")
                                    || tokenList.get(tokenList.size()-1).equals(">")){
                                String temp = tokenList.get(tokenList.size()-1) + symbol + "";
                                all += temp + "\n";
                                tokenList.set(tokenList.size()-1, temp);
                            }
                            else{
                               tokenList.add(symbol + "");
                                all += symbol + "\n";
                            }
                        }
                        else if(symbol == '+' && tokenList.get(tokenList.size()-1).equals("+")){
                            String temp = tokenList.get(tokenList.size()-1) + symbol + "";
                            tokenList.set(tokenList.size()-1, temp);
                        }
                        else if(symbol == '-' && tokenList.get(tokenList.size()-1).equals("-")){
                            String temp = tokenList.get(tokenList.size()-1) + symbol + "";
                            tokenList.set(tokenList.size()-1, temp);
                        }
                        else if(symbol == '|' && tokenList.get(tokenList.size()-1).equals("|")){
                            String temp = tokenList.get(tokenList.size()-1) + symbol + "";
                            tokenList.set(tokenList.size()-1, temp);
                        }
                        else if(symbol == '&' && tokenList.get(tokenList.size()-1).equals("&")){
                            String temp = tokenList.get(tokenList.size()-1) + symbol + "";
                            tokenList.set(tokenList.size()-1, temp);
                        }
                        else if(symbol == '['){
                            int stack = 1;
                            while(stack != 0){
                                token = tokenizer.nextToken();
                                symbol = (char)tokenizer.ttype;
                                if(symbol == '[')
                                    stack++;
                                if(symbol == ']')
                                    stack--;
                            }
                        }/*
                        else if(symbol == '<'){
                            int stack = 1;
                            while(stack != 0){
                                token = tokenizer.nextToken();
                                symbol = (char)tokenizer.ttype;
                                if(symbol == '<')
                                    stack++;
                                if(symbol == '>')
                                    stack--;
                            }
                        }*/
                        else{
                            tokenList.add(symbol + "");
                            all += symbol + "\n";
                        }
                        
                    }
                }
            }
        }
        //System.out.println(all);
        fileReader.close();
        return tokenList;
    }  
    
    public ArrayList<Integer> getX(){
        return x;
    }
}
