//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            (Warp Star Taxi)
// Files:            (GasCloud.java)
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
/**
 * Instances of this class represent gas clouds that the player can collide
 * their taxi into in order to collect additional fuel.
 * 
 * This class should contain and make use of the following private fields:
 * 
 * private Graphic graphic 
 * private float rotationSpeed 
 * private boolean
 * @author Weipeng & Pamela 
 */

public class GasCloud {
	private Graphic graphic;
	private float rotationSpeed;
	private boolean shouldRemove;
	private float direction;
	private final static int FUEL_ADDED = 20;
	private final static float ROTATION_SPEED = 0.001f;

	/**
	 * Initializes a new GasCloud object to be displayed at the specified
	 * initial position and orientation.
	 * 
	 * @param x - is the initial horizontal position for this object's graphic 
	 * @param y - is the initial vertical position for this object's graphic 
	 * @param direction - is the initial orientation for this object's graphic
	 */
	public GasCloud(float x, float y, float direction) {
		// assign all the variables
		this.direction = direction;
		graphic = new Graphic("GAS");
		graphic.setX(x);
		graphic.setY(y);
		graphic.setDirection(direction);
		shouldRemove = false;
	}

	/**
	 * This method rotates the gas cloud before drawing in its new orientation.
	 * 
	 * @param time - is the number of milliseconds that have elapsed since
	 *              the last update
	 */
	public void update(int time) {
		direction += ROTATION_SPEED * time; // update direction
		graphic.setDirection(direction); // set new direction
		graphic.draw(); // draw
	}

	/**
	 * This accessor method retrieves whether this object should be remove from
	 * the level yet or not.
	 * 
	 * @Return true after the player has collected fuel from this object, and
	 *           returns false otherwise
	 */
	public boolean shouldRemove() {
		return this.shouldRemove;
	}

	/**
	 * This method detects whether the player's taxi is currently colliding with
	 * this gas cloud object or not. If it is, that taxi will get more fuel and
	 * this gas cloud will be marked for removal from the level.
	 * 
	 * @param taxi - is the taxi that will get addition fuel if it is
	 *              colliding with this GasCloud object
	 */
	public void handleFueling(Taxi taxi) {
		// add 20 fuel when getting clouds, and tell level to remove the cloud
		if (taxi.checkCollision(graphic)) {
			taxi.addFuel(FUEL_ADDED);
			this.shouldRemove = true;
		} else {
		}

	}

}
