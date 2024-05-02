
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/*	This is the manager controller class the first of two JavaFX instances 
 * 	While initializing this class a new UDP server is created to 
 * 	provide a better work flow  
 * 	after this class loads clients can be added safely 
 */
public class ManagerController {
    @FXML
    private TextArea msgArea;

    @FXML
    void sendPressed(ActionEvent event) {
    	String s = msgArea.getText();
    	if(s==null||s.isEmpty()||s.equals(" "))
    		return;
    	new ManagerThread(s).start();
    	msgArea.clear();
    }
   
    public void initialize() {
    	new UDPServer();	
    }
   
}
