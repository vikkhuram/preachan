/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TwitterJFrame.java
 *
 * Created on 07-Jul-2011, 18:17:45
 */
package twitterclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.ws.rs.core.MultivaluedMap;
import twitter.twitteroauth.twitterresponse.StatusType;
import twitter.twitteroauth.twitterresponse.Statuses;
import twitter.twitteroauth.twitterresponse.UserType;

/**
 *
 * @author Kevin Doyle
 */
public class TwitterJFrame extends javax.swing.JFrame {
private DefaultListModel statusesListModel = new DefaultListModel();
    /** Creates new form TwitterJFrame */
    public TwitterJFrame() {
         Timer t = new Timer("Twitter Updater`", false);
         t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run(){
System.out.println("Timer Task is running");
    try {
        client.initOAuth();
        Statuses response = client.getFriendsTimeline(Statuses.class, null, null, null, "10");
        // Clear the list model so it does not replicate the contents from the last run
        statusesListModel.clear();
        // Create a Status Type object for every status in the Status list, and add an element
        // to the list model for every status type object
        for (final StatusType st : response.getStatus()) {
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                public void run() {
                    statusesListModel.addElement(st);
                }
            });
        }
    } catch (UniformInterfaceException ex) {
    System.out.println("Exception when calling getFriendsTimeline = " + ex.getResponse().getEntity(String.class));
    }
            }
        }, 30000, 75000);
        initComponents();
        try {
        initUserInfo();
    } catch (IOException ex) {
        Logger.getLogger(TwitterJFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Icon");
        jLabel1.setMaximumSize(new java.awt.Dimension(48, 48));
        jLabel1.setMinimumSize(new java.awt.Dimension(48, 48));
        jLabel1.setPreferredSize(new java.awt.Dimension(48, 48));

        jTextField1.setText("status");

        jList1.setModel(statusesListModel);
        jList1.setCellRenderer(new Item());
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       String rawStatus = jTextField1.getText().trim();

    client.makeOAuthRequestUnique();
    try {
        String status = URLEncoder.encode(rawStatus, "UTF-8" );
    client.updateStatus(String.class, status, null);
    } catch(UniformInterfaceException ex){
        System.out.println("Exception when calling updateStatus = " + ex.getResponse().getEntity(String.class));
    }catch (UnsupportedEncodingException uee){}
    }//GEN-LAST:event_jButton1ActionPerformed

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
    try {
    Statuses statuses = client.getUserTimeline(Statuses.class, null, null, null, "1");
    st = statuses.getStatus().get(0);
    jTextField1.setText(st.getText().trim());
    } catch (java.lang.IndexOutOfBoundsException IOfBoundsException) {
        Logger.getLogger(TwitterJFrame.class.getName()).log(Level.SEVERE, null, IOfBoundsException);
    }

    //Get a UserType object from the StatusType object, get the URL of that
    //user's icon, and display that icon in the JLabel
    UserType user = st.getUser();
    String iconSrc = user.getProfileImageUrl();
    URL iconUrl = new URL(iconSrc);
    ImageIcon icon = new ImageIcon(iconUrl, user.getScreenName());
    jLabel1.setIcon(icon);
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new TwitterJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
private TwitterClient client; //used to represent an instance of internal class
    static class TwitterClient {

        private WebResource webResource;
        private Client client;
        private static final String BASE_URI = "http://twitter.com";

        public TwitterClient() {
            com.sun.jersey.api.client.config.ClientConfig config = new com.sun.jersey.api.client.config.DefaultClientConfig();
            client = Client.create(config);
            String resourcePath = "statuses";
            webResource = client.resource(BASE_URI).path(resourcePath);
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



        /**
         * @param responseType Class representing the response
         * @param since query parameter
         * @param since_id query parameter
         * @param page query parameter
         * @param count query parameter
         * @return response object (instance of responseType class)
         */
        public <T> T getUserTimeline(Class<T> responseType, String since, String since_id, String page, String count) throws UniformInterfaceException {
            String[] queryParamNames = new String[]{"since", "since_id", "page", "count"};
            String[] queryParamValues = new String[]{since, since_id, page, count};
            return webResource.path("user_timeline.xml").queryParams(getQueryOrFormParams(queryParamNames, queryParamValues)).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
        }

public <T> T getFriendsTimeline(Class<T> responseType, String since, String since_id, String page, String count) throws UniformInterfaceException {
            String[] queryParamNames = new String[]{"since", "since_id", "page", "count"};
            String[] queryParamValues = new String[]{since, since_id, page, count};
            return webResource.path("friends_timeline.xml").queryParams(getQueryOrFormParams(queryParamNames, queryParamValues)).accept(javax.ws.rs.core.MediaType.TEXT_XML).get(responseType);
        }

public <T> T updateStatus(Class<T> responseType, String status, String in_reply_to_status_id) throws UniformInterfaceException {
            String[] formParamNames = new String[]{"status", "in_reply_to_status_id"};
            String[] formParamValues = new String[]{status, in_reply_to_status_id};
            return webResource.path("update.xml").type(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(responseType, getQueryOrFormParams(formParamNames, formParamValues));
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
    }






}
