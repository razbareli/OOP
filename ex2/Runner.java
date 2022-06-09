/**
 *  This spaceship attempts to run away from the fight. It will always accelerate, and
 * will constantly turn away from the closest ship. If the nearest ship is closer than 0.25 units,
 * and if its angle to the Runner is less than 0.23 radians, the Runner feels threatened and will
 * attempt to teleport.
 */

public class Runner extends SpaceShip {

    @Override
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angle = physics.angleTo(closestShip.physics);
        double distance = physics.distanceFrom(closestShip.physics);
        if (distance < 0.25 && Math.abs(angle) < 0.23){
            this.teleport();
        }
        runAway(angle);
        endOfRoundOperations();
    }
}
