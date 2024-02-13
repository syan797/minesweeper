import model.Cell;
import model.CellLocation;
import model.GameBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestCellGeneration {
    private GameBoard gameBoard;
    private int numMines;
    private List<Cell> allCells;

    @Before
    public void setUp() {
        numMines = 24;
        gameBoard = new GameBoard(8,10,numMines);
        allCells = gameBoard.getAllCells();
    }

    @Test
    public void testNumCellsGenerated() {
        assertEquals(80, allCells.size());
    }

    //Tests that each cell has a unique location
    @Test
    public void testUniqueLocations() {
        HashSet<CellLocation> uniqueLocations = new HashSet<>();
        for (Cell cell : allCells) {
            uniqueLocations.add(cell.getLocation());
        }
        assertEquals(allCells.size(), uniqueLocations.size());
    }

    //Tests that cell indices match locations as expected
    @Test
    public void testIndexPlacement() {
        assertEquals(allCells.get(0).getLocation(), new CellLocation(0,0));
        assertEquals(allCells.get(12).getLocation(), new CellLocation(4,1));
        assertEquals(allCells.get(79).getLocation(), new CellLocation(7,9));
    }

    //Tests that the correct number of mines have been generated
    @Test
    public void testNumMines() {
        List<Cell> mines = new ArrayList<>();
        for (Cell cell : allCells) {
            if (cell.isMine()) {
                mines.add(cell);
            }
        }
        assertEquals(numMines, mines.size());
    }

    //Tests that all cells are unknown at the start of the game
    @Test
    public void testUnknown() {
        for (Cell cell : allCells) {
            if (cell.isKnown()) {
                fail();
            }
        }
    }

}
