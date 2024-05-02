import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*	This task contains two different JavaFX windows
 *	This class should be launched first - further explanation 
 *	exists on ManagerController's documentation  
 */


public class Manager extends Application{ 
	public void start(Stage stage) throws Exception{ 
		Parent root = (Parent) FXMLLoader.load(getClass().getResource("ManagerController.fxml")); 
		Scene scene = new Scene(root); 
		stage.setTitle("Manager"); 
		stage.setScene(scene); 
		stage.show(); 
	} 
	
	public static void main(String[] args) { 
		launch(args); 
		System.out.println();
	} 
}
