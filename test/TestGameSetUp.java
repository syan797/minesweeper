import model.Cell;
import model.CellLocation;
import model.GameBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestGameSetUp {

    private GameBoard gameBoard;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(8,10,24);
    }

    @Test
    public void testCalcs() {
        assertEquals(80, gameBoard.getNumCells());
    }

    @Test
    public void testMineIndices() {
        List<Integer> mineIndices = gameBoard.getMineIndices();
        //Tests for right number of mines
        assertEquals(24, mineIndices.size());
        //Makes sure there are no repeating mine indices.
        HashSet<Integer> uniqueNumbers = new HashSet<>(mineIndices);
        assertEquals(mineIndices.size(), uniqueNumbers.size());
    }

}
