module LogServive {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires com.maxmind.geoip2;
	
	
	exports hust.soict.cybersec.project1.stufftesting to javafx.graphics, javafx.fxml;
	exports hust.soict.cybersec.project1.stuff;
	exports hust.soict.cybersec.project1.controller;
	opens hust.soict.cybersec.project1.stuff to javafx.graphics, javafx.fxml;
	opens hust.soict.cybersec.project1 to javafx.graphics, javafx.fxml;
	opens hust.soict.cybersec.project1.controller to javafx.graphics, javafx.fxml;
	opens hust.soict.cybersec.project1.model to javafx.graphics, javafx.fxml, javafx.base;
}
