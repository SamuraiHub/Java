/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Prerequisites {

private String[] COS;
private int k;
private int m;
private int n;

    /**
     * @param args the command line arguments
     *
     */
    public Prerequisites(){

    }

    public String[] orderClasses(String[] classSchedule){
     COS = new String[classSchedule.length];
     for(int j = 0; j<COS.length; j++)
     {
     m = 0;
     n = 0;
     for(int i = 0; i<classSchedule.length; i++)
     {
      if(classSchedule[i].endsWith(":"))
      {
      if(m>0)
      {
      if(Character.isDigit(classSchedule[i].charAt(3)))
      n = Integer.parseInt(classSchedule[i].substring(3, 6));
      else
      n = Integer.parseInt(classSchedule[i].substring(4, 7));
      }
      else
      {
       k = i;
      if(Character.isDigit(classSchedule[i].charAt(3)))
      m = Integer.parseInt(classSchedule[i].substring(3, 6));
      else
      m = Integer.parseInt(classSchedule[i].substring(4, 7));
      }

       if(m>0 && n>0)
       {
         if(m>n)
         {
          k = i;
          m = n;
         }
         else
         {
          if(m == n)
          {
          for(int l=0; l<4; l++)
          {
           if(classSchedule[k].charAt(l) > classSchedule[i].charAt(l))
           {
            k = i;
            break;
           }
           else
           {
            if(classSchedule[k].charAt(l) < classSchedule[i].charAt(l))
            break;
           }
           }
          }
          }
          }
         }
        }
       
       if(m == 0)
       {
        COS = new String[0];
        break;
       }
       COS[j] = classSchedule[k].replace(":", "");
       
       for(int i = 0; i<classSchedule.length; i++)
       {
           classSchedule[i] = classSchedule[i].replace(" "+classSchedule[k].replace(":", ""), "");
       }
       classSchedule[k] = "";
     }

     return COS;
     }

    public static void main(String[] args) {
        // TODO code application logic here
       Prerequisites preq1 = new Prerequisites();
       String[] c1 = {"CSE258: CSE244 CSE243 INTR100","CSE221: CSE254 INTR100",
"CSE254: CSE111 MATH210 INTR100","CSE244: CSE243 MATH210 INTR100","MATH210: INTR100",
"CSE101: INTR100","CSE111: INTR100","ECE201: CSE111 INTR100",
"ECE111: INTR100","CSE243: CSE254","INTR100:"};
       String[] oc = preq1.orderClasses(c1);
       for(int i = 0; i<oc.length; i++)
       System.out.println(oc[i]);
    }
}
