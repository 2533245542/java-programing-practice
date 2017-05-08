//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            (Container Calculator)
// Files:            (ContainerCalculator.java)
// Semester:         (CS 302) Spring 2017
//
// Author:           (Weipeng Zhou)
// Email:            (wzhou87@wisc.edu)
// CS Login:         (weipeng)
// Lecturer's Name:  (Jim Williams)
// Lab Section:      (324)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:     (name of your pair programming partner)
// Partner Email:    (email address of your programming partner)
// Partner CS Login: (your partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    ___ Write-up states that Pair Programming is allowed for this assignment.
//    ___ We have both read the CS302 Pair Programming policy.
//    ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.
//
// Persons:          (identify each person and describe their help in detail)
// Online Sources:   (identify each URL and describe its assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Scanner;

/**
 * (The program is to calculate the volume and surface area of a cylinder based on 
 * the input diameter and height. It would also validate if the input is greater than 
 * zero and smaller than 2,147,483,648. Based on the input, it would also output a 
 * diameter and height that makes the surface area as small as possible while 
 * maintaining a high volume. Still, based on the input, it would calculate the 
 * diameter and height that make the contain have a lowest aluminum paint weight 
 * while maintaining a high volume.)
 *
 * &lt;p&gt;Bugs: (NA)
 *
 * @author (Weipeng Zhou)  
 */
public class ContainerCalculator 
{
	/**
	 * (The program is to calculate the volume and surface area of a cylinder 
	 * based on the input diameter and height. It would also validate if the
	 *  input is greater than zero and smaller than 2,147,483,648 ). 
	 *  Based on the input, it would also output a diameter and height 
	 *  that makes the surface area as small as possible while maintaining 
	 *  a high volume. Still, based on the input, it would calculate the 
	 *  diameter and height that make the contain have a lowest aluminum 
	 *  paint weight while maintaining a high volume.)
	 *
	 */	
	public static void main(String[] args) 
	{
		//scanner setup
		Scanner scnr = new Scanner(System.in);
		
		//the diameter and height of a cylinder 
		int diameter;
		int height;
		
		//welcome words
		System.out.println("Welcome to the Container Calculator!");
		System.out.println("====================================");
		
		
		//ask the user to enter the diameter of a cylinder
		System.out.print("Enter the diameter of a cylinder (in centimeters): ");
	
// **************Milestone 2****************
		// check if the input is valid (diameter)
		
		// set up 
		int a = 0;
		int b = 0;
		int c = 0;
		int number = 0;
		
		while ( a == 0 && b == 0 && c ==0)
		{
			//check if it is a number, if not, then ask the user to input the value
			//again and the program goes back to the beginning by not jumping into 
			//the other two if's
			if (!(scnr.hasNextInt()))
			{
				scnr.nextLine();
				System.out.print("Please enter an integer value (less than 2,147,483,648) "
						+ "as decimal digits: ");
			}
			else 
			{
				a = 1;
				number = scnr.nextInt ();
				scnr.nextLine();
			}
			//check if it is a positive, if not, then ask the user to input the value 
			//again and the program goes back to the beginning by not jumping into the 
			//other one if
			
			if ( a == 1  )
			{
				if (!(number > 0))
				{
					System.out.print("Please enter a positive integer value: ");
					a = 0;
					b = 0;
				}
				else 
				{
					b = 1;
				}
			}
			
			//check if it is within 2147483648, if not, then ask the user to input the 
			//value again and the program goes back to the beginning by not jumping into 
			//the other two if's
			if ( (a == 1) && (b == 1))
			{
				if (! (number <= 2147483647))
				{
				System.out.print("Please enter an integer value (less than 2,147,483,648) "
						+ "as decimal digits: ");
				a = 0;
				b = 0;
				}
				else 
				{
					c = 1;
				}
			}
			
		}
		// input checking finished, assign the number to diameter
		diameter = number;
		// FINISHED: check if the input is valid (diameter) 
		//****************************//
		
		
		
		
		
		//ask the user to enter the height of a cylinder 
		
		System.out.print("Enter the height of a cylinder (in centimeters): ");
		
// **************Milestone 2****************
		// check if the input is valid (diameter)
		
		// rest booleans and number
		a = 0;
		b = 0;
		c = 0;
		number = 0;
		
		while ( a == 0 && b == 0 && c ==0)
		{
			//check if it is a number, if not, then ask the user to input the value 
			//again and the program goes back to the beginning by not jumping into 
			//the other two if's
			if((scnr.hasNextInt()))
			{
				a = 1;
				number = scnr.nextInt ();
				scnr.nextLine();
				
			}
			else
			{
				scnr.nextLine();
				
				System.out.print("Please enter an integer value (less than 2,147,483,648)"
						+ " as decimal digits: ");
				//scnr.nextLine();
			}
			
			//check if it is a positive, if not, then ask the user to input the value 
			//again and the program goes back to the beginning by not jumping into the
			//other one if
			
			if ( a == 1  )
			{
				if (!(number > 0))
				{
					System.out.print("Please enter a positive integer value: ");
					a = 0;
					b = 0;
				}
				else 
				{
					b = 1;
				}
			}
			
			//check if it is within 2147483648, if not, then ask the user to input
			//the value again and the program goes back to the beginning by not 
			//jumping into the other two if's
			if ( (a == 1) && (b == 1))
			{
				if (! (number <= 2147483647))
				{
				System.out.print("Please enter an integer value (less than 2,147,483,648) "
						+ "as decimal digits: ");
				a = 0;
				b = 0;
				}
				else 
				{
					c = 1;
				}
			}
			
		}
		// input checking finished, assign the number to height
		height = number;
		System.out.println("");
		
		// FINISHED: check if the input is valid (height) 
// **************Milestone 2 finished****************
			


		//calculation : surface area and volume
		double volume = Math.pow(0.5 * diameter , 2) * height * Math.PI;
		double area = Math.pow(0.5 * diameter , 2) * 2 * Math.PI + Math.PI * diameter *
				height;
		
		//Print pre volume printing statement
		System.out.println("A can with a diameter of " + diameter +  " and a height of "
		+ height + " has ");
		System.out.print("	a volume of ");
		
		//Print the volume
		System.out.printf("%.2f",volume); 
		System.out.print(",");
		System.out.println("");
		
		//print pre area printing statement
		System.out.print("	and a surface area of "); 
		
		//Print the area
		System.out.printf("%.2f",area); 
		System.out.print(".");
		System.out.println("");	
		System.out.println("");	

// **************Milestone 3****************		
		
	//Surface Area Optimizer
		System.out.println("*** Surface Area Optimizer ***");
		System.out.println("");
		
		//set up variables
		double maxvolume = 0;
		double minarea = 0;
		double volume1 = 0;
		double area1 = 0;
		int diameter1 = diameter;
		int height1 = height;
		maxvolume = volume;
		minarea = area;
		
		//find the minimum surface area and volume
		for (int i = 1; i <= (int)(volume); ++i)
		{
			for (int j = 1; j <= (int)(volume); ++j) 
			{
				volume1 = Math.pow(0.5 * i, 2) * j * Math.PI;
				area1 = Math.pow(0.5 * i, 2) * 2 * Math.PI + Math.PI * i * j;

				if (volume1 >= volume) 
				{
					if (area1 < minarea)
					{
						minarea = area1;
						maxvolume = volume1;
						diameter1 = i;
						height1 = j;
					}
					else{}

				} 
				else {}
			}
		}

		//print optimized volume and area
		
		//print pre volume printing statement
		System.out.println("A can with a diameter of " + diameter1 +  " and a height of " 
		+ height1 + " has ");
		System.out.print("	a volume of ");
		
		//Print the volume
		System.out.printf("%.2f", maxvolume); 
		System.out.print(",");
		System.out.println("");
		
		//print pre area printing statement
		System.out.print("	and a surface area of "); 
		
		//Print the area
		System.out.printf("%.2f", minarea); 
		System.out.print(".");
		System.out.println("");	
		System.out.println("");	
		
	//Aluminum Weight Optimizer
		System.out.println("*** Aluminum Weight Optimizer ***");
		System.out.println("");
		
		//set up variables
		maxvolume = 0;
		double minweight = 0;
		volume1 = 0;
		minarea = 0;
		area1 = 0;
		double weight1= 0;
		diameter1 = diameter;
		height1 = height;
		maxvolume = volume;
		minweight = 0.0355834 * (2 * Math.PI * 0.5 * diameter * height + 3 * 
				Math.PI * Math.pow(0.5 * diameter, 2) );
		
		//find the minimum weight and volume
		for (int i = 1; i <= (int)(volume); ++i)
		{
			for (int j = 1; j <= (int)(volume); ++j) 
			{
				volume1 = Math.pow(0.5 * i, 2) * j * Math.PI;
				weight1 = 0.0355834 * (2 * Math.PI * 0.5 * i * j + 3 *  Math.PI * 
						Math.pow(0.5 * i, 2) );
				area1 = Math.pow(0.5 * i, 2) * 2 * Math.PI + Math.PI * i * j;
				if (volume1 >= volume) 
				{
					if (weight1 < minweight)
					{
						minweight = weight1;
						maxvolume = volume1;
						diameter1 = i;
						height1 = j;
						minarea = area1;
					}
					else{}

				} 
				else {}
			}
		}

		//print optimized volume and weight
		
		//print pre volume printing statement
		System.out.println("A can with a diameter of " + diameter1 +  " and a height of " + 
		height1 + " has ");
		System.out.print("	a volume of ");
		
		//Print the volume
		System.out.printf("%.2f", maxvolume); 
		System.out.print(",");
		System.out.println("");
		
		//print pre area printing statement
		System.out.print("	a surface area of "); 
		
		//Print the area
		System.out.printf("%.2f", minarea); 
		System.out.print(",");
		System.out.println("");	
		
		//print pre weight printing statement
		System.out.print("	and an aluminum weight of "); 
		
		//Print the weight
		System.out.printf("%.2f", minweight); 
		System.out.print(".");
		System.out.println("");	

		//thank you words
		System.out.println("=============================================");
		System.out.print("Thank you for using the Container Calculator.");
	}
}
