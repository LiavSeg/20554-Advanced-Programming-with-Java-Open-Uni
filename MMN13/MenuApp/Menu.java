import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Menu {
	private ArrayList<Item> _menu;
	private final int NAME = 1;
	private final int KIND = 2;
	private final int PRICE = 3;
	private final static int UNDEFINED_TYPE = -1;
	private final static int UNDEFINED_PRICE = -2;
	private final static int EMPTY_NAME = -3;
	public Menu() {
		_menu = new ArrayList<Item>();
		fillMenu();
	
	}
	public ArrayList<Item> getMenu(){
		return _menu;
	}
	private void fillMenu() {
		int count=0;
		try {
			Scanner input = new Scanner(new File("menu.txt"));
			Item i = new Item();
			while (input.hasNext()){
				String st = input.nextLine();
				count++;
				if(count == NAME) {
				 i.setName(st); 
				}
				else if(count == KIND) {
					i.setKind(st);
				}
				else if(count == PRICE) {
					i.setPrice(st);
					int x = validMenu(i.getName(),i.getKind(),i.getPrice());
					if(x>=0)
						_menu.add(i);
					else
						popAlert(x,i.getName());
					 count%=PRICE;
					  i = new Item();
				}
			}
			input.close();
		}
		catch(IOException e) {	
			System.out.print("Menu File cannot be found");
		}
	}
	
	public static int validMenu(String name,String type, String price) {
		int j=getType(type);
		if(j==-1) {
			return UNDEFINED_TYPE;
	    }
	    try {
	    	Integer.parseInt(price);
	    }
	    catch(NumberFormatException  e) {
	    	j=UNDEFINED_PRICE;
	    }
	    if(name.equals(" ")||name.equals("\n")||name.equals("")){
	    	j= EMPTY_NAME;
	    }
	    return j;
	}
	    	
	 public static int getType(String type) {
		 String mealTypes[] = {"appetizer","main","dessert","drink"};
		 for(int k = 0;k<4;k++)
			if(type.equals(mealTypes[k])) 
				return k;
		 return -1;
	 }
	 private void popAlert(int i,String name) {
		if(i>=0)
			return;
		 Alert alert = new Alert(AlertType.ERROR);
		 alert.setAlertType(Alert.AlertType.ERROR);  
    	 alert.setContentText(name+" dish is ignored");  
    	
    	if(i==UNDEFINED_TYPE) {
			alert.setTitle("Unidentified meal type");
			alert.setHeaderText("Invalid meal type ");
			}
    	else if(i==UNDEFINED_PRICE) {
			alert.setTitle("Invalid meal price");
			alert.setHeaderText("Price has to be a natural number ");  	
		}
    	else if(i==EMPTY_NAME) {
			alert.setTitle("Invalid dish name");
			alert.setHeaderText("Dish's name must contain at least one character");
			alert.setContentText("This nameless dish is ignored");
		}
    	alert.showAndWait();		
    } 
}
