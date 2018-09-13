/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TopCoder;

/**
 *
 * @author Aljarhi
 */
import java.io.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

class MarkovChain {
    final static int[] charId = new int[256];

    static {
        charId['A'] = 0;
        charId['C'] = 1;
        charId['G'] = 2;
        charId['T'] = 3;
    }

    static int stringId(String s) {
        int res = 0;

        if (s.length() == 1)
            return res + charId[s.charAt(0)];
        else
            res += 4;

        if (s.length() == 2)
            return res + 4 * charId[s.charAt(0)] + charId[s.charAt(1)];
        else
            res += 16;

        if (s.length() == 3)
            return res + 16 * charId[s.charAt(0)] + 4 * charId[s.charAt(1)] + charId[s.charAt(2)];
        else
            res += 64;

        return res + 64 * charId[s.charAt(0)] + 16 * charId[s.charAt(1)] + 4 * charId[s.charAt(2)] + charId[s.charAt(3)];
    }

    public char genNext(String prev, Random rnd) {
        int A = prob[stringId(prev + "A")];
        int C = prob[stringId(prev + "C")];
        int G = prob[stringId(prev + "G")];
        int T = prob[stringId(prev + "T")];

        int value = rnd.nextInt(A + C + G + T);
        if (value < A)
            return 'A';
        if (value < A + C)
            return 'C';
        if (value < A + C + G)
            return 'G';
        return 'T';
    }

    final static int TOT = 4 + 16 + 64 + 256;
    int[] prob = new int[TOT];

    public MarkovChain(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        for (int i=0; i < TOT; i++)
            prob[i] = Integer.parseInt(br.readLine());
        br.close();
    }
}

public class RestrictionDigestVis {

       public RestrictionDigestVis(){}
    
        public  double getResult(String seed) throws IOException
    {
        return runTest(seed);
    }

    //generation params
    final static int MIN_DNA_LEN = 5000;
    final static int MAX_DNA_LEN = 100000;
    final static int LOW_FRAGMENT_CNT = 3;
    final static int HIGH_FRAGMENT_CNT = 15;
    //parameters to be passed
    String dna;                             //without "circular" char
    String[] enzymesPar, restrictionsPar;
    //internal storage of parameters
    int N;                                  //number of nucleotides
    boolean circular;

    String[] codes, rs;

    int[] nCuts, A1, B1, A2, B2;

    //restrictions
    int minFragmentCount, maxFragmentCount, minFragmentLength, maxFragmentLength, minRSLength, maxEnzymes, minFragmentDiff;

    int[] rangeLeft, rangeRight;
    List<Set<Integer> > enzymeCuts = new ArrayList<Set<Integer> >();
    // -----------------------------------------
    //generation routines
    private long ceiling(long A, long B) {
        return (A + B - 1) / B;
    }
    // -----------------------------------------
    private int genMaxFragmentLength(SecureRandom rnd, int from, int to) {
        double x = rnd.nextDouble();
        while (x == 0 || (int)Math.floor(from / x) > to)
            x = rnd.nextDouble();

        return (int)Math.floor(from / x);
    }
    // -----------------------------------------
    void generate(long seed) {
      try {
        int i,j;
        String[] s;
        //generate test case
        SecureRandom r1 = SecureRandom.getInstance("SHA1PRNG");
        r1.setSeed(seed);

        //read enzymes and parse them into more useable form - fixed part of generation
        BufferedReader br = new BufferedReader(new FileReader("G:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/build/classes/TopCoder/enzymes.txt"));
        int NE = Integer.parseInt(br.readLine());
        enzymesPar = new String[NE];
        codes = new String[NE];
        rs = new String[NE];
        nCuts = new int[NE];
        A1 = new int[NE];
        B1 = new int[NE];
        A2 = new int[NE];
        B2 = new int[NE];
        for (i=0; i<NE; i++) {
            enzymesPar[i] = br.readLine();
            s = enzymesPar[i].split(" ");
            //codes - recognition - A1, B1, (A2, B2)
            //people will return enzyme index in the array, so we don't even need to store codes
            codes[i] = s[0];
            rs[i] = s[1];
            A1[i] = Integer.parseInt(s[2]);
            B1[i] = Integer.parseInt(s[3]);
            if (s.length == 4)
                nCuts[i] = 2;
            else {
                nCuts[i] = 4;
                A2[i] = Integer.parseInt(s[4]);
                B2[i] = Integer.parseInt(s[5]);
            }
        }
        br.close();

        //-------------------------------------------------------------------------------------------
        //generate DNA as a Markov chain
        int modelInd = -1;
        if (r1.nextInt(3) == 0)
            modelInd = 10;
        else
            modelInd = r1.nextInt(10);
        MarkovChain model = new MarkovChain("G:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/build/classes/TopCoder/models/distr" + (modelInd+1) + ".dat");
        if (debug)
            System.out.println("Model = " + modelInd);

        N = r1.nextInt(MAX_DNA_LEN - MIN_DNA_LEN + 1) + MIN_DNA_LEN;        //dna length
        if (debug)
            System.out.println("DNA length = "+N);
        StringBuilder dnaSB = new StringBuilder();
        String prev = "";
        for (i = 0; i < N; i++) {
            char next = model.genNext(prev, r1);
            dnaSB.append(next);
            prev += next;
            if (prev.length() == 4)
                prev = prev.substring(1);
        }

        //and convert it into String
        dna = dnaSB.toString();
        circular = r1.nextBoolean();
        if (debug) {
//            System.out.println(dna);
            System.out.println((circular?"Circular":"Linear"));
        }

        //-------------------------------------------------------------------------------------------
        //generate restrictions
        minFragmentCount = -1;
        maxFragmentCount = -1;
        while (true) {
            minFragmentCount = r1.nextInt(HIGH_FRAGMENT_CNT - LOW_FRAGMENT_CNT + 1) + LOW_FRAGMENT_CNT;
            maxFragmentCount = r1.nextInt(HIGH_FRAGMENT_CNT - LOW_FRAGMENT_CNT + 1) + LOW_FRAGMENT_CNT;
            if (maxFragmentCount >= minFragmentCount)
                break;
        }

        minFragmentLength = -1;
        maxFragmentLength = -1;
        minFragmentDiff = -1;

        int lowBnd = N / minFragmentCount;
        int highBnd = (int) ceiling(N, maxFragmentCount);

        while (true) {
            minFragmentLength = r1.nextInt(lowBnd) + 1;
            maxFragmentLength = genMaxFragmentLength(r1, highBnd, N);

            if (minFragmentLength > maxFragmentLength)
                continue;

            minFragmentDiff = r1.nextInt(10001);

            while (minFragmentDiff == 10000)
                minFragmentDiff = r1.nextInt(10001);

            long sum = 0, cur = minFragmentLength;
            boolean ok = true;
            for (i=0; i < minFragmentCount; i++) {
                if (cur > maxFragmentLength) {
                    ok = false;
                    break;
                }
                sum += cur;
                cur = ceiling(10000 * cur, 10000 - minFragmentDiff);
            }
            if (!ok || sum > N) {
                continue;
            }

            sum = 0;
            cur = maxFragmentLength;
            ok = true;
            for (i=0; i < maxFragmentCount; i++) {
                if (cur < minFragmentLength) {
                    if (i < minFragmentCount) {
                        ok = false;
                    }
                    break;
                }
                sum += cur;
                cur = cur * (10000 - minFragmentDiff) / 10000;
            }
            if (!ok || sum < N) {
                continue;
            }

            break;
        }

        //minimal length of recognition sequence - between min and half of (min+max) length of RS
        int minRS=20, maxRS=0;
        for (i=0; i<rs.length; i++) {
            minRS = Math.min(minRS, rs[i].length());
            maxRS = Math.max(maxRS, rs[i].length());
        }
        minRSLength = r1.nextInt((maxRS+minRS)/2-minRS+1)+minRS;

        //maximal number of enzymes - 1 per cut is enough - so between maxFragmentCount and half of it
        maxEnzymes = r1.nextInt((maxFragmentCount+1)/2+1)+maxFragmentCount/2;

        //ranges - are present in half of the cases, and if they are, they are at most 3 random pairs
        if (r1.nextBoolean()) {
            rangeLeft = new int[0];
            rangeRight = new int[0];
        } else {
            int nr = r1.nextInt(3)+1;
            rangeLeft = new int[nr];
            rangeRight = new int[nr];
            for (i=0; i<nr; i++)
                if (!circular) {
                    rangeRight[i] = r1.nextInt(N-1);
                    rangeLeft[i] = r1.nextInt(rangeRight[i]+1);
                }
                else {
                    rangeLeft[i] = r1.nextInt(N);
                    rangeRight[i] = r1.nextInt(N);
                }
        }

        //and convert them to String[] parameter
        restrictionsPar = new String[8];
        restrictionsPar[0] = ""+minFragmentCount;
        restrictionsPar[1] = ""+maxFragmentCount;
        restrictionsPar[2] = ""+minFragmentLength;
        restrictionsPar[3] = ""+maxFragmentLength;
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        restrictionsPar[4] = new DecimalFormat("0.000", dfs).format(minFragmentDiff/1000.0);
        restrictionsPar[5] = ""+minRSLength;
        restrictionsPar[6] = ""+maxEnzymes;
        if (rangeLeft.length == 0)
            restrictionsPar[7] = "none";
        else {
            restrictionsPar[7] = rangeLeft[0]+" "+rangeRight[0];
            for (i=1; i<rangeLeft.length; i++)
                restrictionsPar[7] = restrictionsPar[7] + "," + rangeLeft[i]+" "+rangeRight[i];
        }

        if (debug) {
            System.out.println("Restrictions:");
            for (i=0; i<8; i++)
                System.out.println(restrictionsPar[i]);
        }
      }
      catch (Exception e) {
        System.err.println("An exception occurred while generating test case.");
        e.printStackTrace();
      }
    }
    // -----------------------------------------
    final char NO_CHAR_HERE = '#';
    final static boolean[][] wcs = new boolean[91][91];
    void initializeWcMap() {
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
    }
    // -----------------------------------------
    int modulo(int value) {
        while (value >= N) value -= N;
        while (value < 0) value += N;
        return value;
    }
    // -----------------------------------------
    boolean matches(char fromDna, char fromEnz) {
        return wcs[fromEnz][fromDna];
    }
    // -----------------------------------------
    char getCharAtDNA(int pos) {
        if ((pos < 0 || pos >= N) && !circular)
            return NO_CHAR_HERE;
        return dna.charAt(modulo(pos));
    }
    // -----------------------------------------
    boolean validCuts(int enzymeInd, int stPos, int multi) {
        if (getCharAtDNA(stPos + multi * (A1[enzymeInd] - 1)) == NO_CHAR_HERE ||
            getCharAtDNA(stPos + multi * A1[enzymeInd]) == NO_CHAR_HERE ||
            getCharAtDNA(stPos + multi * (B1[enzymeInd] - 1)) == NO_CHAR_HERE ||
            getCharAtDNA(stPos + multi * B1[enzymeInd]) == NO_CHAR_HERE)
            return false;

        if (nCuts[enzymeInd] == 2)
            return true;

        if (getCharAtDNA(stPos + multi * (A2[enzymeInd] - 1)) == NO_CHAR_HERE ||
            getCharAtDNA(stPos + multi * A2[enzymeInd]) == NO_CHAR_HERE ||
            getCharAtDNA(stPos + multi * (B2[enzymeInd] - 1)) == NO_CHAR_HERE ||
            getCharAtDNA(stPos + multi * B2[enzymeInd]) == NO_CHAR_HERE)
            return false;

        return true;
    }
    // -----------------------------------------
    boolean validForwardCuts(int enz, int stPos) {
        return validCuts(enz, stPos, 1);
    }
    // -----------------------------------------
    boolean validBackwardCuts(int enz, int stPos) {
        //note that for backward cuts startPos is at the end of the match
        return validCuts(enz, stPos, -1);
    }
    // -----------------------------------------
    char complementary(char c) {
        if (c == 'A') return 'T';
        if (c == 'T') return 'A';
        if (c == 'C') return 'G';
        if (c == 'G') return 'C';
        return c;
    }
    // -----------------------------------------
    public Set<Integer> getCutSet(int enzymeInd) {
        Set<Integer> cuts = new HashSet<Integer>();
        int M = rs[enzymeInd].length();
        //cut off - if recognition sequence of the enzyme is shorter than minRSLength, it's illegal to use anyways
        if (M < minRSLength)
            return cuts;

        // forward matches
        for (int st = 0; st < N; st++) {
            boolean ok = true;
            for (int i = 0; i < M; i++)
                if (!matches(getCharAtDNA(st + i), rs[enzymeInd].charAt(i))) {
                    ok = false;
                    break;
                }
            if (!ok)
                continue;

            if (validForwardCuts(enzymeInd, st)) {
                cuts.add(modulo(st + A1[enzymeInd] - 1));
                if (nCuts[enzymeInd] == 4)
                    cuts.add(modulo(st + A2[enzymeInd] - 1));
                if (cuts.size() > maxFragmentCount+1)   //using this enzyme will fail the set anyways, since cuts don't disappear
                    return cuts;
            }
        }

        // backward matches
        for (int st = 0; st < N; st++) {
            boolean ok = true;
            for (int i=0; i < M; i++)
                if (!matches(complementary(getCharAtDNA(st - i)), rs[enzymeInd].charAt(i))) {
                    ok = false;
                    break;
                }
            if (!ok)
                continue;

            if (validBackwardCuts(enzymeInd, st)) {
                cuts.add(modulo(st - B1[enzymeInd]));
                if (nCuts[enzymeInd] == 4)
                    cuts.add(modulo(st - B2[enzymeInd]));
                if (cuts.size() > maxFragmentCount+1)
                    return cuts;
            }
        }
        return cuts;
    }
    // -----------------------------------------
    boolean validateSet(int[] enzymes) {
        //given a set of enzymes, check that it satisfies the restrictions which require cutting
        int i;
/*        if (debug) {
            System.out.println("Validating set");
            for (i=0; i<enzymes.length; i++)
                System.out.println(enzymes[i]+" "+enzymeCuts.get(enzymes[i]).size()+" cuts");
        }*/
        //unite cuts of all enzymes in the list into one set
        Map<Integer, Integer> cutsPosNum = new HashMap<Integer, Integer>();
        //stores positions of cuts and number of enzymes which do each position
        for (i=0; i<enzymes.length; i++) {
            //add cuts done by this enzyme to the set of all cuts
            for (int pos : enzymeCuts.get(enzymes[i]))
                if (cutsPosNum.containsKey(pos))
                    cutsPosNum.put(pos, cutsPosNum.get(pos)+1);
                else
                    cutsPosNum.put(pos, 1);
            if (cutsPosNum.size() > maxFragmentCount+1) {   //already more, will not be reduced
                addFatalError("Restriction 1 violation: set of enzymes produces more than "+maxFragmentCount+" fragments.");
                return false;
            }
        }

        //do simple checks on the set
        int numFragments = cutsPosNum.size() + (circular ? 0 : 1);
        if (numFragments > maxFragmentCount) {
            addFatalError("Restriction 1 violation: set of enzymes produces more than "+maxFragmentCount+" fragments.");
            return false;
        }
        if (numFragments < minFragmentCount) {
            addFatalError("Restriction 0 violation: set of enzymes produces less than "+minFragmentCount+" fragments.");
            return false;
        }

        //get the set of lengths and check it
        //convert cuts-and-quantities into cuts-only
        List<Integer> cutPos = new ArrayList<Integer>(cutsPosNum.keySet());
        Collections.sort(cutPos);
        //and extract fragments
        List<Integer> fragments = new ArrayList<Integer>();
        if (!circular)
            fragments.add(cutPos.get(0) + 1);
        for (i=0; i + 1 < cutPos.size(); i++)
            fragments.add(cutPos.get(i + 1) - cutPos.get(i));
        int totalLen = 0;
        for (int len : fragments)
            totalLen += len;
        fragments.add(N - totalLen);
        Collections.sort(fragments);

        //finally, check the lengths
        if (fragments.get(0) < minFragmentLength) {
            addFatalError("Restriction 2 violation: length of the shortest fragment less than "+minFragmentLength+".");
            return false;
        }
        if (fragments.get(fragments.size()-1) > maxFragmentLength) {
            addFatalError("Restriction 3 violation: length of the longest fragment more than "+maxFragmentLength+".");
            return false;
        }
        for (i=0; i<fragments.size()-1; i++) {
            long li = fragments.get(i + 1);
            long lii = fragments.get(i);
            if (100l * 1000l * (li - lii) < (long)minFragmentDiff * li) {
                addFatalError("Restriction 4 violation.");
                return false;
            }
        }

        //check the ranges, if any
        if (rangeLeft.length > 0) {
            boolean found;
            for (i=0; i<rangeLeft.length; i++) {
                found = false;
                for (int pos : cutPos)
                    if (pos>=rangeLeft[i] && pos<=rangeRight[i] || circular && rangeLeft[i]>rangeRight[i] && (pos>=rangeLeft[i] || pos <= rangeRight[i])) {
                        found = true;
                        break;
                    }
                if (!found) {
                    addFatalError("Restriction 7 violation: range "+rangeLeft[i]+"-"+rangeRight[i]+" has no cut.");
                    return false;
                }
            }
        }

        //check that this set can't be reduced: if for each enzyme there is a cut done only by it, it's ok
        boolean need;
        for (i=0; i<enzymes.length; i++) {
            need = false;
            for (int pos : enzymeCuts.get(enzymes[i]))
                if (cutsPosNum.get(pos) == 1) {
                    need = true;
                    break;
                }
            if (!need) {
                addFatalError("The set is reducible: can remove enzyme "+enzymes[i]+".");
                return false;
            }
        }

        return true;
    }
    // -----------------------------------------
    public double runTest(String seed) {
        try {
            generate(Long.parseLong(seed));
            int i;
            int j;
            int k;
            long startTime;
            long endTime;
            //call the solution (and time it)
            startTime = System.currentTimeMillis();
            String[] setsRet = findEnzymeCocktail(dna + (circular ? "+" : "."), enzymesPar, restrictionsPar);
            endTime = System.currentTimeMillis();
            System.out.println("Execution time - " + (endTime - startTime) / 1000.0 + " seconds");
            //validate the return as formal set of sets, without evaluating cuts or enzymes
            //must have non-zero length
            if (setsRet.length == 0) {
                addFatalError("Your return contained no sets.");
                return (endTime - startTime) / 100000.0;
            }
            if (setsRet.length > 100) {
                addFatalError("Your return contained more than 100 sets.");
                return 0;
            }
            int[][] S = new int[setsRet.length][];
            String[] st;
            for (i = 0; i < setsRet.length; i++) {
                if (setsRet[i] == null) {
                    addFatalError("All sets in your return must be non-empty.");
                    return 0;
                }
                st = setsRet[i].split(" ");
                S[i] = new int[st.length];
                for (j = 0; j < st.length; j++) {
                    try {
                        S[i][j] = Integer.parseInt(st[j]);
                        if (!st[j].equals(S[i][j] + "")) {
                            addFatalError("Integers in your return must be written with no leading zeroes.");
                            return 0;
                        }
                        if (S[i][j] < 0 || S[i][j] >= enzymesPar.length) {
                            addFatalError("Invalid enzyme index: " + S[i][j] + ".");
                            return 0;
                        }
                        if (j > 0 && S[i][j] <= S[i][j - 1]) {
                            addFatalError("Enzyme indices in each set must be sorted in ascending order.");
                            return 0;
                        }
                    } catch (Exception e) {
                        addFatalError("Each element of your return must be a list of single-space-separated integers.");
                        return 0;
                    }
                }
                //all sets must be distinct - check as int[]s
                for (k = 0; k < i; k++) {
                    if (Arrays.equals(S[i], S[k])) {
                        addFatalError("All sets in your return must be distinct.");
                        return 0;
                    }
                }
            }
            //validate the sets with respect to minRSLength and maxEnzymes, which don't need cuts evaluation
            for (i = 0; i < S.length; i++) {
                if (S[i].length > maxEnzymes) {
                    addFatalError("Restriction 6 violation: set " + i + " contained more than " + maxEnzymes + " enzymes.");
                    return 0;
                }
                for (j = 0; j < S[i].length; j++) {
                    if (rs[S[i][j]].length() < minRSLength) {
                        addFatalError("Restriction 5 violation: enzyme " + S[i][j] + " has recognition sequence shorter than " + minRSLength + ".");
                        return 0;
                    }
                }
            }
            //-------------------------------------------------------------------------------------------
            //now S contains the sets of enzymes
            //pre-calculate the cuts each enzyme does - common part
            initializeWcMap();
            for (i = 0; i < enzymesPar.length; i++) {
                enzymeCuts.add(getCutSet(i));
            }
            //for each set: get the resulting set of fragments (lengths is enough) and validate it
            for (i = 0; i < S.length; i++) {
                if (!validateSet(S[i])) {
                    addFatalError("Set " + i + " is invalid: " + setsRet[i]);
                    return 0;
                }
            }
            //if the return is still valid, score it
            return setsRet.length + (endTime - startTime) / 100000.0;
        } catch (Exception e) {
        System.err.println("An exception occurred while trying to get your program's results.");
        e.printStackTrace();
        return 0; 
      
    }
 }
// ------------- interaction-with-solution part ------------
    static String exec;
    static Process proc;
    static boolean debug;
    InputStream is;
    OutputStream os;
    BufferedReader br;
    // -----------------------------------------

       private int modulo(int value, int N) {
        while(value >= N) value -= N;
        while(value < 0) value += N;

        return value;
    }

    private String[] findEnzymeCocktail(String dna, String[] enzymes, String[] restrictions) throws IOException {

      return new RestrictionDigest().findEnzymeCocktail(dna, enzymes, restrictions);
  }
    // -----------------------------------------
    public RestrictionDigestVis(String seed) {
      try {
        //interface for runTest
        if (exec != null) {
            try {
                Runtime rt = Runtime.getRuntime();
                proc = rt.exec(exec);
                os = proc.getOutputStream();
                is = proc.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                new ErrorReader(proc.getErrorStream()).start();
                Runtime.getRuntime().addShutdownHook(new Thread() {
                	public void run() {
                		if (proc != null)
                			try { proc.destroy(); }
                			catch (Exception e) { e.printStackTrace(); }
                	}
                });
            } catch (Exception e) { e.printStackTrace(); }
        }
        else {
            System.out.println("No solution specified.");
            return;
        }
        System.out.println("Score = "+runTest(seed));
        if (proc != null)
            try { proc.destroy(); }
            catch (Exception e) { e.printStackTrace(); }
      }
      catch (Exception e) {
    	  e.printStackTrace();
          if (proc != null)
              try { proc.destroy(); }
              catch (Exception e1) { e1.printStackTrace(); }
      }
    }
    // -----------------------------------------
    public static void main(String[] args) {
        String seed = "1";
        debug = false;
        for (int i = 0; i<args.length; i++)
        {   if (args[i].equals("-seed"))
                seed = args[++i];
            if (args[i].equals("-exec"))
                exec = args[++i];
            if (args[i].equals("-debug"))
                debug = true;
        }
        RestrictionDigestVis f = new RestrictionDigestVis(seed);
    }
    // -----------------------------------------
    void addFatalError(String message) {
        System.out.println(message);
    }
}

class ErrorReader extends Thread{
    InputStream error;
    public ErrorReader(InputStream is) {
        error = is;
    }
    public void run() {
        try {
            byte[] ch = new byte[50000];
            int read;
            while ((read = error.read(ch)) > 0)
            {   String s = new String(ch,0,read);
                System.out.print(s);
                System.out.flush();
            }
        } catch(Exception e) { }
    }
}
