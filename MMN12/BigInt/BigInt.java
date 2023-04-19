/*
 * This class implements a data structure that can hold large integers using arrayList.
 * _bigNum is a representation of the number using ArrayList
 * _sign is the number's sign +/-
 */
import java.util.ArrayList;
import java.lang.IllegalArgumentException;
import java.lang.ArithmeticException;
public class BigInt implements Comparable<BigInt>{
	//declarations 
	private ArrayList<Integer> _bigNum;
	private char _sign;
	private final int TEN = 10;
	/**
	 * constructor for a BigInt object
	 * check for invalid numbers, removes leading zeros,assigns a sign ,and stores the digits in reversed order
	 * @parameter numStr string representation of the number
	 * @throws IllegalArgumentException if the number isn't valid
	 */
	
	public BigInt(String numStr) throws IllegalArgumentException {
		_bigNum = new ArrayList<Integer>();
		_sign = ' ';
		int start = 0;
		char sign;
		char tempNum = ' ';
		if(numStr.length()==0)
			throw new IllegalArgumentException("Invalid input - NaN");
		numStr = removeNulls(numStr);
		sign = numStr.charAt(start);
		if(sign == '-') {
			_sign = '-';
			start=1;
		}
		else if(sign == '+') {
			_sign = '+';
			start=1;
		}
		else if(Character.isDigit(sign))
			_sign = '+';	
		for(int i =  numStr.length()-1; i >=start; i--) {
			 tempNum = numStr.charAt(i);
			if(_sign == ' '||!Character.isDigit(tempNum))
				throw new IllegalArgumentException("Invalid input - NaN");
			_bigNum.add(Character.getNumericValue(tempNum));
		}		
	}//end of BigInt constructor 
	
	
	//Arithmetic operations 
	/**
	 * this method implements integers addition
	 * @parameter other BigInt object 
	 * @return a new BigInt the addition result
	 */
	public BigInt plus (BigInt other) {
		BigInt newBig,smaller=null;
		String newNum  = "";
		int flag = 0;
		int tempDigit;
		int biggerNum =  Math.max(other._bigNum.size(),this._bigNum.size());
		int smallerNum =  Math.min(other._bigNum.size(),this._bigNum.size());
		int diff = biggerNum-smallerNum;
		int i=0;
		
		//in case signs aren't equal call for the right method with a matching sign assignments
		//when done restore the original sign and return the results
		if(this._sign != other._sign) {
			if(this._sign == '-') {
				other._sign = '-';
				newBig = this.minus(other);
				other._sign = '+';
			}
			else {
				other._sign = '+';
				newBig = this.minus(other);
				other._sign = '-';
			}	
			return newBig;
		}
		//one number is smaller than the other - pad with zeros
		if(diff>0){
			if(this._bigNum.size()==biggerNum) {
				numberPadding(other,diff);
				smaller = other;
			}
			else {				
				numberPadding(this,diff);
				smaller = this;
			}
		}
		for(i = 0; i<biggerNum; i++) {
			tempDigit =  other._bigNum.get(i)+this._bigNum.get(i);
			if(tempDigit+flag>=TEN) {
				tempDigit %=TEN;
				newNum = (tempDigit+flag)%10+newNum;
				flag = 1;
			}
			else if(tempDigit+flag<TEN) {
				newNum = tempDigit+flag+newNum;
				flag = 0;	
			}
		}
		//remove any leading zeros that may have left during this process
		if(diff>0)
			numberPadding(smaller,-1);

		if(flag!=0)//in case than we carried one but for loop ended
			newNum = flag+newNum;
		if(this._sign==other._sign&&this._sign=='-')
			newNum ='-'+newNum;
	
		newBig = new BigInt(newNum);	
		return newBig;
	}//end of plus
	
	/**
	 * this method implements integers subtraction 
	 * @parameter other BigInt object 
	 * @return a new BigInt the subtraction result
	 */
	public BigInt minus (BigInt other) {
		BigInt newBig,smaller =other,bigger = this;
		String newNum  = "";
		int flag = 0,i=0;
		int tempDigit;
		int biggerNum =  Math.max(other._bigNum.size(),this._bigNum.size());
		int smallerNum =  Math.min(other._bigNum.size(),this._bigNum.size());
		int diff = biggerNum-smallerNum;
		//in case signs aren't equal call for the right method with a matching sign assignments
		//when done restore the original sign and return the results
		if(this._sign != other._sign) {
			if(this._sign == '-') {
				other._sign = '-';
				newBig = this.plus(other);
				other._sign = '+';
			}
			else {
				other._sign = '+';
				newBig = this.plus(other);
				other._sign = '-';
			}
			return newBig;
		}
		//one number is smaller than the other - pad with zeros
		if(diff>0){
			if(this._bigNum.size()==biggerNum) {
				smaller = other;
				numberPadding(other,diff);
			}
			else {				
				smaller = this;
				bigger = other;
				numberPadding(this,diff);
			}
		}
		else if(this.compareTo(other)<0) {
			smaller = this;
			bigger = other;
		}
			
		//subtracting the digits 	
		for(i = 0; i<biggerNum; i++) {
			tempDigit =  bigger._bigNum.get(i)-smaller._bigNum.get(i);
			//in case one of the digits is zero and the other is not
			if(bigger._bigNum.get(i)==0&&smaller._bigNum.get(i)!=0||bigger._bigNum.get(i)!=0&&smaller._bigNum.get(i)==0){
				if(i==biggerNum-1) {
					tempDigit = Math.abs(tempDigit)-flag;
				}
				else if(tempDigit-flag<0) {
					tempDigit = TEN-Math.abs(tempDigit-flag);
					flag =1;
				}
				else if(tempDigit-flag>=0) {
					tempDigit-=flag;
					flag =0;
				}
				newNum = tempDigit+newNum;
					
			}//both digits are not zero
			else if(tempDigit-flag<0) {//borrow case (carry subtraction)
				tempDigit = TEN - Math.abs(tempDigit)-flag;
				newNum = tempDigit+newNum;
				 flag = 1;
			}
			else if(tempDigit-flag>=0) {
				tempDigit -=flag;
				newNum = tempDigit+newNum;
				 flag = 0;
			}
		}
		//remove any leading zeros that may have left during this process
		if(diff>0)
			numberPadding(smaller,-1);
		
		if(this.compareTo(other)==-1)
			newNum ='-'+newNum;
		newBig = new BigInt(newNum);	
		return newBig;
	}// end of minus
	
	/**
	 * this method implements integers multiplication  
	 * @parameter other BigInt object 
	 * @return a new BigInt the multiplication result 
	 */
	public BigInt multiply(BigInt other) {
		BigInt big = this,small = other,temp1=null,temp2=null;
		String newNum = "";
		int tempNum,flag=0;
		int biggerNum =  Math.max(other._bigNum.size(),this._bigNum.size());//size of the longer number
		int smallerNum =  Math.min(other._bigNum.size(),this._bigNum.size());//size of the smaller number
		
		//checks which number is longer to bound the for loop follows this block
		if(biggerNum!=smallerNum)
			if(biggerNum!=this._bigNum.size()) {
				big = other;
				small = this;
			}
		//Multiplication loop -  each digit of the short number multiplied by longer's digits 
		for(int i = 0 ;i<smallerNum;i++) {
			newNum="";
			for(int k=0;k<i;k++)//mimics multiplication of 10^i for each i-th digit position 
				newNum+='0';
			for(int j = 0; j<biggerNum;j++) { //multiplication of i-th digit with each digit 0<=j<biggerNum
				tempNum = small._bigNum.get(i)*big._bigNum.get(j);
				if(tempNum+flag>=TEN) //in case we have to "carry the one"(or any other digit) 
					if(j==biggerNum-1) {//adds the "one" in case its the end of the number, otherwise we lose it
						newNum =(tempNum+flag)+newNum;
						flag = 0;
					}
					else { 
						int temp =tempNum;
						temp=(temp+flag)/TEN;
						tempNum=(tempNum+flag)%TEN;
						newNum =tempNum+newNum;
						flag = temp;
					}
				else if(tempNum+flag<TEN){
					newNum = tempNum+flag+newNum;
					flag =0;
				}	
			}
			//uses two BigInts to sum any of the digits multiplication products with plus method
			if(temp1==null)
				temp1 = new BigInt(newNum);
			else 
				temp2 = new BigInt(newNum);
		
			//adding the results
			if(temp2!=null) {
				temp1 = temp1.plus(temp2);
				temp2 = null;
			}
		}
		//sign assignments post multiplication 
		if(temp1._bigNum.size()==1&&temp1._bigNum.get(0)==0)
			temp1._sign = '+';				
		else if(this._sign!=other._sign)
			temp1._sign='-';
		else
			temp1._sign = '+';
		return temp1;
	}//end of multiply

	/**
	 * this method implements integers devision 
	 * uses plus method adds the other number to itself until its equal or greater than this
	 * @parameter other BigInt object 
	 * @return a new BigInt the devision result 
	 * @throws ArithmeticException when dividing by 0
	 */
	public BigInt divide (BigInt other) throws ArithmeticException {
		BigInt temp;
		int flag=-1 ;
		int count=0;	
		flag = absVal(this,other,-1);
	
		
		if(other._bigNum.size()==1&&other._bigNum.get(0)==0) {
			throw new ArithmeticException("/ by zero");
		}
		
		if(this.equals(other)==true)
			return new BigInt("1");
	
		else if(this.compareTo(other)==1){
			temp = other.plus(other);
			count ++ ;
			while(temp.compareTo(this)<=0) {
				temp = other.plus(temp);
			 	count++;
			}
		}
		if(flag>=0)
			absVal(this,other,flag);

		return new BigInt(""+count);
	}// end of divide
	
	/**
	 * this method overrides toString  method  
	 * @return a String representation of the BigInt 
	 */
	public String toString() {
		String str = "";
		for( int i = 0; i < this._bigNum.size(); i++) {
			//for each 3 digits add a comma for a cleaner representation of the number
			if(i%3==0&&(i!=0))
				str =","+str;
			str = this._bigNum.get(i)+str;
		}
		//add a - if its a negative number, positive sings are omitted  
		if(this._sign == '-')
			str = '-'+str;		
		return str;
	}// end of toString
	
	/**
	 * @Override equals method
	 * uses comnpareTo method
	 * @parameter ob 
	 * @return True if both are equals
	 * 		   False otherwise	
	 */
	public boolean equals(Object ob) {
		BigInt other;
		if(!(ob instanceof BigInt))
			return false;
		other = (BigInt)ob;
		if(this.compareTo(other)!=0)
			return false;
		return true;	
	}// end of equals
	
	/**
	 * @Override compareTo method
	 * compares this BigInt to the other parameter
	 * @parameter other 
	 * @return positive value if this greater than other
	 *  	   zero if this and other are equals 
	 *  	   negative value if other is greater than this	
	 */
	public int compareTo(BigInt other) {
		char thisSign = this._sign;//this number's sign
		char otherSign = other._sign;
		int thisSize = this._bigNum.size();
		int otherSize = other._bigNum.size();
		String num1 = this.toString();
		String num2 = other.toString();		
		//different signs case
		if(thisSign !=otherSign) 
			return thisSign == '+' ?1:-1;
		//same signs case positive and negative cases  
		if(thisSign==otherSign) {
			if(thisSize>otherSize) 
				return thisSign == '+' ?1:-1;
			else if(thisSize<otherSize)
				return thisSign == '+' ?-1:1;
			else {//same number of digits and same sign case
				for(int i =0; i<thisSize;i++) {
					if(num1.charAt(i)>num2.charAt(i))
						return thisSign == '+' ?1:-1;
					if(num1.charAt(i)<num2.charAt(i))
						return thisSign == '+' ?-1:1;
				}
			}	
		}
	return 0;	//if we got here numbers are equal
	}//end of compareTo
	
	
	//private methods
	/**
	 * private method used to get the absolute value of a BigInt 
	 * used by the devision function which is implemented using addition 
	 * therefore absolute value is necessary for this process
	 * a flag indicated the status of the operation if it not negative
	 * the method will restore the original value of the number/s according the flag's value
	 * @parameter this BigInt other BigInt flag 
	 * @return negative value if both positive
	 *  	   one if other is negative and other are equals 
	 *  	   zero if this is negative
	 *  		two if are both negative
	 */
	private int absVal(BigInt thisNum,BigInt other,int flag) {
		
		if(flag==-1) {
			if(thisNum._sign != other._sign)
				if((thisNum._sign == '+' ?1:-1)==1) {
					other._sign = '+';
					return  1;		
				}
				else {
					thisNum._sign = '+';
					return 0;
				}
			else if((thisNum._sign == '+' ?-1:1)==1) {
				other._sign=thisNum._sign='+';
				return 2;
			}
		}
		else {
			if(flag == 2)
				other._sign=thisNum._sign='-';
			else if(flag==1&&!(other._bigNum.size()==1&&other._bigNum.get(0)==0))
				other._sign='-';
			else if (flag == 0&&!(thisNum._bigNum.size()==1&&thisNum._bigNum.get(0)==0))
				thisNum._sign = '-';
			}
		return flag;
	}//end of absVal
	
	/**
	 * private method used to pad the smaller number with zeros to match the greater's digits number and 
	 * removes them when done to keep the original number in the correct form
	 * used by the plus and minus functions 
	 * additionSize indicates the number of zeros needs to be added or if its negative to remove any leading zeros 
	 * @parameter BigInt smaller addidtionSize  
	 */
	private void numberPadding(BigInt smaller,int additonSize) {
		if(additonSize>0) {
			while(additonSize>0) {
				smaller._bigNum.add(0);
				additonSize--;
			}
		}
		if(additonSize==-1)//removing any leading zeros remained after padding 
			for(int i = smaller._bigNum.size()-1;i>0;i--){
				if(smaller._bigNum.get(i)==0)
					smaller._bigNum.remove(i);
				else 
					return;
			}
	}//end of numberPadding
	
	/**
	 * private method used by the constructor
	 * removes leading zeros and adds a minus sigh if it exists
	 * @parameter BigInt smaller addidtionSize  
	 * @return string without any leading zero[unnecessarily a valid number's representation ]
	 * @throws IllegalArgumentException in case sign exists outside its valid location 
	 */
	private String removeNulls(String bigNumber)throws IllegalArgumentException {
		boolean flag = true;
		char sign= ' ';
		int i = 0;
		
		if(bigNumber.equals("0")) {
			return bigNumber;
		}
		if(bigNumber.charAt(i)=='-'||bigNumber.charAt(i)=='+') {
			sign =bigNumber.charAt(i);
			i++;
		}
		while(bigNumber.length()>i&&flag==true) {
			if(bigNumber.charAt(i)=='0') 
				i++;
			else {
				flag = false;
				if(bigNumber.charAt(i)=='-'||bigNumber.charAt(i)=='+')
					throw new IllegalArgumentException("Invalid input - NaN");
			}
		}
		if (i==0)//no leading zeros or excessive signs
			return bigNumber;
		if (i==bigNumber.length())//number is zero case
			return "0";
		bigNumber = bigNumber.substring(i,bigNumber.length());//removes leading zeros
		if(sign=='-')
			bigNumber = sign + bigNumber;
		return bigNumber;
	}// end of removeNulls
}// end of BigInt class





