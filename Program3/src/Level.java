
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            (Warp Star Taxi)
// Files:            (Level.java)
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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This Level class is responsible for managing all of the objects in your game
 * The GameEngine creates a new Level object for each level, and then calls that
 * Level object's update() method repeatedly until it returns either "ADVANCE"
 * (to proceed to the next level) or "QUIT" (to end the entire game). <br/>
 * <br/>
 * This class should contain and make use of the following private fields:
 * <tt><ul>
 * <li>private Random rng</li>
 * <li>private Taxi taxi</li>
 * <li>private ArrayList<WarpStar> warpStars</li>
 * <li>private ArrayList<GasCloud> gasClouds</li>
 * <li>private ArrayList<Planet> planets</li>
 * <li>private int destinationPlanetIndex</li>
 * </ul></tt>
 * @author Weipeng & Pamela 
 */

public class Level {
	// private and static fields
	private Graphic graphic;
	private float thrusterSpeed;
	private Random rng;
	private Taxi taxi;
	private ArrayList<WarpStar> warpStars;
	private ArrayList<GasCloud> gasClouds;
	private ArrayList<Planet> planets;
	private int destinationPlanetIndex;
	private final static int NUM_WARPSTARS = 6;
	private final static int NUM_GasCloud = 6;
	private final static int NUM_Planet = 6;

	/**
	 * This constructor initializes a new level object, so that the GameEngine
	 * can begin calling its update() method to advance the game's play. In the
	 * process of this initialization, all of the objects in the current level
	 * should be instantiated and initialized to their starting states.
	 * 
	 * @param rng
	 *            is the ONLY Random number generator that should be used by
	 *            throughout this level and by any of the objects within it.
	 * @param levelFilename
	 *            is either null (when a random level should be loaded) or a
	 *            reference to the custom level file that should be loaded.
	 */
	public Level(Random rng, String levelFilename) {// this is the level class
													// constructor
		// set destinationPlanetIndex to 0
		this.destinationPlanetIndex = 0;
		// set up arraylists
		warpStars = new ArrayList<WarpStar>();
		gasClouds = new ArrayList<GasCloud>();
		planets = new ArrayList<Planet>();
		// set up random generator
		this.rng = rng;
		// this loads randomLevel or customLevel
		if (levelFilename == null || loadCustomLevel(levelFilename) == false) {
			loadRandomLevel();
		}
		// set up the destination planet
		planets.get(this.destinationPlanetIndex).setDestination(true);

	}

	/**
	 * The GameEngine calls this method repeatedly to update all of the objects
	 * within your game, and to enforce all of your game's rules.
	 * 
	 * @param time
	 *            is the time in milliseconds that have elapsed since the last
	 *            time this method was called (or your constructor was called).
	 *            This can be used to help control the speed of moving objects
	 *            within your game.
	 * @return "CONTINUE", "ADVANCE", or "QUIT". When this method returns
	 *         "CONTINUE" the GameEngine will continue to play your game by
	 *         repeatedly calling it's update() method. Returning "ADVANCE"
	 *         instructs the GameEngine to end the current level, create a new
	 *         level, and to start updating that new level object instead of the
	 *         current one. Returning "QUIT" instructs the GameEngine to end the
	 *         entire game. In the case of either "QUIT" or "ADVANCE" being
	 *         returned, the GameEngine presents a short pause and transition
	 *         message to help the player notice the change.
	 */
	public String update(int time) {
		// update warp star's status
		for (int i = 0; i < warpStars.size(); ++i) {

			warpStars.get(i).update();
			warpStars.get(i).handleNavigation(taxi);

		}
		// remove gasClouds when necessary
		for (int i = gasClouds.size() - 1; i >= 0; i--) {
			if (gasClouds.get(i).shouldRemove()) {
				gasClouds.remove(i);
			}
		}
		// update the status of gasClouds
		for (int i = 0; i < gasClouds.size(); ++i) {

			gasClouds.get(i).update(time);
			gasClouds.get(i).handleFueling(taxi);
		}
		// update the status of planets
		for (int i = 0; i < planets.size(); ++i) {
			planets.get(i).update(time);
			if (planets.get(i).handleLanding(taxi) == true) {
				planets.get(this.destinationPlanetIndex).setDestination(false);
				++this.destinationPlanetIndex;
				if (this.destinationPlanetIndex < planets.size()) {
					planets.get(this.destinationPlanetIndex).setDestination(true);
				}
			}
		}
		// quit the game when game ends
		if (taxi.update(time) == true) {
			return "QUIT";
		}
		// go to the next level
		if (this.destinationPlanetIndex >= planets.size()) {
			return "ADVANCE";
		}
		// keep calling the update method
		return "CONTINUE";
	}

	/**
	 * This method returns a string of text that will be displayed in the upper
	 * left hand corner of the game window. Ultimately this text should convey
	 * the taxi's fuel level, and their progress through the destinations.
	 * However, this may also be useful for temporarily displaying messages that
	 * help you to debug your game.
	 * 
	 * @return a string of text to be displayed in the upper-left hand corner of
	 *         the screen by the GameEngine.
	 */
	public String getHUDMessage() {
		// display the amount of taxi fuel
		// display texts when the taxi crashes or has no fuel
		if (taxi.hasCrashed() == true) {
			return "You've crashed into a planet!\nPress the SPACEBAR to"
					+ " " + "end this game.";
		} else if (taxi.getFuel() == 0) {
			return "You've run out of fuel!\nPress the SPACEBAR to end this game.";
		}
		// display the number of planets visited
		return "Fuel: " + taxi.getFuel() + "\n" + "Fares: " 
		+ this.destinationPlanetIndex + "/" + planets.size();
	}

	/**
	 * This method initializes the current level to contain a single taxi in the
	 * center of the screen, along with 6 randomly positioned objects of each of
	 * the following types: warp stars, gasClouds, and planets.
	 */
	private void loadRandomLevel() {
		// put taxi to the middle of the screen
		taxi = new Taxi(GameEngine.getWidth() / 2, GameEngine.getHeight() / 2);
		// add Warpstars, Gasclouds, and Planets
		for (int i = 0; i < NUM_WARPSTARS; ++i) {
			warpStars.add(
					new WarpStar(rng.nextFloat() * GameEngine.getWidth(), 
							rng.nextFloat() * GameEngine.getHeight()));

		}
		for (int i = 0; i < NUM_GasCloud; ++i) {
			gasClouds.add(new GasCloud(rng.nextFloat() * GameEngine.getWidth(),
					rng.nextFloat() * GameEngine.getHeight(), 
					(float) Math.PI * 2 * rng.nextFloat()));

		}
		for (int i = 0; i < NUM_Planet; ++i) {
			if (i < 1) { // run the first planet constructor
				planets.add(
						new Planet(rng.nextFloat() * GameEngine.getWidth(), 
								rng.nextFloat() * GameEngine.getHeight()));
			} else { // run the second planet constructor that can avoid planet
						// overlapping
				planets.add(new Planet(rng, planets));
			}

		}
	}

	/**
	 * This method initializes the current level to contain each of the objects
	 * described in the lines of text from the specified file. Each line in this
	 * file contains the type of an object followed by the position that it
	 * should be initialized to start the level.
	 * 
	 * @param levelFilename
	 *            is the name of the file (relative to the current working
	 *            directory) that these object types and positions are loaded
	 *            from
	 * @return true after the specified file's contents are successfully loaded
	 *         and false whenever any problems are encountered related to this
	 *         loading
	 */
	private boolean loadCustomLevel(String levelFilename) {
		{
			// set up variables
			File file = null;
			final int EXPECTED_NUM_FIELDS = 2;
			final int NAME_FIELD_INDEX = 0;
			final int COORDS_FIELD_INDEX = 1;
			final int EXPECTED_NUM_COORDS = 2;
			final int X_INDEX = 0;
			final int Y_INDEX = 1;
			file = new File(levelFilename);
			Scanner input = null;
			// try scanning configurations from the file
			try {
				input = new Scanner(file);
				// scan when there is nextline
				while (input.hasNextLine()) {
					String line = input.nextLine();
					String[] parts = line.split("@");
					// check if file format is correct, if not, return false
					if (parts.length != EXPECTED_NUM_FIELDS) {
						return false;
					}
					String[] coords = parts[COORDS_FIELD_INDEX].trim().
							split(",");
					if (coords.length != EXPECTED_NUM_COORDS) {
						return false;
					}
					try {
						// try conversion to primitive data types
						String type = parts[0].trim();
						float x = Float.parseFloat(coords[X_INDEX].trim());
						float y = Float.parseFloat(coords[Y_INDEX].trim());
						// when no exception is thrown, built the game
						if (type.equals("GAS")) {
							gasClouds.add(new GasCloud(x, y, (float) Math.PI 
									* 2 * rng.nextFloat()));
						}
						if (type.equals("PLANET")) {
							planets.add(new Planet(x, y));
						}
						if (type.equals("WARP_STAR")) {
							warpStars.add(new WarpStar(x, y));
						}
						if (type.equals("TAXI")) {
							taxi = new Taxi(x, y);
						}
					} catch (NumberFormatException e) { // catch the exception
						return false;
					}
				}
			} catch (FileNotFoundException e) {
				return false;
			} finally {
				// make sure to close the Scanner when done.
				if (input != null) {
					input.close();
				}
			}
			return true;
		}
	}

	/**
	 * This method creates and runs a new GameEngine with its first Level. Any
	 * command line arguments passed into this program are treated as a list of
	 * custom level filenames that should be played in order by the player.
	 * 
	 * @param args
	 *            is the sequence of costumed level filenames to play through
	 */
	public static void main(String[] args) {
		GameEngine.start(null, args);
	}
}
