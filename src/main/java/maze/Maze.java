package maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class Maze {
    private final int length = 30;
    private final int width = 20;
    private boolean[][] verticalWalls;
    private boolean[][] horizontalWalls;
    private final Random random = new Random();
    private volatile Cell[][] cells;

    private final JFrame frame;
    private final JPanel panel;
    private volatile int currentRow;
    private volatile int currentColumn;

    public Maze() {
        frame = new JFrame("Maze");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        panel = new JPanel(null);
        prepareField();
    }

    public void start() {
        panel.add(createMaze());
        frame.add(panel);
        frame.setVisible(true);
    }

    void handleAction(int action) {
        Cell currentPosition = cells[currentRow][currentColumn];
        switch (action) {
            case KeyEvent.VK_LEFT: {
                if (!currentPosition.isHasWallLeft()) {
                    Cell neighbour = cells[currentRow][currentColumn - 1];
                    swapCells(currentPosition, neighbour);
                    currentColumn--;
                }
                break;
            }
            case KeyEvent.VK_RIGHT: {
                if (!currentPosition.isHasWallRight()) {
                    Cell neighbour = cells[currentRow][currentColumn + 1];
                    swapCells(currentPosition, neighbour);
                    currentColumn++;
                }
                break;
            }
            case KeyEvent.VK_UP: {
                if (!currentPosition.isHasWallAbove()) {
                    Cell neighbour = cells[currentRow - 1][currentColumn];
                    swapCells(currentPosition, neighbour);
                    currentRow--;
                }
                break;
            }
            case KeyEvent.VK_DOWN: {
                if (!currentPosition.isHasWallBelow()) {
                    Cell neighbour = cells[currentRow + 1][currentColumn];
                    swapCells(currentPosition, neighbour);
                    currentRow++;
                }
                break;
            }
        }
    }

    private void prepareField() {
        verticalWalls = new boolean[width][length - 1];
        horizontalWalls = new boolean[width - 1][length];
        fillWallsWithTrue(verticalWalls);
        fillWallsWithTrue(horizontalWalls);
        cells = new Cell[width][length];
        for (int i = 0; i < width; i++) {
            Cell[] row = cells[i];
            for (int j = 0; j < length; j++) {
                row[j] = new Cell(i, j);
            }
        }
        arrangeWalls();
        surroundCellsWithWalls(cells);
        setBorderWalls(cells);
        cells[0][0].setCurrentPosition(true);
    }

    private void arrangeWalls() {
        UnionFindStructure unionFindStructure = new UnionFindStructure(length * width);
        int[] wallsPermutation = new int[(length - 1) * width + (width - 1) * length];
        for (int i = 0; i < wallsPermutation.length; i++) {
            wallsPermutation[i] = i;
        }
        shuffle(wallsPermutation);
        for (int i : wallsPermutation) {
            int rowNum = i / (2 * length - 1);
            int remainder = i % (2 * length - 1);
            boolean isVertical = remainder <= length - 2;

            int cellOne;
            int cellTwo;

            if (isVertical) {
                cellOne = rowNum * length + remainder;
                cellTwo = cellOne + 1;
                if (!unionFindStructure.inOneSet(cellOne, cellTwo)) {
                    unionFindStructure.union(cellOne, cellTwo);
                    verticalWalls[rowNum][remainder] = false;
                }
            } else {
                cellOne = rowNum * length + (remainder % length);
                cellTwo = cellOne + length;
                if (!unionFindStructure.inOneSet(cellOne, cellTwo)) {
                    unionFindStructure.union(cellOne, cellTwo);
                    horizontalWalls[rowNum][remainder % length] = false;
                }
            }
        }
    }

    private void surroundCellsWithWalls(Cell[][] cells) {
        for (int i = 0; i < verticalWalls.length; i++) {
            boolean[] verticalWallsLevel = verticalWalls[i];
            for (int j = 0; j < verticalWallsLevel.length; j++) {
                if (verticalWallsLevel[j]) {
                    cells[i][j].setHasWallRight(true);
                    cells[i][j + 1].setHasWallLeft(true);
                }
            }
        }

        for (int i = 0; i < horizontalWalls.length; i++) {
            boolean[] oneRow = horizontalWalls[i];
            for (int j = 0; j < oneRow.length; j++) {
                if (oneRow[j]) {
                    cells[i][j].setHasWallBelow(true);
                    cells[i + 1][j].setHasWallAbove(true);
                }
            }
        }
    }

    private JPanel createMaze() {
        JPanel innerPanel = new JPanel(new GridLayout(width, length));
        innerPanel.setBounds(40, 40, length * Cell.size, width * Cell.size);
        innerPanel.setFocusable(true);
        innerPanel.addKeyListener(new MyKeyListener(this));
        for (int i = 0; i < width; i++) {
            Cell[] row = cells[i];
            for (int j = 0; j < length; j++) {
                innerPanel.add(row[j]);
            }
        }
        return innerPanel;
    }

    private void setBorderWalls(Cell[][] cells) {
        Cell[] firstRow = cells[0];
        for (Cell value : firstRow) {
            value.setHasWallAbove(true);
        }

        Cell[] lastRow = cells[width - 1];
        for (Cell cell : lastRow) {
            cell.setHasWallBelow(true);
        }

        for (Cell[] cell : cells) {
            cell[0].setHasWallLeft(true);
        }

        for (Cell[] cell : cells) {
            cell[length - 1].setHasWallRight(true);
        }
    }

    private void fillWallsWithTrue(boolean[][] walls) {
        for (boolean[] row : walls) {
            Arrays.fill(row, true);
        }
    }

    private void shuffle(int[] array) {
        int max = array.length;
        for (int i = 0; i < array.length; i++) {
            int randomIndex = random.nextInt(max);
            int temp = array[randomIndex];
            array[randomIndex] = array[max - 1];
            array[max - 1] = temp;
            max--;
        }
    }

    private void swapCells(Cell current, Cell neighbour) {
        current.setCurrentPosition(false);
        current.revalidate();
        current.repaint();
        neighbour.setCurrentPosition(true);
        neighbour.revalidate();
        neighbour.repaint();
    }

}
