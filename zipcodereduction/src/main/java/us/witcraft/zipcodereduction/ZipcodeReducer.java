package us.witcraft.zipcodereduction;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Given a collection of 5-digit ZIP code ranges (each range includes both their
 * upper and lower bounds), provide an algorithm that produces the minimum
 * number of ranges required to represent the same restrictions as the input.
 *
 * Assumptions: input will be read as a single line directly from the command
 * line.
 *
 * @author Dean Witcraft
 */
public class ZipcodeReducer {

    private static final String PROMPT = "\nEnter a set of ZIP code ranges> ";
    private static final String USAGE
            = "Enter a collection of one or more pairs of ZIP code values\n"
            + "surrounded by brackets in this format: [94133,94133] \n"
            + "All values should be entered on the same line.\n";

    private ArrayList<ZipRange> zipRangeList;

    /**
     * @param args the command line arguments The program does not expect entry
     * on the command line
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            System.out.println(USAGE);
        } else {
            new ZipcodeReducer().run();
        }
    }

    /**
     * Main instance method.
     */
    private void run() {
        String line = readInput();
        if (line.length() > 0) {
            parseInput(line);
            reduceCollection();
            writeOutput();
        }
    }

    /**
     * Read input from command line. Valid input must be in the form of one or
     * more pairs of ZipCode values surrounded by brackets in this format:
     * opening bracket, lower value, upper value, closing bracket example:
     * [94133,94133]
     *
     * @return String
     */
    private String readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(PROMPT);
        String line = "";
        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println(USAGE);
        }
        return line.trim();
    }

    /**
     * Parse input line into a collection of ZipRange values representing the
     * upper and lower ZIP code values.
     *
     * @param line
     */
    protected void parseInput(String line) throws NumberFormatException {
        String step1 = line.replace("[", ""); //strip the leading bracket
        String[] zipPairs = step1.split("]"); //use the closing bracket as a separator

        zipRangeList = new ArrayList<>();

        try {
            for (String s : zipPairs) {
                String[] split = s.split(",");
                Integer lower = Integer.valueOf(split[0].trim());
                Integer upper = Integer.valueOf(split[1].trim());
                ZipRange range = new ZipRange(lower, upper);
                zipRangeList.add(range);
            }
        } catch (NumberFormatException ex) {
            System.out.println("An invalid collection was entered");
            System.out.println(USAGE);
            throw ex;
        }
    }

    /**
     * Apply business rules to reduce the number of ZipCode pairs where
     * possible.
     */
    protected void reduceCollection() {
        ArrayList<ZipRange> ranges = getZipRangeList();
        ArrayList<ZipRange> outputRanges = new ArrayList<>();

        for (ZipRange range : ranges) {
            if (!range.isConsolidated()) {
                for (ZipRange range2 : ranges) {
                    if (!range2.isConsolidated()) {
                        if (range != range2) { //skip comparison against self
                            if (rangesOverlap(range, range2)) {
                                outputRanges.add(consolidate(range, range2));
                            }
                        }
                    }
                }
            }
        }

        if (outputRanges.size() > 0) {
            replaceInputCollection(ranges, outputRanges);
            reduceCollection();             //recursive call
        }
    }

    /**
     * Certain input sets can cause a single pass to fail to completely reduce
     * overlapping ZIP codes. See ZipcodeReducerTest OVERLAP_TEST2 for example.
     * This resets the input range collection to allow for a recursive call.
     *
     * @param ranges
     * @param outputRanges
     */
    private void replaceInputCollection(ArrayList<ZipRange> ranges, ArrayList<ZipRange> outputRanges) {
        for (ZipRange range : ranges) {
            if (!range.isConsolidated()) {
                outputRanges.add(range);
            }
        }
        zipRangeList = outputRanges;
    }

    /**
     * Write the output to the terminal.
     */
    protected void writeOutput() {
        System.out.print("\nConsolidated output> ");

        getZipRangeList().forEach((range) -> {
            System.out.print(String.format("[%05d,%05d] ", range.getLower(), range.getUpper()));
        });
        System.out.println("\n");
    }

    /**
     * Determine if the two ZipRange objects have overlapping values.
     *
     * @param range
     * @param range2
     * @return
     */
    protected boolean rangesOverlap(ZipRange range, ZipRange range2) {
        return isBetween(range.getLower(), range2.getLower(), range2.getUpper());
    }

    /**
     * Combine two ZipRange objects into a new ZipRange object using the lowest
     * and highest values of the two ZipRange objects.
     *
     * @param range
     * @param range2
     * @return
     */
    protected ZipRange consolidate(ZipRange range, ZipRange range2) {
        range.setConsolidated(true);
        range2.setConsolidated(true);

        Integer lowerBound = Math.min(range.getLower(), range2.getLower());
        Integer upperBound = Math.max(range.getUpper(), range2.getUpper());
        return new ZipRange(lowerBound, upperBound);
    }

    /**
     * Check to determine if Integer i is between the lower and upper values
     *
     * @param i
     * @param lower
     * @param upper
     * @return
     */
    protected boolean isBetween(Integer i, Integer lower, Integer upper) {
        return (i >= lower && i <= upper);
    }

    protected ArrayList<ZipRange> getZipRangeList() {
        return zipRangeList;
    }
}
