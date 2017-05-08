//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            (Warp Star Taxi)
// Files:            (Planet.java)
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
import java.util.ArrayList;
import java.util.Random;

/**
 * Instances of this class represent planets that the player will need to avoid
 * traveling into at warp speed. At any given time, one planet will be marked as
 * the current destination which the taxi must reach and use their thrusters to
 * collide with in order to drop of their next passenger. Doing this will
 * ultimately change which planet is marked at the destination, or if after all
 * planets have been visited, the player will have beat the current level.
 * 
 * This class should contain and make use of the following private fields:
 * 
 * private Graphic graphic 
 * private boolean isDestination
 * @author Weipeng & Pamela 
 */
public class Planet {
	private Graphic graphic;
	private boolean isDestination;

	/**
	 * Initialize this planet to be displayed at the specified position.
	 *
	 * @param x - is the horizontal coordinate of this planet's initial position
	 * @param y - is the vertical coordinate of this planet's initial position
	 */
	public Planet(float x, float y) {
		graphic = new Graphic("PLANET");
		graphic.setX(x);
		graphic.setY(y);
	}

	/**
	 * Initialized this planet to be displayed at a randomly chose position, and
	 * ensures that this randomly chosen position does not overlap with the
	 * position of a previously created Planet. 
	 * 
	 * @param rng - is used to generate random positions - using nextFloat()
	 * @param planets - is the collection of planets that this new planet cannot be
	 *          overlapping (while it is, a new random position must be
	 *          generated)
	 */
	public Planet(Random rng, ArrayList<Planet> planets) {
		boolean overlap = true;
		graphic = new Graphic("PLANET");
		// create planets that do not overlap
		while (overlap) {
			// create a planet
			graphic.setX(rng.nextFloat() * GameEngine.getWidth());
			graphic.setY(rng.nextFloat() * GameEngine.getHeight());
			overlap = false;
			// leave the while loop if new planet is not overlapping
			// with the previous ones
			for (int i = 0; i < planets.size(); ++i) {
				if (planets.get(i).graphic.isCollidingWith(graphic)) {
					overlap = true;
				}
			}
		}
	}

	/**
	 * This method detects and handles collisions between taxi and planets that
	 * result in either crashing: when traveling at warp speed, or in reaching a
	 * destination and progressing through the current level. Parameters:
	 * 
	 * @param taxi - the taxi that might be colliding with this planet
	 * @Return true when the taxi safely lands on this planet and this planet
	 *           is marked as the current destination, otherwise it returns
	 *           false
	 */
	public boolean handleLanding(Taxi taxi) {
		boolean safe = false;
		// set safe to true if colliding with destination in thruster speed
		if (graphic.getAppearance().equals("DESTINATION") 
				&& taxi.checkCollision(graphic) == true
				&& taxi.isTravellingAtWarp() == false) {
			safe = true;
		}
		// set safe to false if colliding with destination in warp speed
		if (taxi.checkCollision(graphic) == true 
				&& taxi.isTravellingAtWarp() == true) {
			taxi.crash();
			safe = false;
		}
		return safe;

	}

	/**
	 * This method set the current planet to either be the current destination
	 * or not, and updates the appearance of it's graphic accordingly
	 * Parameters:
	 * 
	 * @param isDestination - is true when this planet is being marked as the current
	 *                destination, and false when it is being un-marked or
	 *                returned to its status as a normal planet
	 */
	public void setDestination(boolean isDestination) {
		if (isDestination == true) {
			graphic.setAppearance("DESTINATION");
		} else {
			graphic.setAppearance("PLANET");
		}
	}

	/**
	 * This method simply draws the current planet at it's current position.
	 * Parameters:
	 * 
	 * @param time - is the number of milliseconds that have elapsed since the last
	 *       update
	 */
	public void update(int time) {
		graphic.draw();
	}
}
