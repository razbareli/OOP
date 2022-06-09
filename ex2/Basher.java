/**
 *  This ship attempts to collide with other ships. It will always accelerate, and will
 * constantly turn towards the closest ship. If it gets within a distance of 0.19 units (inclusive)
 * from another ship, it will attempt to turn on its shields.
 */
public class Basher extends SpaceShip{

    @Override
    public void doAction(SpaceWars game) {
        isShieldOn = false;
        SpaceShip closestShip = game.getClosestShipTo(this);
        double angle = physics.angleTo(closestShip.physics);
        double distance = physics.distanceFrom(closestShip.physics);
        stickTo(angle);
        if (distance <= 0.19){
            shieldOn();
        }
        endOfRoundOperations();

    }
}
