/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TopCoder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Muaz Aljarhi
 */
public class ElectronicPartsClassification {
    
    public static String[] classifyParts(String[] trainingData, String[] testingData)
    {
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //2012-11-14 00:00:00.0000000
        
        HashMap<String,Integer> CSP = new HashMap<String, Integer>(28*trainingData.length/50,1);
        HashMap<String,Integer> CSP2 = new HashMap<String, Integer>(378*trainingData.length/50,1);
        HashMap<String,Integer> CSP3 = new HashMap<String, Integer>(6552*trainingData.length/50,1);
        
        for (String trainingData1 : trainingData) 
        {
            String[] EPD = trainingData1.split(",");
            /*int PRODUCT_NUMBER = Integer.parseInt(EPD[0]); 
            int CUSTOMER_NUMBER = Integer.parseInt(EPD[1]);
            Calendar TRANSACTION_DATE = Calendar.getInstance();
            TRANSACTION_DATE.setTime(df.parse(EPD[2].substring(0, 10)));
            float PRODUCT_PRICE = Float.parseFloat(EPD[3]);
            float GROSS_SALES = Float.parseFloat(EPD[4]);
            int REGION = Integer.parseInt(EPD[5]);
            int WAREHOUSE = Integer.parseInt(EPD[6]); 
            int CUSTOMER_ZIP = Integer.parseInt(EPD[7]);
            char CUSTOMER_SEGMENT1 = EPD[8].charAt(0);
            int CUSTOMER_SEGMENT2 = Integer.parseInt(EPD[9]);
            int CUSTOMER_TYPE1 = Integer.parseInt(EPD[10]);
            char CUSTOMER_TYPE2 = EPD[11].charAt(0);
            char CUSTOMER_MANAGED_LEVEL = EPD[12].charAt(0);
            String CUSTOMER_ACCOUNT_TYPE = EPD[13];
            Calendar CUSTOMER_FIRST_ORDER_DATE = Calendar.getInstance();
            CUSTOMER_FIRST_ORDER_DATE.setTime(df.parse(EPD[14].substring(0, 10)));
            int PRODUCT_CLASS_ID1 = Integer.parseInt(EPD[15]);
            int PRODUCT_CLASS_ID2 = Integer.parseInt(EPD[16]);
            int PRODUCT_CLASS_ID3 = Integer.parseInt(EPD[17]);
            int PRODUCT_CLASS_ID4 = Integer.parseInt(EPD[18]);
            char BRAND =  EPD[19].charAt(0);
            int PRODUCT_ATTRIBUTE_X = Integer.parseInt(EPD[20]);
            char PRODUCT_SALES_UNIT =  EPD[21].charAt(0);
            float SHIPPING_WEIGHT = Float.parseFloat(EPD[22]);
            int TOTAL_BOXES_SOLD = Integer.parseInt(EPD[23]);
            float PRODUCT_COST1 = Float.parseFloat(EPD[24]);
            String PRODUCT_UNIT_OF_MEASURE = EPD[25];
            char ORDER_SOURCE = EPD[26].charAt(0);
            int PRICE_METHOD = Integer.parseInt(EPD[27]);
            String SPECIAL_PART = EPD[28];*/
            for (int j = 0; j<28; j++) 
            {    
                if(j != 8)
                {
                if (!CSP.containsKey((EPD[8]+","+EPD[j]).concat(","+EPD[28]))) 
                {
                    CSP.put((EPD[8]+","+EPD[j]).concat(","+EPD[28]), 1);
                } 
                else 
                {
                    CSP.replace((EPD[8]+","+EPD[j]).concat(","+EPD[28]), CSP.get((EPD[8]+","+EPD[j]).concat(","+EPD[28])) + 1);
                }
                }
            }
            
            for(int i = 0; i < 27; i++)
            {
                if(i != 8)
                {
                for(int j = i+1; j<28; j++)
                {
                    if(j != 8)
                    {
                    if (!CSP2.containsKey((EPD[8]+","+EPD[i]+"," +EPD[j]).concat(","+EPD[28]))) 
                    {
                      CSP2.put((EPD[8]+","+EPD[i]+"," +EPD[j]).concat(","+EPD[28]), 1);
                    } 
                    else 
                    {
                      CSP2.replace((EPD[8]+","+EPD[i]+"," +EPD[j]).concat(","+EPD[28]), CSP2.get((EPD[8]+","+EPD[i]+"," +EPD[j]).concat(","+EPD[28])) + 1);
                    }
                    }
                }
                }
            }
            
            for(int i = 0; i < 26; i++)
            {
                if(i != 8)
                {
                for(int j = i+1; j<27; j++)
                {
                    if(j != 8)
                    {
                    for(int k = j+1; k<28; k++)
                    {
                        if(k != 8)
                        {
                        if (!CSP3.containsKey((EPD[8]+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(","+EPD[28]))) 
                        {
                          CSP3.put((EPD[8]+","+EPD[i]+","+EPD[j]+"," +EPD[k]).concat(","+EPD[28]), 1);
                        } 
                        else 
                        {
                          CSP3.replace((EPD[8]+","+EPD[i]+","+EPD[j]+"," +EPD[k]).concat(","+EPD[28]), CSP3.get((EPD[8]+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(","+EPD[28])) + 1);
                        }
                        }
                    }
                    }
                }
                }
            }
        }
        
        Set<String> testResults = new HashSet<String>(testingData.length/10,1);
        
        for (String testingData1 : testingData) 
        {
            String[] EPD = testingData1.split(","); 
            
            int[] SPA = new int[84];
            int[] SPA1 = new int[1134];
            int[] SPA2 = new int[9828];
            int[] SPB = new int[84];
            int[] SPB1 = new int[1134];
            int[] SPB2 = new int[9828];
            
            for (int j = 0; j<28; j++) 
            {    
                if (CSP.containsKey(("A"+","+EPD[j]).concat(",Yes"))) 
                {
                  SPA[3*j] =  CSP.get(("A"+","+EPD[j]).concat(",Yes"));
                } 
                else if (CSP.containsKey(("A"+","+EPD[j]).concat(",No")))
                {
                   SPA[(3*j)+1] = CSP.get(("A"+","+EPD[j]).concat(",No"));
                }
                else if (CSP.containsKey(("A"+","+EPD[j]).concat(",Maybe")))
                {
                    SPA[(3*j)+2] = CSP.get(("A"+","+EPD[j]).concat(",Maybe"));
                }
            }
            
            for (int j = 0; j<28; j++) 
            {    
                if (CSP.containsKey(("B"+","+EPD[j]).concat(",Yes"))) 
                {
                  SPB[3*j] =  CSP.get(("B"+","+EPD[j]).concat(",Yes"));
                } 
                else if (CSP.containsKey(("B"+","+EPD[j]).concat(",No")))
                {
                   SPB[(3*j)+1] = CSP.get(("B"+","+EPD[j]).concat(",No"));
                }
                else if (CSP.containsKey(("B"+","+EPD[j]).concat(",Maybe")))
                {
                    SPB[(3*j)+2] = CSP.get(("B"+","+EPD[j]).concat(",Maybe"));
                }
            }
            
            for(int i = 0; i < 27; i++)
            {
                for(int j = i+1; j<28; j++)
                {
                    if (CSP2.containsKey(("A"+","+EPD[i]+"," +EPD[j]).concat(",Yes"))) 
                    { 
                     SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))] = CSP2.get(("A"+","+EPD[i]+"," +EPD[j]).concat(",Yes"));
                    } 
                    else if (CSP2.containsKey(("A"+","+EPD[i]+"," +EPD[j]).concat(",No"))) 
                    {
                      SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+1] = CSP2.get(("A"+","+EPD[i]+"," +EPD[j]).concat(",No"));
                    }
                    else if (CSP2.containsKey(("A"+","+EPD[i]+"," +EPD[j]).concat(",Maybe")))
                    {
                      SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+2] = CSP2.get(("A"+","+EPD[i]+"," +EPD[j]).concat(",Maybe"));  
                    }    
                }
            }
            
            for(int i = 0; i < 27; i++)
            {
                for(int j = i+1; j<28; j++)
                {
                    if (CSP2.containsKey(("B"+","+EPD[i]+"," +EPD[j]).concat(",Yes"))) 
                    { 
                     SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))] = CSP2.get(("B"+","+EPD[i]+"," +EPD[j]).concat(",Yes"));
                    } 
                    else if (CSP2.containsKey(("B"+","+EPD[i]+"," +EPD[j]).concat(",No"))) 
                    {
                      SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+1] = CSP2.get(("B"+","+EPD[i]+"," +EPD[j]).concat(",No"));
                    }
                    else if (CSP2.containsKey(("B"+","+EPD[i]+"," +EPD[j]).concat(",Maybe")))
                    {
                      SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+2] = CSP2.get(("B"+","+EPD[i]+"," +EPD[j]).concat(",Maybe"));  
                    }    
                }
            }
           
            
            
            for(int i = 0; i < 26; i++)
            {
                for(int j = i+1; j<27; j++)
                {
                    for(int k = j+1; k<28; k++)
                    {
                        if (CSP3.containsKey(("A"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Yes"))) 
                        {
                           SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))] = CSP3.get(("A"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Yes"));
                        } 
                        else if (CSP3.containsKey(("A"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",No"))) 
                        {
                          SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+1] = CSP3.get(("A"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",No"));
                        }
                        else if (CSP3.containsKey(("A"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Maybe"))) 
                        {
                          SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+2] = CSP3.get(("A"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Maybe"));
                        }
                    }
                } 
            }
            
            for(int i = 0; i < 26; i++)
            {
                for(int j = i+1; j<27; j++)
                {
                    for(int k = j+1; k<28; k++)
                    {
                        if (CSP3.containsKey(("B"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Yes"))) 
                        {
                           SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))] = CSP3.get(("B"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Yes"));
                        } 
                        else if (CSP3.containsKey(("B"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",No"))) 
                        {
                          SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+1] = CSP3.get(("B"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",No"));
                        }
                        else if (CSP3.containsKey(("B"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Maybe"))) 
                        {
                          SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+2] = CSP3.get(("B"+","+EPD[i]+"," +EPD[j]+"," +EPD[k]).concat(",Maybe"));
                        }
                    }
                } 
            }
            
            int maxA = 0; String PSPA = "NA";
            
            for(int i = 0; i < 28; i++)
            {
                  if(maxA < SPA[3*i]) { maxA = SPA[3*i]; PSPA = "Yes"; }
                
                  if(maxA < SPA[(3*i)+1]) { maxA = SPA[(3*i)+1]; PSPA = "No"; }
                
                  if(maxA < SPA[(3*i)+2]) { maxA = SPA[(3*i)+2]; PSPA = "Maybe"; }
            }
            
            int maxB = 0; String PSPB = "NA";
            
            for(int i = 0; i < 28; i++)
            {
                  if(maxB < SPB[3*i]) { maxB = SPB[3*i]; PSPB = "Yes"; }
                
                  if(maxB < SPB[(3*i)+1]) { maxB = SPB[(3*i)+1]; PSPB = "No"; }
                
                  if(maxB < SPB[(3*i)+2]) { maxB = SPB[(3*i)+2]; PSPB = "Maybe"; }
            }
             
            int maxA1 = 0; String PSPA1 = "NA";
            
            for(int i = 0; i < 27; i++)
            {
                for(int j = i+1; j<28; j++)
                {
                      if(maxA1 < SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))]) {maxA1 = SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))]; PSPA1 = "Yes";}
                      
                      if(maxA1 < SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+1]) {maxA1 = SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+1]; PSPA1 = "No";}
                    
                      if(maxA1 < SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+2]) {maxA1 = SPA1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+2]; PSPA1 = "Maybe";}
                }
            }
            
             int maxB1 = 0; String PSPB1 = "NA";
            
            for(int i = 0; i < 27; i++)
            {
                for(int j = i+1; j<28; j++)
                {
                      if(maxB1 < SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))]) {maxB1 = SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))]; PSPB1 = "Yes";}
                      
                      if(maxB1 < SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+1]) {maxB1 = SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+1]; PSPB1 = "No";}
                    
                      if(maxB1 < SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+2]) {maxB1 = SPB1[3*((i*27)+j-(i+1)-(((i-1)*i)/2))+2]; PSPB1 = "Maybe";}
                }
            }
            
            int maxA2 = 0; String PSPA2 = "NA";
            
            for(int i = 0; i < 26; i++)
            {
                for(int j = i+1; j<27; j++)
                {
                    for(int k = j+1; k<28; k++)
                    {
                        if(maxA2 < SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))]) 
                        { 
                            maxA2 = SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))]; PSPA2 = "Yes";
                        }
                        
                        if(maxA2 < SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+1]) 
                        { 
                            maxA2 = SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+1]; PSPA2 = "No";
                        }
                        
                        if(maxA2 < SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+2]) 
                        { 
                            maxA2 = SPA2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+2]; PSPA2 = "Maybe";
                        }
                    }
                } 
            }
            
            int maxB2 = 0; String PSPB2 = "NA";
            
            for(int i = 0; i < 26; i++)
            {
                for(int j = i+1; j<27; j++)
                {
                    for(int k = j+1; k<28; k++)
                    {
                        if(maxB2 < SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))]) 
                        { 
                            maxB2 = SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))]; PSPB2 = "Yes";
                        }
                        
                        if(maxB2 < SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+1]) 
                        { 
                            maxB2 = SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+1]; PSPB2 = "No";
                        }
                        
                        if(maxB2 < SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+2]) 
                        { 
                            maxB2 = SPB2[3*((i*351)+(((j-1-i)*(26-i))+k-(j+1)-((j-2-i)*(j-1-i)/2))-(((i-1)*i*(80-i))/6))+2]; PSPB2 = "Maybe";
                        }
                    }
                } 
            }
            
            boolean PinSA = SPA[0] > 0 || SPA[1] > 0 || SPA[2] > 0, 
                    PinSB = SPB[0] > 0 || SPB[1] > 0 || SPB[2] > 0;
   
            String tr1 = "";
            
            if(!PinSA)
            {
               tr1 = EPD[0]+",NA"; 
            }
            else if(!"NA".equals(PSPA2))
            {
                tr1 = EPD[0]+","+PSPA2;
            }
            else if(!"NA".equals(PSPA1))
            {
               tr1 = EPD[0]+","+PSPA1; 
            }
            else if(!"NA".equals(PSPA))
            {
                tr1 = EPD[0]+","+PSPA;
            }
  
            if(!PinSB)
            {
               tr1 = tr1+",NA"; 
            }
            else if(!"NA".equals(PSPB2))
            {
                tr1 = tr1+","+PSPB2;
            }
            else if(!"NA".equals(PSPB1))
            {
               tr1 = tr1+","+PSPB1; 
            }
            else if(!"NA".equals(PSPB))
            {
                tr1 = tr1+","+PSPB;
            }
            
            testResults.add(tr1);
            
        }
        
        String[] y = testResults.toArray(new String[testResults.size()]);
        
      return y;
    }
    
    public static void main(String[] args) throws IOException, ParseException
    {
        BufferedReader R1 = new BufferedReader(new FileReader("C:/Users/Muaz Aljarhi/Downloads/train_data.csv"));
        BufferedReader R2 = new BufferedReader(new FileReader("C:/Users/Muaz Aljarhi/Downloads/test_data.csv"));
        String [] trainingData = new String[37581];
        String [] testData = new String[18790];
        int i = 0;
        
        String s;
        
        while((s = R1.readLine()) != null)
        {
            trainingData[i] = s;
            i++;
        }
        i = 0;
        while((s = R2.readLine()) != null)
        {
            testData[i] = s;
            i++;
        }
        String[] PResults = classifyParts(trainingData, testData);
        
        for(i = 0; i < PResults.length; i++)
        {
            System.out.println(PResults[i]);
        }
        
    }
}
