import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class RestAppController {

    @FXML
    private GridPane appetizers;

    @FXML
    private GridPane desserts;

    @FXML
    private GridPane drinks;

    @FXML
    private GridPane header;

    @FXML
    private GridPane mains;
    @FXML
    private GridPane menuArr[];

    private Order _order;
  
    public void initialize() {
    	 _order = new Order();
    	  headerInit();
    	 // menu = new Menu();
    	  menuArrInit();
    	  menuArramgmets(menuArr);
    	  menuInit();
    }
  
    @FXML
    void orderPressed(MouseEvent event) {
    	LinkedList<String> list = new LinkedList<String>();
    	int totalPrice = 0;
    	for(int i = 0; i<4;i++) {
    		for(int j=0;j<getRowCount(menuArr[i])*4;j+=4) {
    			CheckBox temp = (CheckBox)menuArr[i].getChildren().get(j);
    			if(temp.isSelected()==true) {
    				String itemData= getData(menuArr[i],j);
    				if(!itemData.isEmpty()) {
    					list.add(itemData);
    				totalPrice+=getTotalPrice(itemData);
    				}
    				}
    			}
    		}
    	list.add("                                                           Total Price: "+totalPrice);
    	int  x = Order.orderConfirmation(list,menuArr);
		popAlert(x);
    }
    private int getTotalPrice(String data) {
    	int i=0 ,j= 0;
    	while(data.charAt(i)!='=')
    		i++;
    	j=i+2;
    	while(Character.isDigit(data.charAt(j))) 
    			j++;
    	
    	return Integer.parseInt(data.substring(i+2,j));
    	
    	
    }
    private String getData(GridPane p,int j) {
    	Text  name = (Text) p.getChildren().get(j+1);
		Text  price = (Text) p.getChildren().get(j+2);
		String s1 = price.getText().substring(0, price.getText().length()-1);
		ComboBox<Integer> quant = (ComboBox<Integer>) p.getChildren().get(j+3);//comboBox location is already known (4th column)therefore an instanceof check is not required
		int dishPrice = Integer.parseInt(s1);
		int total = dishPrice*quant.getValue();
		
		if(quant.getValue() == 0)
			return"";
		String space1=" ";
		for(int i = name.getText().length();i<=25;i++)
			 space1+= " ";       
		return "     "+quant.getValue()+space1+name.getText()+space1+quant.getValue()+"*"+dishPrice+" = " +total+"\n"; 
    }
  
    private void headerInit() {
    	Text tx[]= new Text[4];
    	String s[] = {"Appetizers","Main dishes","Desserts","Drinks"};
    	double headerWidth=header.getPrefWidth();
    	for(int i =0;i<4;i++) {
    		ColumnConstraints c = new ColumnConstraints();
    		tx[i]= new Text(s[i]);
    		tx[i].setFont(Font.font(24));
    		c.setPrefWidth(headerWidth);
    		c.setHalignment(HPos.CENTER);	
    		header.getColumnConstraints().add(i,c);
    		header.add(tx[i], i, 0);
    	}
    	header.setStyle("-fx-border-color: black; -fx-border-width:1; -fx-background-color: lightgray;");
    }
    
    private void menuArrInit() {
    	menuArr = new GridPane[4];
    	menuArr[0]=appetizers;
    	menuArr[1]=mains;
    	menuArr[2]=desserts;
    	menuArr[3]=drinks;
    	
    }
    private void menuInit() {
    	int dishCount[] = {0,0,0,0};
    	final int CHECK_COLUMN = 0;
    	final int DISH_COLUMN = 1;
    	final int CBOX_COLUMN = 2;

    	for(Item i: _order._menu.getMenu()) {
    		String type = i.getKind();
    		CheckBox check = new CheckBox();
    		ComboBox<Integer> box = new ComboBox<Integer>();
      		comoBoxInit(box);
    	  	int j = Menu.getType(type);
    		if(j>=0){
    			Text tx1 = new Text(i.getName());
        		Text tx2 = new Text(i.getPrice()+"₪");
    			tx1.setFont(Font.font(18));
    			tx2.setFont(Font.font(18));
    			menuArr[j].add(check,CHECK_COLUMN,dishCount[j]);
    			menuArr[j].add(tx1,DISH_COLUMN,dishCount[j]);
    			menuArr[j].add(tx2,CBOX_COLUMN,dishCount[j]);
    			menuArr[j].add(box,CBOX_COLUMN+1,dishCount[j]);
    			dishCount[j]++;
    		}
    	}
    	
    }
    private void menuArramgmets(GridPane[] g) {
    	double headerWidth=header.getPrefWidth();
    	double colOneWidth = headerWidth/30;
    	double colTwoWidth = headerWidth/2;
    	double colThreeWidth = headerWidth/5;
    	double colFourWidth = headerWidth/5;
    	for(int i=0;i<4;i++) {
        	
    		//for each meal type add four columns 
        	ColumnConstraints c0 = new ColumnConstraints();//checkBox column
    		ColumnConstraints c1 = new ColumnConstraints();//dishe's name column
        	ColumnConstraints c2 = new ColumnConstraints();//price column
        	ColumnConstraints c3 = new ColumnConstraints();//comboBox column
        	g[i].setPrefWidth(headerWidth);        	
        	g[i].getColumnConstraints().addAll(c0, c1, c2,c3);
        	g[i].getColumnConstraints().get(0).setPrefWidth(colOneWidth);
        	g[i].getColumnConstraints().get(1).setPrefWidth(colTwoWidth);
        	g[i].getColumnConstraints().get(2).setPrefWidth(colThreeWidth);
        	g[i].getColumnConstraints().get(3).setPrefWidth(colFourWidth);
        	g[i].setStyle("-fx-border-color: gray; -fx-border-width:1; -fx-background-color: lightgray;");
        	c0.setHalignment(HPos.CENTER);
        	c2.setHalignment(HPos.RIGHT);
    	}
    }
    private void comoBoxInit(ComboBox<Integer> b) {
    	final int MAX  = 15;
    	for(int i=0;i<=MAX;i++)
    		b.getItems().add(i);	
    	b.setValue(0);
    	}
    private int getRowCount(GridPane p) {
    	int rows =1;
    	for (Node n: p.getChildren()) 	{
    		if(GridPane.getRowIndex(n)+1>rows)
    			rows++;	
    	}
    	return rows;
    }
    private void setToDefault(GridPane[] p) {    	
    	for(int i = 0; i<4;i++) {
    		for(int j=0;j<getRowCount(p[i])*4;j+=4) {
    			CheckBox temp = (CheckBox)p[i].getChildren().get(j);
    			
    			ComboBox<Integer> cb =(ComboBox<Integer>) p[i].getChildren().get(j+3);
    			if(temp.isSelected())
    				temp.setSelected(false);
				if(cb.getValue()!=0)
					cb.setValue(0);
    		}
    	}
    	
    }
    
    private void popAlert(int i) {
    	if(i==10)
    		return;
    	Alert alert  = new Alert(Alert.AlertType.INFORMATION);
    	 if (i==0) {
			alert.setTitle("Order Cancellation");
			alert.setHeaderText("Your order has been cancelled ");
			alert.setContentText("");    
    	 }
    	 if (i==1) {
			alert.setTitle("Order completed");
			alert.setHeaderText("Your order has been confirmed and saved in our system ");
			alert.setContentText("");

    	 }
    	 else if(i==-2) {
 			alert.setTitle("Order cannot be confirmed");
 			alert.setHeaderText("Each order must contain at least one product");
 			alert.setContentText("");
 		}
    	 else if(i==2) {
    		alert.setTitle("Order comfirmation failed");
    		alert.setHeaderText("Order confirmaiton can't be found in our system");
    		alert.setContentText("");    		
    	 }
    	setToDefault(menuArr);
    	alert.showAndWait();		
    }
}
