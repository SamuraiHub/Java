/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder.EliteClassifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Aljarhi
 */
public class TrainingParameters implements Comparator{

    /**
     * @param args the command line arguments
     */
    
    public  int compare(Object o1, Object o2) {
        
        double n1 = Double.parseDouble((String)o1);
        double n2 = Double.parseDouble((String)o2);
        return Double.compare(n1, n2);
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        TrainingParameters tp = new TrainingParameters();
        
        int dataSize = 474413; // size of the trianing data

        List<String> year = Arrays.asList("2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008");
        List<String> rep = Arrays.asList("0", "1", "2"); List<String> type = Arrays.asList("conv", "RR1", "RR2Y");
        
        // for storing the different distinct attrinutes  
        TreeSet<String> LOCCD = new TreeSet<String>(tp);
        TreeSet<String> Yield = new TreeSet<String>(tp);
        TreeSet<String> MN = new TreeSet<String>(tp);
        AbstractSet<String> RM = new TreeSet<String>(tp);
        
        BufferedReader r = new BufferedReader(new FileReader("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/DataTraining.csv"));
        String l;
        int nm = 0,nr = 0;//for getting the total null values in maturity number and relative msturity respectively
        while((l = r.readLine()) != null)
        {
            String[] field = l.split(","); 
            
            LOCCD.add(field[2]); Yield.add(field[6]); 
            
            if(!"NULL".equals(field[7]))
            { 
               MN.add(field[7]);
            }
            else
            {
                nm++;
            }
            
            if(!"NULL".equals(field[8]))
            {
               RM.add(field[8]);
            }
            else
            {
                nr++;
            }
            
        } r.close(); 
        
        String[] LOC = LOCCD.toArray(new String[LOCCD.size()]); String[] Y = Yield.toArray(new String[Yield.size()]);
            String[] mn = MN.toArray(new String[MN.size()]); String[] rm = RM.toArray(new String[RM.size()]);
        
        int[] yl = new int[9]; int[] rl = new int[3]; int[] tl = new int[3]; int[] ll = new int[LOCCD.size()];
        int[] Yl = new int[Yield.size()]; int[] ml = new int[MN.size()]; int[] Rl = new int[RM.size()];
        Arrays.fill(yl, 0); Arrays.fill(rl, 0); Arrays.fill(tl, 0); Arrays.fill(ll, 0); Arrays.fill(Yl, 0);
        Arrays.fill(ml, 0); Arrays.fill(Rl, 0); 
        
         r = new BufferedReader(new FileReader("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/DataTraining.csv"));
        // adding the occurence of each diffrent attribute
         while((l = r.readLine()) != null)
        {
            String[] field = l.split(",");
            
            yl[year.indexOf(field[1])]++; ll[Arrays.binarySearch(LOC, field[2], tp)]++;
            rl[rep.indexOf(field[3])]++; tl[type.indexOf(field[4])]++; Yl[Arrays.binarySearch(Y, field[6], tp)]++;
            
            if(!"NULL".equals(field[7]))
            {
               ml[Arrays.binarySearch(mn, field[7], tp)]++; 
            }
            if(!"NULL".equals(field[8]))
            {
               Rl[Arrays.binarySearch(rm, field[8], tp)]++;
            }                        
        } r.close();
        
        double am = 0, ar = 0; // average values for maturity and relative maturity
        
       for(int i = 0; i < ml.length; i++)
       {
           am = am + (Double.parseDouble(mn[i])) * ml[i];
       }
       am = am/(dataSize - nm);
       
       for(int i = 0; i < Rl.length; i++)
       {
           ar = ar + (Double.parseDouble(rm[i])) * Rl[i];
       }
       ar = ar/(dataSize - nr); 
           
        int[] nms = new int[ml.length]; int[] nrs = new int[Rl.length]; int j = 0, k = 0;
        Arrays.fill(nms, 0); Arrays.fill(nrs, 0); 
        // transforms null values to existing vaslues in the data
        r = new BufferedReader(new FileReader("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/DataTraining.csv"));
        
        BufferedWriter td = new BufferedWriter(new FileWriter("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/TransformedData.csv"));
        
        while((l = r.readLine()) != null)
        {
            String[] field = l.split(","); 
           
            if("NULL".equals(field[7]))
            {
                int xm = j;
                do
                {
                if(nms[j] < (int)(((double)ml[j]*nm/(dataSize-nm))+0.5))
                {
                    field[7] = mn[j];
                    nms[j]++;
                }
                  j++;
                  
                 if(j == ml.length)
                 {
                    j = 0;
                 }
                  
                  if(!"NULL".equals(field[7]))
                  {
                      break;
                  } 
                  
                } while(j != xm);
                     
                     if("NULL".equals(field[7]))
                     {
                         field[7] = String.valueOf(am);
                     }
            }
            
            if("NULL".equals(field[8]))
            {
                int xr = k;
                do
                {
                if(nrs[k] < (int)(((double)Rl[k]*nr/(dataSize-nr))+0.5))
                {
                    field[8] = rm[k];
                    nrs[k]++;
                }
                  k++;
                  
                     if(k == Rl.length)
                 {
                    k = 0;
                 }
                  
                  if(!"NULL".equals(field[8]))
                  {
                      break;
                  }   
                 
                } while(k != xr);
                  
                     if("NULL".equals(field[8]))
                     {
                         field[8] = String.valueOf(ar);
                     }
            } 
            
            for(int i = 0; i < 10; i++)
            {
                td.write(field[i] + ",");
            }
            td.write(field[10] + "\r\n"); 
        } r.close(); td.close();
        
        
         int[] yel = new int[9]; int[] rel = new int[3]; int[] tel = new int[3]; int[] lel = new int[LOCCD.size()];
        int[] Yel = new int[Yield.size()]; int[] mel = new int[MN.size()]; int[] Rel = new int[RM.size()];
        Arrays.fill(yel, 0); Arrays.fill(rel, 0); Arrays.fill(tel, 0); Arrays.fill(lel, 0); Arrays.fill(Yel, 0);
         
         
        int im = Arrays.binarySearch(mn, String.valueOf(am), tp);
        
        if(im < 0)
        {
            ml = new int[ml.length+1]; mel = new int[ml.length+1];
            
            mn = Arrays.copyOf(mn, mn.length+1);
            
            System.arraycopy(mn, -im-1, mn, -im,
			 mn.length + im);
	mn[-im-1] = String.valueOf(am);
            
        } Arrays.fill(ml, 0); Arrays.fill(mel, 0);
        
        int ir = Arrays.binarySearch(rm, String.valueOf(ar), tp);
        
        if(ir < 0)
        {
            Rl = new int[Rl.length+1]; Rel = new int[Rl.length+1];
            
         rm = Arrays.copyOf(rm, rm.length+1);
            
            System.arraycopy(rm, -ir-1, rm, -ir,
			 rm.length + ir);
	rm[-ir-1] = String.valueOf(ar);
        } Arrays.fill(Rl, 0); Arrays.fill(Rel, 0);  
        
                 r = new BufferedReader(new FileReader("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/TransformedData.csv")); double pe = 0, ne; 
          // adding the occurence of each diffrent attribute when the variety is elite                
          while((l = r.readLine()) != null)
        {
            String[] field = l.split(",");
            
            ml[Arrays.binarySearch(mn, field[7], tp)]++; 
            
            Rl[Arrays.binarySearch(rm, field[8], tp)]++;
            
            if(field[10].equals("1"))
           {
            yel[year.indexOf(field[1])]++; lel[Arrays.binarySearch(LOC, field[2], tp)]++;
            rel[rep.indexOf(field[3])]++; tel[type.indexOf(field[4])]++; Yel[Arrays.binarySearch(Y, field[6], tp)]++;
   
               mel[Arrays.binarySearch(mn, field[7], tp)]++;

               Rel[Arrays.binarySearch(rm, field[8], tp)]++; pe++;
           }
            
        } r.close(); double[] ev  = new double[7]; Arrays.fill(ev, 0); double[] nev  = new double[7]; Arrays.fill(nev, 0);
        
        ne = dataSize - pe; String nl;
          
        BufferedWriter w = new BufferedWriter(new FileWriter("F:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/src/"
                + "TopCoder/EliteClassifier/TrainingParameters.in"));
        
        w.write("String[] year = {"); l = "int[] yep = {"; nl = "int[] ynep = {"; 
        
        for(int i = 0; i < 9; i++)
        {
            double d = (double)yel[i]/pe;
            double nd = (double)(yl[i] - yel[i])/ne;
            ev[0] = ev[0] + d;
            nev[0] = nev[0] +nd;
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
            w.write("\""+year.get(i) + "\",");
            
               l = l + (int)d + ",";

               nl = nl + (int)nd + ",";
        }
        l = l.substring(0, l.length()-1); nl = nl.substring(0, nl.length()-1);
        w.write("};\r\n"+l+"};\r\n"+nl+"};\r\n");
        
        w.write("String[] rept = {"); l = "int[] rep = {"; nl = "int[] rnep = {"; 
        
        for(int i = 0; i < 3; i++)
        {
            double d = (double)rel[i]/pe;
            double nd = (double)(rl[i] - rel[i])/ne;
            ev[1] = ev[1] + d;
            nev[1] = nev[1] +nd;
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
            w.write("\""+rep.get(i) + "\",");

               l = l + (int)d + ",";

               nl = nl + (int)nd + ",";
        }
        l = l.substring(0, l.length()-1); nl = nl.substring(0, nl.length()-1);
        w.write("};\r\n"+l+"};\r\n"+nl+"};\r\n");
        
        w.write("String[] type = {"); l = "int[] tep = {"; nl = "int[] tnep = {"; 
        
        for(int i = 0; i < 3; i++)
        {
            double d = (double)tel[i]/pe;
            double nd = (double)(tl[i] - tel[i])/ne;
            ev[2] = ev[2] + d;
            nev[2] = nev[2] +nd;
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
            w.write("\""+type.get(i) + "\",");

               l = l + (int)d + ",";
 
               nl = nl + (int)nd + ",";
        }
        l = l.substring(0, l.length()-1); nl = nl.substring(0, nl.length()-1);
        w.write("};\r\n"+l+"};\r\n"+nl+"};\r\n");
        
        w.write("String[] lOCCD = {"); l = "int[] lep = {"; nl = "int[] lnep = {"; 
        
        for(int i = 0; i < ll.length; i++)
        {
            double d = (double)lel[i]/pe;
            double nd = (double)(ll[i] - lel[i])/ne;
            ev[3] = ev[3] + d;
            nev[3] = nev[3] +nd;
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
            w.write("\""+ LOC[i] + "\",");
            
               l = l + (int)d + ",";

               nl = nl + (int)nd + ",";
        }
        l = l.substring(0, l.length()-1); nl = nl.substring(0, nl.length()-1);
        w.write("};\r\n"+l+"};\r\n"+nl+"};\r\n");
        
        w.write("String[] Yield = {"); l = "int[] Yep = {"; nl = "int[] Ynep = {"; 
        
        for(int i = 0; i < Yl.length; i++)
        {
            double d = (double)Yel[i]/pe;
            double nd = (double)(Yl[i] - Yel[i])/ne;
            ev[4] = ev[4] + d;
            nev[4] = nev[4] +nd;
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
            w.write("\""+ Y[i] + "\",");

               l = l + (int)d + ",";

               nl = nl + (int)nd + ",";
        }
        l = l.substring(0, l.length()-1); nl = nl.substring(0, nl.length()-1);
        w.write("};\r\n"+l+"};\r\n"+nl+"};\r\n");
        
        List<String> mnd = new ArrayList<String>(mn.length); k = 0; j = 0; int[] mde = new int[mn.length]; 
        int[] mdl = new int[mn.length]; int[] mno = new int[mn.length]; Arrays.fill(mno, 0);
        
        for(String M : mn)
        {
            double dm = Double.parseDouble(M); dm = (int)((10*dm)+0.5)/10.0;
            
            int mi = mnd.indexOf(String.valueOf(dm));
                
                if(mi < 0)
                {
                    mde[k] = mel[j];
                    mdl[k] = ml[j];
                    mnd.add(String.valueOf(dm));
                    mno[k] = 1;
                    k++;
                }
                 else
                {
                   mde[mi] = mde[mi] + mel[j];
                   mdl[mi] = mdl[mi] + ml[j];
                   mno[mi]++;
                }
                
                j++;
                
        }  mn = mnd.toArray(new String[mnd.size()]);
        
        for(int i = 0; i < k; i++)
        {
            mde[i] = mde[i]/mno[i];
            mdl[i] = mdl[i]/mno[i];
        }
        mel = Arrays.copyOfRange(mde, 0, k); ml = Arrays.copyOfRange(mdl, 0, k);
        
        w.write("String[] MN = {"); l = "int[] mep = {"; nl = "int[] mnep = {"; 
        
        for(int i = 0; i < ml.length; i++)
        {
            double d = (double)mel[i]/pe;
            double nd = (double)(ml[i] - mel[i])/ne;
            ev[5] = ev[5] + d;
            nev[5] = nev[5] +nd;
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
            w.write("\""+mn[i] + "\",");
            
               l = l + (int)d + ",";

               nl = nl + (int)nd + ",";
        }
        l = l.substring(0, l.length()-1); nl = nl.substring(0, nl.length()-1);
        w.write("};\r\n"+l+"};\r\n"+nl+"};\r\n");
        
        w.write("String[] RM = {"); l = "int[] Rep = {"; nl = "int[] Rnep = {"; 
        
        for(int i = 0; i < Rl.length; i++)
        {
            double d = (double)Rel[i]/pe;
            double nd = (double)(Rl[i] - Rel[i])/ne;
            ev[6] = ev[6] + d;
            nev[6] = nev[6] +nd;
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
            w.write("\""+rm[i] + "\",");
            
               l = l + (int)d + ",";

               nl = nl + (int)nd + ",";
        }
        l = l.substring(0, l.length()-1); nl = nl.substring(0, nl.length()-1);
        w.write("};\r\n"+l+"};\r\n"+nl+"};\r\n");
        
        int[] as = {9, 3, 3, ll.length, Yl.length, ml.length, Rl.length};
        
        w.write("int[] ev = {"); l = "int[] nev = {";
        
        for(int i = 0; i < 7; i++)
        {
            double d = ev[i]/as[i];
            double nd = nev[i]/as[i];
            d = (int)((1000000*d) + 0.5);
            nd = (int)((1000000*nd) + 0.5);
            
              l = l + (int)nd + ",";
               w.write((int)d + ",");

        }
         l = l.substring(0, l.length()-1); w.write("};\r\n"+l+"};");
         
        pe = (int)((1000000*pe/dataSize) + 0.5); ne = (int)((1000000*ne/dataSize) + 0.5);
        
        w.write("int pe = "+ pe + ", ne = "+ne+";");
          
        w.close();
    }
}
