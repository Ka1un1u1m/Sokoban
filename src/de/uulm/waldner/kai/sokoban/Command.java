package de.uulm.waldner.kai.sokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static de.uulm.waldner.kai.sokoban.Sokoban.*;


public class Command implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_DOWN:
                moveSouth(sokoban);
                reDrawGui(sokoban);
                checkIfFinished(sokoban);
                //System.out.println("down");
                break;

            case KeyEvent.VK_UP:
                moveNorth(sokoban);
                reDrawGui(sokoban);
                checkIfFinished(sokoban);
                //System.out.println("up");
                break;

            case KeyEvent.VK_LEFT:
                moveWest(sokoban);
                reDrawGui(sokoban);
                checkIfFinished(sokoban);
                //System.out.println("left");
                break;

            case KeyEvent.VK_RIGHT:
                moveEast(sokoban);
                reDrawGui(sokoban);
                checkIfFinished(sokoban);
                //System.out.println("right");
                break;

            case KeyEvent.VK_ESCAPE:
                frame.dispose();
                System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
