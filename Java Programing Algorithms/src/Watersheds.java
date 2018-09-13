
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.UnsupportedEncodingException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */



public class Watersheds extends Thread implements Runnable {

        public Watersheds() {
        
    }
static boolean debug = true;
static String phone="";
static String message="";
static String ppgHost="http://192.168.1.100:8080";

public static void main(String[] args) throws MalformedURLException, IOException
{
        try {
            phone = URLEncoder.encode(getMobileId(), "UTF-8");
            if (debug) {
                System.out.println("phone---->" + phone);
            }
            message = URLEncoder.encode(getMessage(), "UTF-8");
            if (debug) {
                System.out.println("message---->" + message);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Watersheds.class.getName()).log(Level.SEVERE, null, ex);
        }


String url_str=ppgHost+"?PhoneNumber="+phone+"&Text="+message;
if(debug)
System.out.println("path name---url_str----->"+url_str);
URL url2=new URL(url_str);
HttpURLConnection connection = (HttpURLConnection) url2.openConnection();
connection.setDoOutput(false);
connection.setDoInput(true);
if(debug)
System.out.println("connection :"+connection);
String res=connection.getResponseMessage();
if(debug)
System.out.println("res :"+res);

int code = connection.getResponseCode () ;

if ( code == HttpURLConnection.HTTP_OK )
{

connection.disconnect() ;
}
}
   private static String getMobileId() {
        return "0127900339";
    }

    private static String getMessage() {
        return "You are pro";
    }
}
