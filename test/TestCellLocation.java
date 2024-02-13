import model.CellLocation;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCellLocation {

    private CellLocation A, B, C;

    @Before
    public void setUp() {
        A = new CellLocation(38, 42);
        B = new CellLocation(38, 42);
        C = new CellLocation(38, 76);
    }

    @Test
    public void testEquals() {
        assertEquals(A, B);
        assertNotSame(A, B);
        assertNotEquals(A, C);
    }

}
