package hust.soict.cybersec.project1;
	
import hust.soict.cybersec.project1.controller.DashboardController;
import hust.soict.cybersec.project1.controller.ExplorerController;
import hust.soict.cybersec.project1.controller.LoginController;
import hust.soict.cybersec.project1.controller.OverviewController;
import hust.soict.cybersec.project1.controller.StreamController;
import hust.soict.cybersec.project1.controller.WelcomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	// some important log file location
	private static final String accesslog = "D:\\XAMPP\\apache\\logs\\access.log";
	private static final String errorlog  = "";
	private static final String modseclog = "";
	
	private Stage primaryStage;
	private Scene welcome,stream,explorer,overview,dashboard,login,register;
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
		
			this.primaryStage = primaryStage;
			primaryStage.setTitle("Service Log Analyze");
			Image appicon = new Image(getClass().getResourceAsStream("/image/detective.png"));
			primaryStage.getIcons().add(appicon);
			
			
			//load login
			FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/fxml/LoginPage.fxml"));
			Parent loginParent = loginLoader.load();
	        LoginController loginController = loginLoader.getController();
	        loginController.setMainApp(this);
	        login = new Scene(loginParent);
			
			//load welcome
			FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("/fxml/Welcome.fxml"));
			Parent welcomeParent = welcomeLoader.load();
	        WelcomeController welcomeController = welcomeLoader.getController();
	        welcomeController.setMainApp(this);
	        welcome = new Scene(welcomeParent);
	        
			//load dashboard
	        FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("/fxml/DashBoard.fxml"));
	        Parent dashboardParent = dashboardLoader.load();
	        DashboardController dashboardController = dashboardLoader.getController();
	        dashboardController.setMainApp(this);
	        dashboard = new Scene(dashboardParent);
	        
			//load overview
	        FXMLLoader overviewLoader = new FXMLLoader(getClass().getResource("/fxml/Overview.fxml"));
	        Parent overviewParent = overviewLoader.load();
	        OverviewController overviewController = overviewLoader.getController();
	        overviewController.setMainApp(this);
	        overview = new Scene(overviewParent);
	        
			//load stream
	        FXMLLoader streamLoader = new FXMLLoader(getClass().getResource("/fxml/Stream.fxml"));
	        Parent streamParent = streamLoader.load();
	        StreamController streamController = streamLoader.getController();
	        streamController.setMainApp(this);
	        stream = new Scene(streamParent);
	        
			//load explorer
	        FXMLLoader ExplorerLoader = new FXMLLoader(getClass().getResource("/fxml/Explorer.fxml"));
	        Parent explorerParent = ExplorerLoader.load();
	        ExplorerController ExplorerController = ExplorerLoader.getController();
	        ExplorerController.setMainApp(this);
	        explorer = new Scene(explorerParent);
	        
	        
	        this.primaryStage.setScene(login);
			this.primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void register() {
		
		primaryStage.setScene(register);
	}
	public void logout() {
		primaryStage.setScene(login);
	}
	public void switchToWelcome() {
		primaryStage.setScene(welcome);
//		primaryStage.setMaximized(true);
	}
	public void switchToDashboard() {
		double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
		primaryStage.setScene(dashboard);
		primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
		
	}
	public void switchToOverview() {
		double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
		primaryStage.setScene(overview);
		primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
	}
	public void switchToStream() {
		double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
		primaryStage.setScene(stream);
		primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
	}
	public void switchToExplorer() {
		double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
		primaryStage.setScene(explorer);
		primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
	}
	
	
	public static String getAccesslog() {
		return accesslog;
	}

	public static String getErrorlog() {
		return errorlog;
	}

	public static String getModseclog() {
		return modseclog;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
