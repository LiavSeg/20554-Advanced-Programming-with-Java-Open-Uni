/*
 * this is a constructor of Elevator class which represents an elevator alarm
 * gets a string which represents an address and an integer number which represents floor 
 * and initializes it's instance variables
 */
public class Smoke extends Alarm{
	String _caller;
	public Smoke(String address,String callersName)throws BadAlarm {
		super(address);
		_address = new String(address);
		_caller = new String(callersName);
	}
	public void action() {
		System.out.println("***Smoke alarm Triggered***");
		System.out.println("Time of action " + super.toString());
		System.out.println("Activator's name " + this._caller );
	}
}
