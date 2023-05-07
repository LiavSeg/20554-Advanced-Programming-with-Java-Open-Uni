import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JOptionPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;

public class Order {
	protected Menu _menu;
	private final static int VALID_OREDER = 10;
	public Order() {
		_menu = new Menu();	
	}

	public static int orderConfirmation(LinkedList<String> list,GridPane[] menuArr) {
		String options[]= {"Order confirmation","Order Update","Order cancellation"};		
		int  buttonPressed
		= JOptionPane.showOptionDialog(null,formatOrderSummary(list),null, 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		
		if(buttonPressed==0){
			if(list.toString().length()==2) 
				return -2;
			else 
				return orderDialog(list);
			}
		else if(buttonPressed==2) {
			return 0;
    	}
		return VALID_OREDER;
    }
	public static String formatOrderSummary(LinkedList<String> s) {
	
		String str ="Order summary\n"
				+"Quantity               Dish         Price\n";
		
		
		if(s.isEmpty())
			return "Order is empty";
		for(String st: s) {
			st.substring(0,st.length()-1);
			str +=st; 
		}
		return str;
	}
	
	private static int orderDialog(LinkedList<String> list) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Order Confirmation");
		dialog.setHeaderText("To complete your order please provide your name and ID number without spaces");
		Optional<String> result = dialog.showAndWait();
		try {
			FileWriter fw = new FileWriter(result+".txt");
			fw.write(formatOrderSummary(list));
			fw.close();
		}
		catch(IOException e) {	
			return 2;
		}
		return 1;
	}
}
