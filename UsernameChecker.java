import java.util.Scanner;

public class UsernameChecker{
    public static void checker(String s1, String s2){
        int l1 = s1.length();
        int l2 = s2.length();
        System.out.println("Length 1: "+l1);
        System.out.println("Length 2: "+l2);
        
        System.out.println("Lengths match: "+(l1 == l2));
        // It compares memory addresses (references), not the actual text content.
        // System.out.println("Strings match: "+(s1 == s2));
        System.out.println("Strings match: "+(s1.equals(s2)));
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter username: ");
        String usname = sc.nextLine();

        System.out.print("Confirm username: ");
        String confname = sc.nextLine();
        
        checker(usname, confname);

        sc.close();

    }
}