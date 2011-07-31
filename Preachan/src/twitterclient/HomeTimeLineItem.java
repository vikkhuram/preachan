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

        jEPTweet.setText(tp.ParseTweet(twitteruname.toUpperCase().toString()+": "+st.getText().toString()));
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jEPTweet = new javax.swing.JEditorPane();

        setBackground(new java.awt.Color(204, 204, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Tahoma", 1, 11));
        setPreferredSize(new java.awt.Dimension(550, 50));

        jScrollPane2.setOpaque(false);
        jScrollPane2.setPreferredSize(new java.awt.Dimension(548, 50));

        jEPTweet.setBackground(new java.awt.Color(204, 102, 255));
        jEPTweet.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jEPTweet.setContentType("text/html");
        jEPTweet.setEditable(false);
        jEPTweet.setPreferredSize(new java.awt.Dimension(546, 20));
        jEPTweet.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEPTweetHyperlinkUpdate(evt);
            }
        });
        jScrollPane2.setViewportView(jEPTweet);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jEPTweetHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEPTweetHyperlinkUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_jEPTweetHyperlinkUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEPTweet;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}