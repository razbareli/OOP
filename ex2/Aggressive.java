/**
 * This ship pursues other ships and tries to fire at them. It will always accelerate,
 * and turn towards the nearest ship. When its angle to the nearest ship is less than 0.21
 * radians, it will try to fire.
 */
public class Aggressive extends SpaceShip{

    @Override
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angle = physics.angleTo(closestShip.physics);
        stickTo(angle);
        if (Math.abs(angle) < 0.21){
            fire(game);
        }
        endOfRoundOperations();
    }
}
