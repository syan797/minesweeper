package model;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    private final GameBoard gameBoard;
    private final int cellIndex;
    private final CellLocation location;
    private final boolean isMine;
    private boolean isKnown;
    private List<Cell> surroundingCells;
    private int mineCount;

    public Cell(CellLocation location, int cellIndex, boolean isMine, GameBoard gameBoard) {
        this.location = location;
        this.cellIndex = cellIndex;
        this.isMine = isMine;
        this.gameBoard = gameBoard;
        isKnown = false;
        surroundingCells = new ArrayList<>();
    }

    private void calculateSurroundingCells() {
        List<Cell> allCells = gameBoard.getAllCells();
        for (Direction dir : Direction.values()) {
            Cell surroundingCell = gameBoard.getCell(dir, this);
            if (surroundingCell != null) {
                surroundingCells.add(surroundingCell);
            }
        }
    }

    public void calculateMineCount() {
        calculateSurroundingCells();
        int count = 0;
        for (Cell cell : surroundingCells) {
            if (cell.isMine()) {
                count++;
            }
        }
        mineCount = count;
    }

    public void reveal() {
        this.isKnown = true;
        if (!gameBoard.isDead() && this.getMineCount()==0) {
            for (Cell cell : surroundingCells) {
                if (!cell.isKnown) {
                    cell.reveal();
                }
            }
        }
    }

    public CellLocation getLocation() {
        return location;
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isKnown() {
        return isKnown;
    }

    public List<Cell> getSurroundingCells() {
        return surroundingCells;
    }

    public int getMineCount() {
        return mineCount;
    }

}
