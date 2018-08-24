package us.witcraft.zipcodereduction;

/**
 * Utility class to contain the upper and lower range of two ZIP code values.
 *
 * @author Dean Witcraft
 */
public class ZipRange {

    private Integer lower; //The lower value of the ZIP code range
    private Integer upper; //The upper value of the ZIP code range
    private boolean consolidated; //flag set to true when the range has been consolidated

    ZipRange(Integer lower, Integer upper) {
        this.lower = lower;
        this.upper = upper;
        this.consolidated = false;
    }

    // accessors
    public Integer getLower() {
        return lower;
    }

    public void setLower(Integer lower) {
        this.lower = lower;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public boolean isConsolidated() {
        return consolidated;
    }

    public void setConsolidated(boolean consolidated) {
        this.consolidated = consolidated;
    }

}
