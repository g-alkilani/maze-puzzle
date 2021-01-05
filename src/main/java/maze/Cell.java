package maze;

import javax.swing.*;
import java.awt.*;

public class Cell extends JComponent {
    final static int size = 24;
    private boolean currentPosition;
    private final int row;
    private final int column;
    private boolean hasWallAbove;
    private boolean hasWallBelow;
    private boolean hasWallLeft;
    private boolean hasWallRight;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (currentPosition) {
            g.setColor(Color.GREEN);
            g.fillRect(2, 2, size - 4, size - 4);
        }
        g.setColor(Color.BLACK);
        if (hasWallAbove && row == 0) {
            g.fillRect(0, 0, size, 2);
        }
        if (hasWallBelow) {
            g.fillRect(0, size - 2, size, 2);
        }
        if (hasWallLeft && column == 0) {
            g.fillRect(0, 0, 2, size);
        }
        if (hasWallRight) {
            g.fillRect(size - 2, 0, 2, size);
        }
    }

    public boolean isCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(boolean currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isHasWallAbove() {
        return hasWallAbove;
    }

    public void setHasWallAbove(boolean hasWallAbove) {
        this.hasWallAbove = hasWallAbove;
    }

    public boolean isHasWallBelow() {
        return hasWallBelow;
    }

    public void setHasWallBelow(boolean hasWallBelow) {
        this.hasWallBelow = hasWallBelow;
    }

    public boolean isHasWallLeft() {
        return hasWallLeft;
    }

    public void setHasWallLeft(boolean hasWallLeft) {
        this.hasWallLeft = hasWallLeft;
    }

    public boolean isHasWallRight() {
        return hasWallRight;
    }

    public void setHasWallRight(boolean hasWallRight) {
        this.hasWallRight = hasWallRight;
    }
}
