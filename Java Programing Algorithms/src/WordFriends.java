
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class WordFriends {

    /**
     * @param args the command line arguments
     */
    
    public static int getSocialNetworkSize(String word) throws IOException
    {
        BufferedReader r = new BufferedReader(new FileReader("word.list")); 
        
        String[] words = new String[264061]; // Array to hold the words in the word list. (264061 is the size of the 
                                            //  word list gotten using notepad
        int i;
        for(i = 0; i < 264061; i++)
        {
            words[i] = r.readLine();
        }
        
        String[] SN = new String[264061]; // output to hold the words in the social network
        int p = 0; // word position in SN
        
        SN[p++] = word;
        
        for(i = 0; i < 264061; i++)
        {
            int d = 0; // LevenshteinDistance 
            
            if(words[i].length() == word.length())
            {
                for(int  j = 0; j < word.length(); j++)
                {
                    if(word.charAt(j) != words[i].charAt(j))
                    {
                        if(d == 1)
                        {  
                            d = 0;
                            break;
                        }
                        
                        d = 1;
                    }
                } 
            }
            else if(words[i].length() == word.length() + 1)
            {                
                    int m = 0, n = 0;
                
                    while(n < word.length())
                    {
                         if(words[i].charAt(m) == word.charAt(n))
                         { 
                           n++; 
                           if(n == word.length())
                           d = 1;    
                         }
                         else
                         {
                             if(d == 1)
                             {
                                 d = 0;
                                 break;
                             }
                          
                             d = 1;
                         }
                                   
                           m++;
                    } 
            }
            else if(word.length() == words[i].length() + 1)
            {   
                    int m = 0, n = 0;
                
                    while(m < words[i].length())
                    {
                         if(words[i].charAt(m) == word.charAt(n))
                         { 
                           m++;
                           if(m == word.length())
                           d = 1; 
                         }
                         else
                         {
                             if(d == 1)
                             {
                                 d = 0;
                                 break;
                             }
                          
                             d = 1;
                         }
                                   
                           n++;
                    } 
            }
            if(d == 1)
                SN[p++] = words[i];
        }
        
            for(int k = 1; k < p; k++)
            {
                for (i = 0; i < 264061; i++) 
                {
                    int d = 0; // LevenshteinDistance 
                    
                    if (words[i].length() == SN[k].length())
                    {
                        
                        for (int j = 0; j < SN[k].length(); j++) 
                        {
                            
                            if (SN[k].charAt(j) != words[i].charAt(j)) 
                            {
                                if (d == 1) 
                                {
                                    d = 0;
                                    break;
                                }

                                d = 1;
                            }
                        }
                    } 
                    else if (words[i].length() == SN[k].length() + 1) 
                    {
                            int m = 0, n = 0;

                            while (n < SN[k].length()) 
                            {
                                
                                if (words[i].charAt(m) == SN[k].charAt(n)) 
                                {
                                    n++;
                                    if(n == word.length())
                                    d = 1; 
                                } 
                                else 
                                {
                                    
                                    if (d == 1) 
                                    {
                                        d = 0;
                                        break;
                                    }

                                    d = 1;
                                }

                                m++;
                            }
                    } 
                    else if(SN[k].length() == words[i].length() + 1)
                    {
                            int m = 0, n = 0;

                            while (m < words[i].length()) 
                            {
                                if (words[i].charAt(m) == SN[k].charAt(n)) 
                                {
                                    m++;
                                    if(m == word.length())
                                    d = 1; 
                                } 
                                else 
                                {
                                    if (d == 1) 
                                    {
                                        d = 0;
                                        break;
                                    }

                                    d = 1;
                                }

                                n++;
                            }
                    }
                    
                    if (d == 1) 
                    {
                        for(int j = 0; j < p; j++)
                        {
                            if(SN[j].equals(words[i]))
                            {
                              d = 0; 
                              break;
                            }    
                        }
                        if(d == 1)
                            SN[p++] = words[i];
                    }
                }
            }
        
        return p-1;
    }
    
    public static void main(String[] args) throws IOException {
 
            // TODO code application logic here
            
           // System.out.println("Network Size: " + getSocialNetworkSize("causes"));
            
        BufferedReader gr = new BufferedReader(new FileReader("F:/Stuff/Downloads/GRE/notFoundWords.txt"));
        
           String[] words = new String[946]; String r; 
     
     for(int i = 0; (r = gr.readLine()) != null; i++)
     {
         words[i] = r; gr.readLine();
     }
     
     gr.close();  
     BufferedWriter writer = new BufferedWriter(new FileWriter("F:/Stuff/Downloads/GRE/NFWords.txt"));
     int k = 0;
     for(int j = 0; j < words.length; j++)
     {
         String def = "Definitions: ", ex = "Examples: ";
        
            /* getting word meaning/example form oxford dictionary*/
 
            try {
                URL Oxford = new URL("http://oxforddictionaries.com/definition/" + words[j].toLowerCase());
            
            
            HttpURLConnection getMeaning = (HttpURLConnection) Oxford.openConnection(); 
            
            
            BufferedReader dis = new BufferedReader(new InputStreamReader(new DataInputStream(getMeaning.getInputStream())));
                String inputLine; int i, b = 0;
            
                 while ((inputLine = dis.readLine()) != null) {
                    
                    i = inputLine.indexOf("\"definition\""); boolean t = false;
                    
                    while(i > 0)
                    {
                      i = i+13; t = true;
                      
                       b = inputLine.indexOf('<', i);
                      
                      String m = inputLine.substring(i, b);
                      
                      def = def + m + ", ";
                      
                      b = b + 54; i = b+9;
                      
                     if((inputLine.substring(b, i)).equals("\"example\""))
                     {
                        i = i + 1;
                        b = inputLine.indexOf('<', i);
                        m = inputLine.substring(i, b);
                        ex = ex + m + ", ";
                     }
                      
                      i = inputLine.indexOf("\"definition\"", b);
                    }
                    if(t == true)
                        break;
                }
                dis.close(); 
            }            
            catch (IOException e) {
            System.out.println("Oxford: " + words[j]+ "\r\n");
        }    
           
            
            /* getting word meaning/example form webester dictionary*/ 
           try
           {  
           URL Webster = new URL("http://www.merriam-webster.com/dictionary/" + words[j].toLowerCase());
            
            HttpURLConnection getMeaning = (HttpURLConnection) Webster.openConnection(); boolean t = false;
            
           BufferedReader dis = new BufferedReader(new InputStreamReader(new DataInputStream(getMeaning.getInputStream())));
              String inputLine; int i, b = 0;
       
                 while ((inputLine = dis.readLine()) != null) {
                    
                    i = inputLine.indexOf(">:<");
                    
                    while(i > 0)
                    {
                       i = i+12; t = true; 
                    
                      b = i - 4;
                      
                       String m = ""; 
                       while(!(inputLine.substring(b, i).equals("</span>")))
                            {
                              if(inputLine.charAt(i) == '&')
                             {
                               i = inputLine.indexOf(";", i)+1;   
                             }
                               b = inputLine.indexOf('<', i);
                               
                               if(b - i > 4)
                               {
                                m = m + inputLine.substring(i, b - 4);
                               
                               if(inputLine.charAt(b - 4) != '&')
                             {
                               m = m + inputLine.substring(b-4, b);  
                             } 
                            }
                             else
                               {
                              m = m + inputLine.substring(i,b);
                               }
                               i = inputLine.indexOf('>', b)+1;
                            }
                       def = def + m + ", ";
                      i = inputLine.indexOf(">:<", b);
                    }
                    if(t == true)
                    break;
                 }
                  while ((inputLine = dis.readLine()) != null) { 
                      
                    i = inputLine.indexOf("\"example-sentences\"");
                    
                    if(i > 0)
                    {
                        i += 35;
                        i = inputLine.indexOf("\"always-visible\"", i);
                        i += 17; b = i - 4;

                     while(true)
                     {   
                            String m = ""; i = inputLine.indexOf('>', b)+1;
                            
                            while(!(inputLine.substring(b, i).equals("</li>")) && 
                                    !(inputLine.substring(b, i).equals("</ol>")))
                            {
                              if(inputLine.charAt(i) == '&')
                             {
                               i = inputLine.indexOf(";", i)+1;   
                             }
                               b = inputLine.indexOf('<', i);
                               
                               if(b - i >= 4)
                               {
                                if(!"more".equals(inputLine.substring(i,b)) && !"hide".equals(inputLine.substring(i,b)))   
                                { 
                                    m = m + inputLine.substring(i, b - 4);
                               
                               if(inputLine.charAt(b - 4) != '&')
                             {
                               m = m + inputLine.substring(b-4, b);  
                             } 
                                }
                            }
                             else
                               {
                                   if(!"[-]".equals(inputLine.substring(i,b)) && !"[+]".equals(inputLine.substring(i,b)))
                                   m = m + inputLine.substring(i,b);
                               }
                               i = inputLine.indexOf('>', b)+1;
                            }
                            
                            ex = ex + m + ", "; 
                            
                            if( i == inputLine.length())
                            {
                              inputLine = dis.readLine(); b = 0;
                            }
                            else break;
                     }
                     break;
                    }
                 }

                dis.close();
            writer.write(words[j]);
            writer.write("\r\n" + def.substring(0, def.length()-2));
            writer.write("\r\n" + ex.substring(0, ex.length()-2)+"\r\n\r\n");
            k++;
        } catch (IOException e) {
            System.out.println("Webster: " + words[j]+ "\r\n");
        } 
     }
      writer.close();
      System.out.println(k);
    }
}
