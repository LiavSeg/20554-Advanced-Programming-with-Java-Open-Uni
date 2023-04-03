import java.util.ArrayList;
import java.util.Scanner;
public class RegServices {

 /*main method uses the CashRegister services
  * uses BillLine, CashRegister and Item classes
  * assumes  inputs are valid 
  */
	public static void main(String[] args) {
		ArrayList<Item> inventory = new ArrayList<Item>();
		CashReg register  = new CashReg();
		try (Scanner scan = new Scanner(System.in)) {
			Item tempItem;
			boolean flag;
			int units ;
			double earns;
			double payment;
			String str = "";
			storeInventory(inventory);
			while(!str.equals("bye")) {
				System.out.println("Hello,welcome to our thirft shop. How can i help you today ?");
				System.out.println("For shopping spree please press $$$");
				str =scan.nextLine();
				if(str.equals("$$$")) {
					System.out.println("Which product would you like to get?");
					System.out.println("Our current inventory is:");
					for(Item element: inventory) 
						System.out.println(element.getName());
					while(!str.equals("no")) {
						str = scan.nextLine();
						flag = inverntorySerach(inventory,str);	
						while(flag == false) {	
								str = scan.nextLine();
								flag = inverntorySerach(inventory,str);
							}
							System.out.println("Great, how many units?");
							units = scan.nextInt();
							tempItem = getItem(inventory,str);
							str = scan.nextLine();
							if(units == 0) {
								
							}
							if(units>0){
								register.addToBill(tempItem, units);
								System.out.println("You got it. Anything else ? yes/no/bill check");
								str = scan.nextLine();
								if(str.equals("bill check")) {
									register.printCurrSum();
									System.out.println("You got it. Anything else ? yes/no");
									str = scan.nextLine();
								}
								while(!str.equals("yes")&&!str.equals("no")) {
									System.out.println("Can you repeat your answer?");
									str = scan.nextLine();
								}
								if(str.equals("yes")) { 
								    System.out.println("Let us know which product please:");
								}

								}
							}
				}	
				System.out.println("\nOk,lets checkout");
				register.checkOut();
				payment =scan.nextDouble();
				register.billPayment(payment);
				while(payment<register.getSum()) {
					System.out.println("I am still waiting for the money ");
					payment =scan.nextDouble();
					register.billPayment(payment);
					

					}
				scan.nextLine();
				System.out.println("\nIs there a new costumer in line? say anything but bye if you are there!");
				str = scan.nextLine();
				}
			earns = register.getRegMoney();
			System.out.println("Time to close our thrift shop");
			System.out.println("Total earnings of this day:"+earns);
		}
		System.out.println("Goodbye");
		return ;	
		}

	

//fills a list of products
public static void storeInventory( ArrayList<Item> inventory){
	Item a = new Item("Banana",6.8);
	Item b = new Item("Tablet",2599.9);
	Item c = new Item("Coffe",15.9);
	Item d = new Item("Apple",8.99);
	Item e = new Item("Walnuts",15.46);
	Item f = new Item("Toothpaste",30.3);
	Item g = new Item("Microwave",299.9);
	Item h = new Item("Cucumber",100);
	Item i = new Item("Decaf Coffee",39.9);
	Item j = new Item("Oatmeal",5.99);
	Item k = new Item("Strawberries",19.9);
	Item l = new Item("Headphones",1000.0);
	Item m = new Item("Call of duty MW ",200.0);
	Item n = new Item("Hogwarts Legacy PC edition",250);
	Item o = new Item("Not apple Smartphone",2999);
	Item p = new Item("Stranger Things Funko Pop ",100.0);
	Item q = new Item("Blueberries",30.3);
	Item r = new Item("Tomato",30.3);
	Item s = new Item("Salt",3.99);
	Item t = new Item("Peper",1.99);
	Item u = new Item("Coconut",15.0);
	Item v = new Item("Raspberries",25.9);
	Item w = new Item("Cilnatro",4.5);
	Item x = new Item("Mochi",9.99);
	Item y = new Item("Rice",10.0);
	Item z = new Item("Blueberries",30.0);
	inventory.add(a);
	inventory.add(b);
	inventory.add(c);
	inventory.add(d);
	inventory.add(e);
	inventory.add(f);
	inventory.add(g);
	inventory.add(h);
	inventory.add(i);
	inventory.add(j);
	inventory.add(k);
	inventory.add(l);
	inventory.add(m);
	inventory.add(n);
	inventory.add(o);
	inventory.add(p);
	inventory.add(q);
	inventory.add(r);
	inventory.add(s);
	inventory.add(t);
	inventory.add(u);
	inventory.add(v);
	inventory.add(w);
	inventory.add(y);
	inventory.add(x);
	inventory.add(z);
	}	
//checks if the product is in stock
public static boolean inverntorySerach(ArrayList<Item> inventory,String str) {
	boolean  flag = false; //In case product isn't found 

	for(Item element: inventory) { 
		if(str.equals(element.getName())) {
			flag  = true;
			return flag;
		}
	}	
		 
	System.out.println("Sorry we cant find this product,can we offer you somthing else ?");
	return flag;

}

// gets an item from the inventory list 
public static Item getItem(ArrayList<Item> inventory,String str) {
	
	for(Item element: inventory) { 
		if(str.equals(element.getName())) {
			return element;
		}
	}	
		

	return null;

}





}