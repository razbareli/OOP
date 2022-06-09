/**
 * this spaceship will act like some kind of mine
 * it will stay in constant speed, turn around
 * all the time and fire to all directions.
 */

public class Special extends SpaceShip{

    @Override
    public void doAction(SpaceWars game) {
        physics.move(false, 1);
        fire(game);
        endOfRoundOperations();
    }
}
