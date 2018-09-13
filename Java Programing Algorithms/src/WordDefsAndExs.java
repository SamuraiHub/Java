import java.io.*;
import java.net.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */
public class WordDefsAndExs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
          BufferedReader gr = new BufferedReader(new FileReader("F:/Downloads/GRE/Word Lists/Splitted Lists/List 2.wl"));
        
           String[] words = new String[110]; String r; 
     
     for(int i = 0; (r = gr.readLine()) != null; i++)
     {
         words[i] = r; gr.readLine();
     }
     
     gr.close();  
     
      BufferedWriter writer = new BufferedWriter(new FileWriter("F:/Downloads/GRE/Word Lists/Splitted Lists/List 2.wadael"));
     int k = 0;
     for(int j = 0; j < words.length; j++)
     {
         String def = "Definitions: ", ex = "Examples: ";

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
            if("Definitions: ".equals(def))
            {
                k++;
                System.out.println("No Def: "+ words[j]+ "\r\n");
            }
            else if("Examples: ".equals(ex))
            {
               k++;
               System.out.println("No Ex: "+ words[j]+ "\r\n"); 
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + words[j]+ "\r\n");
        } 
     }
      writer.close();
      System.out.println(k);
     /* for(int j = 0; j < words.length; j++)
     {
         URL BNC = new URL("http://bnc.bl.uk/saraWeb.php?qy=" + words[j].toLowerCase() + "&mysubmit=Go");
            
            HttpURLConnection getSentences = (HttpURLConnection) BNC.openConnection(); //boolean t = false;
            
           BufferedReader dis = new BufferedReader(new InputStreamReader(new DataInputStream(getSentences.getInputStream())));
              String inputLine; //int i, b = 0;
       
                 while ((inputLine = dis.readLine()) != null) {
                  
                     if(inputLine.contains("solutions"))
                     break;    
                 }
                 inputLine = dis.readLine();
                 if(inputLine != null && inputLine.startsWith("<p>No"))
                     System.out.println(words[j]);
     } */
    }
}
