/*
 * This program implements a calculator that can perform elementary arithmetic operations on very large integers using the BigInt class functions .
 * prompts the user to enter two numbers and an arithmetic operation to perform on them.
 * checks that the input numbers are valid BigInts and the operation is valid before performing the calculation.
 * program ends by typing END after a successful calculation
 */
import java.util.Scanner;
public class BigCalculator {
	// static variables - id of each arithmetic operation 
	private static final int PLUS = 1;
	private  static final int MINUS = 2;
	private static final int MULTIPLY = 3;
	private static final int DIVIDE = 4;
	
	public static void main(String[]args) {
		//declarations
		Scanner scan = new Scanner(System.in);
		BigInt num1=null,num2=null,res1,res2,res3,res4;
		System.out.println("Hello this calculator can perform elementary arithmetic operations on very large integers ");		
		String s1,s2,end =" ";
		int flag = 0;
		//keeps calculating until user types END
		while(!end.equals("END")) {
			//gets the first number input and validating it
			System.out.println("Please provide the first number");
			s1 = scan.nextLine();
			while(flag!=1) {	
				try {
					num1 = new BigInt(s1);
					flag =1;
				}
				catch(IllegalArgumentException e) {
					System.out.println(s1+" Is not a valid number, \nplease provide a valid number because i wont let you go until you will");
					s1 = scan.nextLine();
				}	
			}		
			//gets the second number input and validating it
			System.out.println("Got it. Please provide the second number");
			flag  = 0;
			s2 = scan.nextLine();
			while(flag!=1) {
				try {
					num2 = new BigInt(s2);
					flag =1;
				}
				catch(IllegalArgumentException e) {
					System.out.println(s2+" Is not a valid number, \nplease provide a valid number because i wont let you go until you will");
					s2 = scan.nextLine();
				}		
			}
			System.out.println("Starts calculating");
			System.out.println("__________________________________________________________________________________\n");
			res1 = callOperation(num1,num2,PLUS);
			res2 = callOperation(num1,num2,MINUS);
			res3 = callOperation(num1,num2,MULTIPLY);
			res4 = callOperation(num1,num2,DIVIDE);
			res3 = callOperation(res2,res4,PLUS);
			res2 = callOperation(res2,res1,MINUS);
			res3 = callOperation(res3,res2,MULTIPLY);
			res4 = callOperation(res2,res3,DIVIDE);
			res4 = callOperation(num1,res2,DIVIDE);
			res3 = callOperation(res2,res1,PLUS);
			res2 = callOperation(res2,res1,MINUS);
			res3 = callOperation(res3,res1,MULTIPLY);
			res4 = callOperation(num2,res1,DIVIDE);
			System.out.println("__________________________________________________________________________________");
			System.out.println("If you would like to termintae the calculator please write END, else press any key");
			end = scan.nextLine();
			flag = 0;
		}
		scan.close();
		System.out.println("Calcualtor's operation had ended - program exiting");
	}//end of main
	
	
	/*
	 * this method calls for the right operation on the given input 
	 * checks the flag input and picks the right operation
	 * prints the opeartion's result in the format of A@B=C
	 * returns a BigInt object 
	 */
	public static BigInt callOperation(BigInt n1,BigInt n2,int flag)  {
		BigInt res= null;
		if(flag== PLUS) {
			res = n1.plus(n2);
			System.out.println("("+n1+")"+"+"+"("+n2+")"+" = "+res);
			return res;
		}
		else if(flag==MINUS) {
			res=n1.minus(n2);
			System.out.println("("+n1+")"+"-"+"("+n2+")"+" = "+res);
			return res;
		}
		else if(flag == MULTIPLY) {
			res= n1.multiply(n2);
			System.out.println("("+n1+")"+"*"+"("+n2+")"+" = "+res);
			return res;
		}
		else 
			try {
				res= n1.divide(n2);
				System.out.println("("+n1+")"+"/"+"("+n2+")"+" = "+res);
				return res;
			}
			catch(ArithmeticException e){
				System.out.println("/ by zero");
			}
		return n1;
	}
}//end of callOperation
