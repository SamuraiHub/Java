import java.io.*;
import java.util.*;

class RopeProblem {
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
        
          // Vertex of a splay tree
    class Vertex {
        // Sum of all the keys in the subtree - remember to update
        // it after each operation that changes the tree.
        char c;
        int sum;
        Vertex left;
        Vertex right;
        Vertex parent;

        Vertex(char c, int sum, Vertex left, Vertex right, Vertex parent) {
            this.sum = sum;
            this.c = c;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    class VertexPair {
        Vertex left;
        Vertex right;
  
        VertexPair(Vertex left, Vertex right) {
            this.left = left;
            this.right = right;
        }
    }

    // Searches for the given key in the tree with the given root
    // and calls splay for the deepest visited node after that.
    // Returns pair of the result and the new root.
    // If found, result is a pointer to the node with the given key.
    // Otherwise, result is a pointer to the node with the smallest
    // bigger key (next value in the order).
    // If the key is bigger than all keys in the tree,
    // then result is null.
    VertexPair find(Vertex root, int key) {
        Vertex v = root;
        Vertex next = null;
        
        ArrayDeque<Vertex> Rights = new ArrayDeque<>(18);
        
        if(key <= 0)
           return new VertexPair(next,root);
        
        if(key > v.sum)
            return new VertexPair(root,next);

        while (v != null) {
            
            if (v.sum == key) 
            {
                next = v;
                break;
            }
             
            if(v.left != null)
            {
              if(v.left.sum < key)
              {
                key = key - v.left.sum;
                v = v.right;
              }
              else
              {
                  if(v.right != null) { Rights.push(v);}
                  v = v.left;
              }
            }
            else
            {
                v = v.right;
            }    
        }
        
        Vertex R = Rights.poll();
        
        if(R!= null)
        {
            R.sum = R.left.sum;
            R.c = R.left.c;            
            next = R.left.right;
            R.left = R.left.left;
            if(R.left != null) { R.left.parent = R; }
            R = R.right;
            R.parent.right = next;
            if(next != null) { next.parent = R.parent; }
            next = R.parent;
            R.parent = null;
        }
        
        while(!Rights.isEmpty())
        {
            Vertex R1 = Rights.pop();
            while(next.parent.sum != R1.sum)
            {
              next = next.parent;  
              next.sum = (next.left != null ? next.left.sum : 0) + (next.right != null ? next.right.sum : 0);
            }
            
            R1.sum = R1.left.sum;
            R1.c = R1.left.c;
            next = R1.left.right;
            R1.left = R1.left.left;
            if(R1.left != null) { R1.left.parent = R1; }
            R1 = R1.right;
            R1.parent.right = next;
            if(next != null) { next.parent = R1.parent; }
            next = R1.parent;
            R1.parent = null;
            R = merge(R, R1);
        }
        
        while(next.parent != null)
        {  
           next = next.parent; 
           next.sum = (next.left != null ? next.left.sum : 0) + (next.right != null ? next.right.sum : 0);
        }
         
        VertexPair NandR = new VertexPair(next,R);
        
        return NandR;
    }

    VertexPair split(Vertex root, int key) {
 
        VertexPair findandRight = find(root, key);
        //root = findAndRoot.right;
   
        if (findandRight.left == null) 
        {
            return new VertexPair(null, root);
        }
           
        return findandRight;
    }

    Vertex merge(Vertex left, Vertex right) {
        if (left == null) return right;
        if (right == null) return left;
    
        int h1 = 0, h2 = 0, h = 0;
        
        Vertex l = left;
        
        while((l = l.left) != null)
        {
           h1++; 
        }
        
        Vertex r = right;
        
        while((r = r.left) != null)
        {
            h2++;
        }
        
        if(h2 > h1 && right.left != null)
        {
          r = right;
          h2 = h2 - h1;
          while(h < h2)
          {
              h++;
              r = r.left;
          }
          l = new Vertex('\u0000', left.sum+r.sum, left, r, r.parent);
          r=r.parent;
          r.left = l;
          left.parent = l;
          l.right.parent = l; 
          r.sum = (r.left != null ? r.left.sum : 0) + (r.right != null ? r.right.sum : 0);
          r = r.parent;
          while(r != null)
          {
              r.sum = (r.left != null ? r.left.sum : 0) + (r.right != null ? r.right.sum : 0);
              r = r.parent;
          }
        }
        else if(h1 > h2 && left.right != null)
        {
          l = left;
          h1 = h1 - h2;
          while(l.right != null && h < h1)
          {
              h++;
              l = l.right;
          }

          r = new Vertex('\u0000', l.sum+right.sum, l, right, l.parent);
          l=l.parent;
          l.right = r;
          right.parent = r;
          r.left.parent = r;
          l.sum = (l.left != null ? l.left.sum : 0) + (l.right != null ? l.right.sum : 0);
          
          while(l.parent != null)
          {
              l = l.parent;
              l.sum = (l.left != null ? l.left.sum : 0) + (l.right != null ? l.right.sum : 0);
          }
          right = l;
        }
        else
        {
            r = new Vertex('\u0000', left.sum+right.sum, left, right, null);
            left.parent = r;
            right.parent = r;
            right = r;
        }

        return right;
    }


    Vertex root = null;

    void insert(char c) 
    {
        Vertex new_vertex = new Vertex(c, 1, null, null, null);
        
        if(root != null)
        {
            Vertex v = root;
            while(v.right != null)
            {
                v = v.right;
            }
            if(v.sum == 1)
            {
               if(v.left != null)
               {
                   while (v.left.c == '\u0000') 
                   {
                       v = v.left;
                   }
                   
                   v.right = new_vertex;
                   new_vertex.parent = v;
                   v.sum = 2;

                   while (v.parent != null) 
                   {
                       v = v.parent;
                       v.sum += 1;
                   }
               }
               else
               {
                   Vertex nr = new Vertex('\u0000', 1 + root.sum, null, null, null);

                   nr.right = new Vertex('\u0000', 1, null, null, null);
                   Vertex lf = nr.right;
                   lf.parent = nr;
                   while (v.parent != null) {
                       v = v.parent;
                       lf.left = new Vertex('\u0000', 1, null, null, null);
                       lf.left.parent = lf;
                       lf = lf.left;
                   }
                   lf.c = c;

                   root.parent = nr;
                   nr.left = root;
                   root = nr;
               }
            }
            else
            {   
               
               Vertex n = v; 
                while (v.left.c == '\u0000') 
                {
                    v = v.left;
                    if(v.right != null)
                    {
                        n = v.parent;
                        v = v.right;
                        while(v.right != null) {
                           v = v.right; }
                       
                        if(v.left == null)
                            break;
                        else
                            n = v;
                    }
                }
                if(v.left == null)
                {
                    n.sum += 1;
                    n.right = new Vertex('\u0000', 1, null, null, null);
                    v = v.parent;
                    Vertex lf = n.right;
                     lf.parent = n;
                    while(v.sum != n.sum)
                    {
                       v = v.parent;
                       lf.left = new Vertex('\u0000', 1, null, null, null);
                       lf.left.parent = lf;
                       lf = lf.left;
                    }
                    lf.c = c;
                    
                    n = n.parent;
                    n.sum += 1;
                    while(n.parent != null)
                    {
                        n = n.parent;
                        n.sum += 1;
                    }
                }
                else
                {
                   v.right = new_vertex;
                   new_vertex.parent = v;
                   v.sum = 2;

                   while (v.parent != null) 
                   {
                       v = v.parent;
                       v.sum += 1;
                   } 
                }
            }    
        }
        else
        {
            root = new Vertex('\u0000', 1, null, null, null);
            root.left = new_vertex;
            new_vertex.parent = root;
        }  
    }


	class Rope {
		int l;

		void process( int i, int j, int k ) throws IOException {
                        // Replace this code with a faster implementation
                        //String t = s.substring(0, i) + s.substring(j + 1);
                        //s = t.substring(0, k) + s.substring(i, j + 1) + t.substring(k);
                        if(k < i)
                        {
                            //k = k+j+1-i;
                            VertexPair middleRight = split(root, j+1);
                            Vertex right = middleRight.right;
                            Vertex middle = middleRight.left;
                            VertexPair leftMiddle = split(middle, k);
                            Vertex left = leftMiddle.left;
                            middle = leftMiddle.right;
                            VertexPair Middle = split(middle, i-k);
                            middle = Middle.left;
                            Vertex mr = Middle.right;
         
                            root = merge(merge(left,mr),merge(middle,right));
                        }
                        else
                        {
                            k = k+j+1-i;
                            VertexPair middleRight = split(root, k);
                            Vertex right = middleRight.right;
                            Vertex middle = middleRight.left;
                            //System.out.println(result(middle));
                            VertexPair leftMiddle = split(middle, i);
                            Vertex left = leftMiddle.left;
                            middle = leftMiddle.right;
                            //System.out.println(result(middle));
                            VertexPair Middle = split(middle,j+1-i);
                            middle = Middle.left;
                            Vertex mr = Middle.right;

                            root = merge(merge(left,mr),merge(middle,right));
                        }
                        
                        
                        //VertexPair leftMiddle = split(root, i);
                        //Vertex left = leftMiddle.left;
                        //Vertex middle = leftMiddle.right;
                        //VertexPair middleRight = split(middle, j+1);
                        //middle = middleRight.left;
                        //Vertex right = middleRight.right;
                        //changeKey(right, j+1-i);
                        //changeKey(middle,i-k);
                        //Vertex BegEnd = merge(left,right);
                        //VertexPair leftRight = split(BegEnd, k);
                        //left = leftRight.left;
                        //right = leftRight.right;
                        //changeKey(right, i-j-1);
                        //root = merge(merge(left,middle),right);       
		}

		String result() {
			
                    char[] c = new char[l];
                    int k = 0;
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
                        ArrayDeque<Vertex> v = new ArrayDeque<>(20);
                        Vertex a = root;
                        v.push(a);
                        
                        while(!v.isEmpty())
                        {
                           a = v.pop();
                           if(a.right != null) { v.push(a.right); }
                           if(a.left != null) { v.push(a.left); }
                           else { if(a.right == null) { c[k] = a.c; k++; } }  
                        }
                          
                        return String.valueOf(c);
		}
                
		Rope( String s ) {
                        l = s.length();

                        for(int i = 0; i < s.length(); i++)
                        {
                          insert(s.charAt(i));
                        }
		}
	}

	public static void main( String[] args ) throws IOException {
		new RopeProblem().run();
                
        /*int maxOperations = 100001;
        int maxNumber = 300001;
        String inputData = "";

        //inputData = maxOperations + "\n";

//for the operations            
        for (int j = 0; j < 1; j++) 
        {
           
//select the operator                
            Random operatorRand = new Random();
            int l = operatorRand.nextInt(maxNumber);
            StringBuilder a = new StringBuilder(l);
            Random randChar = new Random();
            for(int i = 0; i < l; i++)
                a.append((char)('a'+randChar.nextInt(25)));
            
            inputData = a.toString()+"\n";
//select a random number to be used                
            Random randNumber = new Random();
            int num = randNumber.nextInt(maxOperations);
            inputData = inputData + num+"\n";
            for(int i = 0; i < num; i++)
            {
               Random rn = new Random(); 
               int m = rn.nextInt(l);
               int n = rn.nextInt(l-m)+m;
               int k =rn.nextInt(l-(n-m));
               inputData = inputData +m+" "+n+" "+k+"\n";
            }

           
        System.out.println(inputData);
        InputStream stdin = System.in;

        try {
            System.setIn(new ByteArrayInputStream(inputData.getBytes()));
            (new RopeProblem()).run();
        } finally {
            System.setIn(stdin);
        }
                
	}*/
      }
	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		Rope rope = new Rope(in.next());
		for (int q = in.nextInt(); q > 0; q--) {
			int i = in.nextInt();
			int j = in.nextInt();
			int k = in.nextInt();
			rope.process(i, j, k);
		}
		out.println(rope.result());
		out.close();
	}
}
