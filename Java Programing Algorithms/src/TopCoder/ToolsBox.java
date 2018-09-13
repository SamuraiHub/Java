package TopCoder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class ToolsBox {

    /**
     * @param args the command line arguments
     */
    public ToolsBox(){
        
    }
    
    public String[] countTools(String[] need)
    {
      int ct = 0;
      
      for (int i=0; i<need.length; i++)
      {
       String[] ift = need[i].split(" ");  
       
      ct = ct + ift.length;
       
       for(int k=i+1; k<need.length; k++)
       {
               for(int j=0; j<ift.length; j++)
       {
        if(need[k].contains(ift[j]))
        {
         
          need[k] = need[k].replace(ift[j], "").trim();
        }   
       }    
      }        
      }
      return need;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ToolsBox cts = new ToolsBox();

        String[] tools = {"SAW HAMMER SCREWDRIVER","SCREWDRIVER HAMMER KNIFE"};

        String[] cnt = cts.countTools(tools);

        for(int i=0; i<cnt.length; i++)
        {
         System.out.println(cnt[i]);
        }
    }

}
