import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TicketCategories {
    public static void categories(ArrayList<String>x){
        HashSet<String> hs = new HashSet<>();
        System.out.println("ArrayList: "+x);
        HashMap<String, Integer> hm = new HashMap<>();
        for(String s: x){
            hs.add(s);
            hm.put(s, hm.getOrDefault(s,0)+1);
        }
        System.out.println("HashSet: "+ hs);
        System.out.println("HashMap: "+ hm);

    }
    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        ArrayList<String> x = new ArrayList<>();

        for(int i=0;i<10;i++){
            String in = sc.nextLine();
            x.add(in);
        }
        categories(x);
        sc.close();


    }
}
