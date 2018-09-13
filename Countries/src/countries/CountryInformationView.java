/*
 * CountryInfromationView.java
 */

package countries;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import java.io.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.*;





/**
 * The application's main frame.
 */
public class CountryInformationView extends FrameView {

    public CountryInformationView(SingleFrameApplication app) throws SAXException, IOException {
        super(app);

        initComponents();

        BufferedReader CtryNS = new BufferedReader(new FileReader("F:/Stuff/Muaz/GUC/Semi-Structured Data and the Web/countries.xml"));

        String Reader;

        Countries.addItem("All Countries");

        while((Reader = CtryNS.readLine()) != null)
        {
          Reader = Reader.trim();
          if((Reader).startsWith("<name>"))
          {
              Countries.addItem(Reader.substring(6, Reader.length()-7));
          }
        }
         ACXLT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"
                + "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n"
                + "<xsl:output method=\"html\"/>\n"
                + "<xsl:template match=\"/\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "<h3>Countries</h3>\n"
                + "</head>\n"
                + "<body>\n"
                + "<table border=\"1\">\n"
                + "<tr bgcolor=\"#00FFFF\">\n"
                + "<th>Name</th>\n"
                + "<th>Capital City</th>\n"
                + "<th>Population</th>\n"
                + "<th>Population_Growth</th>"
                + "<th>Government</th>\n"
                + "<th>Religions</th>\n"
                + "<th>Cities</th>\n"
                + "</tr>\n"
                + "<xsl:for-each select=\"countries/country\">\n"
                + "<tr>\n"
                + "<td><xsl:value-of select=\"name\"/></td>\n"
                + "<td><xsl:value-of select=\"city[@id = //country/@capital]\"/></td>\n"
                + "<td><xsl:value-of select=\"population\"/></td>\n"
                + "<td><xsl:value-of select=\"population/@growth\"/></td>\n"
                + "<td><xsl:value-of select=\"government\"/></td>\n"
                + " <td>\n"
                + "<xsl:for-each select=\"religions\">\n"
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
                + "<xsl:for-each select=\"city\">\n"
                + "<xsl:choose>\n"
                + "<xsl:when test=\"not(position()=last())\">\n"
                + "<xsl:value-of select=\"text()\"/>,\n"
                + "</xsl:when>\n"
                + "<xsl:otherwise><xsl:value-of select=\"text()\"/></xsl:otherwise>\n"
                + "</xsl:choose>\n"
                + "</xsl:for-each>\n"
                + "</td>\n"
                + "</tr>\n"
                + "</xsl:for-each>"
                + "</table>\n"
                + "</body>\n"
                + "</html>\n"
                + "</xsl:template>\n"
                + "</xsl:stylesheet>";
    }

    public String XSLT(String Country)
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
                + "<th>Capital City</th>\n"
                + "<th>Population</th>\n"
                + "<th>Population_Growth</th>"
                + "<th>Government</th>\n"
                + "<th>Religions</th>\n"
                + "<th>Cities</th>\n"
                + "</tr>\n"
                + "<xsl:variable name=\"c\" select=\"//country[name ='"+Country+"']\"/>\n"
                + "<tr>\n"
                + " <td><xsl:value-of select=\"$c/name\"/></td>\n"
                + "  <td><xsl:value-of select=\"$c/city[@id = $c/@capital]\"/></td>\n"
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

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = CountryInformation.getApplication().getMainFrame();
            aboutBox = new CountryInformationAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        CountryInformation.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Countries = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(countries.CountryInformation.class).getContext().getResourceMap(CountryInformationView.class);
        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        Countries.setModel(new javax.swing.DefaultComboBoxModel());
        Countries.setName("Countries");

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel2))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Countries, 0, 139, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Countries, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(countries.CountryInformation.class).getContext().getActionMap(CountryInformationView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setName("jEditorPane1"); // NOI18N
        jEditorPane1.setPreferredSize(new java.awt.Dimension(1200, 900));
        jScrollPane1.setViewportView(jEditorPane1);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

           JFrame jframe = new JFrame();

        try {
            TransformerFactory tFactory = TransformerFactory.newInstance();
            BufferedReader Ctrys = new BufferedReader(new FileReader("F:/Stuff/Muaz/GUC/Semi-Structured Data and the Web/countries.xml"));
            StringWriter HO = new StringWriter();
            BufferedWriter ho = new BufferedWriter(HO);
            if(((String) Countries.getSelectedItem()).equals("All Countries"))
            {
            BufferedReader ac = new BufferedReader(new StringReader(ACXLT));
            Transformer transformer = tFactory.newTransformer(new StreamSource(ac));
            transformer.transform(new StreamSource(Ctrys), new StreamResult(ho));
            Ctrys.close(); ac.close();  ho.close();
            }
            else
            {
            String c =  (String) Countries.getSelectedItem();
            BufferedReader ac = new BufferedReader(new StringReader(XSLT(c)));
            Transformer transformer = tFactory.newTransformer(new StreamSource(ac));
            transformer.transform(new StreamSource(Ctrys), new StreamResult(ho));
            Ctrys.close(); ac.close();  ho.close();
            }


            jEditorPane1.setText(HO.toString());
            jEditorPane1.setEditable(false);
   
            jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            jframe.add(jScrollPane1, BorderLayout.CENTER);
            jframe.pack();
            jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            jframe.setLocationRelativeTo(CountryInformation.getApplication().getMainFrame());
             
        } catch (Exception ex) {
            Logger.getLogger(CountryInformationView.class.getName()).log(Level.SEVERE, null, ex);
        }
           jframe.setVisible( true );
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               CS = new CountrySearch();
               CS.setLocationRelativeTo(CountryInformation.getApplication().getMainFrame());
               CountryInformation.getApplication().getMainFrame().setEnabled(false);
               CS.setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton2ActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Countries;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

    private final String ACXLT;
    private JDialog aboutBox;
    static CountrySearch CS;
}
