//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            (Warp Star Taxi)
// Files:            (Taxi.java)
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
 * Instances of this class represent the space taxi that the player controls
 * using both thrusters and warp stars, to travel between planets delivering
 * fares to their destinations.
 * 
 * This class should contain and make use of the following private fields:
 * private Graphic graphic 
 * private float thrusterSpeed 
 * private float fuel
 * private float warpSpeed 
 * private boolean isTravellingAtWarp 
 * private boolean hasCrashed
 * @author Weipeng & Pamela 
 */
public class Taxi {
	private Graphic graphic;
	private float thrusterSpeed;
	private float fuel;
	private float warpSpeed;
	private boolean isTravellingAtWarp;
	private boolean hasCrashed;

	/**
	 * Initialized all fields of the new taxi object so that it will appear at
	 * the specified position. Parameters:
	 * 
	 * @param x - specifies the initial horizontal position for this new object
	 * @param y - specifies the initial vertical position for this new object
	 */
	public Taxi(float x, float y) {
		thrusterSpeed = 0.01f;
		graphic = new Graphic("TAXI");
		graphic.setX(x);
		graphic.setY(y);
		fuel = 30;
		warpSpeed = 0.2f;
		isTravellingAtWarp = false;
		hasCrashed = false;
	}

	/**
	 * Mutates the state of this taxi to begin traveling at warp speed in the
	 * direction of the specified position. Parameters:
	 * 
	 * @param x - is the horizontal coordinate to move at warp speed toward
	 * @param y - is the vertical coordinate to move at warp speed toward
	 */
	public void setWarp(float x, float y) {
		graphic.setDirection(x, y);
		this.isTravellingAtWarp = true;

	}

	/**
	 * This accessor retrieves whether this taxi is travelling at warp speed.
	 * 
	 * @Return true when travelling at warp speed, and false otherwise
	 */
	public boolean isTravellingAtWarp() {
		return this.isTravellingAtWarp;
	}

	/**
	 * This method is primarily responsible for updating the taxi's position so
	 * that they move appropriately: using thrusters or warp star travel. When a
	 * taxi moves off an edge of the screen, they should simultaneously enter
	 * from the opposite screen edge. The a taxi is either out of fuel or has
	 * crashed into a planet, they should not move at all. Parameters:
	 * 
	 * @param time - in milliseconds is used to move taxi at the correct speed
	 * @Return true when the player has (either crashed or run out of fuel)
	 *           and also pressed the space bar to acknowledge that they are
	 *           done playing, in all other cases this method should return
	 *           false
	 */
	public boolean update(int time) {
		// if is traveling in warp speed
		if (isTravellingAtWarp() == true && fuel > 0) {
			graphic.setX(graphic.getX() + warpSpeed * time 
					* graphic.getDirectionX());
			graphic.setY(graphic.getY() + warpSpeed * time 
					* graphic.getDirectionY());
		}
		// if there is fuel
		if (fuel > 0) {
			// move the taxi, and reduce fuel when moving
			if (GameEngine.isKeyHeld("D") || GameEngine.isKeyHeld("RIGHT")) {
				this.isTravellingAtWarp = false;
				graphic.setX(graphic.getX() + thrusterSpeed * time);
				graphic.setDirection(0);
				fuel -= thrusterSpeed * time;
				if (fuel < 0) {
					fuel = 0;
				}
			}
			if (GameEngine.isKeyHeld("A") || GameEngine.isKeyHeld("LEFT")) {
				this.isTravellingAtWarp = false;
				graphic.setX(graphic.getX() - thrusterSpeed * time);
				graphic.setDirection((float) Math.PI);
				fuel -= thrusterSpeed * time;
				if (fuel < 0) {
					fuel = 0;
				}
			}
			if (GameEngine.isKeyHeld("W") || GameEngine.isKeyHeld("UP")) {
				this.isTravellingAtWarp = false;
				graphic.setY(graphic.getY() - thrusterSpeed * time);
				graphic.setDirection((float) Math.PI / 2);
				fuel -= thrusterSpeed * time;
				if (fuel < 0) {
					fuel = 0;
				}
			}
			if (GameEngine.isKeyHeld("S") || GameEngine.isKeyHeld("DOWN")) {
				this.isTravellingAtWarp = false;
				graphic.setY(graphic.getY() + thrusterSpeed * time);
				graphic.setDirection((float) Math.PI / 2 * -1);
				fuel -= thrusterSpeed * time;
				if (fuel < 0) {
					fuel = 0;
				}
			}
		}
		// let the graphic goes to the other side of the screen when graphic
		// is at the end of one side
		if (graphic.getX() >= (float) GameEngine.getWidth()) {
			graphic.setX(graphic.getX() - GameEngine.getWidth());
		}
		if (graphic.getY() >= (float) GameEngine.getHeight()) {
			graphic.setY(graphic.getY() - GameEngine.getHeight());
		}
		if (graphic.getX() < 0) {
			graphic.setX(graphic.getX() + GameEngine.getWidth());
		}
		if (graphic.getY() < 0) {
			graphic.setY(graphic.getY() + GameEngine.getHeight());
		}
		// when no fuel, set taxi to crashed
		graphic.draw();
		if (this.hasCrashed == true || fuel <= 0) {
			if (GameEngine.isKeyPressed("SPACE")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines whether this taxi object's graphic is overlapping with the
	 * graphic of another object in the game. Parameters:
	 * 
	 * @param other - is the graphic to check for a collision against
	 * @Return true when other overlaps with this taxi's graphic, else false
	 */
	public boolean checkCollision(Graphic other) {
		boolean collide = false;
		if (graphic.isCollidingWith(other)) {
			collide = true;
		} else {
			collide = false;
		}
		return collide;
	}

	/**
	 * This accessor method retrieves a taxi object's fuel level.
	 * 
	 * @Return the amount of fuel that this taxi currently holds.
	 */
	public float getFuel() {
		return this.fuel;
	}

	/**
	 * This method increments a taxi's fuel level by the specified amount
	 * Parameters:
	 * 
	 * @param fuel - is the amount that should be added to this taxi's fuel
	 */
	public void addFuel(float fuelAmount) {
		this.fuel += fuelAmount;
	}

	/**
	 * This method changes the appearance of this taxi's graphic to EXPLOSION
	 * and also changes this object's state to be crashed which effects the
	 * ship's movement among other things.
	 * 
	 */
	public void crash() {
		hasCrashed = true;
		graphic.setAppearance("EXPLOSION");
		fuel = 0;
	}

	/**
	 * This accessor retrieves whether this taxi has crashed into a planet.
	 * 
	 * @Return true when this taxi has crashed into a planet, otherwise false
	 */
	public boolean hasCrashed() {
		if(hasCrashed == true){
			return true;
		}
		else{
			return false;
		}
	}
}