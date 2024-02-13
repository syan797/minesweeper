import model.CellLocation;
import org.junit.Before;
import org.junit.Test;
import view.GameBoardPanel;

import java.awt.*;
import static org.junit.Assert.*;


public class TestPointToCellLocation {

    GameBoardPanel boardPanel;

    @Before
    public void setUp() {
        boardPanel = new GameBoardPanel();
        boardPanel.setBorderSize(2);
        boardPanel.setCellSize(10);
    }

    @Test
    public void testBoxClick() {
        assertEquals(new CellLocation(1,0),
                boardPanel.getCellLocation(new Point(17,7)));
        assertEquals(new CellLocation(3,2),
                boardPanel.getCellLocation(new Point(41,26)));
    }

    @Test
    public void testLineClick() {
        assertNull(boardPanel.getCellLocation(new Point(2,36)));
        assertNull(boardPanel.getCellLocation(new Point(36,102)));
        assertNull(boardPanel.getCellLocation(new Point(42,36)));
        assertNull(boardPanel.getCellLocation(new Point(36,42)));
    }

    @Test
    public void testBorderClick() {
        assertNull(boardPanel.getCellLocation(new Point(0,0)));
        assertNull(boardPanel.getCellLocation(new Point(1,56)));
        assertNull(boardPanel.getCellLocation(new Point(28,1)));
        assertNull(boardPanel.getCellLocation(new Point(83,56)));
        assertNull(boardPanel.getCellLocation(new Point(28,103)));
    }

}
