import model.Cell;
import model.CellLocation;
import model.Direction;
import model.GameBoard;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestSurroundingCells {
    private GameBoard gameBoard;
    private List<Cell> allCells;
    private Cell middleCell, topCell, bottomCell, leftCell, rightCell, tLCorner, tRCorner, bLCorner, bRCorner;

    @Before
    public void setUp() {
        gameBoard = new GameBoard(8,10,24);
        allCells = gameBoard.getAllCells();
        middleCell = gameBoard.getCell(new CellLocation(4,4));
        topCell = gameBoard.getCell(new CellLocation(4,0));
        bottomCell = gameBoard.getCell(new CellLocation(4,9));
        leftCell = gameBoard.getCell(new CellLocation(0, 4));
        rightCell = gameBoard.getCell(new CellLocation(7, 4));
        tLCorner = gameBoard.getCell(new CellLocation(0, 0));
        tRCorner = gameBoard.getCell(new CellLocation(7, 0));
        bLCorner = gameBoard.getCell(new CellLocation(0, 9));
        bRCorner = gameBoard.getCell(new CellLocation(7, 9));
    }

    //Test that we are getting the right coordinates
    @Test
    public void testCellAboveLeft(){
        //Cell exists
        assertEquals(new CellLocation(3, 3),
                gameBoard.getCell(Direction.AboveLeft, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.AboveLeft, topCell));
        assertNull(gameBoard.getCell(Direction.AboveLeft, leftCell));
        assertNull(gameBoard.getCell(Direction.AboveLeft, tLCorner));
    }

    @Test
    public void testCellAbove(){
        //Cell exists
        assertEquals(new CellLocation(4, 3),
                gameBoard.getCell(Direction.Above, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.Above, topCell));
        assertNull(gameBoard.getCell(Direction.Above, tRCorner));
    }

    @Test
    public void testCellAboveRight(){
        //Cell exists
        assertEquals(new CellLocation(5, 3),
                gameBoard.getCell(Direction.AboveRight, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.AboveRight, topCell));
        assertNull(gameBoard.getCell(Direction.AboveRight, rightCell));
        assertNull(gameBoard.getCell(Direction.AboveRight, tRCorner));
    }

    @Test
    public void testCellRight(){
        //Cell exists
        assertEquals(new CellLocation(5, 4),
                gameBoard.getCell(Direction.RightOf, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.RightOf, rightCell));
        assertNull(gameBoard.getCell(Direction.RightOf, tRCorner));
    }

    @Test
    public void testCellBelowRight(){
        //Cell exists
        assertEquals(new CellLocation(5, 5),
                gameBoard.getCell(Direction.BelowRight, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.BelowRight, bottomCell));
        assertNull(gameBoard.getCell(Direction.BelowRight, rightCell));
        assertNull(gameBoard.getCell(Direction.BelowRight, bRCorner));
    }

    @Test
    public void testCellBelow(){
        //Cell exists
        assertEquals(new CellLocation(4, 5),
                gameBoard.getCell(Direction.Below, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.Below, bottomCell));
        assertNull(gameBoard.getCell(Direction.Below, bRCorner));
    }

    @Test
    public void testCellBelowLeft(){
        //Cell exists
        assertEquals(new CellLocation(3, 5),
                gameBoard.getCell(Direction.BelowLeft, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.BelowLeft, bottomCell));
        assertNull(gameBoard.getCell(Direction.BelowLeft, leftCell));
        assertNull(gameBoard.getCell(Direction.BelowLeft, bLCorner));
    }

    @Test
    public void testCellLeft(){
        //Cell exists
        assertEquals(new CellLocation(3, 4),
                gameBoard.getCell(Direction.LeftOf, middleCell).getLocation());
        //Cell doesn't exist
        assertNull(gameBoard.getCell(Direction.LeftOf, leftCell));
        assertNull(gameBoard.getCell(Direction.LeftOf, bLCorner));
    }

    //Test that we have the correct number of surrounding cells
    @Test
    public void testMiddleCell() {
        assertEquals(8,middleCell.getSurroundingCells().size());
    }

    @Test
    public void testWalls() {
        assertEquals(5,topCell.getSurroundingCells().size());
        assertEquals(5,bottomCell.getSurroundingCells().size());
        assertEquals(5,leftCell.getSurroundingCells().size());
        assertEquals(5,rightCell.getSurroundingCells().size());
    }

    @Test
    public void testCorners() {
        assertEquals(3,tLCorner.getSurroundingCells().size());
        assertEquals(3,tRCorner.getSurroundingCells().size());
        assertEquals(3,bLCorner.getSurroundingCells().size());
        assertEquals(3,bRCorner.getSurroundingCells().size());
    }

}
