
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.net.URI;
import java.awt.Desktop;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aljarhi
 */
public class Compile_And_Run_A_Simple_Java_Program {

    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        //compiler.run(null, null, null, "-d /build/classes /src/Hello.java");
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        List<String> optionList = new ArrayList<String>(Arrays.asList("-d","build/classes"));

 Iterable<? extends JavaFileObject> classes = fileManager.getJavaFileObjectsFromFiles(Arrays.asList(new File("src/Hello.java")));


CompilationTask task = compiler.getTask(null, null, null, optionList,null, classes);

task.call();

          Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec("java Hello", null, new File("G:/Stuff/Muaz/Programming/Java/Java Programing Algorithms/build/classes"));
           // Process proc = rt.exec("java G:/Stuff/Muaz/Programming/Java/Hello");
             //ProcessBuilder pb = new ProcessBuilder("java G:/Stuff/Muaz/Programming/Java/Hello");
             //Process proc = pb.start();
            int ec = proc.waitFor();
            System.out.println(ec);
            InputStream in = proc.getInputStream();
        while (true) {
            byte[] buffer = new byte[1024];
                int r = in.read(buffer);
                if (r <= 0) {
                        break;
                }
                System.out.write(buffer, 0, r);
        }
        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
        //desktop.browse(URI.create("http://zaplobe.wordpress.com/"));
    }
}
