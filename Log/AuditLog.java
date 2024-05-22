package Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
public class AuditLog {
    public AuditLog(){

    }
    
    public static void main(String[] args){
        String filePath = "E:\\PROJECT1\\WebLogService\\src\\Resources\\modsec_audit.log";
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
