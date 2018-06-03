/*
 * ANTI-JAVA SOURCE CODE PLAGIARSIM
 * USING STRING MATCHING AND
 * SEQUENTIAL SEARCH ALGORITHMS
 */
package codeurown;

import java.util.ArrayList;

/**
 * 
 * @authors
 * Castañeda, Joshua Robert B.
 * Militar, Jessica Justine E.
 * Zubiri, Ceasar Jem Rick D.
 * Fiestada, Angelo F.
 */
public class CodeBlock implements Comparable<CodeBlock>{
    
    private String name;
    private ArrayList<String> codeContent = new ArrayList<>();
    private ArrayList<CodeBlock> blockContent = new ArrayList<>();
    private BlockType blockType;
    public String className = "";
    
    public static enum BlockType{
        CLASS("Class"),
        METHOD("Method"),
        SWITCH("Switch"),
        TRY_CATCH("Try Catch"),
        PROGRAM("Program"),
        LOOP("Loop"),
        CONDITION("Condition");
        
        
        private String blockName;
        BlockType(String blockName){
            this.blockName = blockName;
        }
        
        public String getName(){
            return blockName;
        }
    }
    
    CodeBlock(String name, BlockType type){
        this.name = name;
        blockType = type;
    }
    
    public void addBlock(CodeBlock block){
        blockContent.add(block);
    }
    
    public void addCode(String code){
        codeContent.add(code);
    }
    
    public ArrayList<CodeBlock> getBlocks(){
        return blockContent;
    }
    
    public ArrayList<String> getCodes(){
        return codeContent;
    }
    
    public String getName(){
        return name;
    }
    
    public BlockType getType(){
        return blockType;
    }
    
    public int compareTo(CodeBlock prevBlock){
        if(this.blockContent.size() + this.codeContent.size() == prevBlock.getBlocks().size() + prevBlock.getCodes().size()){
            return name.compareTo(prevBlock.getName());
        }
        else if(this.blockContent.size() + this.codeContent.size() > prevBlock.getBlocks().size() + prevBlock.getCodes().size())
            return 1;
        else
            return -1;
    }
}
