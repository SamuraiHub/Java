/*
 * ServerAboutBox.java
 */

package server;

import org.jdesktop.application.Action;

public class ServerAboutBox extends javax.swing.JDialog {

    public ServerAboutBox(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    @Action public void closeAboutBox() {
        setVisible(false);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        javax.swing.JLabel vendorLabel = new javax.swing.JLabel();
        javax.swing.JLabel homepageLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel versionLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel CreatorsLabel = new javax.swing.JLabel();
        javax.swing.JLabel appVersionLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appTitleLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appDescLabel1 = new javax.swing.JLabel();
        javax.swing.JLabel appCreatorsLabel = new javax.swing.JLabel();
        appCreatorsLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(server.ServerApp.class).getContext().getResourceMap(ServerAboutBox.class);
        setTitle(resourceMap.getString("title")); // NOI18N
        setModal(true);
        setName("aboutBox"); // NOI18N
        setResizable(false);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(server.ServerApp.class).getContext().getActionMap(ServerAboutBox.class, this);
        closeButton.setAction(actionMap.get("closeAboutBox")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N

        vendorLabel.setFont(vendorLabel.getFont().deriveFont(vendorLabel.getFont().getStyle() | java.awt.Font.BOLD));
        vendorLabel.setName("vendorLabel"); // NOI18N

        homepageLabel.setFont(homepageLabel.getFont().deriveFont(homepageLabel.getFont().getStyle() | java.awt.Font.BOLD));
        homepageLabel.setName("homepageLabel"); // NOI18N

        imageLabel.setName("imageLabel"); // NOI18N

        imageLabel1.setIcon(null);
        imageLabel1.setName("imageLabel1"); // NOI18N

        versionLabel1.setFont(versionLabel1.getFont().deriveFont(versionLabel1.getFont().getStyle() | java.awt.Font.BOLD));
        versionLabel1.setText(resourceMap.getString("versionLabel1.text")); // NOI18N
        versionLabel1.setName("versionLabel1"); // NOI18N

        CreatorsLabel.setFont(CreatorsLabel.getFont().deriveFont(CreatorsLabel.getFont().getStyle() | java.awt.Font.BOLD));
        CreatorsLabel.setText(resourceMap.getString("CreatorsLabel.text")); // NOI18N
        CreatorsLabel.setName("CreatorsLabel"); // NOI18N

        appVersionLabel1.setText(resourceMap.getString("appVersionLabel1.text")); // NOI18N
        appVersionLabel1.setName("appVersionLabel1"); // NOI18N

        appTitleLabel1.setFont(appTitleLabel1.getFont().deriveFont(appTitleLabel1.getFont().getStyle() | java.awt.Font.BOLD, appTitleLabel1.getFont().getSize()+4));
        appTitleLabel1.setText(resourceMap.getString("appTitleLabel1.text")); // NOI18N
        appTitleLabel1.setName("appTitleLabel1"); // NOI18N

        appDescLabel1.setText(resourceMap.getString("appDescLabel1.text")); // NOI18N
        appDescLabel1.setName("appDescLabel1"); // NOI18N

        appCreatorsLabel.setText(resourceMap.getString("appCreatorsLabel.text")); // NOI18N
        appCreatorsLabel.setName("appCreatorsLabel"); // NOI18N

        appCreatorsLabel1.setText(resourceMap.getString("appCreatorsLabel1.text")); // NOI18N
        appCreatorsLabel1.setName("appCreatorsLabel1"); // NOI18N

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(imageLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(imageLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(vendorLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(versionLabel1)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                        .add(appVersionLabel1)
                                        .add(112, 112, 112))
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, appTitleLabel1)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, appDescLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                        .add(CreatorsLabel)
                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                            .add(appCreatorsLabel)
                                            .add(appCreatorsLabel1))
                                        .add(207, 207, 207))))
                            .add(homepageLabel))
                        .add(0, 0, 0))
                    .add(layout.createSequentialGroup()
                        .add(closeButton)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(imageLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 179, Short.MAX_VALUE)
                .add(imageLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(90, 90, 90)
                        .add(vendorLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(homepageLabel))
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(appTitleLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(appDescLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(versionLabel1)
                            .add(appVersionLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createSequentialGroup()
                                .add(appCreatorsLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(appCreatorsLabel1))
                            .add(CreatorsLabel))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 21, Short.MAX_VALUE)
                .add(closeButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel appCreatorsLabel1;
    private javax.swing.JButton closeButton;
    // End of variables declaration//GEN-END:variables
    
}
