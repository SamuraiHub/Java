/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
 import java.io.*;

public class PermutationRecovery
{

public static int [] PR(int [] a)
{
 int  [] b = new int [a.length];
 
 int n = 1;
for(int i = 0; i < a.length; i++)
{
int s = -1;	
int A = a[i];
for(int j = 0; j < b.length; i++)  
{
if(b[j] == 0)
{
s++;
}
if(s == A)
{
b[j] = n;
n++;
break;
}
}
}
return b;
}

public static void main(String argus[]) throws Exception
{

BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

System.out.print("InputPermutationSize: ");
int c = Integer.parseInt(input.readLine());

int [] d = new int[c];

for (int j=0; j<c; j++)
{
System.out.print("InputPermutationNumber: ");

int f = Integer.parseInt(input.readLine());
d[j] = f;
}
d = PR(d);
for(int i=0; i<d.length; i++)
{
System.out.print(d[i]);
}
} 

}