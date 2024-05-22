package se.kth.iv1350.amazingpos.task2;

/**
 * Class that wraps an primitive int-value in an Object. 
 * Has public methods for getting the value as other primitive datatypes.
 */
public class Integer {
    private int value;

    public Integer (int value) {
        this.value = value;
    }
 
    public int intValue() {
        return this.value;
    }

    public double doubleValue() {
        return (double)this.value;
    }

    public float floatValue() {
        return (float)this.value;
    }

    public long longValue() {
        return (long)this.value;
    }
}
