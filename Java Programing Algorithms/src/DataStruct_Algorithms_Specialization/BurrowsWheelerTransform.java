import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BurrowsWheelerTransform {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    public static char[] getColumn(char[][] array, int index){
    char[] column = new char[array[index].length];  
    for(int i=0; i<column.length; i++){
       column[i] = array[i][index];
    }
    return column;
}

    String BWT(String text) {
        // write your code here
        int l = text.length()-1;
        char[][] cyclicRotations = new char[text.length()][text.length()];
        cyclicRotations[0] = text.toCharArray();
        
        for(int i = 1; i < text.length(); i++)
        {
            cyclicRotations[i][0] = cyclicRotations[i-1][l];
            System.arraycopy(cyclicRotations[i-1], 0, cyclicRotations[i], 1, l);
        }
        
        Arrays.sort(cyclicRotations, (Object o1, Object o2) -> {
            char[] co1 = (char[]) o1;
            char[] co2 = (char[]) o2;
            for(int i = 0; i < co1.length; i++)
            {
                if(co1[i] < co2[i])
                    return -1;
                if(co1[i] > co2[i])
                    return 1;
            }
            return 0;
        });

        return String.copyValueOf(getColumn(cyclicRotations, l));
    }

    static public void main(String[] args) throws IOException {
        new BurrowsWheelerTransform().run();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();
        String text = scanner.next();
        System.out.println(BWT(text));
    }
}
