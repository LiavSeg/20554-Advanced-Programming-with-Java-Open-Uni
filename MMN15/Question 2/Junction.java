import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Junction  extends Application{ 
	
	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("Junc.fxml")); 
		Scene scene = new Scene(root); 
		terminateWhenDone(stage);
		stage.setTitle("Junction"); 
		stage.setScene(scene); 
		stage.show(); 
		
	}
	
	private void terminateWhenDone(Stage stage) {
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		       @Override
		       public void handle(WindowEvent e) {
		          Platform.exit();
		          System.exit(0);
		       }
		    });
	}
	
	public static void main(String[] args) { 
		launch(args); 
		System.out.println();
	} 
}
