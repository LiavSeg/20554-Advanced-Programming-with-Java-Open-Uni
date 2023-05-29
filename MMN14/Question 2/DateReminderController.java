import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class DateReminderController {
	  @FXML
	    private VBox vBox;

    @FXML
    private ComboBox<String> dayCmbx;

    @FXML
    private ComboBox<String> monthCmbx;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> yearCmbx;
    private HashMap<Date,String> map;
    private boolean isSaved;
   
    public void initialize() {
    	map = new HashMap<Date,String>();
    	cmbxInit();
    	readFromFile();
    	isSaved = false;
    }
    @FXML
    void loadPressed(ActionEvent event) {
    	try {
    		Date d = new Date(Integer.parseInt(dayCmbx.getValue()),Integer.parseInt(monthCmbx.getValue()),Integer.parseInt(yearCmbx.getValue()));
			if(ValidDate(d)==1) {
				String s = map.get(d);
				textArea.setText(s);
			}
    	}
    	catch(NumberFormatException e) {
    		popAlert("-1");
    	}
    }

    @FXML
    void savedPressed(ActionEvent event) {
    	try {
    		Date d = new Date(Integer.parseInt(dayCmbx.getValue()),Integer.parseInt(monthCmbx.getValue()),Integer.parseInt(yearCmbx.getValue()));
    		if(map.get(d)==null)
    			map.put(d, "");
    		if(ValidDate(d)==1) {
    			map.put(d, textArea.getText());
    			textArea.clear();
    			getClosingEvent();
    		}
    	}
    	catch(NumberFormatException e) {
    		popAlert("-1");
    	}
    	

    }
       private void writeToFile(File f) {
    	FileOutputStream fo;
		ObjectOutputStream out ;
		try {
			fo = new FileOutputStream(f);
			out  = new ObjectOutputStream(fo);	
			out.writeObject(map);
			out.close();
			fo.close();
		} 
		catch (IOException e) {
			System.out.println("Could not write to the file. " + e.getMessage());
		}
			return;
    }
    private void getClosingEvent() {
    	Stage stage = (Stage)((Node) vBox).getScene().getWindow();
        stage.getScene().getWindow().addEventHandler(
        WindowEvent.WINDOW_CLOSE_REQUEST, event1 -> {
        	 saveFile();
           }); 
    }
   //user interface related alerts 
    private void  popAlert(String s) {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	if(s==null) {
    		alert.setTitle("New memo");
			alert.setHeaderText("Initializing a new memo file");
			alert.setContentText(""); 
    	}
    	else if(s.equals("-1")) {
    		alert.setAlertType(Alert.AlertType.ERROR);
    		alert.setTitle("Invalid memo date");
    		alert.setHeaderText("Invalid date selection ");
    		alert.setContentText(""); 
    	}
    	else if(s.equals("0")){
    		alert.setAlertType(Alert.AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setHeaderText("Your memo reminder remains unchanged ");
    		alert.setContentText("No changes were made to the memo reminder"); 
    	}
    	else if(s.charAt(0)=='1') {
    		s = s.substring(1);
    		alert.setTitle("");
    		alert.setHeaderText("Your memo reminder has been updated successfully");
    		alert.setContentText("Memo's name: "+s); 
    	}
    	else if(s.equals("Invalid")){
    		alert.setTitle("Warning");
    		alert.setHeaderText("This memo reminder supports .TXT files only ");
    		alert.setContentText("Can't procced - Program termination "); 
    		
    	}
    	else {
    		alert.setTitle("");
			alert.setHeaderText("Loading existing memo file");
			alert.setContentText("File's name: " +s); 
    	}
		alert.showAndWait();
    }
    //date related alerts
    private void dateAlert(int s) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Invalid date selection");
    	alert.setTitle("Invalid memo date");
		alert.setHeaderText("Invalid date selection ");
    	
		String months[] = {"April","June","August","October","December"};
		if(s==8) {
    		alert.setContentText("Febuary has 29 days this year"); 
    	}
    	else if(s==4){
    		alert.setContentText("Febuary has 28 days this year"); 
    	}
    	else if(s%31==0) {
    		s%= 31;
    		s=s/2+1;
    		alert.setContentText(months[s]+" has 30 days"); 
    	}
		alert.showAndWait();
    }
    private void readFromFile() {
    	File f  = getFile();
    	String fileName =null;
    	if(f!=null)
    		try {
    			FileInputStream fi = new FileInputStream(f);
    			ObjectInputStream ois = new ObjectInputStream(fi);
    			map = (HashMap<Date, String>)ois.readObject();
    			if(f.getName().charAt(0)=='.')
    				fileName = f.getName();
    			else
    				fileName = f.getName().substring(0,f.getName().length()-4);
    			ois.close();
    			fi.close();
    			popAlert(fileName);
        	}
    		catch(IOException | ClassNotFoundException e) {
    			popAlert("Invalid");
    			System.exit(0);
    	}
    	else
        	popAlert(fileName);

        		
    }
    private void saveFile() {
    	if(isSaved)
    		return ;
    	String alertFlag  = "0";
    	FileChooser fileC = new FileChooser();
    	fileC.setTitle("Save Memo File");
    	fileC.setInitialDirectory(new File("."));
    	fileC.getExtensionFilters().add( new ExtensionFilter("Text Files (*.txt)", "*.txt"));
    	File f = fileC.showSaveDialog(null);
    		if(f!=null) {
    			writeToFile(f);
    			alertFlag = "1"+f.getName();
    		}
    	popAlert(alertFlag);
    	isSaved = true;	
    }
    private File getFile() {
    	FileChooser fileC = new FileChooser();
    	fileC.setTitle("Select Memo File");
    	fileC.setInitialDirectory(new File(".")); 
    	File f = fileC.showOpenDialog(null);
    	return f;
    }
    
    private void cmbxInit() {
    	final int DAYS = 31;
    	final int MONTHS = 12;
    	final int START_YEAR = 1995;
    	final int END_YEAR = 2025;
    	for(int i = 1; i <= DAYS ; i++)
    		dayCmbx.getItems().add(""+i);
    	for(int i = 1; i <= MONTHS ; i++)
    		monthCmbx.getItems().add(""+i);
    	for(int i = START_YEAR; i <=END_YEAR; i++)
    		yearCmbx.getItems().add(""+i);
    	
    	dayCmbx.setValue("Day");
    	monthCmbx.setValue("Month");
    	yearCmbx.setValue("Year");
    	
    }
    private int  ValidDate(Date d) {
    	 if(d.getMonth()%2!=0)
			return 1;
    	//February validation 
    	 if(d.getMonth()==2) {
			if(d.getYear()%4==0&&d.getDay()>29)//leap year check
				dateAlert(d.getMonth()*4);
			else if(d.getDay()>28&&d.getYear()%4!=0)//regular year
				dateAlert(d.getMonth()*2);
			else 
				return 1;
		}
    	 //checks for each even month if day input is abode 30
		else if(d.getMonth()%2==0) {
			if(d.getDay()==31)
				dateAlert(d.getMonth()*31);	
			else 
				return 1;
			}
		return 0;
		}
    
    }


