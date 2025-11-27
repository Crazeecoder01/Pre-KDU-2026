import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PopularMerchandise {

    public static void PrintTopK(HashMap<String, Integer>hm, Integer k){
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
            (a, b)-> b.getValue()-a.getValue()
        );

        for(Map.Entry<String, Integer> it: hm.entrySet()){
            pq.offer(it);
        }

        for(int i=0;i<k;i++){
            if(!pq.isEmpty()){
                Map.Entry<String, Integer> entry = pq.poll();
                System.out.println(entry);
            }
        }
    }
    public static void main(String args[]){

        String filePath = "Items.csv";
        HashMap<String, Integer> hm = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            System.out.println("Reading file: " + filePath);
            String line;
            String delimiter = ",";
            while((line = reader.readLine())!=null){
                String[] tokens = line.split(delimiter);
                for(String t: tokens){
                    t = t.trim();
                    if(!t.isEmpty()){
                        hm.put(t, hm.getOrDefault(t, 0)+1);
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Error: File '" + filePath + "' not found!");
            return;
        }  
        catch(IOException e){
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        
        System.out.println("All merchandise counts: " + hm);

        PrintTopK(hm, 3);
    }
}
