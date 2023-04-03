/*
 * This class represents an item
 * _name - item's name
 * _price -  item's price
 * 
 */
public class Item {
	private String _name; // Item's name
	private  double _price;//Item's price
	//constructs an item with a given name and price
	public Item(String name,double pr) {
		this._name = name;
		this.setPrice(pr);
	}
	//empty constructor - nameless and priceless item 
	public Item() {
		this._name = "";
		this.setPrice(0);
	}
	//getters and setters name 
	public String getName()	{
		return _name;
	}
	
	public void SetName(String name) {
		this._name = name;
	}
	//getters and setters price
	public double getPrice() {
		return _price;
	}
	public void setPrice(double price) {
		this._price = price;
	}
}
