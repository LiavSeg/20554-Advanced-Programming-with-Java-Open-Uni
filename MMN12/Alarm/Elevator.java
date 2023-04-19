/*
* this is a constructor of Elevator class which represents an elevator alarm
* inherits from Alarm 
* gets a string which represents an address and an integer number which represents floor 
* and initializes it's instance variables
* 
*/
public class Elevator extends Alarm {
	private int _floor;
	public  Elevator(String address,int floor) throws BadAlarm{
		super(address);//calls for the alarm constructor 
		_address = new String(address);
		_floor = floor;
	
	}
	public void action() {
		System.out.println("***Elevator alarm Triggered***");
		System.out.println("Time of action " + super.toString());
		System.out.println("Took place in " + this._address + " on the - "+ this._floor +"th floor");
	}
	public void setFloor() {
		this._floor = 0;	
	}
}
