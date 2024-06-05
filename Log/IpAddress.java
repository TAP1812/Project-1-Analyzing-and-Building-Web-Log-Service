package Log;


public class IpAddress {
    private String ip;
    private int frequency;
    private String countryName;
    
    public IpAddress(String ip){
        this.ip = ip;
    }

    public IpAddress(String ip, int frequency){
        this.ip = ip;
        this.frequency = frequency;
    }

    public void setCountryName(String name){
        this.countryName = name;
    }

    public void setFrequency(int frequency){
        this.frequency = frequency;
    }

    public String getCountryName(){
        return this.countryName;
    }

    public String getIp(){
        return this.ip;
    }

    public int getFrequency(){
        return this.frequency;
    }
}
