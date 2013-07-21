
/***********************************
 * Student Name : Thomas Khalil	   *		
 * ASSIGNMENT 2                    *
 ***********************************/

import java.util.Scanner;

public class testToy {

	public static void main(String[] args) {

		Toy[] toys = createLibrary();

		rangeEval(toys);

		toyHire(toys);

		toyReturn(toys);

		for (int i = 0; i < toys.length; i++) {
			if (toys[i] != null) {
				toys[i].print();
			}
		}



	}

	private static void toyReturn(Toy[] toys) {

		boolean rePlay = true;

		do {

			Scanner in = new Scanner(System.in);

			String returnToyID, returnDate,hireAgain,AvS;
			int toyExists = 0, returnable = 0;
			double returnFee;
			char Ava;


			System.out.print("To return a Toy, enter the toy ID: ");
			returnToyID = in.nextLine();

			System.out.print("Enter the return date (dd/mm/yyyy): ");
			returnDate = in.nextLine();



			for (int i = 0; i < toys.length; i++) {
				if (toys[i] != null) {

					if (returnToyID.equalsIgnoreCase(toys[i].getToyID())) { 
						toyExists++; 
						Ava = toys[i].getStatus(); 
						AvS = Character.toString(Ava); 

						if (AvS.equalsIgnoreCase("U")) { 

							returnFee = toys[i].returnToy(returnDate);
							System.out
							.print("The return was successfully processed."
									+ "the charge is: $");
							System.out.printf("%.2f", returnFee);
							System.out.println("\n");
							returnable++;
						} else {
							returnable=0;

						}
					}

				}

			}

	
			if (toyExists == 0) {
				System.out.println("Sorry , this toy doesn't exist "
						+ ", please check your toy ID and try again");
				in.reset();
				toyReturn(toys);
			}
			if (returnable == 0) {
				System.out.println("This toy is currently unavaliable for " +
						"returning .Please select another toy.");
				returnable = 0;
				in.reset();
				toyReturn(toys);

			}


			System.out.print("Any more returns (Y/N) ?");
			hireAgain = in.next();


			if (hireAgain.equalsIgnoreCase("y")) {
				rePlay = true;
			} else if (hireAgain.equalsIgnoreCase("n")) {
				rePlay = false;
			}
			in.reset();

		} while (rePlay == true);
	}

	private static Toy[] createLibrary() {
		//we create an array and our new toy objects
		Toy[] toys = new Toy[9];

		toys[0] = new Toy("TOY022","Train Set",2,0.30);
		toys[1] = new Toy("TOY093","Snake Rattle",0,0.10,'U',"MEM012",
				"23/02/2012");
		toys[2] = new Toy("TOY837","Home Science Kit",5,0.50);
		toys[3] = new Toy("TOY112","Lego Technic Car",9,0.50);
		toys[4] = new Toy("TOY009","Metal Tricycle",3,0.30,'U',"MEM034",
				"16/10/2011");
		toys[5] = new Toy("TOY981","Fabric Lion Puzzle",0,0.20);

		//Prints out a short list detailing the inventory of toy objects

		for (int i = 0; i < toys.length; i++) {
			if (toys[i]!=null) { 
				System.out.printf("%-9s",toys[i].getToyID());
				System.out.printf("%-20s",toys[i].getDesc());
				System.out.printf("%4s",toys[i].getAge()+"yo"+"\n");

			} 

		}
		return toys;
	}

	private static void rangeEval(Toy[] toys) {
		Scanner in = new Scanner(System.in);
		double lower, upper;
		int counter=0;

		System.out.println("\n");

		//This section of code is responsible for asking the user for lower
		// and upper monetary value from which the program can propose
		//toys from those  

		System.out.print("Enter a lower limit for daily hire rate: ");
		lower = in.nextDouble();
		System.out.println("");
		System.out.print("Enter an upper limit for daily hire rate: ");
		upper = in.nextDouble();

		for (int i = 0; i < toys.length; i++) {
			if (toys[i]!=null) {

				char test;

				Double comp;

				comp = toys[i].rate;
				test =toys[i].stat;


				if (comp>= lower && comp<=upper && test=='A') {


					System.out.printf("%-9s",toys[i].getToyID());
					System.out.printf("%-20s",toys[i].getDesc());
					System.out.printf("%4s",toys[i].getAge()+"yo");
					System.out.printf("%2s","$");
					System.out.printf("%.2f",+toys[i].getDailyrate());
					System.out.printf("%4s",toys[i].getStatus()+"\n");
					counter++;
				}

			} 
		}

		//Here we notify the user that no toys were found within his range
		//and proceeds to query the use user all over again for new parameters

		if(counter==0){
			System.out.println("There are no Toys within those hire " +
					"limits");
			rangeEval(toys);

		}

	} 

	private static void toyHire(Toy[] toys) {

		boolean rePlay = true;

		do {

			Scanner in = new Scanner(System.in);

			String memHireID, hireID,hireAgain,AvS;
			int toyExists = 0,unAvailable=0;
			boolean hireIndicator = false;
			char Ava;


			System.out.print("To hire a toy, enter a toy ID: ");
			hireID = in.nextLine();

			System.out.print("Enter your member ID: ");
			memHireID = in.nextLine();

			System.out.print("Enter the child's DOB: ");
			String DOB = in.nextLine();
			//	Check to see if toy exists
			for (int i = 0; i < toys.length; i++) {
				if (toys[i] != null) {

					if (hireID.equalsIgnoreCase(toys[i].getToyID())) { 

						toyExists++;									
						Ava =toys[i].getStatus();							
						AvS=Character.toString(Ava);						

						if (AvS.equalsIgnoreCase("U")) {					
							unAvailable++;									
						}
						else if (unAvailable==0) {
							hireIndicator = toys[i].borrowToy(memHireID, DOB);
						}
					}
				}

			}

			//These blocks of code are designed to handle negative events ,
			//such as when a user enters an invalid toy id , when the toy is 
			//unsuitable for the child based on the child's established age,
			// as well as positive events such as a completed hiring process.

			if (toyExists == 0) {
				System.out.println("Sorry , this toy doesn't exist "
						+ ", please check your toy ID and try again");
				in.reset();
				toyHire(toys);
			}
			if (unAvailable!=0) {
				System.out.println("This toy is currently unavaliable for" +
						" borrowing. Please select another toy.");
				unAvailable = 0;
				in.reset();
				toyHire(toys);

			}
			if (hireIndicator == true && toyExists != 0) {
				System.out.println("The hire was successfully processed");
			} else if (hireIndicator == false) {
				System.out.println("The child is too young to hire the toy");
				toyHire(toys);
			}

			System.out.print("Any more hires (Y/N) ?");
			hireAgain = in.next();


			if (hireAgain.equalsIgnoreCase("y")) {
				rePlay = true;
			} else if (hireAgain.equalsIgnoreCase("n")) {
				rePlay = false;
			}
			in.reset();

		} while (rePlay == true);
	}

}
