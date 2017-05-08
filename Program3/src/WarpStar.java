//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            (Warp Star Taxi)
// Files:            (WarpStar.java)
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
 * Instances of this class represent warp stars that the player can click on to
 * travel at warp speed on a heading that intersects with this star.
 * 
 * This class should contain and make use of the following private fields:
 * private Graphic graphic
 *@author Weipeng & Pamela 
 */

public class WarpStar {
	Graphic graphic;

	/**
	 * Initializes a new WarpStar object to show up at the specified position.
	 * Parameters:
	 * 
	 * @param x - is the horizontal position of the newly created warp star.
	 * @param y - is the vertical position of the newly created warp star.
	 */
	public WarpStar(float x, float y) {
		graphic = new Graphic("WARP_STAR");
		graphic.setX(x);
		graphic.setY(y);
	}

	/**
	 * Draws this WarpStar object at its current position.
	 */
	public void update() {
		graphic.draw();
	}

	/**
	 * This method detects whether both 1) the player's taxi has fuel, and 2)
	 * the player is clicking on this WarpStar object. When both are detected
	 * this method sets the taxi to travel at warp speed toward this WarpStar.
	 * Parameters:
	 * 
	 * @param taxi - is the taxi that is both checked for fuel, and then set to travel
	 *       at warp speed when it has fuel and the player clicks this object.
	 */
	public void handleNavigation(Taxi taxi) {
		// make the taxi move to a warpstar when has fuel and a warpstar is clicked
		if (taxi.getFuel() > 0 && GameEngine.isKeyPressed("MOUSE")
				&& graphic.isCoveringPosition(GameEngine.getMouseX(),
						GameEngine.getMouseY())) {
			taxi.setWarp(GameEngine.getMouseX(), GameEngine.getMouseY());
		}
	}
}
