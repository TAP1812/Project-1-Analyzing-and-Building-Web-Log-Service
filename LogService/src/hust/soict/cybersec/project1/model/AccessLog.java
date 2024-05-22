package hust.soict.cybersec.project1.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessLog {
    private  String type;
    private  String IP;
    private  String remoteIdent;
    private  String remoteUser;
    private  String timestamp;
    private  String userAgent;
    private  String requestUrl;
    private  int statusCode;
    private  int bytesSent;
    private  String referer;
    private  String logEntry;

    public AccessLog(String logEntry) {
        this.type = "Apache Access Log";
        this.logEntry = logEntry;
        setIP();
        setRemoteIdent();
        setTimestamp();
        setRemoteUser();
        setUserAgent();
        setRequestUrl();
        setStatusCode();
        setBytesSent();
        setReferer();
    }

    
    //::1 - - [20/Feb/2024:16:41:34 +0700] "GET / HTTP/1.1" 302 - "-"
    // "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36"
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Type:" + type + "\n" +
               "IP: " + IP + "\n" + 
               "Timestamp: " + timestamp + "\n" +
               "Remote Log: " + remoteIdent + "\n" +
               "Remote User: " + remoteUser + "\n" + 
               "User Agent: " + userAgent + "\n" + 
               "Request Url: " + requestUrl + "\n" +
               "Status Code: " + statusCode + "\n" +
               "Bytes Sent: " + bytesSent + "\n" + 
               "Referer: " + referer;
    }

    public void setTimestamp(){
        String regex = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            this.timestamp = matcher.group(1);
        }
        else{
            this.timestamp = "-";
        }
    }

    public void setIP() {
        String regex = "^([\\d.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            this.IP = matcher.group(1);
        }
        else{
            this.IP = "-";
        }
    }

    public void setRemoteIdent() {
        this.remoteIdent = "-";
    }

    public void setRemoteUser() {
        this.remoteUser = "-";
    }

    public void setUserAgent() {
        String regex = "^\\S+ \\S+ \\S+ \\[.+?\\] \\\".+?\\\" \\S+ \\S+ \\\".+?\\\" \\\"(.+?)\\\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            this.userAgent = matcher.group(1);
        }
        else{
            this.userAgent = "-";
        }
    }

    public void setRequestUrl() {
        String regex = "\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            this.requestUrl = matcher.group(1);
        }
        else{
            this.requestUrl = "-";
        }
    }

    public void setStatusCode() {
        String regex = "(\\d+)\s(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            this.statusCode = Integer.parseInt(matcher.group(1));
        }
        else{
            this.statusCode = 0;
        }
    }

    public void setBytesSent() {
        String regex = "(\\d+|-)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            this.bytesSent = Integer.parseInt(matcher.group(1));
        }
        else{
            this.bytesSent = 0;
        }
    }

    public void setReferer() {
        String regex = "^.*\\\"(?:GET|POST) .*\\\" \\d{3} \\d+ \\\"([^\\\"]*)\\\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            this.referer = matcher.group(1);
        }
        else{
            this.referer = "-";
        }
    }

    public String getType(){
        return type;
    }
    
    public String getIP() {
        return IP;
    }

    public String getTimestamp(){
        return timestamp;
    }
    
    public String getRemoteIdent() {
        return remoteIdent;
    }

    public String getRemoteUser() {
        return remoteUser;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getBytesSent() {
        return bytesSent;
    }

    public String getReferer() {
        return referer;
    }
    
    public String getLogEntry(){
        return this.logEntry;
    }
    
}
