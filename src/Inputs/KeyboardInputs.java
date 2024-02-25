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
                player.changeYDelta(-5);
                break;
            case Input.KEY_A:
            case Input.KEY_LEFT:
                player.changeXDelta(-5);
                break;
            case Input.KEY_S:
            case Input.KEY_DOWN:
                player.changeYDelta(5);
                break;
            case Input.KEY_D:
            case Input.KEY_RIGHT:
                player.changeXDelta(5);
                break;
        }
    }

    @Override
    public void keyReleased(int i, char c) {

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
