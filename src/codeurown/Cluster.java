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
public class Cluster extends ArrayList<String>{
    private String name;
    private static char names = 'A';
    private int clusterCount = 0;
    
    Cluster(String codeName1, String codeName2){
        add(codeName1);
        add(codeName2);
        name = "Cluster " + names++;
        clusterCount++;
    }
    
    Cluster(String codeName){
        add(codeName);
        name = "Cluster " + names++;
        clusterCount++;
    }
    
    public void addToCluster(String codeName){
        add(codeName);
        clusterCount++;
    }
    
    public String toString(){
        return name;
    }
    
    public static void resetName(){
        names = 'A';
    }
}
