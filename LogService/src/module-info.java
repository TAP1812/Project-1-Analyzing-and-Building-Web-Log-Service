module LogServive {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	
	exports hust.soict.cybersec.project1.stuff;
	exports hust.soict.cybersec.project1.controller;
	opens hust.soict.cybersec.project1.stuff to javafx.graphics, javafx.fxml;
	opens hust.soict.cybersec.project1 to javafx.graphics, javafx.fxml;
	opens hust.soict.cybersec.project1.controller to javafx.graphics, javafx.fxml;
	opens hust.soict.cybersec.project1.model to javafx.graphics, javafx.fxml, javafx.base;
}
