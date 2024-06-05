package hust.soict.cybersec.project1.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StatusCount {
	
	public IntegerProperty statuscode;
	private IntegerProperty count;
	
	public StatusCount(int statuscode, int count) {
		
		this.statuscode = new SimpleIntegerProperty(statuscode);
		this.count = new SimpleIntegerProperty(count);
	}
	
	public int getStatusCode() {
        return statuscode.get();
    }

    public void setStatusCode(int code) {
        this.statuscode.set(code);
    }

    public int getCount() {
        return count.get();
    }

    public void setCount(int count) {
        this.count.set(count);
    }

    public IntegerProperty statusCodeProperty() {
        return statuscode;
    }

    public IntegerProperty countProperty() {
        return count;
    }
	
	
}
