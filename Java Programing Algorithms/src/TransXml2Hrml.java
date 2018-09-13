
import java.awt.BorderLayout;
import java.io.*;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Al-Jarhi
 */
public class TransXml2Hrml {

    /**
     * @param args the command line arguments
     *
     */

        public static String XSLT(String Country)
    {
       String XSL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"
                + "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n"
                + "<xsl:output method=\"html\"/>\n"
                + "<xsl:template match=\"/\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "<h3>Country</h3>\n"
                + "</head>\n"
                + "<body>\n"
                + "<table border=\"1\">\n"
                + "<tr bgcolor=\"#00FFFF\">\n"
                + "<th>Name</th>\n"
                + "<th>Population</th>\n"
                + "<th>Population_Growth</th>"
                + "<th>Government</th>\n"
                + "<th>Religions</th>\n"
                + "<th>Cities</th>\n"
                + "</tr>\n"
                + "<xsl:variable name=\"c\" select=\"//country[name ='"+Country+"']\" />\n"
                + "<tr>\n"
                + " <td><xsl:value-of select=\"$c/name\"/></td>\n"
                + "<td><xsl:value-of select=\"$c/population\"/></td>\n"
                + "<td><xsl:value-of select=\"$c/population/@growth\"/></td>\n"
                + "<td><xsl:value-of select=\"$c/government\"/></td>\n"
                + " <td>\n"
                + "<xsl:for-each select=\"$c/religions\">\n"
                + "<xsl:choose>\n"
                + "<xsl:when test=\"not(position()=last())\">\n"
                + "<xsl:value-of select = \"@percentage\"/>% <xsl:value-of select=\"text()\"/>,\n"
                + "</xsl:when>\n"
                + "<xsl:otherwise>\n"
                + "<xsl:value-of select = \"@percentage\"/>% <xsl:value-of select=\"text()\"/>\n"
                + "</xsl:otherwise>\n"
                + "</xsl:choose>\n"
                + "</xsl:for-each>\n"
                + "</td>\n"
                + "<td>\n"
                + "<xsl:for-each select=\"$c/city\">\n"
                + "<xsl:choose>\n"
                + "<xsl:when test=\"not(position()=last())\">\n"
                + "<xsl:value-of select=\"text()\"/>,\n"
                + "</xsl:when>\n"
                + "<xsl:otherwise><xsl:value-of select=\"text()\"/></xsl:otherwise>\n"
                + "</xsl:choose>\n"
                + "</xsl:for-each>\n"
                + "</td>\n"
                + "</tr>\n"
                + "</table>\n"
                + "</body>\n"
                + "</html>\n"
                + "</xsl:template>\n"
                + "</xsl:stylesheet>";
        return XSL;
    }

    public static void main(String[] args) throws Exception{
        // TODO code application logic here



       JFrame jframe = new JFrame();
       jframe.setSize( 500, 1000 );

        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            BufferedReader CtrySl = new BufferedReader(new StringReader(XSLT("United States")));
            Transformer transformer = tFactory.newTransformer(new StreamSource(CtrySl));
            BufferedReader Ctrys = new BufferedReader(new FileReader("F:/Stuff/Muaz/GUC/Semi-Structured Data and the Web/countries.xml"));
            StringWriter HO = new StringWriter();
            BufferedWriter CtryHtml = new BufferedWriter(HO);
            transformer.transform(new StreamSource(Ctrys), new StreamResult(CtryHtml));
            CtrySl.close(); Ctrys.close(); CtryHtml.close();

           BufferedWriter OF = new BufferedWriter(new FileWriter("OutFIle.html"));

            OF.write(HO.toString()); OF.close();

            JEditorPane jEditorPane1 = new JEditorPane();

            jEditorPane1.setContentType("text/html");

            jEditorPane1.setText(HO.toString());

            jEditorPane1.setEditable( false );
            JScrollPane jScrollPane1 =
                        new JScrollPane( jEditorPane1,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );

            jframe.add(jScrollPane1, BorderLayout.CENTER);
            jframe.pack();
            jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
        jframe.setVisible( true );
        
        } catch (Exception ex) {
        }
       
    }

}
