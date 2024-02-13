package model;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    private final int boardWidth, boardHeight, numCells;
    private List<Cell> allCells;
    private List<Integer> mineIndices;
    private int numMines;
    private boolean isDead;

    public GameBoard(int boardWidth, int boardHeight, int numMines) {
        this.isDead = false;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        numCells = boardWidth*boardHeight;
        this.numMines = numMines;
        generateCells(boardWidth, boardHeight);
    }

    private void generateCells(int boardWidth, int boardHeight) {
        allCells = new ArrayList<>();
        generateMineIndices();
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth; x++) {
                int newCellIndex = allCells.size();
                Cell newCell = new Cell(new CellLocation(x,y), newCellIndex, mineIndices.contains(newCellIndex), this);
                allCells.add(newCell);
            }
        }
        for (Cell cell : allCells) {
            cell.calculateMineCount();
        }
    }

    private void generateMineIndices() {
        mineIndices = new ArrayList<>();
        for (int i = 0; i < numMines; i++) {
            int newMineIndex = (int)(Math.random()*numCells);
            while (mineIndices.contains(newMineIndex)) {
                newMineIndex = (int)(Math.random()*numCells);
            }
            mineIndices.add(newMineIndex);
        }
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getNumCells() {
        return numCells;
    }

    public List<Integer> getMineIndices() {
        return mineIndices;
    }

    public List<Cell> getAllCells() {
        return allCells;
    }

    public Cell getCell(Direction direction, Cell cell) {
        int cellX = cell.getLocation().getX();
        int cellY = cell.getLocation().getY();
        switch (direction) {
            case AboveLeft:
                if (cellY == 0 || cellX == 0) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX-1, cellY-1));
                }
            case Above:
                if (cellY == 0) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX, cellY-1));
                }
            case AboveRight:
                if (cellY == 0 || cellX == boardWidth-1) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX+1, cellY-1));
                }
            case RightOf:
                if (cellX == boardWidth-1) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX+1, cellY));
                }
            case BelowRight:
                if (cellY == boardHeight-1 || cellX == boardWidth-1) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX+1, cellY+1));
                }
            case Below:
                if (cellY == boardHeight-1) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX, cellY+1));
                }
            case BelowLeft:
                if (cellY == boardHeight-1 || cellX == 0) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX-1, cellY+1));
                }
            case LeftOf:
                if (cellX == 0) {
                    return null;
                } else {
                    return getCell(new CellLocation(cellX-1, cellY));
                }
        }
        return null;
    }

    public Cell getCell(CellLocation location) {
        for (Cell cell : allCells) {
            if (cell.getLocation().equals(location)) {
                return cell;
            }
        }
        return null;
    }

    public void gameOver() {
        this.isDead = true;
        for (Cell cell: allCells) {
            if (cell.isMine()) {
                cell.reveal();
            }
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isComplete() {
        return allSafeCellsUncovered() || allMinesFlagged();
    }

    private boolean allSafeCellsUncovered() {
        for (Cell cell : allCells) {
            if (!cell.isMine() && !cell.isKnown()) {
                return false;
            }
        }
        return true;
    }

    private boolean allMinesFlagged() {
        //complete this later
        return false;
    }

    public int getNumMines() {
        return numMines;
    }
}
