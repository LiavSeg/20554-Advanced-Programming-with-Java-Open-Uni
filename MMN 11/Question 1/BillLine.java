

/*
 * This class represents a line of a bill
 * _total total cost of this line 
 * _quantity quantity of the product on this line
 * _item the item on this line
 */
public class BillLine {
	private double _total;
	private final double _quantity;
	private Item _item;
	
	// constructs a bill line with a given item and quantity 
	public BillLine(Item i,int quantity) {
		this._quantity =quantity;
		this._item = i;
		this._total =(_item.getPrice()*_quantity);	
	}
	// empty constructor 
	public BillLine() {
		this._quantity =0;
		this._item = new Item();
		this._total =0;	
		
	}
	//gets the quantity
	public double getQuantity(){
		return _quantity;
	}
	//gets the total cost of this line
	public double getTotal(){
		return _total;
	}
	//sets total cost of this line
	public void setTotal(double total) {
		this._total = total;
	}
	
	// returns a string representation of the whole line
	public String getLine() {
		String name = _item.getName();
		double price = _item.getPrice();
		double quant = _quantity;
		double total = _total;
		String s = "Product: "+name+" "+"Price: "+price+" "+"Quantity: "+quant+" Total price: "+total+"\n";
	return s;
	}

		
	
}
