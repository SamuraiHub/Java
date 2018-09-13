
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */


public class sorting {

    /**
     */

    public sorting(){}

    public int[] mergeSort(int[] num)
    {
       for(int j=0; j<Math.ceil(Math.log(num.length)/Math.log(2)); j++)
       {
        int[] result = new int[num.length];
        int n = 0;
        int i = 0;
        int e = (int)Math.pow(2,j);

        while(i<num.length)
       {
        int k = i + e;

        int m = k;

        int p = m + e;

        if(p > num.length) p = num.length;

        if(k<p && num[k] > num[k-1])
        {
          while(i<m)
          {
             result[n] = num[i];
             i++;
             n++;
          }
           while(k<p)
          {
           result[n] = num[k];
           k++;
           n++;
          }
        }
        else if(num[p-1]<num[i])
        {
           while(k<p)
          {
           result[n] = num[k];
           k++;
           n++;
          }
            while(i<m)
          {
             result[n] = num[i];
             i++;
             n++;
          }
        }
        else
        {
        while(i<m && k<p)
        {
         if(num[k] < num[i])
         {
         result[n] = num[k];
         k++;
         n++;
         }
         else
         {
         result[n] = num[i];
         i++;
         n++;
         }
         }

          if(i<m)
          {
             if(m > num.length) m = num.length;
            
           while(i<m)
          {
           result[n] = num[i];
           i++;
           n++;
          }
          }
          else
          {
           while(k<p)
          {
           result[n] = num[k];
           k++;
           n++;
          }
          }
          }
          i = m + e;
        }
         num = result;
       }
     return num;
    }

 public int[] toIntArray(List<Integer> integerList) {
int[] intArray = new int[integerList.size()];
for (int i = 0; i < intArray.length; i++) {
intArray[i] = integerList.get(i);
}
return intArray;
}

 public List<Integer> AsIntegerList(int[] intArray)
 {
     List<Integer> IntegerList = new ArrayList<Integer>(intArray.length);

     for (int i = 0; i < intArray.length; i++){
     IntegerList.add(intArray[i]);
     }
     return IntegerList;
 }

    public int[] bucketSort(int[] num, int r)
    {
       ArrayList<Integer>[] bySize = new ArrayList[10];
        for(int i = 0; i<10; i++)
        bySize[i]= new ArrayList<Integer>(num.length/10);

        for(int i = 0; i<num.length; i++)
        {
            int j = 0;
            int k = num[i];
            
            if(k >= r*4)
            {
                j = j + 4; k = k-(r*4); 
            }
             if(k >= r*3)
            {
                j=j+3; k = k-(r*3);
            }   
            if(k >= r*2)
            {
                j=j+2; k=k-(r*2);
            }
            if(k >= r)
            {
                j=j+1;
            }

            bySize[j].add(num[i]);  
        }

         int[] res = new int[num.length];

         int rp = 0;

         for(int i = 0; i < 10; i++)
         {
             
            if(bySize[i].size()*Math.log10(bySize[i].size())/Math.log10(2.0) < (bySize[i].size() + r))
            {
              Collections.sort(bySize[i]);
              
              for(int j = 0; j <bySize[i].size(); j++)
              { 
                res[rp] = bySize[i].get(j);
                rp++;
              }
            }
            else
            {
                int[] ct = new int[r]; int m = i*r;
                
                for(int j = 0; j <bySize[i].size(); j++)
                {
                  ct[bySize[i].get(j)-m]++;  
                }
                
                for(int j = 0; j<r; j++)
                {
                    for(int k = 0; k<ct[j]; k++)
                    {
                       res[rp] = j + m;
                       rp++;         
                    }
                }
            }
         }
         Arrays.fill(bySize, null);
         return res;
    }

     private int[] intSort(ArrayList<Integer> num, int size)
    {
           ArrayList<Integer>[] l = new ArrayList[10];
         for(int i = 0; i<10; i++)
        l[i]= new ArrayList<Integer>(num.size()/10);
        int p = (int)Math.pow(10, size);

         for(int i = 0; i<num.size(); i++)
         {
          if(num.get(i)< p)
          {
             l[0].add(num.get(i));
          }
         else
          {
             int j = (num.get(i)/p) - 10 * (int)(num.get(i)/(10*(long)p));


          l[j].add(num.get(i));

         }
        }

        int[] res = new int[num.size()];

        int k = 0;

         for(int i = 0; i<10; i++)
        {
             if(l[i].size() > 1)
             {
             if(size == 0)
             {
             for(int j = 0; j < l[i].size(); j++)
             {
                res[k] = l[i].get(j);
                k++;
             }
                l[i].clear();
             }
            else
             {
            int[] RS = intSort(l[i], size-1);

            for(int j = 0; j < l[i].size(); j++)
            {
                res[k] = RS[j];
                k++;
            }
             }
            }
            else if(l[i].size() == 1)
            {
               res[k] = l[i].get(0);
               k++;
            }
        }

       Arrays.fill(l, null);

        return res;
    }

     public int[] intSort(int[] num, int size)
    {
           ArrayList<Integer>[] l = new ArrayList[10];
         for(int i = 0; i<10; i++)
        l[i]= new ArrayList<Integer>(num.length/10);
        int p = (int)Math.pow(10, size);

         for(int i = 0; i<num.length; i++)
         {
          if(num[i] < p)
          {
             l[0].add(num[i]);
          }
         else
          {
                int j = (num[i]/p) - 10 * (int)(num[i]/(10*(long)p));

          l[j].add(num[i]);

         }
        }

        int[] res = new int[num.length];

        int k = 0;

         for(int i = 0; i<10; i++)
        {
             if(l[i].size() > 1)
             {
               if(size == 0)
             {
             for(int j = 0; j < l[i].size(); j++)
             {
                res[k] = l[i].get(j);
                k++;
             }
                l[i].clear();
             }
            else
             {
            int[] RS = intSort(l[i], size-1);

            for(int j = 0; j < l[i].size(); j++)
            {
                res[k] = RS[j];
                k++;
            }
             }
            }
            else if(l[i].size() == 1)
            {
               res[k] = l[i].get(0);
               k++;
            }
        }

       Arrays.fill(l, null);

        return res;
    }
     
    public void countSort(int[] num, int maxLength)
    {
        int[] c = new int[maxLength]; 
        
        for(int i : num)
        {
            c[i]++;  
        }
        
        int n = 0;
        for(int i = 0; i <maxLength; i++)
        {
          for(int j = 0; j < c[i]; j++)
          {
             num[n] = i;
             n++;
          }
        }
    } 

     public String[] mergeWordSort(String[] st)
    {
       for(int j=0; j<Math.ceil(Math.log(st.length)/Math.log(2)); j++)
       {
        String[] result = new String[st.length];
        int n = 0;
        int i = 0;
        int e = (int)Math.pow(2,j);

        while(i<st.length)
       {
        int k = i + e;

        int m = k;

        int p = m + e;

        if(p > st.length) p = st.length;

        if(k<p && st[k].compareTo(st[k-1]) > 0)
        {
          while(i<m)
          {
             result[n] = st[i];
             i++;
             n++;
          }
           while(k<p)
          {
           result[n] = st[k];
           k++;
           n++;
          }
        }
        else if(st[p-1].compareTo(st[i])< 0)
        {
           while(k<p)
          {
           result[n] = st[k];
           k++;
           n++;
          }
            while(i<m)
          {
             result[n] = st[i];
             i++;
             n++;
          }
        }
        else
        {
        while(i<m && k<p)
        {
         if(st[k].compareTo(st[i]) < 0)
         {
         result[n] = st[k];
         k++;
         n++;
         }
         else
         {
         result[n] = st[i];
         i++;
         n++;
         }
         }

          if(i<m)
          {
             if(m > st.length) m = st.length;

           while(i<m)
          {
           result[n] = st[i];
           i++;
           n++;
          }
          }
          else
          {
           while(k<p)
          {
           result[n] = st[k];
           k++;
           n++;
          }
          }
          }
          i = m + e;
        }
         st = result;
       }
     return st;
    } 

    private void remove(String[] st, int len, int index)
    {
         for(int m = index; m < len; m++)
         {
            st[m] = st[m+1];
         }
    }

    public String[] ABCsort(String[] st, int len, int p)
    {

        String[][] l = new String[27][len];

        int[] L = new int[27];

         String[] res = new String[len];

         int k = 0;

        int b;
        for(int i = 0; i<len; i++)
        {
           b = st[i].charAt(p)-'`';
           l[b][L[b]] = st[i];
           L[b]++;

        }
        for(int i = 0; i < L[0]; i++)
        {
           res[k] = l[0][i];
           k++;
        }

         for(int i = 1; i<27; i++)
        {
             if(L[i] > 1)
             {

            String[] RS = ABCsort(l[i], L[i], p+1);

            for(int j = 0; j < RS.length; j++)
            {
                res[k] = RS[j];
                k++;
            }
            
            }
            else if(L[i] == 1)
            {
               res[k] = l[i][0];
               k++;
            }
        }
        // Arrays.fill(l, null);
        return res;
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        sorting ms = new sorting();

        Random r= new Random(System.currentTimeMillis());

         long t1 = 0, t2 = 0, t3 = 0, t4 = 0, t5 = 0;

          for(int j = 0; j<100; j++)
        {

        int[] numb = new int[10000];

            for(int k = 0; k < 10000; k++)
        {
         int l = r.nextInt((int)Math.pow(2, 19));

         numb[k] = l;
        }
        
        long start = System.currentTimeMillis();

        int[] numb1 = ms.mergeSort(numb);

         long finish = System.currentTimeMillis();

        t1 += (finish-start);
        
        start = System.currentTimeMillis();

        int[] numb2 = ms.intSort(numb, 10);

         finish = System.currentTimeMillis();

        t2 += (finish-start);

        start = System.currentTimeMillis();

         int[] numb3 = ms.bucketSort(numb, 52429);

         finish = System.currentTimeMillis();

        t3 += (finish-start);
        
        int[] b = Arrays.copyOf(numb, numb.length);

          start = System.currentTimeMillis();

          Arrays.sort(b);
 
         finish = System.currentTimeMillis();

        t4 += (finish-start);
        
        start = System.currentTimeMillis();

          ms.countSort(numb, (int)Math.pow(2, 19));
 
         finish = System.currentTimeMillis();
         
         t5 += (finish-start);
         
       }
          
        System.out.println("Average time (t1): " + t1/100.0);
        System.out.println("Average time (t2): " + t2/100.0);
        System.out.println("Average time (t3): " + t3/100.0);
        System.out.println("Average time (t4): " + t4/100.0); 
        System.out.println("Average time (t5): " + t5/100.0);

      /* Random r= new Random(System.currentTimeMillis());

        long t1 = 0, t2 = 0, t3 = 0;

         for(int j = 0; j<50; j++)
        {

        String[] st = new String[10000];

        for(int k = 0; k < 10000; k++)
        {
         String s = "";

         int l = r.nextInt(10) + 1;

         for(int m = 0; m < l; m++)
         {
         int c = r.nextInt(26);

          s = s + (char)('a'+ c);
         }
         st[k] = s+'`';
        }

          long start = System.currentTimeMillis();

         String[]  st1 = ms.ABCsort(st, st.length, 0);

       long finish = System.currentTimeMillis();

       t1 += (finish-start);
       
       start = System.currentTimeMillis();

      String[] st2 = ms.mergeWordSort(st);

       finish = System.currentTimeMillis();

       t2 += (finish-start);
       
       start = System.currentTimeMillis();

       Arrays.sort(st);

       finish = System.currentTimeMillis();

       t3 += (finish-start);
     }
        System.out.println("Average time (t1): " + t1/50.0);
        System.out.println("Average time (t2): " + t2/50.0);
        System.out.println("Average time (t3): " + t3/50.0); */
    } 

}
