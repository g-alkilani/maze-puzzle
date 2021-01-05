package maze;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {

    private final Maze maze;

    public MyKeyListener(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        maze.handleAction(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
