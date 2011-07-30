package twitterclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.ws.rs.core.MultivaluedMap;
import twitter.twitteroauth.twitterresponse.StatusType;
import twitter.twitteroauth.twitterresponse.Statuses;
import twitter.twitteroauth.twitterresponse.UserType;

/**
 * @author Kevin Doyle
 */
public class TwitterJFrame extends javax.swing.JFrame {

     private DefaultListModel homeTimeLineStatusesListModel = new DefaultListModel();
     private DefaultListModel MentionsStatusesDeaultListModel = new DefaultListModel();
      private DefaultListModel DirectMessagesDeaultListModel = new DefaultListModel();
        private DefaultListModel ListsDeaultListModel = new DefaultListModel();
             /** Creates new form TwitterJFrame */
    public TwitterJFrame() {
Timer t = new Timer("Twitter Updater`", false);
     t.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run(){

                System.out.println("Timer Task is running");
    try {//this gets the usertimeline
        //Call getUserTimeline, get a list of statuses, pass the most recent
    //status as a StatusType object, and display the text of that object
    //in the JTextField
        //Bring st back into scope at method level KD 15/07/11
    StatusType stup = new StatusType();
    Statuses statuses = client.getUserTimeline(Statuses.class, null, null, null, "1");
    stup = statuses.getStatus().get(0);
    jTACurrentStatus.setText(stup.getText().trim());
        client.initOAuth();
        Statuses response = client.getFriendsTimeline(Statuses.class, null, null, null, "10");
        // Clear the list model so it does not replicate the contents from the last run
        homeTimeLineStatusesListModel.clear();
        // Create a Status Type object for every status in the Status list, and add an element
        // to the list model for every status type object
        for (final StatusType st : response.getStatus()) {
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                public void run() {
                    homeTimeLineStatusesListModel.addElement(st);
                }
            });
        }
    }
    catch (UniformInterfaceException ex) {
    System.out.println("Exception when calling getFriendsTimeline = " + ex.getResponse().getEntity(String.class));
    }

     try {//this gets the mentions timeline
        //Call getUserTimeline, get a list of statuses, pass the most recent
    //status as a StatusType object, and display the text of that object
    //in the JTextField
        //Bring st back into scope at method level KD 15/07/11

  //  Statuses statuses = client.getUserTimeline(Statuses.class, null, null, null, "1");
        client.initOAuth();
     Statuses response = client.getMentions(Statuses.class, null, null, null, "10");

        MentionsStatusesDeaultListModel.clear();
        // Create a Status Type object for every status in the Status list, and add an element
        // to the list model for every status type object
        for (final StatusType st : response.getStatus()) {
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                public void run() {
                    MentionsStatusesDeaultListModel.addElement(st);
                }
            });
        }
    }
    catch (UniformInterfaceException ex) {
    System.out.println("Exception when calling getFriendsTimeline = " + ex.getResponse().getEntity(String.class));
    }
/**
      try {//this gets the direct messages
        //Call getUserTimeline, get a list of statuses, pass the most recent
    //status as a StatusType object, and display the text of that object
    //in the JTextField
        //Bring st back into scope at method level KD 15/07/11

  //  Statuses statuses = client.getUserTimeline(Statuses.class, null, null, null, "1");
        client.initOAuth();
   //    Statuses response = client.getDirectMessagesToMe(Statuses.class, null, null, "20");

        DirectMessagesDeaultListModel.clear();
        // Create a Status Type object for every status in the Status list, and add an element
        // to the list model for every status type object
     //   for (final StatusType st : response.getStatus()) {
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                public void run() {
                    DirectMessagesDeaultListModel.addElement(st);
                }
            });
        }
    }
    catch (UniformInterfaceException ex) {
    System.out.println("Exception when calling getFriendsTimeline = " + ex.getResponse().getEntity(String.class));
    }
**/
      try {//this gets the Lists
          //  Statuses statuses = client.getUserTimeline(Statuses.class, null, null, null, "1");
        client.initOAuth();
       Statuses response = client.getAllLists(Statuses.class, null, null, null);


       ListsDeaultListModel .clear();
        // Create a Status Type object for every status in the Status list, and add an element
        // to the list model for every status type object
        for (final StatusType st : response.getStatus()) {
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                public void run() {
                    ListsDeaultListModel .addElement(st);
                }
            });
        }
    }
    catch (UniformInterfaceException ex) {
    System.out.println("Exception when calling getFriendsTimeline = " + ex.getResponse().getEntity(String.class));
    }

            }
        }, 40000, 75000);
        initComponents();
        try {
        initUserInfo();
    } catch (IOException ex) {
        Logger.getLogger(TwitterJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    //removed main function from here.
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TwitterJFrame().setVisible(true); } });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPPhotoHandle = new javax.swing.JPanel();
        jLUserIcon = new javax.swing.JLabel();
        jLTwitterHandle = new javax.swing.JLabel();
        jLCurrentStatus = new javax.swing.JLabel();
        jBTLogin = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTACurrentStatus = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jToggleBTimeLine = new javax.swing.JToggleButton();
        jToggleBMentions = new javax.swing.JToggleButton();
        jToggleBMessages = new javax.swing.JToggleButton();
        jToggleBLists = new javax.swing.JToggleButton();
        jToggleBProfiles = new javax.swing.JToggleButton();
        jToggleBSearch = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jBPublishTweet = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAStatusUpDate = new javax.swing.JTextArea();
        jLTweet = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jSPLists = new javax.swing.JScrollPane();
        jLLists = new javax.swing.JList();
        jSPDirectMessages = new javax.swing.JScrollPane();
        jLDirectMessage = new javax.swing.JList();
        jSPHomeTimeLine = new javax.swing.JScrollPane();
        jLHomeTimeLine = new javax.swing.JList();
        jSPMentions = new javax.swing.JScrollPane();
        jLMentions = new javax.swing.JList();
        jPLoginPane = new javax.swing.JPanel();
        jTFToken = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPProfile = new javax.swing.JPanel();
        jPSearch = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("___ Project Preáchán___");

        jPPhotoHandle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLUserIcon.setText("icon");
        jLUserIcon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));
        jLUserIcon.setMaximumSize(new java.awt.Dimension(48, 48));
        jLUserIcon.setMinimumSize(new java.awt.Dimension(48, 48));
        jLUserIcon.setPreferredSize(new java.awt.Dimension(48, 48));

        jLTwitterHandle.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLTwitterHandle.setForeground(new java.awt.Color(0, 0, 255));
        jLTwitterHandle.setText("Twitter Handle");

        jLCurrentStatus.setText("Status:");

        jBTLogin.setText("Twitter Login");
        jBTLogin.setEnabled(false);
        jBTLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTLoginActionPerformed(evt);
            }
        });

        jTACurrentStatus.setBackground(new java.awt.Color(204, 204, 204));
        jTACurrentStatus.setColumns(20);
        jTACurrentStatus.setEditable(false);
        jTACurrentStatus.setFont(new java.awt.Font("Monospaced", 1, 13));
        jTACurrentStatus.setForeground(new java.awt.Color(255, 255, 255));
        jTACurrentStatus.setLineWrap(true);
        jTACurrentStatus.setRows(2);
        jTACurrentStatus.setAutoscrolls(false);
        jTACurrentStatus.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jTACurrentStatus.setFocusable(false);
        jScrollPane3.setViewportView(jTACurrentStatus);

        javax.swing.GroupLayout jPPhotoHandleLayout = new javax.swing.GroupLayout(jPPhotoHandle);
        jPPhotoHandle.setLayout(jPPhotoHandleLayout);
        jPPhotoHandleLayout.setHorizontalGroup(
            jPPhotoHandleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPhotoHandleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPPhotoHandleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPPhotoHandleLayout.createSequentialGroup()
                        .addComponent(jLUserIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPPhotoHandleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLTwitterHandle)
                            .addGroup(jPPhotoHandleLayout.createSequentialGroup()
                                .addComponent(jLCurrentStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jBTLogin))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPPhotoHandleLayout.setVerticalGroup(
            jPPhotoHandleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPPhotoHandleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPPhotoHandleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPPhotoHandleLayout.createSequentialGroup()
                        .addComponent(jLTwitterHandle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPPhotoHandleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLCurrentStatus)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLUserIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jBTLogin)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setMaximumSize(new java.awt.Dimension(3276, 32767));

        jToggleBTimeLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitterclient/icon_time_line.gif"))); // NOI18N
        jToggleBTimeLine.setText("Time Line");
        jToggleBTimeLine.setSelected(true);
        jToggleBTimeLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBTimeLineActionPerformed(evt);
            }
        });

        jToggleBMentions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitterclient/icon_mentions.gif"))); // NOI18N
        jToggleBMentions.setText("Mentions");
        jToggleBMentions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBMentionsActionPerformed(evt);
            }
        });

        jToggleBMessages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitterclient/icon_messages.gif"))); // NOI18N
        jToggleBMessages.setText("Messages");
        jToggleBMessages.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBMessagesActionPerformed(evt);
            }
        });

        jToggleBLists.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitterclient/icon_lists.gif"))); // NOI18N
        jToggleBLists.setText("Lists");
        jToggleBLists.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBListsActionPerformed(evt);
            }
        });

        jToggleBProfiles.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitterclient/icon_profile.gif"))); // NOI18N
        jToggleBProfiles.setText("Profile");
        jToggleBProfiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBProfilesActionPerformed(evt);
            }
        });

        jToggleBSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitterclient/icon_search.gif"))); // NOI18N
        jToggleBSearch.setText("Search");
        jToggleBSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleBSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToggleBTimeLine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jToggleBMentions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jToggleBLists, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jToggleBSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jToggleBProfiles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                    .addComponent(jToggleBMessages, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleBTimeLine)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleBMentions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleBMessages)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleBLists)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleBProfiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleBSearch)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jBPublishTweet.setText("Publish Your Tweet!");
        jBPublishTweet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBPublishTweetActionPerformed(evt);
            }
        });

        jTAStatusUpDate.setColumns(20);
        jTAStatusUpDate.setLineWrap(true);
        jTAStatusUpDate.setRows(2);
        jTAStatusUpDate.setAutoscrolls(false);
        jScrollPane2.setViewportView(jTAStatusUpDate);

        jLTweet.setText("Tweet:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBPublishTweet)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLTweet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLTweet)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBPublishTweet)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        String path = "C:/Documents and Settings/Kevin Doyle/My Documents/NetBeansProjects/TwitterUIDesign/src/UI/logo.gif";
        jPanel4.add(new JLabel(new ImageIcon(path)));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/twitterclient/logo.gif"))); // NOI18N

        jLabel1.setText("Kevin Doyle © 2011");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1)))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jLayeredPane1.setBackground(new java.awt.Color(204, 0, 204));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("layer pane"));

        jLLists.setModel(ListsDeaultListModel);
        jLLists.setCellRenderer(new twitterclient.ListsItem());
        jSPLists.setViewportView(jLLists);

        jSPLists.setBounds(10, 20, 570, 360);
        jLayeredPane1.add(jSPLists, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLDirectMessage.setModel(DirectMessagesDeaultListModel);
        jLDirectMessage.setCellRenderer(new twitterclient.DirectMessageItem());
        jSPDirectMessages.setViewportView(jLDirectMessage);

        jSPDirectMessages.setBounds(10, 20, 570, 360);
        jLayeredPane1.add(jSPDirectMessages, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jSPHomeTimeLine.setForeground(new java.awt.Color(204, 204, 255));
        jSPHomeTimeLine.setFont(new java.awt.Font("Tahoma", 1, 11));
        jSPHomeTimeLine.setPreferredSize(new java.awt.Dimension(570, 360));

        jLHomeTimeLine.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLHomeTimeLine.setForeground(new java.awt.Color(255, 255, 255));
        jLHomeTimeLine.setModel(homeTimeLineStatusesListModel);
        jLHomeTimeLine.setCellRenderer(new twitterclient.HomeTimeLineItem());
        jLHomeTimeLine.setVisibleRowCount(20);
        jSPHomeTimeLine.setViewportView(jLHomeTimeLine);

        jSPHomeTimeLine.setBounds(10, 20, 570, 360);
        jLayeredPane1.add(jSPHomeTimeLine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLMentions.setModel(MentionsStatusesDeaultListModel);
        jLMentions.setCellRenderer(new twitterclient.MentionsItem());
        jSPMentions.setViewportView(jLMentions);

        jSPMentions.setBounds(10, 20, 570, 360);
        jLayeredPane1.add(jSPMentions, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPLoginPane.setBackground(new java.awt.Color(204, 0, 204));
        jPLoginPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTFToken.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTokenActionPerformed(evt);
            }
        });
        jTFToken.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTFTokenKeyPressed(evt);
            }
        });

        jLabel4.setText("Enter Token Number");

        javax.swing.GroupLayout jPLoginPaneLayout = new javax.swing.GroupLayout(jPLoginPane);
        jPLoginPane.setLayout(jPLoginPaneLayout);
        jPLoginPaneLayout.setHorizontalGroup(
            jPLoginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPLoginPaneLayout.createSequentialGroup()
                .addGap(572, 572, 572)
                .addGroup(jPLoginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jTFToken, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPLoginPaneLayout.setVerticalGroup(
            jPLoginPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPLoginPaneLayout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTFToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205))
        );

        jPLoginPane.setBounds(10, 20, 570, 360);
        jLayeredPane1.add(jPLoginPane, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPProfile.setBackground(new java.awt.Color(51, 153, 255));
        jPProfile.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPProfileLayout = new javax.swing.GroupLayout(jPProfile);
        jPProfile.setLayout(jPProfileLayout);
        jPProfileLayout.setHorizontalGroup(
            jPProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
        jPProfileLayout.setVerticalGroup(
            jPProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        jPProfile.setBounds(10, 20, 570, 360);
        jLayeredPane1.add(jPProfile, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPSearch.setBackground(new java.awt.Color(51, 255, 204));
        jPSearch.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPSearchLayout = new javax.swing.GroupLayout(jPSearch);
        jPSearch.setLayout(jPSearchLayout);
        jPSearchLayout.setHorizontalGroup(
            jPSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );
        jPSearchLayout.setVerticalGroup(
            jPSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 356, Short.MAX_VALUE)
        );

        jPSearch.setBounds(10, 20, 570, 360);
        jLayeredPane1.add(jPSearch, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPPhotoHandle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPPhotoHandle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(84, 84, 84))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jLayeredPane1.getAccessibleContext().setAccessibleName("layered pane");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleBTimeLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleBTimeLineActionPerformed
        // TODO add your handling code here:
        jToggleBLists.setSelected(false);
        jToggleBMentions.setSelected(false);
        jToggleBMessages.setSelected(false);
        jToggleBProfiles.setSelected(false);
        jToggleBSearch.setSelected(false);
        jLayeredPane1.moveToFront(jSPHomeTimeLine);
    }//GEN-LAST:event_jToggleBTimeLineActionPerformed

    private void jToggleBMentionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleBMentionsActionPerformed
        // TODO add your handling code here:
         jToggleBLists.setSelected(false);
        jToggleBTimeLine.setSelected(false);
        jToggleBMessages.setSelected(false);
        jToggleBProfiles.setSelected(false);
        jToggleBSearch.setSelected(false);
        jLayeredPane1.moveToFront(jSPMentions);

    }//GEN-LAST:event_jToggleBMentionsActionPerformed

    private void jToggleBMessagesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleBMessagesActionPerformed
        // TODO add your handling code here:
         jToggleBLists.setSelected(false);
        jToggleBMentions.setSelected(false);
        jToggleBTimeLine.setSelected(false);
        jToggleBProfiles.setSelected(false);
        jToggleBSearch.setSelected(false);
        jLayeredPane1.moveToFront(jSPDirectMessages);

    }//GEN-LAST:event_jToggleBMessagesActionPerformed

    private void jToggleBListsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleBListsActionPerformed
        // TODO add your handling code here:
         jToggleBTimeLine.setSelected(false);
        jToggleBMentions.setSelected(false);
        jToggleBMessages.setSelected(false);
        jToggleBProfiles.setSelected(false);
        jToggleBSearch.setSelected(false);
       jLayeredPane1.moveToFront(jSPLists);
    }//GEN-LAST:event_jToggleBListsActionPerformed

    private void jToggleBProfilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleBProfilesActionPerformed
        // TODO add your handling code here:
         jToggleBLists.setSelected(false);
        jToggleBMentions.setSelected(false);
        jToggleBMessages.setSelected(false);
        jToggleBTimeLine.setSelected(false);
        jToggleBSearch.setSelected(false);
        jLayeredPane1.moveToFront(jPProfile);
    }//GEN-LAST:event_jToggleBProfilesActionPerformed

    private void jToggleBSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleBSearchActionPerformed
        // TODO add your handling code here:
         jToggleBLists.setSelected(false);
        jToggleBMentions.setSelected(false);
        jToggleBMessages.setSelected(false);
        jToggleBProfiles.setSelected(false);
        jToggleBTimeLine.setSelected(false);
        jLayeredPane1.moveToFront(jPSearch);
    }//GEN-LAST:event_jToggleBSearchActionPerformed

    private void jBTLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBTLoginActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jBTLoginActionPerformed

    private void jBPublishTweetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBPublishTweetActionPerformed
       String rawStatus = jTAStatusUpDate.getText().trim();

    client.makeOAuthRequestUnique();
    try {

        String status = URLEncoder.encode(rawStatus, "UTF-8" );
    client.updateStatus(String.class, status, null);
    } catch(UniformInterfaceException ex){
        System.out.println("Exception when calling updateStatus = " + ex.getResponse().getEntity(String.class));
    }catch (UnsupportedEncodingException uee){}

jTAStatusUpDate.setText(" ");
    }//GEN-LAST:event_jBPublishTweetActionPerformed

    private void jTFTokenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTFTokenKeyPressed
        // TODO add your handling code here:
        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
         jLayeredPane1.moveToBack(jPLoginPane);
        }

    }//GEN-LAST:event_jTFTokenKeyPressed

    private void jTFTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTokenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTokenActionPerformed

    private void initUserInfo() throws MalformedURLException, IOException {

    //Create an instance of the internal service class
    client = new TwitterClient();
    //Bring st back into scope at method level KD 15/07/11
    StatusType st = new StatusType();



    //Log in, get tokens, and append the tokens to the consumer and secret
    //keys

    client.login();

    client.initOAuth();


    //Call getUserTimeline, get a list of statuses, pass the most recent
    //status as a StatusType object, and display the text of that object
    //in the JTextField
    Statuses statuses = client.getUserTimeline(Statuses.class, null, null, null, "1");
    st = statuses.getStatus().get(0);
    //try decoding the text before showing it in the ui 22/07/11 - KD
    jTACurrentStatus.setText(st.getText().trim());



    //Get a UserType object from the StatusType object, get the URL of that
    //user's icon, and display that icon in the JLabel
    UserType user = st.getUser();
    String iconSrc = user.getProfileImageUrl();
    URL iconUrl = new URL(iconSrc);
    ImageIcon icon = new ImageIcon(iconUrl, user.getScreenName());
    jLUserIcon.setIcon(icon);
    jLTwitterHandle.setText(user.getScreenName());
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBPublishTweet;
    private javax.swing.JButton jBTLogin;
    private javax.swing.JLabel jLCurrentStatus;
    private javax.swing.JList jLDirectMessage;
    private javax.swing.JList jLHomeTimeLine;
    private javax.swing.JList jLLists;
    private javax.swing.JList jLMentions;
    private javax.swing.JLabel jLTweet;
    private javax.swing.JLabel jLTwitterHandle;
    private javax.swing.JLabel jLUserIcon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPLoginPane;
    private javax.swing.JPanel jPPhotoHandle;
    private javax.swing.JPanel jPProfile;
    private javax.swing.JPanel jPSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jSPDirectMessages;
    private javax.swing.JScrollPane jSPHomeTimeLine;
    private javax.swing.JScrollPane jSPLists;
    private javax.swing.JScrollPane jSPMentions;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTACurrentStatus;
    private javax.swing.JTextArea jTAStatusUpDate;
    private javax.swing.JTextField jTFToken;
    private javax.swing.JToggleButton jToggleBLists;
    private javax.swing.JToggleButton jToggleBMentions;
    private javax.swing.JToggleButton jToggleBMessages;
    private javax.swing.JToggleButton jToggleBProfiles;
    private javax.swing.JToggleButton jToggleBSearch;
    private javax.swing.JToggleButton jToggleBTimeLine;
    // End of variables declaration//GEN-END:variables
private TwitterClient client;


    static class TwitterClient {

        private WebResource webResource;
        private Client client;
        private static final String BASE_URI = "https://twitter.com";

        public TwitterClient() {
            com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig(); // SSL configuration
            config.getProperties().put(com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new com.sun.jersey.client.urlconnection.HTTPSProperties(getHostnameVerifier(), getSSLContext()));
            client = Client.create(config);
            String resourcePath = "statuses";
            //change this as not all resources are a status
             //webResource = client.resource(BASE_URI).path(resourcePath);
            webResource = client.resource(BASE_URI);
        }
        private static final String OAUTH_BASE_URL = "http://twitter.com/oauth";
        /**
         * Please, specify the consumer_key string obtained from service API pages
         */
        private static final String CONSUMER_KEY = "uDgRAVEtjxsBtR52PqAA";
        /**
         * Please, specify the consumer_secret string obtained from service API pages
         */
        private static final String CONSUMER_SECRET = "85zAfuclWgW7cZTmgqVUq2JEicD6kV6sCOH6SIIZU";
        private OAuthParameters oauth_params;
        private OAuthSecrets oauth_secrets;
        private OAuthClientFilter oauth_filter;
        private String oauth_access_token;
        private String oauth_access_token_secret;

        //public void setResourcePath(String format) {
          //  String resourcePath = java.text.MessageFormat.format("statuses/user_timeline.{0}", new Object[]{format});
            //webResource = client.resource(BASE_URI).path(resourcePath);
        //}

        /**
         * @param responseType Class representing the response
         * @param since query parameter
         * @param since_id query parameter
         * @param page query parameter
         * @param count query parameter
         * @return response object (instance of responseType class)
         */
        //This function returns my tweets
        public <T> T getUserTimeline(Class<T> responseType, String since, String since_id, String page, String count) throws UniformInterfaceException {
            String[] queryParamNames = new String[]{"since", "since_id", "page", "count"};
            String[] queryParamValues = new String[]{since, since_id, page, "count"};
            return webResource.path("/statuses/user_timeline.xml").queryParams(getQueryOrFormParams(queryParamNames, queryParamValues)).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
        }
        //this function returns tweets that mention me
         public <T> T getMentions(Class<T> responseType, String since, String since_id, String page, String count) throws UniformInterfaceException {
            String[] queryParamNames = new String[]{"since", "since_id", "page", "count"};
            String[] queryParamValues = new String[]{since, since_id, page, "count"};
            return webResource.path("/statuses/mentions.xml").queryParams(getQueryOrFormParams(queryParamNames, queryParamValues)).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
        }

        // public <T> T getDirectMessagesToMe(Class<T> responseType, String since, String since_id, String page) throws UniformInterfaceException {
          //  String[] queryParamNames = new String[]{"since", "since_id", "page"};
           // String[] queryParamValues = new String[]{since, since_id, page};
            //return webResource.path("direct_messages.xml").queryParams(getQueryOrFormParams(queryParamNames, queryParamValues)).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
       // }

         public <T> T getAllLists(Class<T> responseType, String since, String since_id, String page) throws UniformInterfaceException {
            String[] queryParamNames = new String[]{"since", "since_id", "page"};
            String[] queryParamValues = new String[]{since, since_id, page};
            return webResource.path("/lists/all.xml").queryParams(getQueryOrFormParams(queryParamNames, queryParamValues)).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
        }

            //this function returns the tweets of people I follow
            public <T> T getFriendsTimeline(Class<T> responseType, String since, String since_id, String page, String count) throws UniformInterfaceException {
            String[] queryParamNames = new String[]{"since", "since_id", "page", "count"};
            String[] queryParamValues = new String[]{since, since_id, page, count};
            //updated endpoint as per twitter API change 19/07/11 -KD
            return webResource.path("/statuses/home_timeline.xml").queryParams(getQueryOrFormParams(queryParamNames, queryParamValues)).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
        }
            //this function allows me to update my status
            public <T> T updateStatus(Class<T> responseType, String status, String in_reply_to_status_id) throws UniformInterfaceException {
            String[] formParamNames = new String[]{"status", "in_reply_to_status_id"};
            String[] formParamValues = new String[]{status, in_reply_to_status_id};
            return webResource.path("/statuses/update.xml").type(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(responseType, getQueryOrFormParams(formParamNames, formParamValues));
        }
        private MultivaluedMap getQueryOrFormParams(String[] paramNames, String[] paramValues) {
            MultivaluedMap<String, String> qParams = new com.sun.jersey.api.representation.Form();
            for (int i = 0; i < paramNames.length; i++) {
                if (paramValues[i] != null) {
                    qParams.add(paramNames[i], paramValues[i]);
                }
            }
            return qParams;
        }

        public void close() {
            client.destroy();
        }

        /**
         * You need to call this method at the beginning to authorize the application to work with user data.
         * The method obtains the OAuth access token string, that is appended to each API request later.
         */
        public void login() throws IOException, UniformInterfaceException {
            Form requestTokenResponse = getOAuthRequestToken();
            String oauth_verifier = authorizeConsumer(requestTokenResponse);
            Form accessTokenResponse = getOAuthAccessToken(requestTokenResponse, oauth_verifier);
            oauth_access_token_secret = accessTokenResponse.getFirst("oauth_token_secret");
            oauth_access_token = accessTokenResponse.getFirst("oauth_token");
        }

        private Form getOAuthRequestToken() throws UniformInterfaceException {
            WebResource resource = client.resource(OAUTH_BASE_URL).path("request_token");
            oauth_params = new OAuthParameters().consumerKey(CONSUMER_KEY).signatureMethod(com.sun.jersey.oauth.signature.HMAC_SHA1.NAME).version("1.0").nonce().timestamp();
            oauth_secrets = new OAuthSecrets().consumerSecret(CONSUMER_SECRET);
            oauth_filter = new OAuthClientFilter(client.getProviders(), oauth_params, oauth_secrets);
            resource.addFilter(oauth_filter);
            return resource.get(Form.class);
        }

        private Form getOAuthAccessToken(Form requestTokenResponse, String oauth_verifier) throws UniformInterfaceException {
            WebResource resource = client.resource(OAUTH_BASE_URL).path("access_token");
            oauth_params.token(requestTokenResponse.getFirst("oauth_token")).signatureMethod(com.sun.jersey.oauth.signature.HMAC_SHA1.NAME).version("1.0").nonce().timestamp().verifier(oauth_verifier);
            oauth_secrets.tokenSecret(requestTokenResponse.getFirst("oauth_token_secret"));
            resource.addFilter(oauth_filter);
            return resource.get(Form.class);
        }

        /**
         * The method sets the OAuth parameters for webResource.
         * The method needs to be called after login() method, or when the webResource path is changed
         */
        public void initOAuth() {
            oauth_params = new OAuthParameters().consumerKey(CONSUMER_KEY).token(oauth_access_token).signatureMethod(com.sun.jersey.oauth.signature.HMAC_SHA1.NAME).version("1.0").nonce().timestamp();
            oauth_secrets = new OAuthSecrets().consumerSecret(CONSUMER_SECRET).tokenSecret(oauth_access_token_secret);
            oauth_filter = new OAuthClientFilter(client.getProviders(), oauth_params, oauth_secrets);
            webResource.addFilter(oauth_filter);
        }

        /**
         * The method increases OAuth nonce and timestamp parameters to make each request unique.
         * The method should be called when repetitive requests are sent to service API provider:
         * <pre>
         * client.initOauth();
         * client.getXXX(...);
         * client.makeOAuthRequestUnique();
         * client.getYYY(...);
         * client.makeOAuthRequestUnique();
         * client.getZZZ(...);
         * </pre>
         */
        public void makeOAuthRequestUnique() {
            if (oauth_params != null) {
                oauth_params.nonce().timestamp();
            }
        }

        private java.lang.String authorizeConsumer(Form requestTokenResponse) throws IOException {

            try {
                java.awt.Desktop.getDesktop().browse(new java.net.URI("http://twitter.com/oauth/authorize?oauth_token=" + requestTokenResponse.getFirst("oauth_token")));
            } catch (java.net.URISyntaxException ex) {
            }
           java.io.BufferedReader br = null;
            String oauth_verifier = null;
            try {
                br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
                System.out.print("Type oauth_verifier string (taken from callback page url):");
                oauth_verifier = br.readLine();
            } finally {
                br.close();
            }
            return oauth_verifier;
        }

        private HostnameVerifier getHostnameVerifier() {
            return new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                    return true;
                }
            };
        }

        private SSLContext getSSLContext() {
            javax.net.ssl.TrustManager x509 = new javax.net.ssl.X509TrustManager() {

                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                    return;
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws java.security.cert.CertificateException {
                    return;
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            SSLContext ctx = null;
            try {
                ctx = SSLContext.getInstance("SSL");
                ctx.init(null, new javax.net.ssl.TrustManager[]{x509}, null);
            } catch (java.security.GeneralSecurityException ex) {
            }
            return ctx;
        }
    }
}