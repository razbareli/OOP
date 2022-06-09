import oop.ex2.*;

/**
 * This class has a single static method. It is used by the supplied driver to create all the spaceship
 * objects according to the command line arguments.
 */
public class SpaceShipFactory {
    /**
     * The function create spaceship objects according to the command line arguments.
     *
     * @param args Program arguments.
     * @return Array filled with spaceships.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        int len = args.length;
        SpaceShip[] ans = new SpaceShip[len];
        for (int i = 0; i < len; i++) {
            String curr = args[i];
            if (curr.equals("h")) {ans[i] = new Human();}
            if (curr.equals("r")) {ans[i] = new Runner();}
            if (curr.equals("b")) {ans[i] = new Basher();}
            if (curr.equals("a")) {ans[i] = new Aggressive();}
            if (curr.equals("d")) {ans[i] = new Drunkard();}
            if (curr.equals("s")) {ans[i] = new Special();}
        }
        return ans;
    }
}
