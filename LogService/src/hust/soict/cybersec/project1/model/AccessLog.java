package hust.soict.cybersec.project1.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccessLog {
	
    private  StringProperty type;
    private  StringProperty IP;
    private  StringProperty remoteIdent;
    private  StringProperty remoteUser;
    private  StringProperty timestamp;
    private  StringProperty userAgent;
    private  StringProperty requestUrl;
    private  IntegerProperty statusCode;
    private  IntegerProperty bytesSent;
    private  StringProperty referer;
    private  StringProperty logEntry;

    public AccessLog(String logEntry) {
    	
    	this.type = new SimpleStringProperty("Apache Access Log");
    	this.logEntry = new SimpleStringProperty(logEntry);
        this.IP = new SimpleStringProperty();
        this.remoteIdent = new SimpleStringProperty();
        this.remoteUser = new SimpleStringProperty();
        this.timestamp = new SimpleStringProperty();
        this.userAgent = new SimpleStringProperty();
        this.requestUrl = new SimpleStringProperty();
        this.statusCode = new SimpleIntegerProperty();
        this.bytesSent = new SimpleIntegerProperty();
        this.referer = new SimpleStringProperty();
        
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
        return "Type:" + type.get() + "\n" +
               "IP: " + IP + "\n" + 
               "Timestamp: " + timestamp.get() + "\n" +
               "Remote Log: " + remoteIdent.get() + "\n" +
               "Remote User: " + remoteUser.get() + "\n" + 
               "User Agent: " + userAgent.get() + "\n" + 
               "Request Url: " + requestUrl.get() + "\n" +
               "Status Code: " + statusCode.get() + "\n" +
               "Bytes Sent: " + bytesSent.get() + "\n" + 
               "Referer: " + referer.get();
    }
    
    
    //setter
    private void setTimestamp(){
        String regex = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry.get());
        if (matcher.find()){
            this.timestamp.set(matcher.group(1));
        }
        else{
            this.timestamp.set("-");
        }
    }

    private void setIP() {
        String regex = "^([\\d.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry.get());
        if (matcher.find()){
            this.IP.set(matcher.group(1));
        }
        else{
            this.IP.set("-");
        }
    }

    private void setRemoteIdent() {
        this.remoteIdent.set("-");
    }

    private void setRemoteUser() {
        this.remoteUser.set("-");
    }

    private void setUserAgent() {
        String regex = "^\\S+ \\S+ \\S+ \\[.+?\\] \\\".+?\\\" \\S+ \\S+ \\\".+?\\\" \\\"(.+?)\\\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry.get());
        if (matcher.find()){
            this.userAgent.set(matcher.group(1));
        }
        else{
            this.userAgent.set("-");
        }
    }

    private void setRequestUrl() {
        String regex = "\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry.get());
        if (matcher.find()){
            this.requestUrl.set(matcher.group(1));
        }
        else{
            this.requestUrl.set("-");
        }
    }

    private void setStatusCode() {
        String regex = "(\\d+)\s(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry.get());
        if (matcher.find()){
            this.statusCode.set(Integer.parseInt(matcher.group(1)));
        }else{
            this.statusCode.set(0);
        }
    }

    private void setBytesSent() {
        String regex = "(\\d+|-)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry.get());
        if (matcher.find()){
            this.bytesSent.set(Integer.parseInt(matcher.group(1)));
        }
        else{
            this.bytesSent.set(0);
        }
    }

    private void setReferer() {
        String regex = "^.*\\\"(?:GET|POST) .*\\\" \\d{3} \\d+ \\\"([^\\\"]*)\\\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(logEntry.get());
        if (matcher.find()){
            this.referer.set(matcher.group(1));
        }
        else{
            this.referer.set("-");
        }
    }
    
    //getter

    public String getType(){
        return type.get();
    }
    
    public String getIP() {
        return IP.get();
    }

    public String getTimestamp(){
        return timestamp.get();
    }
    
    public String getRemoteIdent() {
        return remoteIdent.get();
    }

    public String getRemoteUser() {
        return remoteUser.get();
    }

    public String getUserAgent() {
        return userAgent.get();
    }

    public String getRequestUrl() {
        return requestUrl.get();
    }

    public int getStatusCode() {
        return statusCode.get();
    }

    public int getBytesSent() {
        return bytesSent.get();
    }

    public String getReferer() {
        return referer.get();
    }
    
    public String getLogEntry(){
        return this.logEntry.get();
    }

    
    
    //property
    
    public StringProperty getTypeProperty(){
        return type;
    }
    
    public StringProperty getIPProperty() {
        return IP;
    }

    public StringProperty getTimestampProperty(){
        return timestamp;
    }
    
    public StringProperty getRemoteIdentProperty() {
        return remoteIdent;
    }

    public StringProperty getRemoteUserProperty() {
        return remoteUser;
    }

    public StringProperty getUserAgentProperty() {
        return userAgent;
    }

    public StringProperty getRequestUrlProperty() {
        return requestUrl;
    }

    public IntegerProperty getStatusCodeProperty() {
        return statusCode;
    }

    public IntegerProperty getBytesSentProperty() {
        return bytesSent;
    }

    public StringProperty getRefererProperty() {
        return referer;
    }
    
    public StringProperty getLogEntryProperty(){
        return logEntry;
    }
    
    public int getHour(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z");
        LocalDateTime dateTime = LocalDateTime.parse(getTimestamp(), formatter);
        return dateTime.getHour();
    }
    
}
