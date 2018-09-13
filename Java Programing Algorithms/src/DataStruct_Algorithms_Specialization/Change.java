import java.util.Scanner;

public class Change {
    private static int getChange(int n) {
        //write your code here
        
        int i = 0;
        
        while(10 <= n) {
            
            n = n - 10;
            i++;
        }
            
        
        if(5 <= n) {
            
            n = n - 5;
            i++;
        }
        
        while(1 <= n) {
            
            n = n - 1;
            i++;
        }
        
        return i;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(getChange(n));

    }
}

