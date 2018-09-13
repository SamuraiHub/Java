
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class ridership_stats extends Thread implements Runnable 
{
    private String FileName;
    
    private double mtd; // median trip duration
    private int tds; // total size (number of rows) of the data (9937981)
    private int rst; // rides that start and end at same station
    private HashMap<Integer, HashSet<Integer>> bvs; // number of different visited stations by a single bike 
    private double td;// trip disrance
    
    public ridership_stats(String FileName)
    {
        this.FileName = FileName;
    }
    
    @Override
    public void run() 
    {
        mtd = 0;
        tds = 0;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(FileName));
            br.readLine();
            for(String s = br.readLine(); s != null; s = br.readLine())
            {
               String[] colums = s.split(",");
               mtd += Double.parseDouble(colums[0]);
               tds++;
               int bid = Integer.parseInt(colums[11]);
               if(!bvs.containsKey(bid))  
                 bvs.put(bid, new HashSet<>());
               
               int ss = Integer.parseInt(colums[3]);
               int es = Integer.parseInt(colums[7]);
               
               if(ss == es)
               {
                   bvs.get(bid).add(ss);
                   rst++;
               }
               else
               {
                   bvs.get(bid).add(ss);
                   bvs.get(bid).add(es);
                   
                   double sla = Double.parseDouble(colums[5]); // start latitude
                   double slo = Double.parseDouble(colums[6]); // start longitude
                   double ela = Double.parseDouble(colums[9]); // end latitude
                   double elo = Double.parseDouble(colums[10]); // end longitude
                   td = td + (12742 * Math.asin(Math.sqrt(Math.pow(Math.sin(Math.abs(ela-sla)/2), 2) + (Math.cos(sla) * Math.cos(ela) * 
                       Math.pow(Math.sin(Math.abs(elo-slo)/2), 2)))));
                
                
               }
               
              
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ridership_stats.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ridership_stats.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args)
    {
        
    }
}
