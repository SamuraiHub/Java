/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.*;

/**
 *
 * @author Muaz
 */
public class Helpers {

    private static javax.swing.JTextArea lblOut;
    protected static List userlist = Collections.synchronizedList(new ArrayList());

    public static void init(javax.swing.JTextArea p_label) {
        lblOut = p_label;
    }

    public static void OutputText(String txt) {
        lblOut.append("\r\n" + txt);
    }

    public static boolean IsUsernameValid(String p_username) {
        String rex = "^[A-Za-z][A-Za-z0-9]*";
        if (p_username.length() <= 64) {
            return p_username.matches(rex);
        }
        return false;
    }

    public static boolean DoesUsernameExist(String p_ip, String p_username) {
        Client userx;
        for (int i = 0; i < userlist.size(); i++) {
            userx = (Client) userlist.get(i);
            if (userx.ip.equals(p_ip) && userx.username.equals(p_username)) {
                return true;
            }
        }
        return false;
    }
   public static boolean DoesIPExist(String p_ip) {
        Client userx;
        for (int i = 0; i < userlist.size(); i++) {
            userx = (Client) userlist.get(i);
            if (userx.ip.equals(p_ip)) {
                return true;
            }
        }
        return false;
    }
    
    
    public static boolean CreateUser(String p_ip, String p_name) {
        Client userx = new Client();
        userx.ip = p_ip;
        userx.username = p_name;
        userlist.add(userx);
        return true;
    }

    public static int numofUsers() {
        return userlist.size();
    }

    public static int numofFiles() {
        int numoFiles = 0;
        Client userx;
        for (int i = 0; i < userlist.size(); i++) {
            userx = (Client) userlist.get(i);
            numoFiles += userx.filelist.size();
        }
        return numoFiles;
    }

    public static Client GetUserbyIP(String p_ip) {
        Client userx = null;
        for (int i = 0; i < userlist.size(); i++) {
            userx = (Client) userlist.get(i);
            if (userx.ip.equals(p_ip)) {
                return userx;
            }
        }
        return null;
    }

    public static Object[] SearchFiles(String p_term) {
           List toReturn = Collections.synchronizedList(new ArrayList( ));
         String tempStr;
        Client userx = null;
        for (int i = 0; i < userlist.size(); i++) {
            userx = (Client) userlist.get(i);
              for (int j = 0; j< userx.filelist.size(); j++) {
                 
                  tempStr = (String)userx.filelist.get(j);
                  if(tempStr.contains(p_term))
                      toReturn.add(tempStr +  userx.ip);
              }
        }
        return toReturn.toArray();
    }
    }
