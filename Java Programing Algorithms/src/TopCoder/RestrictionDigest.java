/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TopCoder;

import java.util.Arrays;

/**
 *
 * @author Aljarhi
 */
import java.io.*;
import java.util.Arrays;

public class RestrictionDigest {

    public RestrictionDigest(){}

        /**
     * Sorts the specified sub-array of integers into ascending order.
     */
    private static void sort1(int x[], int off, int len) {

	// Choose a partition element, v
	int m = off + (len >> 1);       // Small arrays, middle element
	if (len > 7) {
	    int l = off;
	    int n = off + len - 1;
	    if (len > 40) {        // Big arrays, pseudomedian of 9
		int s = len/8;
		l = med3(x, l,     l+s, l+2*s);
		m = med3(x, m-s,   m,   m+s);
		n = med3(x, n-2*s, n-s, n);
	    }
	    m = med3(x, l, m, n); // Mid-size, med of 3
	}
	int v = x[m];

	// Establish Invariant: v* (<v)* (>v)* v*
	int a = off, b = a, c = off + len - 1, d = c;
	while(true) {
	    while (b <= c && x[b] <= v) {
		if (x[b] == v)
		    swap(x, a++, b);
		b++;
	    }
	    while (c >= b && x[c] >= v) {
		if (x[c] == v)
		    swap(x, c, d--);
		c--;
	    }
	    if (b > c)
		break;
	    swap(x, b++, c--);
	}

	// Swap partition elements back to middle
	int s, n = off + len;
	s = Math.min(a-off, b-a  );  vecswap(x, off, b-s, s);
	s = Math.min(d-c,   n-d-1);  vecswap(x, b,   n-s, s);

	// Recursively sort non-partition-elements
	if ((s = b-a) > 1)
	    sort1(x, off, s);
	if ((s = d-c) > 1)
	    sort1(x, n-s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(int x[], int a, int b) {
	int t = x[a];
	x[a] = x[b];
	x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(int x[], int a, int b, int n) {
	for (int i=0; i<n; i++, a++, b++)
	    swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed integers.
     */
    private static int med3(int x[], int a, int b, int c) {
	return (x[a] < x[b] ?
		(x[b] < x[c] ? b : x[a] < x[c] ? c : a) :
		(x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }

        private static int binarySearch(int[] a, int fromIndex, int toIndex,
				     int key) {
	int low = fromIndex;
	int high = toIndex - 1;

	while (low <= high) {
	    int mid = (low + high) >>> 1;
	    int midVal = a[mid];

	    if (midVal < key)
		low = mid + 1;
	    else if (midVal > key)
		high = mid - 1;
	    else
		return mid; // key found
	}
	return -(low + 1);  // key not found.
    }

                    /* tries to add the cut to the existing number of cuts for the specfic enzymme (if it does not exist). return true if
         successfully the cut is added. Else if some of the required restictions did not satisfy returns false
         (this means that this specific enzyme will fail the set, since cuts don't disappear).*/
      boolean addCut(int a1, int k[], int i, int[] cuts, int N, int maxFragmentCount, int minFragmentLength)
    {
          if(k[i] == 0)
          {
              if(k[i]+1 ==  maxFragmentCount)
               {
                    return false;
               }

            if(a1  < minFragmentLength ||  N - a1 < minFragmentLength)
            {
                return false;
            }

              cuts[0] = a1;
              k[i]++;
              return true;
          }
                    if(N/(k[i]+1) < minFragmentLength)
                    return false;

                        int f = binarySearch(cuts, 0, k[i], a1);

                   if(f < 0)
                   {
                       if(k[i]+1 ==  maxFragmentCount)
                       {
                          return false;
                       }

                           f = -f-1;

                        if(f == k[i])
                       {
                         if((a1 - cuts[f-1])  < minFragmentLength ||  N - a1 < minFragmentLength)
                          {
                              return false;
                          }
                       }

                       else  if (f > 0 && f < k[i])
                       {
                          if((a1 - cuts[f-1]) < minFragmentLength || (cuts[f] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                       }

                         else
                      {
                           if(a1 < minFragmentLength || (cuts[0] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                      }

                     add(cuts, a1, f, k[i]);
                     k[i]++;
                   }
                        return true;
      }

            boolean addCircularCut(int a1, int[] k, int i, int[] cuts, int N, int maxFragmentCount, int minFragmentLength)
    {
          if(k[i] == 0)
          {
              cuts[0] = a1;
               k[i]++;
              return true;
          }
          if(N/k[i] < minFragmentLength)
           return false;

                        int f = binarySearch(cuts, 0, k[i], a1);

                   if(f < 0)
                   {
                     if(k[i] ==  maxFragmentCount)
                     {
                        return false;
                     }

                           f = -f-1;

                        if(f == k[i])
                       {
                         if((a1 - cuts[f-1])  < minFragmentLength ||  N - a1 + cuts[0] < minFragmentLength)
                          {
                              return false;
                          }
                       }

                       else  if (f > 0 && f < k[i])
                       {
                          if((a1 - cuts[f-1]) < minFragmentLength || (cuts[f] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                       }

                         else
                      {
                           if(a1 + N - cuts[k[i]-1] < minFragmentLength || (cuts[0] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                      }

                     add(cuts, a1, f, k[i]);
                     k[i]++;
                   }
                        return true;
      }

                  boolean addCut1(int a1, int k[], int i, int[] cuts, int N, int maxFragmentCount, int minFragmentLength)
    {
                  if(N/(k[i]+1) < minFragmentLength)
                    return false;
           
                        int f = binarySearch(cuts, 0, k[i], a1);

                   if(f < 0)
                   {
                       if(k[i]+1 ==  maxFragmentCount)
                       {
                          return false;
                       }

                           f = -f-1;

                        if(f == k[i])
                       {
                         if((a1 - cuts[f-1])  < minFragmentLength ||  N - a1 < minFragmentLength)
                          {
                              return false;
                          }
                       }

                       else  if (f > 0 && f < k[i])
                       {
                          if((a1 - cuts[f-1]) < minFragmentLength || (cuts[f] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                       }

                         else
                      {
                           if(a1 < minFragmentLength || (cuts[0] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                      }

                     add(cuts, a1, f, k[i]);
                     k[i]++;
                   }
                        return true;
      }

            boolean addCircularCut1(int a1, int[] k, int i, int[] cuts, int N, int maxFragmentCount, int minFragmentLength)
    {
                          if(N/k[i] < minFragmentLength)
              return false;

                        int f = binarySearch(cuts, 0, k[i], a1);

                   if(f < 0)
                   {
                     if(k[i] ==  maxFragmentCount)
                     {
                        return false;
                     }

                           f = -f-1;

                        if(f == k[i])
                       {
                         if((a1 - cuts[f-1])  < minFragmentLength ||  N - a1 + cuts[0] < minFragmentLength)
                          {
                              return false;
                          }
                       }

                       else  if (f > 0 && f < k[i])
                       {
                          if((a1 - cuts[f-1]) < minFragmentLength || (cuts[f] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                       }

                         else
                      {
                           if(a1 + N - cuts[k[i]-1] < minFragmentLength || (cuts[0] - a1) < minFragmentLength)
                          {
                              return false;
                          }
                      }

                     add(cuts, a1, f, k[i]);
                     k[i]++;
                   }
                        return true;
      }

     //----------------------------------------
     private int modulo(int value, int N) {
        while(value >= N) value -= N;
        while(value < 0) value += N;

        return value;
    }
    // -----------------------------------------
      private boolean isOutside(int N, int pos) {
        if (pos < 0 || pos >= N)
            return true;
        return false;
    }
     // -----------------------------------------
     private boolean validCuts(int N, int nc, int A1, int B1, int A2, int B2, int stPos, int multi) {
        if (isOutside(N, stPos + multi * (A1 - 1)) ||
            isOutside(N, stPos + multi * A1) ||
            isOutside(N, stPos + multi * (B1 - 1)) ||
            isOutside(N, stPos + multi * B1))
            return false;

        if (nc == 2)
            return true;

        if (isOutside(N, stPos + multi * (A2 - 1)) ||
            isOutside(N, stPos + multi * A2) ||
            isOutside(N, stPos + multi * (B2 - 1)) ||
            isOutside(N, stPos + multi * B2))
            return false;

        return true;
    }
         boolean validForwardCuts(int N, int nc, int A1, int B1, int A2, int B2, int stPos) {
        return validCuts(N, nc, A1, B1, A2, B2, stPos, 1);
    }
    // -----------------------------------------
    private char complementary(char c) {
        if (c == 'A') return 'T';
        if (c == 'T') return 'A';
        if (c == 'C') return 'G';
        return 'C';
    }
    // -----------------------------------------
    private boolean validBackwardCuts(int N, int nc, int A1, int B1, int A2, int B2, int stPos) {
        //note that for backward cuts startPos is at the end of the match
        return validCuts(N, nc, A1, B1, A2, B2, stPos, -1);
    }
    // -----------------------------------------
    private void add(int[] src, int element, int index, int len)
    {
        int h = src[index];
        src[index] = element;
        for(int i = index+1; i <= len; i++)
        {
            int t = src[i];
            src[i] = h;
            h = t;
        }
    }
    // -----------------------------------------
    //checks that the cuts of an eznyme are with the ranges (if they are present)(for a circular dna)
   private boolean checkCircularRanges(int[] cuts, int k, int[] rpl, int[] rpr)
  {
          if (rpl != null) {
                                  for (int j = 0; j < rpl.length; j++) {
                                      int st = binarySearch(cuts, 0, k, rpl[j]);

                                      int ed = binarySearch(cuts, 0, k, rpr[j]);

                                      if (st < 0) {

                                                if (ed < 0) {
                                              ed = -ed - 1;
                                              st = -st - 1;

                                              if (st == ed) {
                                                 if(rpr[j] >= rpl[j])
                                                 return false;
                                              }

                                              if(st == k && ed == 0)
                                              {
                                                  return false;
                                              }
                                          }
                                      }
                                  }
                              }
      return true;
  }
    // -----------------------------------------
    //checks that the cuts of an eznyme are with the ranges (if they are present)(for a linear dna)
      private boolean checkRanges(int[] cuts, int k, int[] rpl, int[] rpr)
  {
          if (rpl != null) {
                                  for (int j = 0; j < rpl.length; j++) {
                                      int st = binarySearch(cuts, 0, k, rpl[j]);

                                      int ed = binarySearch(cuts, 0, k, rpr[j]);

                                      if (st < 0) {

                                                if (ed < 0) {
                                              ed = -ed - 1;
                                              st = -st - 1;

                                              if (st == ed) {
                                                 return false;
                                              }
                                          }
                                      }
                                  }
                              }
      return true;
  }
    // -----------------------------------------
    public String[] findEnzymeCocktail(String dna, String[] enzymes, String[] restrictions)
    {
        final boolean[][] wcs = new boolean[91][91];
        // wc map
        wcs['A']['A'] = true;
        wcs['C']['C'] = true;
        wcs['G']['G'] = true;
        wcs['T']['T'] = true;
        wcs['M']['A'] = true;
        wcs['M']['C'] = true;
        wcs['R']['A'] = true;
        wcs['R']['G'] = true;
        wcs['W']['A'] = true;
        wcs['W']['T'] = true;
        wcs['S']['C'] = true;
        wcs['S']['G'] = true;
        wcs['Y']['C'] = true;
        wcs['Y']['T'] = true;
        wcs['K']['G'] = true;
        wcs['K']['T'] = true;
        wcs['V']['A'] = true;
        wcs['V']['C'] = true;
        wcs['V']['G'] = true;
        wcs['H']['A'] = true;
        wcs['H']['C'] = true;
        wcs['H']['T'] = true;
        wcs['D']['A'] = true;
        wcs['D']['G'] = true;
        wcs['D']['T'] = true;
        wcs['B']['C'] = true;
        wcs['B']['G'] = true;
        wcs['B']['T'] = true;
        wcs['X']['G'] = true;
        wcs['X']['A'] = true;
        wcs['X']['T'] = true;
        wcs['X']['C'] = true;
        wcs['N']['G'] = true;
        wcs['N']['A'] = true;
        wcs['N']['T'] = true;
        wcs['N']['C'] = true;

        boolean circular;
        int N = dna.length()-1;
        if(dna.charAt(N) == '.')
        {
        circular = false;
        }
        else
        {
         circular = true;
        }
        int M = enzymes.length; int es = 0;
        int A1, A2 = 0, B1, B2 = 0; //enzyme cuts;
        boolean ok; // used in checking if enzyme is contained a sub string inside the dna
        int minFC = Integer.parseInt(restrictions[0]); // min fragment count
        int maxFC = Integer.parseInt(restrictions[1]); // max fragment count
        int[][] cuts = new int[M][maxFC];
        int minFL = Integer.parseInt(restrictions[2]); // min fragment
        int maxFL = Integer.parseInt(restrictions[3]);
        float minFD = Float.parseFloat(restrictions[4]); // min fragment difference 100 * (Li - Li+1) ≥ minFD * Li, for each i, 0 ≤ i ≤ K-2
        int minRSLength = Integer.parseInt(restrictions[5]);
        int mE = Integer.parseInt(restrictions[6]); // max enzymes
        String[] rps =  restrictions[7].split(",");
        int rpl[] = null, rpr[] = null; // for storing the ranges were a cut should be made

        if(!rps[0].equals("none"))
        {
           if(rps.length == 3)
           {
            rpl = new int[3]; rpr= new int[3];
            int i = rps[0].indexOf(' ');

            rpl[0] = Integer.parseInt(rps[0].substring(0, i));
            rpr[0] = Integer.parseInt(rps[0].substring(i+1));

           i = rps[1].indexOf(' ');
           rpl[1] = Integer.parseInt(rps[1].substring(0, i));
           rpr[1] = Integer.parseInt(rps[1].substring(i+1));

           i = rps[2].indexOf(' ');
           rpl[2] = Integer.parseInt(rps[2].substring(0, i));
           rpr[2] = Integer.parseInt(rps[2].substring(i+1));
           }
           else if(rps.length == 2)
           {
             rpl = new int[2]; rpr= new int[2];
             int i = rps[0].indexOf(' ');

           rpl[0] = Integer.parseInt(rps[0].substring(0, i));
           rpr[0] = Integer.parseInt(rps[0].substring(i+1));

           i = rps[1].indexOf(' ');
           rpl[1] = Integer.parseInt(rps[1].substring(0, i));
           rpr[1] = Integer.parseInt(rps[1].substring(i+1));
           }
           else
           {
               rpl = new int[1]; rpr= new int[1];
               int i = rps[0].indexOf(' ');

             rpl[0] = Integer.parseInt(rps[0].substring(0, i));
             rpr[0] = Integer.parseInt(rps[0].substring(i+1));
           }
        }
             int[] ve = new int[M]; // stores indexes of the enzymes the have passed specific restrictions (i.e. can be in aset with other enzymes)
              int[] k = new int[M]; // corresponds to the number of cuts found in an enzyme
              boolean[] ae = new boolean[M]; //boolean for each enzyme indicating if the enxyme cuts are with ranges (if they exist) or not
              int[] L = new int[maxFC]; // contians the number of fragments optianed by an enzyme or an enzyme set
              String result[] = new String[100]; // for storing and returning results
              int[] r = new int[1]; // stores the number of elements int result
              int[][][] Set =  new int[mE-1][100000][mE-1]; int[][][] setCuts = new int[mE-1][100000][maxFC];
              int[] setsi = new int[mE-1]; boolean[][][][] Irr = new boolean[mE-1][100000][mE-1][maxFC];
              int[][] nc = new int[mE-1][100000]; boolean[][] isr = new boolean[mE-1][100000];

if(circular)
{
       for (int i=0; i<M; i++)
            {
            String[] s = enzymes[i].split(" ");
            String rs = s[1];
            int m = rs.length();
            if(m < minRSLength)
            {
                continue;
            }
            A1 = Integer.parseInt(s[2]);
            B1 = Integer.parseInt(s[3]);
            int nCuts; // number of cuts of the enzyme
            if (s.length == 4)
            {
                nCuts = 2;

                          // forward matches
        for(int st = 0; st < N; st++)
        {
            ok = true; int j;
            if(st > N-m)
            {
                 for (j = 0; j < m; j++,st++)
            {
                 if(st >= N)
               {
                  st = st - N;

                if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    st = st + N;
                    ok = false;
                    break;
                }
                  st = st + N;
                }
                 else
                     if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    ok = false;
                    break;
                }
                }

            }
            else
            {
            for (j = 0; j < m; j++,st++)
            {
                     if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            }
            st = st - j;
            if(ok)
            {
                int a1 = modulo(st + A1, N);

                if(!addCircularCut(a1, k, es, cuts[es], N, maxFC, minFL))
                {
                   ae[es] =  true;
                   break;
                }
           }
        }
            if(ae[es])
            {
                ae[es] = false;
                for (int j=0; j<k[es]; j++)
                {
                    cuts[es][j] = 0;
                }
                    k[es] = 0;
                continue;
             }
             String dnaC = "";

             for(int c = 0; c < N; c++)
             {
                dnaC = dnaC + complementary(dna.charAt(c));
             }

                 // backward matches
        for (int st = 0; st < N; st++)
        {
             ok = true; int j;

             if(st < m-1)
             {
                      for (j=0; j < m; j++,st--)
            {
                   if(st < 0)
               {
                  st = st + N;

                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    st = st - N;
                    ok = false;
                    break;
                }
                  st = st - N;
                }
                 else
                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    ok = false;
                    break;
                }
            }

             }
             else
             {

            for (j=0; j < m; j++,st--)
            {
                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            }
            st = st + j;
            if (ok)
            {
                 int a1 = modulo(st - B1+1, N);

                    if(!addCircularCut(a1, k, es, cuts[es], N, maxFC, minFL))
                    {
                        ae[es] =  true;
                        break;
                    }
           }
        }
            }
            else {
                nCuts = 4;
                A2 = Integer.parseInt(s[4]);
                B2 = Integer.parseInt(s[5]);

                  // forward matches
        for(int st = 0; st < N; st++)
        {
           ok = true; int j;
            if(st > N-m)
            {
                 for (j = 0; j < m; j++,st++)
            {
                 if(st >= N)
               {
                  st = st - N;

                if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    st = st + N;
                    ok = false;
                    break;
                }
                  st = st + N;
                }
                 else
                     if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    ok = false;
                    break;
                }
                }

            }
            else
            {
            for (j = 0; j < m; j++,st++)
            {
                     if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            }
            st = st - j;
            if(ok)
            {
                int a1 = modulo(st + A1, N);

                if(!addCircularCut(a1, k, es, cuts[es], N, maxFC, minFL))
                {
                   ae[es] =  true;
                   break;
                }

                    a1 = modulo(st + A2, N);

                    if(!addCircularCut(a1, k, es, cuts[es], N, maxFC, minFL))
                    {
                        ae[es] =  true;
                        break;
                    }
           }
        }
             if(ae[es])
             {
                ae[es] = false;
                for (int j=0; j<k[es]; j++)
                {
                    cuts[es][j] = 0;
                }
                    k[es] = 0;
              continue;
             }
             String dnaC = "";

             for(int c = 0; c < N; c++)
             {
                dnaC = dnaC + complementary(dna.charAt(c));
             }

                 // backward matches

        for (int st = 0; st < N; st++)
        {
             ok = true; int j;

             if(st < m-1)
             {
                      for (j=0; j < m; j++,st--)
            {
                   if(st < 0)
               {
                  st = st + N;

                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    st = st - N;
                    ok = false;
                    break;
                }
                  st = st - N;
                }
                 else
                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    ok = false;
                    break;
                }
            }

             }
             else
             {

            for (j=0; j < m; j++,st--)
            {
                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            }
             st = st + j;
            if (ok)
            {
                 int a1 = modulo(st - B1+1, N);

                    if(!addCircularCut(a1, k, es, cuts[es], N, maxFC, minFL))
                    {
                        ae[es] =  true;
                        break;
                    }

                   a1 = modulo(st - B2+1, N);

                   if(!addCircularCut(a1, k, es, cuts[es], N, maxFC, minFL))
                   {
                       ae[es] = true;
                       break;
                   }
           }
        }
            }

             if (!ae[es] && k[es] != 0) {

                          ve[es] = i; int e = k[es];
        
                  findCircularEnzymeSets(cuts,setCuts, nc, setsi, ve, Irr, cuts[es], es, k, N, isr, Set, minFC, L,
                                  maxFC, minFL, maxFL, minFD, mE, rpl, rpr, result, r);

                  if(r[0] == 100)
                  return result;

                          if (e >= minFC && N/(float)e <= maxFL) {

                              ae[es] = checkCircularRanges(cuts[es], e, rpl, rpr);

                              if (ae[es] == true) {
                                  m = 0;
                                  e--;
                                  for (int l = 0; l < e; l++) {
                                      L[l] = cuts[es][l+1] - cuts[es][l];
                                      if (L[l] > m) {
                                          m = L[l];
                                      }
                                  }

                                  L[e] = N - cuts[es][e] + cuts[es][0];

                                  if (L[e] > m) {
                                      m = L[e];
                                  }
                                  if (m <= maxFL)
                                  {
                                      sort1(L, 0, e+1);

                                      for(int j = e; j > 0; j--)
                                     {
                                         if(100 * (L[j] - L[j-1]) < minFD * L[j])
                                         {
                                            ae[es] = false;
                                            break;
                                         }
                                     }
                                       if(ae[es] == true)
                                       {
                                         result[r[0]] = ""+i;
                                         r[0]++;
                                         System.out.println("Set "+ r[0] + ": " +i);
                                         if(r[0] == 100)
                                         return result;
                                       }
                                        else
                                       {
                                          ae[es] = true;
                                       }
                                  }
                              }
                          }
                           boolean[][] irr = new boolean[es+1][];

                   for(int l = 0; l< es; l++)
           {
              irr[l]  = new boolean[k[l]];
           }
                  irr[es]  = new boolean[k[es]];
                  
                          int a = 0; int[] b = {es};
                               Set[a][setsi[a]] = b;
                               setCuts[a][setsi[a]] = cuts[es];
                               nc[a][setsi[a]] = k[es];
                               Irr[a][setsi[a]] = irr;
                               isr[a][setsi[a]] = ae[es];
                               setsi[a]++;
                    es++;
                }
                     else
                     {
                        ae[es] = false;
                        for (int j=0; j<k[es]; j++)
                        {
                             cuts[es][j] = 0;
                        }
                             k[es] = 0;
                     }
              }
}
 else
{
              for (int i=0; i<M; i++)
              {
            String[] s = enzymes[i].split(" ");
            String rs = s[1];
            int m = rs.length();
            if(m < minRSLength)
            {
                continue;
            }
            A1 = Integer.parseInt(s[2]);
            B1 = Integer.parseInt(s[3]);
            int nCuts; // number of cuts of the enzyme
            if (s.length == 4)
            {
                nCuts = 2;

                           // forward matches
        for(int st = 0; st <= N-m; st++)
        {
            ok = true;
            int j;
            for (j = 0; j < m; j++,st++)
            {
                if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            st = st - j;
            if(ok)
            {

            if (validForwardCuts(N, nCuts, A1, B1, A2, B2, st))
            {
                int a1 = st + A1;

                if(!addCut(a1, k, es, cuts[es], N, maxFC, minFL))
                {
                   ae[es] =  true;
                   break;
                }
             }
           }
        }
             if(ae[es])
             {
                ae[es] = false;
                for (int j=0; j<k[es]; j++)
                {
                    cuts[es][j] = 0;
                }
                    k[es] = 0;
              continue;
             }
             String dnaC = "";

             for(int c = 0; c < N; c++)
             {
                dnaC = dnaC + complementary(dna.charAt(c));
             }

                 // backward matches

        for (int st = m-1; st < N; st++)
        {
            ok = true;
            int j;
            for (j=0; j < m; j++,st--)
            {
                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            st = st + j;
            if (ok)
            {

            if (validBackwardCuts(N, nCuts, A1, B1, A2, B2, st))
            {
                 int a1 = st - B1 + 1;

                    if(!addCut(a1, k, es, cuts[es], N, maxFC, minFL))
                    {
                        ae[es] =  true;
                        break;
                    }
             }
           }
        }
            }
            else {
                nCuts = 4;
                A2 = Integer.parseInt(s[4]);
                B2 = Integer.parseInt(s[5]);

                    // forward matches
        for(int st = 0; st <= N-m; st++)
        {
            ok = true;
            int j;
            for (j = 0; j < m; j++,st++)
            {
                if (!wcs[rs.charAt(j)][dna.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            st = st - j;
            if(ok)
            {

            if (validForwardCuts(N, nCuts, A1, B1, A2, B2, st))
            {
                int a1 = st + A1;

                if(!addCut(a1, k, es, cuts[es], N, maxFC, minFL))
                {
                   ae[es] =  true;
                   break;
                }

                    a1 = st + A2;

                    if(!addCut(a1, k, es, cuts[es], N, maxFC, minFL))
                    {
                        ae[es] =  true;
                        break;
                    }
             }
           }
        }
             if(ae[es])
             {
                ae[es] = false;
                for (int j=0; j<k[es]; j++)
                {
                    cuts[es][j] = 0;
                }
                    k[es] = 0;

              continue;
             }
             String dnaC = "";

             for(int c = 0; c < N; c++)
             {
                dnaC = dnaC + complementary(dna.charAt(c));
             }

                 // backward matches

        for (int st = m-1; st < N; st++)
        {
            ok = true;
            int j;
            for (j=0; j < m; j++,st--)
            {
                if (!wcs[rs.charAt(j)][dnaC.charAt(st)]) {
                    ok = false;
                    break;
                }
            }
            st = st + j;
            if (ok)
            {

            if (validBackwardCuts(N, nCuts, A1, B1, A2, B2, st))
            {
                 int a1 = st - B1 + 1;

                    if(!addCut(a1, k, es, cuts[es], N, maxFC, minFL))
                    {
                        ae[es] =  true;
                        break;
                    }

                   a1 = st - B2 + 1;

                   if(!addCut(a1, k, es, cuts[es], N, maxFC, minFL))
                   {
                       ae[es] = true;
                       break;
                   }
             }
           }
        }
            }
                      if (!ae[es] && k[es] != 0) {

                          ve[es] = i; int e = k[es]+1;

                             findEnzymeSets(cuts,setCuts, nc, setsi, ve, Irr, cuts[es], es, k, N, isr, Set, minFC, L,
                                  maxFC, minFL, maxFL, minFD, mE, rpl, rpr, result, r);

                       if(r[0] == 100)
                       return result;

                          if (e >= minFC && N/(float)e <= maxFL) {
                              e--;
                              ae[es] = checkRanges(cuts[es], e, rpl, rpr);

                              if (ae[es] == true) {

                                  m = cuts[es][0];
                                  L[0] = m;

                                  for (int l = 1; l < e; l++) {
                                      L[l] = cuts[es][l] - cuts[es][l-1];
                                      if (L[l] > m) {
                                          m = L[l];
                                      }
                                  }

                                  L[e] = N - cuts[es][e-1];

                                  if (L[e] > m) {
                                      m = L[e];
                                  }
                                  if (m <= maxFL)
                                  {
                                      sort1(L, 0, e+1);

                                      for(int j = e; j > 0; j--)
                                     {
                                         if(100 * (L[j] - L[j-1]) < minFD * L[j])
                                         {
                                            ae[es] = false;
                                            break;
                                         }
                                     }
                                       if(ae[es] == true)
                                       {
                                         result[r[0]] = ""+i;
                                         r[0]++;
                                         System.out.println("Set "+ r[0] + ": " +i);
                                         if(r[0] == 100)
                                         return result;
                                       }
                                       else
                                       {
                                          ae[es] = true;
                                       }
                                  }
                              }
                          }

                        boolean[][] irr = new boolean[es+1][];

                   for(int l = 0; l< es; l++)
           {
              irr[l]  = new boolean[k[l]];
           }
               irr[es]  = new boolean[k[es]];

                           int a = 0; int[] b = {es};
                               Set[a][setsi[a]] = b;
                               setCuts[a][setsi[a]] = cuts[es];
                               nc[a][setsi[a]] = k[es];
                               Irr[a][setsi[a]] = irr;
                               isr[a][setsi[a]] = ae[es];
                               setsi[a]++;
               
                          es++;
                      }
                     else
                     {
                          ae[es] = false;
                          for (int j=0; j<k[es]; j++)
                          {
                               cuts[es][j] = 0;
                          }
                               k[es] = 0;
                     }
              }
        }
                      String[] cr = new String[r[0]];
                      System.arraycopy(result, 0, cr, 0, r[0]);

                return cr;
    }

    private boolean enzymeContianedSameCuts(boolean[] ez)
    {
        for(boolean b : ez)
        {
          if(b == false)
          return false;
        }
        return true;
    }

    private String toStringSet(int[] set, int ve[])
    {
        String s = "";

        for(int i : set)
        {
           s = s + ve[i] + " ";
        }
        s = s.substring(0, s.length()-1);
        
        return s;
    }


    private void findEnzymeSets(int[][] cuts, int[][][] setCuts, int[][] nc, int[] Setsi, int ve[], boolean[][][][] irr, int eCS[], int e, int[] k, int N, boolean[][] isr,
            int[][][] eSet, int minFC, int[] L, int maxFC, int minFL, int maxFL, float minFD, int mE, int rpl[], int rpr[], String[] result, int[] r)
    {
        for(int o = 0; o < mE-1; o++)
        {
           

        for(int n = 0; n < Setsi[o]; n++)
       {
              if(k[e]+nc[o][n] > maxFC)
            continue;

            boolean[] irrC1 = new boolean[k[e]];

              int irl = irr[o][n].length;
           boolean[][] irrC = new boolean[irl+1][];

           for(int l = 0; l< irl; l++)
           {
              irrC[l]  = new boolean[irr[o][n][l].length];

              System.arraycopy(irr[o][n][l], 0, irrC[l], 0, irr[o][n][l].length);
           }
           irrC[irl] = irrC1;

           boolean sc = false;

          for(int m = 0; m < eSet[o][n].length; m++)
          {
             for(int l = 0; l < k[e]; l++)
             {
               int t = binarySearch(cuts[eSet[o][n][m]], 0, k[eSet[o][n][m]], eCS[l]);

               if(t >= 0)
               {
                 irrC1[l] = true;
                 irrC[m][t] = true;
                 if(enzymeContianedSameCuts(irrC[m]) || enzymeContianedSameCuts(irrC1))
                 {
                     sc = true;
                     break;
                 }
               }
             }
           if(sc == true)
             break;
          }
             if(sc)
             continue;
           int eCS1[] = new int[setCuts[o][n].length];
           System.arraycopy(setCuts[o][n], 0, eCS1, 0, nc[o][n]);
             boolean v = true; int[] ePS1 = new int[1]; ePS1[0] = nc[o][n];

             for(int l = 0; l < k[e]; l++)
             {
                 if(!addCut1(eCS[l], ePS1, 0, eCS1, N, maxFC, minFL))
                 {
                     v = false;
                     break;
                 }
             }
                  if (v) {
                           int ne1 = o+1;
                                int[] eSet1 = new int[ne1+1];

                          System.arraycopy(eSet[o][n], 0, eSet1, 0, ne1);
                         eSet1[ne1] = e;

                         int e1 = ePS1[0]+1;
                          boolean ae  = isr[o][n];
                          if (e1 >= minFC && N/(float)e1 <= maxFL) {
                              e1--;
                              if(! ae)
                              {
                              ae  = checkRanges(eCS1, e1, rpl, rpr);
                             }
                              if (ae == true) {

                                  int m = eCS1[0];
                                  L[0] = m;

                                  for (int l = 1; l < e1; l++) {
                                      L[l] = eCS1[l] - eCS1[l-1];
                                      if (L[l] > m) {
                                          m = L[l];
                                      }
                                  }

                                  L[e1] = N - eCS1[e1-1];

                                  if (L[e1] > m) {
                                      m = L[e1];
                                  }
                                  if (m <= maxFL)
                                  {
                                      sort1(L, 0, e1+1);

                                     for(int K = e1; K > 0; K--)
                                     {
                                         if(100 * (L[K] - L[K-1]) < minFD * L[K])
                                         {
                                            ae = false;
                                            break;
                                         }
                                     }
                                       if(ae == true)
                                       {
                                         String ES1 =  toStringSet(eSet1, ve);
                                         result[r[0]] = ES1;
                                         r[0]++;
                                         System.out.println("Set "+ r[0] + ": " +ES1);
                                         if(r[0] == 100)
                                         return;
                                       }
                                         else
                                       {
                                          ae = true;
                                       }
                                  }
                              }
                          }
                         
                           if(o != mE-2)
                           {
                               int a = o+1;
                               eSet[a][Setsi[a]] = eSet1;
                               setCuts[a][Setsi[a]] = eCS1;
                               nc[a][Setsi[a]] = ePS1[0];
                               irr[a][Setsi[a]] = irrC;
                               isr[a][Setsi[a]] = ae;
                               Setsi[a]++;
                           }
                      }
       }
    }
    }

   private void findCircularEnzymeSets(int[][] cuts, int[][][] setCuts, int[][] nc, int[] Setsi, int ve[], boolean[][][][] irr, int eCS[], int e, int[] k, int N, boolean[][] isr,
            int[][][] eSet, int minFC, int[] L, int maxFC, int minFL, int maxFL, float minFD, int mE, int rpl[], int rpr[], String[] result, int[] r)
    {
         for(int o = 0; o < mE-1; o++)
        {

        for(int n = 0; n < Setsi[o]; n++)
       {
            if(k[e]+nc[o][n] > maxFC)
            continue;

            boolean[] irrC1 = new boolean[k[e]];

              int irl = irr[o][n].length;
           boolean[][] irrC = new boolean[irl+1][];

           for(int l = 0; l< irl; l++)
           {
              irrC[l]  = new boolean[irr[o][n][l].length];

              System.arraycopy(irr[o][n][l], 0, irrC[l], 0, irr[o][n][l].length);
           }
           irrC[irl] = irrC1;

           boolean sc = false;

          for(int m = 0; m < eSet[o][n].length; m++)
          {
             for(int l = 0; l < k[e]; l++)
             {
               int t = binarySearch(cuts[eSet[o][n][m]], 0, k[eSet[o][n][m]], eCS[l]);

               if(t >= 0)
               {
                 irrC1[l] = true;
                 irrC[m][t] = true;
                 if(enzymeContianedSameCuts(irrC[m]) || enzymeContianedSameCuts(irrC1))
                 {
                     sc = true;
                     break;
                 }
               }
             }
           if(sc == true)
             break;
          }
             if(sc)
             continue;

             int eCS1[] = new int[setCuts[o][n].length];
           System.arraycopy(setCuts[o][n], 0, eCS1, 0, nc[o][n]);
             boolean v = true; int[] ePS1 = new int[1]; ePS1[0] = nc[o][n];

             for(int l = 0; l < k[e]; l++)
             {
                 if(!addCircularCut1(eCS[l], ePS1, 0, eCS1, N, maxFC, minFL))
                 {
                     v = false;
                     break;
                 }
             }
                  if (v) {

                       int ne1 = o+1;
                                int[] eSet1 = new int[ne1+1];

                          System.arraycopy(eSet[o][n], 0, eSet1, 0, ne1);
                         eSet1[ne1] = e;

                          int e1 = ePS1[0];
                          boolean ae  = isr[o][n];
                          if (e1 >= minFC && N/(float)e1 <= maxFL) {
                              if(! ae)
                              {
                              ae  = checkCircularRanges(eCS1, e1, rpl, rpr);
                             }
                              if (ae == true) {
                                  e1--;
                                  int m = 0;

                                  for (int l = 0; l < e1; l++) {
                                      L[l] = eCS1[l + 1] - eCS1[l];
                                      if (L[l] > m) {
                                          m = L[l];
                                      }
                                  }

                                  L[e1] = N - eCS1[e1] + eCS1[0];

                                  if (L[e1] > m) {
                                      m = L[e1];
                                  }

                                  if (m <= maxFL)
                                  {
                                      sort1(L, 0, e1+1);

                                     for(int K = e1; K > 0; K--)
                                     {
                                         if(100 * (L[K] - L[K-1]) < minFD * L[K])
                                         {
                                            ae = false;
                                            break;
                                         }
                                     }
                                       if(ae == true)
                                       {
                                         String ES1 =  toStringSet(eSet1, ve);
                                         result[r[0]] = ES1;
                                         r[0]++;
                                         System.out.println("Set "+ r[0] + ": " +ES1);
                                         if(r[0] == 100)
                                         return;
                                       }
                                         else
                                       {
                                          ae = true;
                                       }
                                  }
                              }
                          }
                            if(o != mE-2)
                           {
                               int a = o+1;
                               eSet[a][Setsi[a]] = eSet1;
                               setCuts[a][Setsi[a]] = eCS1;
                               nc[a][Setsi[a]] = ePS1[0];
                               irr[a][Setsi[a]] = irrC;
                               isr[a][Setsi[a]] = ae;
                               Setsi[a]++;
                           }
                      }
       }

        }
    }

    public static void main(String[] args) throws IOException
    {
     
         System.out.println(new RestrictionDigestVis().getResult("9"));
        
    }
}
