
import java.util.ArrayList;
/*
 * This class represents a cash register 
 * _sum  sum of the current costumer 
 * _regMoney total money of the register 
 * _bill  bill current client's bill 
 */
public class CashReg {
	private double _sum;
	private double _regMoney;
	private  ArrayList<String> _bill;
	
	public CashReg(double init) {
		this._regMoney = init;
		_bill = new ArrayList<String>();
	}
	public CashReg() {
		this._regMoney = 0;
	_bill = new ArrayList<String>();
	}
	//gets sum of the current costumer 
	public double getSum() {
		return _sum;
	}
	// sets the current's client sum
	public void setSum(double sum) {
		this._sum+=sum;
	}
	/* adds to the bill quantXi items to the bill
	 * updates the bill with a new line 
	 */
	
	public void addToBill(Item i,int quant) {
		BillLine line = new BillLine(i,quant);
		String s =line.getLine();
		_bill.add(s);
		this.setSum(line.getTotal());
	}
	// prints the current bill 
	public void printBill() {
		if(_bill.isEmpty())
			System.out.println("Shopping cart is empty");
		else
			for(String element: _bill) {
				System.out.println(element);
				System.out.println("Total cost:"+_sum);
		}
		
	}
	// gets (current) total moeny in the register 
	public double getRegMoney() {
		return _regMoney;
	}
	// sets the total money 
	public void setRegMoney(double p) {
		 this._regMoney += p;
	}
	// prints clinet's bill
	public void printCurrSum() {
		System.out.println(getSum());
	}
	
	/*checks out the client 
	 * sums up the bill and displays it 
	 * payment request 
	 */
	public void checkOut() {
		for(String element: _bill) {
			System.out.println(element);
			}
		System.out.println("Total cost:"+_sum);
		System.out.println("\nGive me the money");
		
	}
	// gets payment and sets the new total money on the register and clears the bill
	 
	public void billPayment(double payment) {
		System.out.println("Your check is: "+this.getSum()+"\n");
		if(payment<0)
			System.out.println("Are you trying to rob me?\n");
		if(this.getSum()<=payment) {
			System.out.println("\nYour change is: "+(payment-this._sum)+"₪ - "+"*Don't forget Take your change and products.");
			setRegMoney(this.getSum());
			this.setSum(0);
			this._bill.clear();
		}
		else
			System.out.println("You are missig "+(this._sum-payment)+"₪ - "+"Please check your payment\n");
		
	}
	
}
