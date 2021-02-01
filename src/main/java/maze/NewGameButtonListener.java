package maze;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewGameButtonListener implements MouseListener {

    private final Maze maze;

    public NewGameButtonListener(Maze maze) {
        this.maze = maze;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        maze.startNewGame();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
