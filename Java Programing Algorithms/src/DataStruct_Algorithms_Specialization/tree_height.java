import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
                        // Replace this code with a faster implementation
			int[] Height = new int[n]; int maxHeight = 0;
                        
			for (int vertex = 0; vertex < n; vertex++) {
				if(Height[vertex] != 0) 
                                    continue;
                                
                                Height[vertex] = 1;
                                int i = parent[vertex];
				for (; i != -1; i = parent[i])
                                {
                                    if(Height[i] > 0)
                                    {
                                        Height[vertex] = Height[vertex] + Height[i];
                                        break;
                                    }
                                    else
                                    {
					Height[vertex]++;
                                    }
                                }
				maxHeight = Math.max(maxHeight, Height[vertex]);
                                int k = vertex;
                                for(int j = parent[k]; j != i; j = parent[j])
                                {
                                    Height[j] = Height[k] - 1;
                                    k = j;
                                }
			}
			return maxHeight;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
