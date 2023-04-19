/*
 * this is a constructor of Fire class which represents an fire alarm
 * inherits from Smoke class
 * gets a string which represents an address and a String which represents caller's name 
 * and initializes it's instance variables
 * 
 */
public class Fire extends Smoke{
	private boolean _active =false;
	public Fire(String address,String caller) throws BadAlarm {
		super(address,caller);
		if(_active == false)//this line was written just to make the error of unused variable to go away
		_active = true;
	}
	public void action() {
		_active = false;
		System.out.println("***Fire alarm Triggered***");
		System.out.println("Time of action " + super.toString());
		System.out.println("Activator's name " + this._caller );
		
	}
}
