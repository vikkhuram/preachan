/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Item.java
 *
 * Created on 06-Aug-2011, 14:22:25
 */
package twitterclient;

import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.Document;
import twitter.twitteroauth.twitterresponse.StatusType;

/**
 *
 * @author Kevin Doyle
 */
public class Item extends javax.swing.JPanel implements ListCellRenderer{

    /** Creates new form Item */
    public Item() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();

        jEditorPane1.setContentType("text/html");
        jEditorPane1.setEditable(false);
        jEditorPane1.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                jEditorPane1HyperlinkUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jEditorPane1HyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_jEditorPane1HyperlinkUpdate
           HyperlinkEvent.EventType type = evt.getEventType();
final URL url = evt.getURL();

if (type == HyperlinkEvent.EventType.ACTIVATED) {
    // Retain reference to original
Document doc = jEditorPane1.getDocument();
try {
jEditorPane1.setPage(url);
} catch (IOException ioException) {
JOptionPane.showMessageDialog(null, "Error following link",
"Invalid link", JOptionPane.ERROR_MESSAGE);
jEditorPane1.setDocument(doc);
}
}
    }//GEN-LAST:event_jEditorPane1HyperlinkUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
       StatusType st = (StatusType) value;
        //put the username into the text box with the txt message 23/07/11 - KD
        String twitteruname=(st.getUser().getScreenName());

         jEditorPane1.setText(twitteruname.toUpperCase()+": "+st.getText());
return this;

    }
}