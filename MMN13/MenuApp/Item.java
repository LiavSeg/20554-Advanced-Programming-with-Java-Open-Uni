
public class Item {
	private String _name;
	private String _kind;
	private String _price;
	
	/*public Item(String name,String kind,String price) {
		_name=name;
		_kind = kind;
		_price = price;
		
	}*/
	public Item() {
		_name="";
		_kind = "";
		_price="";
		
	}
	
	public 	String getName() {
		return _name;
	}
	public String getKind() {
		return _kind;
	}
	public String getPrice() {
		return _price;
	}
	public 	void setName(String name) {
		_name=name;
	}
	public void setKind(String kind) {
		_kind = kind;
	}
	public void setPrice(String price) {
		_price = price;
	}
		
	
}
