/**
 *  Its pilot had a tad too much to drink.
 *  this ship will giggle between right and left turn,
 *  teleport randomly with a chance of 0.001 each round
 *  and fire randomly with a chance of 0.01 each round
 *  it will not accelerate and won't turn on it's shield
 */
public class Drunkard extends SpaceShip{

    @Override
    public void doAction(SpaceWars game) {
        boolean teleport = false;
        int direction = 1;
        boolean fire = false;

        double rand1 = Math.random();
        double rand2 = Math.random();
        double rand4 = Math.random();

        if (rand1 < 0.001){
            teleport = true;
        }
        if (rand2 < 0.50){
            direction = -1;
        }
        if (rand4 < 0.01){
            fire = true;
        }

        if (teleport) {teleport();}
        physics.move(false, direction);
        if (fire){ fire(game);}

        endOfRoundOperations();
    }
}
