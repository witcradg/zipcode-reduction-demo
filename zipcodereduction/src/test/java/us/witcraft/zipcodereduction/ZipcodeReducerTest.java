package us.witcraft.zipcodereduction;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test class for demo.ZipRange.java
 *
 * @author Dean Witcraft
 */
public class ZipcodeReducerTest {

    private static final String TEST1 = "[94133,94133] [94200,94299] [94600,94699]";
    private static final String OVERLAP_TEST1 = "[94133,94133] [94200,94299] [94226,94399]";
    private static final String INVALID_INPUT = "[94133,94133] [94200,94299 [94226,94399]";
    private static final String OVERLAP_TEST2 = "[11111,22222] [15555,25555] [23333,33333] [24444,35555]";
    private static final String OVERLAP_TEST3 = "[24444,35555] [23333,33333] [15555,25555] [11111,22222]";

    public ZipcodeReducerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class ZipcodeReducer.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = {"-help"}; //force usage statement output
        ZipcodeReducer.main(args);
    }

    /**
     * Test of parseInput method, of class ZipcodeReducer. Confirm new objects
     * are created properly
     */
    @Test
    public void testParseInput() {
        System.out.println("parseInput");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(TEST1);
        ArrayList<ZipRange> zipRangeList = instance.getZipRangeList();
        assertEquals(zipRangeList.size(), 3);
    }

    /**
     * Test of parseInput method, of class ZipcodeReducer. Confirm exception is
     * thrown.
     */
    @Test(expected = NumberFormatException.class)
    public void testParseInput_invalidInput() {
        System.out.println("parseInput");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(INVALID_INPUT);
    }

    /**
     * Test of reduceCollection method, of class ZipcodeReducer. with no
     * overlaps
     */
    @Test
    public void testReduceCollection() {
        System.out.println("reduceCollection");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(TEST1);
        instance.reduceCollection();
        ArrayList<ZipRange> zipRangeList = instance.getZipRangeList();
        assertEquals(zipRangeList.size(), 3);
    }

    /**
     * Test of reduceCollection method, of class ZipcodeReducer.
     */
    @Test
    public void testReduceCollection_withOverlap1() {
        System.out.println("reduceCollection");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(OVERLAP_TEST1);
        instance.reduceCollection();
        ArrayList<ZipRange> zipRangeList = instance.getZipRangeList();
        assertEquals(zipRangeList.size(), 2);
    }

        /**
     * Test of reduceCollection method, of class ZipcodeReducer.
     */
    @Test
    public void testReduceCollection_withOverlap2() {
        System.out.println("reduceCollection");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(OVERLAP_TEST2);
        instance.reduceCollection();
        ArrayList<ZipRange> zipRangeList = instance.getZipRangeList();
        assertEquals(zipRangeList.size(), 1);
    }

         /**
     * Test of reduceCollection method, of class ZipcodeReducer.
     */
    @Test
    public void testReduceCollection_withOverlap3() {
        System.out.println("reduceCollection");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(OVERLAP_TEST3);
        instance.reduceCollection();
        ArrayList<ZipRange> zipRangeList = instance.getZipRangeList();
        assertEquals(zipRangeList.size(), 1);
    }

    /**
     * Test of writeOutput method, of class ZipcodeReducer.
     */
    @Test
    public void testWriteOutput() {
        System.out.println("writeOutput");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(TEST1);
        instance.writeOutput();
    }

    /**
     * Test of getZipRangeList method, of class ZipcodeReducer.
     */
    @Test
    public void testGetZipRangeList() {
        System.out.println("getZipRangeList");
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.parseInput(TEST1);
        int expected = 3;
        ArrayList<ZipRange> result = instance.getZipRangeList();
        assertEquals(expected, result.size());
    }

    /**
     * Test of overlap method, of class ZipcodeReducer.
     */
    @Test
    public void testOverlapTrue() {
        System.out.println("overlap");
        ZipRange range = new ZipRange(25555, 25555);
        ZipRange range2 = new ZipRange(22222, 35555);
        ZipcodeReducer instance = new ZipcodeReducer();
        boolean expResult = true;
        boolean result = instance.rangesOverlap(range, range2);
        assertEquals(expResult, result);
    }
    /**
     * Test of overlap method, of class ZipcodeReducer.
     */
    @Test
    public void testOverlapFalse() {
        System.out.println("overlap");
        ZipRange range = new ZipRange(11111, 22222);
        ZipRange range2 = new ZipRange(25555, 35555);
        ZipcodeReducer instance = new ZipcodeReducer();
        boolean expResult = false;
        boolean result = instance.rangesOverlap(range, range2);
        assertEquals(expResult, result);
    }

    /**
     * Test of consolidate method, of class ZipcodeReducer.
     */
    @Test
    public void testConsolidate() {
        System.out.println("consolidate");
        ZipRange range = new ZipRange(94200,94299);
        ZipRange range2 = new ZipRange(94226,94399);
        ZipcodeReducer instance = new ZipcodeReducer();
        instance.consolidate(range, range2);
    }

    /**
     * Test of isBetween method, of class ZipcodeReducer.
     */
    @Test
    public void testIsBetween_true() {
        System.out.println("isBetween");
        Integer i = 22222;
        Integer lower = 11111;
        Integer upper = 33333;
        ZipcodeReducer instance = new ZipcodeReducer();
        boolean expResult = true;
        boolean result = instance.isBetween(i, lower, upper);
        assertEquals(expResult, result);
    }

        /**
     * Test of isBetween method, of class ZipcodeReducer.
     */
    @Test
    public void testIsBetween_false() {
        System.out.println("isBetween");
        Integer i = 55555;
        Integer lower = 11111;
        Integer upper = 33333;
        ZipcodeReducer instance = new ZipcodeReducer();
        boolean expResult = false;
        boolean result = instance.isBetween(i, lower, upper);
        assertEquals(expResult, result);
    }
}
