import java.awt.Image;

import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game.
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 * a base class for the other spaceships or any other option you will choose.
 */
public abstract class SpaceShip {

    /**
     * A SpaceShipPhysics object (from the helper package),
     * that represents the position, direction and velocity of the ship
     */
    protected SpaceShipPhysics physics = new SpaceShipPhysics();

    /**
     * maximum energy the ship can have
     */
    protected int maxEnergy = 210;

    /**
     * current energy of the ship
     * which is between 0 and the maximal energy level.
     */
    protected int currEnergy = 190;

    /**
     * current health of the ship
     * between 0 and 22
     */
    protected int health = 22;

    /**
     * indicated if the shield is on or not
     */
    protected boolean isShieldOn = false;

    /**
     * indicates how many rounds have passed
     * since the last time the ship fired
     */
    protected int fireCounter = 8;

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     *
     * @param game The game object to which this ship belongs.
     */
    public abstract void doAction(SpaceWars game);

    /**
     * This method is called every time a collision with this ship occurs.
     */
    public void collidedWithAnotherShip() {
        this.gotHit();
        if (isShieldOn) {
            maxEnergy += 18;
            currEnergy += 18;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        maxEnergy = 210;
        currEnergy = 190;
        health = 22;
        isShieldOn = false;
        fireCounter = 8;
        physics = new SpaceShipPhysics();
    }

    /**
     * Checks if this ship is dead.
     *
     * @return True if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Gets the physics object that controls this ship.
     *
     * @return The physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return physics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!isShieldOn) {
            health -= 1;
            maxEnergy -= 10;
            if (currEnergy > maxEnergy) {
                currEnergy = maxEnergy;
            }
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return The image of this ship.
     */
    public Image getImage() {
        if (isShieldOn){
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.ENEMY_SPACESHIP_IMAGE;
    }

    /**
     * Attempts to fire a shot.
     *
     * @param game The game object.
     */
    public void fire(SpaceWars game) {
        if (currEnergy > 19 && fireCounter >= 8){
            game.addShot(physics);
            currEnergy -= 19;
            fireCounter = 1;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currEnergy >= 3){
            currEnergy -= 3;
            isShieldOn = true;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currEnergy >= 140){
            physics = new SpaceShipPhysics();
            currEnergy -= 140;

        }
    }

    /**
     * executed operations at the end of the round
     * according to the rules of the game
     */
    protected void endOfRoundOperations(){
        if (currEnergy < maxEnergy){
            currEnergy ++;
        }
        fireCounter++;
    }

    /**
     * makes the ship stick to a certain destination
     * @param angle the angle of the destination
     */
    protected void stickTo(double angle){
        int run = 0;
        if (angle < 0){run = -1;}
        if (angle > 0) {run = 1;}
        physics.move(true, run);
    }

    /**
     * makes the ship run from a certain destination
     * @param angle the angle of the destination
     */
    protected void runAway(double angle){
        int run;
        if (angle < 0){run = 1;}
        else{run = -1;}
        physics.move(true, run);
    }
}
