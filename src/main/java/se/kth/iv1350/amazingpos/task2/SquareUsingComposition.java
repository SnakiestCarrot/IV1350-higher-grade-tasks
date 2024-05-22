package se.kth.iv1350.amazingpos.task2;

/**
 * Square using composition, including the functionality of Integer-class.
 */
public class SquareUsingComposition {
    private int value;
    private int square;

    public SquareUsingComposition(int value) {
        this.value = value;
        this.square = value*value;
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

    public int getSquare () {
        return square;
    }
}
