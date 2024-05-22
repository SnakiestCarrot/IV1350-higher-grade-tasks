package se.kth.iv1350.amazingpos.task2;

/**
 * Thes square class extends integer and has a method for getting a square of integer.
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
