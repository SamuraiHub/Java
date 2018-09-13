package module6;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class AirportFrame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1723146421960003776L;
	private AirportMap papplet;
    private JButton PartofAirports;

    public AirportFrame() {
      
    	HashMap<String, String[]> CountBCont = new HashMap<String, String[]>();
    	
    	String[] Af = new String[54];
    	//AFRICA (54)
    	Af[0] = "Algeria";
    	Af[1] = "Angola";
    	Af[2] = "Benin";
    	Af[3] = "Botswana";
    	Af[4] = "Burkina";
    	Af[5] = "burundi";
    	Af[6] = "cameroon";
    	Af[7] = "cape Verde";
    	Af[8] = "central African Republic";
    	Af[9] = "chad";
    	Af[10] = "comoros";
    	Af[11] = "Republic of the Congo";
        Af[12] = "Democratic Republic of the Congo";
    	Af[13] = "Djibouti";
    	Af[14] = "Egypt";
    	Af[15] = "Equatorial Guinea";
    	Af[16] = "Eritrea";
    	Af[17] = "Ethiopia";
    	Af[18] = "Gabon";
    	Af[19] = "Gambia";
    	Af[20] = "Ghana";
    	Af[21] = "Guinea";
    	Af[22] = "Guinea-Bissau";
    	Af[23] = "Ivory Coast";
    	Af[24] = "Kenya";
    	Af[25] = "Lesotho";
    	Af[26] = "Liberia";
    	Af[27] = "Libya";
    	Af[28] = "Madagascar";
    	Af[29] = "Malawi";
    	Af[30] = "Mali";
    	Af[31] = "Mauritania";
    	Af[32] = "Mauritius";
    	Af[33] = "Morocco";
    	Af[34] = "Mozambique";
    	Af[35] = "Namibia";
    	Af[36] = "Niger";
    	Af[37] = "Nigeria";
    	Af[38] = "Rwanda";
    	Af[39] = "Sao Tome and Principe";
    	Af[40] = "Senegal";
    	Af[41] = "Seychelles";
    	Af[42] = "Sierra Leone";
    	Af[43] = "Somalia";
    	Af[44] = "South Africa";
    	Af[45] = "South Sudan";
    	Af[46] = "Sudan";
    	Af[47] = "Swaziland";
    	Af[48] = "Tanzania";
    	Af[49] = "Togo";
    	Af[50] = "Tunisia";
    	Af[51] = "Uganda";
    	Af[52] = "Zambia";
    	Af[53] = "Zimbabwe";
    	
    	CountBCont.put("Africa", Af);
    	
    	String[] AS = new String[44];
    	//ASIA (44)
    	AS[0] = "Afghanistan";
    	AS[1] = "Bahrain";
    	AS[2] = "Bangladesh";
    	AS[3] = "Bhutan";
    	AS[4] = "Brunei";
    	AS[5] = "Burma (Myanmar)";
    	AS[6] = "Cambodia";
    	AS[7] = "China";
    	AS[8] = "East Timor";
    	AS[9] = "India";
    	AS[10] = "Indonesia";
    	AS[11] = "Iran";
    	AS[12] = "Iraq";
    	AS[13] = "Israel";
    	AS[14] = "Japan";
    	AS[15] = "Jordan";
    	AS[16] = "Kazakhstan";
    	AS[17] = "Korea, North";
    	AS[18] = "Korea, South";
    	AS[19] = "Kuwait";
    	AS[20] = "Kyrgyzstan";
    	AS[21] = "Laos";
    	AS[22] = "Lebanon";
    	AS[23] = "Malaysia";
    	AS[24] = "Maldives";
    	AS[25] = "Mongolia";
    	AS[26] = "Nepal";
    	AS[27] = "Oman";
    	AS[28] = "Pakistan";
    	AS[29] = "Philippines";
    	AS[30] = "Qatar";
    	AS[31] = "Russian Federation";
    	AS[32] = "Saudi Arabia";
    	AS[33] = "Singapore";
    	AS[34] = "Sri Lanka";
    	AS[35] = "Syria";
    	AS[36] = "Tajikistan";
    	AS[37] = "Thailand";
    	AS[38] = "Turkey";
    	AS[39] = "Turkmenistan";
    	AS[40] = "United Arab Emirates";
    	AS[41] = "Uzbekistan";
    	AS[42] = "Vietnam";
    	AS[43] = "Yemen";
    	
    	CountBCont.put("Asia", AS);
    	
    	String[] EU = new String[47];
    	
    	//EUROPE (47)
    	EU[0] = "Albania";
    	EU[1] = "Andorra";
    	EU[2] = "Armenia";
    	EU[3] = "Austria";
    	EU[4] = "Azerbaijan";
    	EU[5] = "Belarus";
    	EU[6] = "Belgium";
    	EU[7] = "Bosnia and Herzegovina";
    	EU[8] = "Bulgaria";
    	EU[9] = "Croatia";
    	EU[10] = "Cyprus";
    	EU[11] = "Czech Republic";
    	EU[12] = "Denmark";
    	EU[13] = "Estonia";
    	EU[14] = "Finland";
    	EU[15] = "France";
    	EU[16] = "Georgia";
    	EU[17] = "Germany";
    	EU[18] = "Greece";
    	EU[19] = "Hungary";
    	EU[20] = "Iceland";
    	EU[21] = "Ireland";
    	EU[22] = "Italy";
    	EU[23] = "Latvia";
    	EU[24] = "Liechtenstein";
    	EU[25] = "Lithuania";
    	EU[26] = "Luxembourg";
    	EU[27] = "Macedonia";
    	EU[28] = "Malta";
    	EU[29] = "Moldova";
    	EU[30] = "Monaco";
    	EU[31] = "Montenegro";
    	EU[32] = "Netherlands";
    	EU[33] = "Norway";
    	EU[34] = "Poland";
    	EU[35] = "Portugal";
    	EU[36] = "Romania";
    	EU[37] = "San Marino";
    	EU[38] = "Serbia";
    	EU[39] = "Slovakia";
    	EU[40] = "Slovenia";
    	EU[41] = "Spain";
    	EU[42] = "Sweden";
    	EU[43] = "Switzerland";
    	EU[44] = "Ukraine";
    	EU[45] = "United Kingdom";
    	EU[46] = "Vatican City";
    	
    	CountBCont.put("Europe", EU);
    	
    	String[] NA = new String[23];
    	
    	//N. AMERICA (23)
    	NA[0] = "Antigua and Barbuda";
    	NA[1] = "Bahamas";
    	NA[2] = "Barbados";
    	NA[3] = "Belize";
    	NA[4] = "Canada";
    	NA[5] = "Costa Rica";
    	NA[6] = "Cuba";
    	NA[7] = "Dominica";
    	NA[8] = "Dominican Republic";
    	NA[9] = "El Salvador";
    	NA[10] = "Grenada";
    	NA[11] = "Guatemala";
    	NA[12] = "Haiti";
    	NA[13] = "Honduras";
    	NA[14] = "Jamaica";
    	NA[15] = "Mexico";
    	NA[16] = "Nicaragua";
    	NA[17] = "Panama";
    	NA[18] = "Saint Kitts and Nevis";
    	NA[19] = "Saint Lucia";
    	NA[20] = "Saint Vincent and the Grenadines";
    	NA[21] = "Trinidad and Tobago";
    	NA[22] = "United States";
    	
    	CountBCont.put("North America", NA);
    	
    	String[] OC = new String[14];
    	
    	//OCEANIA (14)
    	OC[0] = "Australia";
    	OC[1] = "Fiji";
    	OC[2] = "Kiribati";
    	OC[3] = "Marshall Islands";
    	OC[4] = "Micronesia";
    	OC[5] = "Nauru";
    	OC[6] = "New Zealand";
    	OC[7] = "Palau";
    	OC[8] = "Papua New Guinea";
    	OC[9] = "Samoa";
    	OC[10] = "Solomon Islands";
    	OC[11] = "Tonga";
    	OC[12] = "Tuvalu";
    	OC[13] = "Vanuatu";
    	
    	CountBCont.put("Oceania", OC);
    	
    	String[] SA = new String[12];
    	
    	//S. AMERICA (12)
    	SA[0] = "Argentina";
    	SA[1] = "Bolivia";
    	SA[2] = "Brazil";
    	SA[3] = "Chile";
    	SA[4] = "Colombia";
    	SA[5] = "Ecuador";
    	SA[6] = "Guyana";
    	SA[7] = "Paraguay";
    	SA[8] = "Peru";
    	SA[9] = "Suriname";
    	SA[10] = "Uruguay";
    	SA[11] = "Venezuela";
    	
    	CountBCont.put("South America", SA);
    	                
    	
      //setLayout(null);
      this.setLayout(new BorderLayout());
      papplet = new AirportMap();
      // So we can resize the frame to get the sketch canvas size reqd.
      papplet.frame = this;
      
      setResizable(true);
      setTitle("Map of Airports");
      //setLocation(100, 100);
      setPreferredSize(new Dimension(1350,750));
      //papplet.setBounds(200, 0);

      add(papplet, BorderLayout.CENTER);
      papplet.init();
        
      JPanel Key = new JPanel();
      Key.setLayout(new BoxLayout(Key, BoxLayout.Y_AXIS));
      Key.setPreferredSize(new Dimension(450,750));
      add(Key, BorderLayout.WEST);
      //Key.setAlignmentX(1f);
      Key.setBackground(new Color(255, 250, 240));
      //Key.add(Box.createRigidArea(new Dimension(200, 15)));  
      JLabel I = new JLabel("Airport Key");
      Key.add(Box.createRigidArea(new Dimension(0, 50)));  
      Key.add(I);
      JLabel G = new APMarker();
      G.setHorizontalAlignment(JLabel.CENTER);
      G.setText("              Airport Marker");
      
      JLabel M = new JLabel("Move the mouse to Any airport to display airport name and location");
      JLabel N = new JLabel("Click on any airport to display routes to");
      JLabel N1 = new JLabel("other airports and anywhere else to reset");
      
      
      Key.add(Box.createRigidArea(new Dimension(0, 50)));
      G.setAlignmentX(0.7f);
      Key.add(G);
      Key.add(Box.createRigidArea(new Dimension(0, 30)));
      M.setAlignmentX(0.2f);
      Key.add(M);
      Key.add(Box.createRigidArea(new Dimension(0, 15))); 
      N.setAlignmentX(0.2f);
      N1.setAlignmentX(0.2f);
      Key.add(N);
      Key.add(N1);
      Key.add(Box.createVerticalGlue());
      //Key.add(Box.createRigidArea(new Dimension(0, 15)));
      JLabel N2 = new JLabel("Select a continent from below then click on the Sub Airports");
      JLabel N3 = new JLabel("button if you want to display only airports from that continent");
      Key.add(Box.createRigidArea(new Dimension(0, 25)));
      N2.setAlignmentX(0.2f);
      N3.setAlignmentX(0.2f);
      Key.add(N2);
      Key.add(N3);
      Key.add(Box.createRigidArea(new Dimension(275, 25)));
      
      DefaultListModel<String> model = new DefaultListModel<String>();
      JList<String> SABC = new JList<String>(model);
      SABC.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      SABC.setPreferredSize(new Dimension(150,600));
      SABC.setSelectedIndex(0);
      model.addElement("All");
      model.addElement("Africa");
      model.addElement("Asia");
      model.addElement("Europe");
      model.addElement("North America");
      model.addElement("Oceania");
      model.addElement("South America");
      
      PartofAirports = new JButton("Sub Airports");
      //PartofAirports.setLocation(25, 50);
      PartofAirports.setPreferredSize(new Dimension(125,50));
      
      PartofAirports.addActionListener(new ActionListener(){

          @Override
          public void actionPerformed(ActionEvent e) {
              if(SABC.getSelectedIndex() == 0)
            	  papplet.DisplayAll();
              else
              {
            	  papplet.AirportByContinent(CountBCont.get(SABC.getSelectedValue()));
              }
        	  
        	  papplet.redraw();
          };

      });
    
      Key.add(SABC);
      Key.add(Box.createRigidArea(new Dimension(75, 25)));
      Key.add(PartofAirports);
      Key.add(Box.createRigidArea(new Dimension(75, 15)));
      pack();
    }
    
    class APMarker extends JLabel {

        private Image mshi;

        public APMarker() {
            
            loadImage();
            setPreferredSize(new Dimension(500,125));
        }

        private void loadImage() {

            mshi = new ImageIcon("data/airport.jpg").getImage();
            
        }
        

        private void doDrawing(Graphics g) {

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(1,170,201));
            g2d.setStroke(new BasicStroke(1.0f));
            g2d.fillOval(0, 0, 40, 40);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.drawImage(mshi, 10, 10, 20, 20, null);
            //g2d.drawImage()
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            doDrawing(g);
        }
    } 
    
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
              public void run() {
            	  AirportFrame frame = new AirportFrame();
                   frame.setVisible(true);
              }
        });
    }
}
