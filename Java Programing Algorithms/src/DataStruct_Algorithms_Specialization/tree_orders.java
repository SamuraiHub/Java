import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
                        
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		int[] inOrder() {
			int[] result = new int[n];
                        int[] stack = new int[n];
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
                        int i = 0, j = n-1, k = 0; stack[j] = 0; 
                        
                        while(j < n)
                        {
                            while (left[i] != -1) 
                            {
                                i = left[i];
                                j--;
                                stack[j] = i;
                            }
                            
                            result[k++] = key[stack[j]];
                            
                            if (right[stack[j]] != -1) 
                            {
                                stack[j] = right[stack[j]];
                                i = stack[j];
                            }
                            else
                            {
                                j++;
                            }
                        }
                        
			return result;
		}

		int[] preOrder() {
			int[] result = new int[n];
                        int[] stack = new int[n];
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
                        int i = 0, j = n-1, k = 0; stack[j] = 0; result[k++] = key[0]; 
                        
                        while(j < n)
                        {
                            while (left[i] != -1) 
                            {
                                i = left[i];
                                j--;
                                stack[j] = i;
                                result[k++] = key[i];
                            }
                            
                            if (right[stack[j]] != -1) 
                            {
                                stack[j] = right[stack[j]];
                                i = stack[j];
                                result[k++] = key[i];
                            }
                            else
                            {
                                j++;
                            }
                            
                        }
                        
			return result;
		}

		int[] postOrder() {
			int[] result = new int[n];
                        int[] stack = new int[n];
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
                        int i = 0, j = n-1, k = 0; stack[j] = 0; 
                        
                        while(j < n)
                        {
                            while (left[i] != -1) 
                            {
                                i = left[i];
                                j--;
                                stack[j] = i;
                            }
                            
                            if (right[stack[j]] != -1 && right[stack[j]] != stack[j-1]) 
                            {
                                stack[j-1] = right[stack[j]];
                                j--;
                                i = stack[j];
                            }
                            else
                            {
                                result[k++] = key[stack[j]];
                                j++;
                            }
                        }
                        
			return result;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(int[] x) {
		for (int a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
