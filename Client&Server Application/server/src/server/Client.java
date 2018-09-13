package server;

import java.util.*;

public class Client {

   public String ip;
    public String username;
    public List filelist = Collections.synchronizedList(new ArrayList( ));

   
    
    public void Addfiles(String[] p_files) {
        for (int i = 0; i < p_files.length; i++) {
            Addfile(p_files[i]);
        }
    }

    public void Addfile(String x) {
        if (!(filelist.contains(x))) {
            filelist.add(x);
        }
    }

    public void Clearfiles() {
        filelist.clear();
    }
}
