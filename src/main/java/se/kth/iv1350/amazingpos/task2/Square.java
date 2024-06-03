package se.kth.iv1350.amazingpos.task2;

/**
 * The square class extends integer and stores the square of integer.
 */
public class Square extends Integer{
    private int square;

    public Square(int value) {
        super(value);
        this.square = value*value;
    }

    public int getSquare () {
        return square;
    }
}
