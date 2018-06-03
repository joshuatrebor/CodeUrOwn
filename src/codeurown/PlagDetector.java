/*
 * ANTI-JAVA SOURCE CODE PLAGIARSIM
 * USING STRING MATCHING AND
 * SEQUENTIAL SEARCH ALGORITHMS
 */
package codeurown;
import javax.swing.*;
import java.util.*;
import java.io.*;
/**
 * 
 * @authors
 * Castañeda, Joshua Robert B.
 * Militar, Jessica Justine E.
 * Zubiri, Ceasar Jem Rick D.
 * Fiestada, Angelo F.
 */
public class PlagDetector extends SwingWorker<Void, Void>{
    
    private ArrayList<File> files;
    private ArrayList<Cluster> clusters = new ArrayList<>();
    private ArrayList<String> tokenStrings = new ArrayList<>();
    private ArrayList<String[]> splitTokens = new ArrayList<>();
    private float filesParsed = 0;
    private ArrayList<ArrayList<Integer>> x = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private String log = "LOG\n\n";
    private int pPercentage;
    
    PlagDetector(ArrayList<File> files, int percentage) throws IOException{
        this.files = files;
        pPercentage = percentage;
    }
    
    protected Void doInBackground(){
        float progress = 0;
        for(File file : files){
            try{
                float max = files.size();
                Parser parser = new Parser();
                ArrayList<String> tokens = parser.tokenize(file);
                String a = parser.getToken(tokens);
                tokenStrings.add(a);
                filesParsed++;
                progress = filesParsed/max/2*100;
                log += file.getName() + "\nGenerated Code Structure Representation\n" + parser.getFormattedToken() + "Generated Code Token: " + a + "\n\n";
                setProgress((int)progress);
                names.add(file.getName());
                x.add(parser.getX());
            }catch(IOException e){}
            
        }
        log += "\n\nProgram Comparison Results      (Difference/Average code size)";
        detect();
        return null;
    }
    
    public void done(){
        setProgress(0);
        new Graph(x, names, clusters, log);
    }
    
    //SEQUENTIAL SEARCH ALGORITHM
    public void detect(){
        parseToken();
        double progress;
        double ctr = 0;
        boolean[] housed = new boolean[splitTokens.size()];
        for(int i = 0; i < housed.length; i++)
            housed[i] = false;
        Cluster.resetName();
        for(int i = 0; i < splitTokens.size(); i++){
            if(!housed[i]){
                housed[i] = true;
                clusters.add(new Cluster(names.get(i)));
            }
            for(int j = i+1; j < splitTokens.size(); j++){
                int diff = compare(splitTokens.get(i), splitTokens.get(j));
                log += names.get(i) + " vs. " + names.get(j) + "\t" + diff + "/" + ((splitTokens.get(i).length + splitTokens.get(j).length)/2) + "\n";
                double dRate = (double)diff/(double)((splitTokens.get(i).length + splitTokens.get(j).length)/2) * 100;
                int pRate = 100 - (int)dRate;
                System.out.println("  " +pRate);
                if(pRate >= pPercentage){
                    getCluster(names.get(i)).add(names.get(j));
                    housed[j] = true;
                }

            }
            ctr++;
            progress = ctr/files.size()/2*100 + 50;
            setProgress((int)progress);
        }
        
        for(Cluster cluster : clusters){
            System.out.println(cluster);
            for(String name : cluster){
                System.out.println("\t" + name);
            }
        }    
    }
    
    public Cluster getCluster(String codeName){
        for(Cluster cluster : clusters){
            if(cluster.contains(codeName))
                return cluster;
        }
        System.out.println("NULLED");
        return null;
    }
    
    //STRING MATCHING ALGORITHM
    public int compare(String[] token1, String[] token2){
        int ctr1 = 0;
        int ctr2 = 0;
        int diff = 0;
        while(ctr1 < token1.length && ctr2 < token2.length){
            if(token1[ctr1].compareTo(token2[ctr2]) == 0){
                ctr1++;
                ctr2++;
            }
            else if(token1[ctr1].compareTo(token2[ctr2]) > 0){
                ctr2++;
                diff++;
            }
            else if(token1[ctr1].compareTo(token2[ctr2]) < 0){
                ctr1++;
                diff++;
            }
        }
        return diff;     
    }
    
    public void parseToken(){
        for(String token : tokenStrings){
            String[] splitStrings = token.split(",");
            Arrays.sort(splitStrings);
            splitTokens.add(splitStrings);
        } 
    }
    
    
}
