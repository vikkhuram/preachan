/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HomeTimeLineItem.java
 *
 * Created on 24-Jul-2011, 13:35:45
 */
package twitterclient;

import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import twitter.twitteroauth.twitterresponse.StatusType;

/**
 *
 * @author Kevin Doyle
 */
public class HomeTimeLineItem extends javax.swing.JPanel implements ListCellRenderer  {

    /** Creates new form HomeTimeLineItem */
    public HomeTimeLineItem() {
        initComponents();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean sel, boolean focus) {
        //create a reference to my tweet parser
        TweetParser tp = new TweetParser();

        StatusType st = (StatusType) value;
        //put the username into the text box with the txt message 23/07/11 - KD
        String twitteruname=(st.getUser().getScreenName());

        //parse tweet for hyperlinks
        jLabel1.setText(tp.ParseTweet(twitteruname.toUpperCase().toString()+": "+st.getText().toString()));

        return this;
}

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(204, 204, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        setPreferredSize(new java.awt.Dimension(550, 50));

        jLabel1.setText("jLabel1");
        add(jLabel1);

        jScrollPane1.setViewportView(jEditorPane1);

        add(jScrollPane1);
    }// </editor-fold>//GEN-END:initComponents

    private void jEPTweetHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEPTweetHyperlinkUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jEPTweetHyperlinkUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
