
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            (GameOfLife)
// Files:            (GameOfLife.java)
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
// Partner Name:     (Pamela Malasi)
// Partner Email:    (malasi@wisc.edu)
// Partner CS Login: (pamala)
// Lecturer's Name:  (Gary Dahl)
// Lab Section:      (342)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    _X_ Write-up states that Pair Programming is allowed for this assignment.
//    _X_ We have both read the CS302 Pair Programming policy.
//    _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.
//
// Persons:          (null)
// Online Sources:   (null)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Random;
import java.util.Scanner;

/**
 * (The program allows the user to play the game Game of Life. The following
 * patterns can be selected: Glider, Beacon, Beehive, P-pentomino, and Random.
 * The player can also costumize the pattern. The player can choose to see
 * different generations of cell pattern by pressing enter. The player can enter
 * "end" to exit the pattern. The player can then enter "7" to exit the game.)
 *
 * &lt;p&gt;Bugs: (NA)
 *
 * @author (Weipeng Zhou, Pamela Malasi)
 */
public class GameOfLife {
	Scanner input = new Scanner(System.in);

	/**
	 * (This is the main method of the program. It allows the player to choose
	 * the pattern they want to play and allow them to choose to exit the game.)
	 *
	 */
	public static void main(String[] args) {

		// setup variables
		boolean[][] world = new boolean[Config.WORLD_ROWS][Config.WORLD_COLUMNS];
		boolean exit = false; // check if the player wants to exit or not
		Scanner input = new Scanner(System.in);

		// ask for the player's choice
		System.out.print("Welcome to Conway's Game Of Life\n---------------------" + "-----------\n1"
				+ ")Glider 2)Beacon 3)Beehive " + "4)R-pentomino\n5)Random "
				+ "6)Custom or 7)Exit\nChoose a pattern: ");

		// this loop allows the player to choose the way (pattern) they like to
		// play the game
		while (!exit) {

			// check the validity of input pattern
			boolean hasnoInt = true;
			int pattern = 0;

			// System.out.println("=======");
			pattern = getIntChoice(input, 1, 7);

			// draw and run the pre-set pattern
			if (pattern == 1) {
				initializeGliderWorld(world);
				runSimulation(input, world);
			} else if (pattern == 2) {
				initializeBeaconWorld(world);
				runSimulation(input, world);
			} else if (pattern == 3) {
				initializeBeehiveWorld(world);
				runSimulation(input, world);
			} else if (pattern == 4) {
				initializeRpentominoWorld(world);
				runSimulation(input, world);
			} else if (pattern == 5) {
				initializeRandomWorld(world);
				runSimulation(input, world);
			} else if (pattern == 6) {
				initializeCustomWorld(input, world);
				runSimulation(input, world);
			} else if (pattern == 7) {
				exit = true;
			}
		}

		// end of the game
		System.out.println("----------------------------\nEnd of Conway's " + "Game Of Life");
	}

	/**
	 * Prints out the world showing each cell as alive or dead.
	 * 
	 * Loops through every cell of the world. If a cell is alive, print out the
	 * Config.ALIVE character, otherwise print out the Config.DEAD character.
	 * 
	 * Counts how many cells are alive and prints out the number of cells alive.
	 * For 2 or more cells alive, for 1 cell alive and 0 cells alive the
	 * following messages are printed: 5 cells are alive. 1 cell is alive. No
	 * cells are alive.
	 * 
	 * @param world
	 *            The array representation of the current world.
	 */
	public static void printWorld(boolean[][] world) {

		// print the world array
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (world[i][j] == false) {
					System.out.print(Config.DEAD);
				} else {
					System.out.print(Config.ALIVE);
				}
			}
			System.out.println();
		}

	}

	/**
	 * This method clears the world by setting all the cells in the world to
	 * false (dead). This method uses array lengths, not constants, to determine
	 * the size of the world.
	 * 
	 * @param world
	 *            the world to clear
	 */
	public static void clearWorld(boolean[][] world) {

		// set the world array to all false
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				world[i][j] = false;
			}
		}

	}

	/**
	 * This method expects an integer to be entered by the user between and
	 * including the values of min and max. If the value entered is not an
	 * integer or is an integer but less than min or greater than max the
	 * following message is displayed: Enter a number between 1 and 5: Assuming
	 * that min was 1 and max was 5 when this method was called.
	 * 
	 * @param input
	 *            The Scanner instance for reading from System.in.
	 * @param min
	 *            The minimum acceptable integer.
	 * @param max
	 *            The maximum acceptable integer.
	 * @return An integer between and including min and max.
	 */
	public static int getIntChoice(Scanner input, int min, int max) {

		// setup variables
		boolean hasnoInt = true; // if the input has an integer or not
		int pattern = 0; // the input pattern number

		// get an integer
		while (hasnoInt == true) {

			if (input.hasNextInt() == false) {
				input.nextLine();
				System.out.print("Enter a number between 1 and 7: ");

			} else {
				pattern = input.nextInt();
				if (pattern <= 7 && pattern >= 1) {
					hasnoInt = false;
					input.nextLine();
					System.out.print("");
				} else {
					input.nextLine();
					System.out.print("Enter a number between 1 and 7: ");
				}
			}
		}

		return pattern;
	}

	/**
	 * Initializes the world to the Glider pattern.
	 * 
	 * <pre>
	 * ..........
	 * .@........
	 * ..@@......
	 * .@@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * </pre>
	 * 
	 * The world may have any pattern within it when passed into this method.
	 * After this method call, the only living cells in the world is the Glider
	 * pattern as shown.
	 * 
	 * @param world
	 *            the existing double dimension array that will be reinitialized
	 *            to the Glider pattern.
	 */
	public static void initializeGliderWorld(boolean[][] world) {
		clearWorld(world);
		world[1][1] = true;
		world[2][2] = true;
		world[3][2] = true;
		world[3][1] = true;
		world[2][3] = true;
	}

	/**
	 * Initializes the world to the Beacon pattern.
	 * 
	 * <pre>
	 * ..........
	 * .@@.......
	 * .@........
	 * ....@.....
	 * ...@@.....
	 * ..........
	 * ..........
	 * ..........
	 * </pre>
	 * 
	 * The world may have any pattern within it when passed into this method.
	 * After this method call, the only living cells in the world is the Beacon
	 * pattern as shown.
	 *
	 * @param world
	 *            the existing 2-dimension array that will be reinitialized to
	 *            the Beacon pattern.
	 */
	public static void initializeBeaconWorld(boolean[][] world) {
		clearWorld(world);
		world[1][1] = true;
		world[2][1] = true;
		world[1][2] = true;
		world[4][3] = true;
		world[3][4] = true;
		world[4][4] = true;
	}

	/**
	 * Initializes the world to the Beehive pattern.
	 * 
	 * <pre>
	 * ..........
	 * ..@@......
	 * .@..@.....
	 * ..@@......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * </pre>
	 * 
	 * The world may have any pattern within it when passed into this method.
	 * After this method call, the only living cells in the world is the Beehive
	 * pattern as shown.
	 *
	 * @param world
	 *            the existing double dimension array that will be reinitialized
	 *            to the Beehive pattern.
	 */
	public static void initializeBeehiveWorld(boolean[][] world) {
		clearWorld(world);
		world[1][2] = true;
		world[1][3] = true;
		world[2][1] = true;
		world[2][4] = true;
		world[3][2] = true;
		world[3][3] = true;
	}

	/**
	 * Initializes the world to the R-pentomino pattern.
	 * 
	 * <pre>
	 * ..........
	 * ..@@......
	 * .@@.......
	 * ..@.......
	 * ..........
	 * ..........
	 * ..........
	 * ..........
	 * </pre>
	 * 
	 * The world may have any pattern within it when passed into this method.
	 * After this method call, the only living cells in the world is the
	 * R-pentomino pattern as shown.
	 * 
	 * @param world
	 *            the existing double dimension array that will be reinitialized
	 *            to the R-pentomino pattern.
	 */
	public static void initializeRpentominoWorld(boolean[][] world) {
		clearWorld(world);
		world[2][1] = true;
		world[3][2] = true;
		world[1][2] = true;
		world[2][2] = true;
		world[1][3] = true;
	}

	/**
	 * Initialize the GameOfLife world with a random selection of cells alive.
	 * 
	 * For testing purposes, implementations should use the Config.CHANCE_ALIVE
	 * constant and Config.SEED. Create an instance of the java.util.Random
	 * class, setting the seed to the SEED constant. For each cell, if the
	 * returned value of the nextDouble() method is less than
	 * Config.CHANCE_ALIVE then the cell is alive.
	 * 
	 * @param world
	 *            the existing double dimension array that will be reinitialized
	 *            to a Random pattern.
	 */
	public static void initializeRandomWorld(boolean[][] world) {
		clearWorld(world);
		Random rand = new Random(Config.SEED);
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[i].length; j++) {
				if (rand.nextDouble() < Config.CHANCE_ALIVE) {
					world[i][j] = true;
				}
			}
		}
	}

	/**
	 * Prompt for a pattern of cells. Each line of input corresponds to one row
	 * in the world. Continue reading lines until 'END' is entered on a line of
	 * its own. Ignore case and leading and trailing spaces when comparing to
	 * 'END'. (See String methods such as trim() method.)
	 * 
	 * The maximum size is the size of the world passed into this method. Any
	 * additional characters typed by the user are ignored. When interpreting
	 * the characters typed in, only the Config.ALIVE character is used to
	 * determine which cells are alive. All other characters are interpreted as
	 * dead cells.
	 * 
	 * @param input
	 *            The Scanner instance that reads from System.in.
	 * @param world
	 *            The world array that is filled with the pattern the user
	 *            enters.
	 */
	public static void initializeCustomWorld(Scanner input, boolean[][] world) {

		clearWorld(world);

		// ask the player to input his/er pattern
		System.out.println("Enter a pattern using " + Config.ALIVE + " for alive and " + Config.DEAD
				+ " as dead cells.\nTo end the pattern, type END on its own line.");
		String nextLine;
		int rowCount = 0;

		// scan each alive cell from the input pattern
		while (true) {
			nextLine = input.nextLine().trim().toLowerCase();
			if (nextLine.equals("end")) {
				break;
			}
			for (int j = 0; j < nextLine.length(); j++) {
				if (nextLine.charAt(j) == Config.ALIVE && rowCount < world.length && j < world[rowCount].length) {
					world[rowCount][j] = true;
				}
			}
			++rowCount;

		}
	}

	/**
	 * Checks whether a specific cell is alive or dead.
	 * 
	 * Note that cellRow and cellColumn may not be valid indexes into the world
	 * array. Return false for any cell outside the world array. Checks the
	 * values of cellRow and cellColumn to make sure they are valid prior to
	 * looking in the world array. Does not use try-catch blocks or other
	 * exception handling mechanisms.
	 * 
	 * @param world
	 *            The current world.
	 * @param cellRow
	 *            The row of the cell which we are wanting to know whether it is
	 *            alive.
	 * @param cellColumn
	 *            The column of the cell which we are wanting to know whether it
	 *            is alive.
	 * 
	 * @return Whether the specified cell is alive.
	 */
	public static boolean isCellAlive(boolean[][] world, int cellRow, int cellColumn) {

		boolean aliveOrNot; // if the cell is alive or not alive

		// check if a cell is alive or not
		if (world.length > cellRow && world[0].length > cellColumn && cellRow >= 0 && cellColumn >= 0
				&& world[cellRow][cellColumn] == true) {
			aliveOrNot = true;
		} else {
			aliveOrNot = false;
		}

		return aliveOrNot;
	}

	/**
	 * Counts the number of neighbors that are currently living around the
	 * specified cell.
	 *
	 * A cell has eight neighbors. The neighbors are the cells that are
	 * horizontally, vertically and diagonally adjacent.
	 * 
	 * Calls the isCellAlive method to determine whether any specific cell is
	 * alive.
	 * 
	 * @param world
	 *            The current world.
	 * @param row
	 *            The row of the cell for which we are looking for living
	 *            neighbors.
	 * @param column
	 *            The column of the cell for which we are looking for living
	 *            neighbors.
	 * 
	 * @return The number of neighbor cells that are currently living.
	 */
	public static int numNeighborsAlive(boolean[][] world, int row, int column) {
		int numAlive = 0; // the number of alive cells

		// check if a cell is alive
		if (isCellAlive(world, row - 1, column) == true) {
			++numAlive;
		}
		if (isCellAlive(world, row + 1, column) == true) {
			++numAlive;
		}
		if (isCellAlive(world, row, column + 1) == true) {
			++numAlive;
		}
		if (isCellAlive(world, row, column - 1) == true) {
			++numAlive;
		}
		if (isCellAlive(world, row - 1, column - 1) == true) {
			++numAlive;
		}
		if (isCellAlive(world, row + 1, column + 1) == true) {
			++numAlive;
		}
		if (isCellAlive(world, row - 1, column + 1) == true) {
			++numAlive;
		}
		if (isCellAlive(world, row + 1, column - 1) == true) {
			++numAlive;
		}

		return numAlive;
	}

	/**
	 * Whether a cell is living in the next generation of the game.
	 * 
	 * The rules of the game are as follows: 1) Any live cell with fewer than
	 * two live neighbors dies, as if caused by under-population. 2) Any live
	 * cell with two or three live neighbors lives on to the next generation. 3)
	 * Any live cell with more than three live neighbors dies, as if by
	 * overcrowding. 4) Any dead cell with exactly three live neighbors becomes
	 * a live cell, as if by reproduction.
	 * 
	 * @param numLivingNeighbors
	 *            The number of neighbors that are currently living.
	 * @param cellCurrentlyLiving
	 *            Whether the cell is currently living.
	 * 
	 * @return true if this cell is living in the next generation, otherwise
	 *         false.
	 */
	public static boolean isCellLivingInNextGeneration(int numLivingNeighbors, boolean cellCurrentlyLiving) {

		// if a cell is alive
		if (cellCurrentlyLiving == true) {
			if (numLivingNeighbors < 2 || numLivingNeighbors > 3) {
				return false;
			} else {
				return true;
			}
		}

		// if a cell is dead
		if (cellCurrentlyLiving == false) {
			if (numLivingNeighbors == 3) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * Determines the cells living in the next generation of the world. The next
	 * generation is created by applying the 4 rules simultaneously to every
	 * cell in the previous generation. Births and deaths occur simultaneously.
	 * In other words, look only at whether cells are living in the current
	 * generation to determine whether a cell lives in the new generation. Don't
	 * look at other cells in the new generation.
	 * 
	 * For each cell in the current generation, determine whether the cell at
	 * the same coordinates is living in the next generation using the
	 * numNeighborsAlive and the isCellLivingInNextGeneration methods.
	 * 
	 * @param currentWorld
	 *            The world currently shown.
	 * @param newWorld
	 *            The new world based on the rules of life.
	 */
	public static void nextGeneration(boolean[][] currentWorld, boolean[][] newWorld) {

		clearWorld(newWorld);
		
		//setup the next generation of the world
		for (int i = 0; i < currentWorld.length; i++) {
			for (int j = 0; j < currentWorld[i].length; j++) {
				int numLivingNeighbors = numNeighborsAlive(currentWorld, i, j);
				boolean cellCurrentlyLiving = isCellAlive(currentWorld, i, j);
				newWorld[i][j] = isCellLivingInNextGeneration(numLivingNeighbors, cellCurrentlyLiving);
			}
		}
	}

	/**
	 * This shows each generation of the simulation starting with generation 0.
	 * The display of the world is by calling the printWorld method and then
	 * prompting the user for whether to calculate and show the next generation.
	 * Then, for any input other then 'end', this calculates the next generation
	 * using the nextGeneration method and shows it. Any case of 'end' is
	 * acceptable, and also ignore leading and trailing whitespace. (See String
	 * trim() method.)
	 * 
	 * Note that any number of generations are possible to implement with only
	 * the world passed as the parameter and one other 2-dimensional array the
	 * same size. Create the second world the same size as the world passed in,
	 * by using the length attributes rather than using constant values. The
	 * world passed in will be rectangular and not irregular.
	 * 
	 * @param input
	 *            The Scanner object used to read from System.in.
	 * @param world
	 *            The initialized world to show as generation 0.
	 */
	public static void runSimulation(Scanner input, boolean[][] world) {
		
		int generationCount = 0; //the number of generations 
		boolean continuous = true; //if go inside the loop or not

		//output the generations and print the corresponding world
		while (continuous) {
			System.out.println();
			System.out.println("Generation: " + generationCount);
			boolean[][] newWorld = new boolean[world.length][world[0].length];
			printWorld(world);

			// count alive cells
			int aliveNum = 0;
			for (int i = 0; i < world.length; i++) {
				for (int j = 0; j < world[i].length; j++) {
					if (world[i][j] == true) {
						++aliveNum;
					}
				}
			}

			// ask if want to continue or end
			if (aliveNum > 0) {
				if(aliveNum == 1){
					System.out.println("1 cell is alive.");
				}
				else{System.out.println(aliveNum + " cells are alive.");}
			} else {
				System.out.println("No cells are alive.");
			}
			System.out.print("Press Enter for next generation, 'end' to stop: ");
			String continueOrNot = input.nextLine().trim().toLowerCase();
			nextGeneration(world, newWorld);
			world = newWorld;
			if (continueOrNot.equals("end")) {
				System.out.print("1)Glider 2)Beacon 3)Beehive 4)R-pentomino\n5)Random" + " 6)Custom or 7)"
						+ "Exit\nChoose a pattern: ");
				continuous = false;
			} else
				generationCount++;

		}

	}
}