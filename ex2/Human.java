import oop.ex2.GameGUI;

import java.awt.*;

/**
 * Controlled by the user. The keys are: left-arrow and right-arrow to
 * turn, up-arrow to accelerate, ’d’ to fire a shot, ’s’ to turn on the shield, ’a’ to teleport.
 */
public class Human extends SpaceShip{

    @Override
    public void doAction(SpaceWars game) {
        isShieldOn = false;
        if (game.getGUI().isTeleportPressed()){
            teleport();
        }
        doMovement(game);
        if (game.getGUI().isShieldsPressed()){
            shieldOn();
        }
        if (game.getGUI().isShotPressed()){
            fire(game);
        }
        endOfRoundOperations();
    }

    @Override
    public Image getImage() {
        if (isShieldOn){
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
        return GameGUI.SPACESHIP_IMAGE;
    }

    /**
     * decides how to call the move() method in SpaceShipPhysics
     * @param game - current SpaceWars object
     */
    private void doMovement(SpaceWars game){
        boolean accelerate = false;
        int leftRight = 0;
        if (game.getGUI().isUpPressed()){
            accelerate = true;
        }
        if (game.getGUI().isLeftPressed() && !game.getGUI().isRightPressed()){
            leftRight = 1;
        }
        if (!game.getGUI().isLeftPressed() && game.getGUI().isRightPressed()){
            leftRight = -1;
        }
        this.physics.move(accelerate, leftRight);
    }

}
