/*
 * ANTI-JAVA SOURCE CODE PLAGIARSIM
 * USING STRING MATCHING AND
 * SEQUENTIAL SEARCH ALGORITHMS
 */
package codeurown;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @authors
 * Castañeda, Joshua Robert B.
 * Militar, Jessica Justine E.
 * Zubiri, Ceasar Jem Rick D.
 * Fiestada, Angelo F.
 */
public class Keywords extends HashMap<String, String> {
    
    Keywords(){
        try{
            loadKeywords();
        }catch(FileNotFoundException e){
        }
    }
    
    public void loadKeywords() throws FileNotFoundException{
        Scanner sc = new Scanner(new File("keywords.csv"));
        String keyWord;
        while(sc.hasNext()){
            keyWord = sc.nextLine();
            put(keyWord.split(",")[1], keyWord.split(",")[0]);
        }
    }
    
}
