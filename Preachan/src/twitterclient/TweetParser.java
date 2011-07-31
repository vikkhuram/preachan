/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterclient;

import java.util.StringTokenizer;

/**
 *
 * @author Kevin Doyle
 */
public class TweetParser {
    public TweetParser(){

    }

 public String ParseTweet(String s1){
     StringTokenizer tokenisedTextString = new StringTokenizer(s1, " ");
     String aggregatedTextString="";
 while(tokenisedTextString.hasMoreTokens())
    {
       String key = tokenisedTextString.nextToken();
       if(key.startsWith("#")){
       key="<a href='#"+key+"'"+">"+key+"</a>";
       }
       else if(key.startsWith("@")){
       key="<a href='#"+key+"'"+">"+key+"</a>";
       }
        else if(key.startsWith("http")){
       key="<a href='#"+key+"'"+">"+key+"</a>";
       }
       else if(key.startsWith("https")){
       key="<a href='#"+key+"'"+">"+key+"</a>";
        }
        aggregatedTextString+=key+" ";
    }
 return("<html>"+aggregatedTextString);
 }


}
