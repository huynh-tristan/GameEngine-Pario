package Inputs;

import GameStuff.Player;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class KeyboardInputs implements KeyListener {

    private Player player;
    public KeyboardInputs(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(int i, char c) {
        switch (i) {
            case Input.KEY_W:
            case Input.KEY_UP:
                player.setUpDir(true);
                //player.changeYDelta(-5);
                break;
            case Input.KEY_A:
            case Input.KEY_LEFT:
                player.setLeft(true);
                //player.changeXDelta(-5);
                break;
            case Input.KEY_S:
            case Input.KEY_DOWN:
                player.setDown(true);
                //player.changeYDelta(5);
                break;
            case Input.KEY_D:
            case Input.KEY_RIGHT:
                player.setRight(true);
                //player.changeXDelta(5);
                break;
            case Input.KEY_SPACE:
                player.setJump(true);
                break;
        }
    }

    @Override
    public void keyReleased(int i, char c) {
        switch (i) {
            case Input.KEY_W:
            case Input.KEY_UP:
                player.setUpDir(false);
                break;
            case Input.KEY_A:
            case Input.KEY_LEFT:
                player.setLeft(false);
                break;
            case Input.KEY_S:
            case Input.KEY_DOWN:
                player.setDown(false);
                break;
            case Input.KEY_D:
            case Input.KEY_RIGHT:
                player.setRight(false);
                break;
            case Input.KEY_SPACE:
                player.setJump(false);
                break;
        }
    }


    public void setInput(Input input) {

    }

    public boolean isAcceptingInput() {
        return true;
    }

    public void inputEnded() {

    }

    public void inputStarted() {

    }
}
