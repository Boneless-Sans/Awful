package com.boneless.code.neighborhood;

/*
 * A Painter that paints rows in The Neighborhood
 */
public class RowPainter extends Painter {

    /*
     * Sets the x and y values to the starting x and y location,
     * direction to the specified starting direction, and paint
     * to the specified starting paint amount
     */
    public RowPainter(int x, int y, String direction, int paint) {
        super(x, y, direction, paint);
    }

    /*
     * Turns the RowPainter object to the right
     */
    public void turnRight() {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /*
     * Moves the RowPainter object forward in the direction it is facing if it can move
     */
    public void moveIfCanMove() {
        if (canMove()) {
            move();
        }
    }

    /*
     * Paints a row in The Neighborhood with firstColor
     * then moves to the next row with secondColor
     */
    public void paintArea(String firstColor, String secondColor) {
        while (canMove()) {
            paintEast(firstColor);
            paintWest(secondColor);
            moveIfCanMove();

            if (!canMove()) {
                paintEast(firstColor);
                paintWest(secondColor);
                moveToNextRow();
            }
        }
    }

    /*
     * Paints a row if facing east and y location is even
     */
    public void paintEast(String color) {
        /* ----------------------------------- TO DO -----------------------------------
         * ✅ If the RowPainter object is facing east and its y location is an even
         * number, paint with the parameter color.
         * -----------------------------------------------------------------------------
         */
        if (getY() % 2 == 0) {
            while (!isFacingEast()) {
                turnLeft();
            }
            while (canMove() && isFacingEast()) {
                paint(color);
                move();
                if(!isOnPaint()){
                    paint(color);
                }
            }
        }
    }

    /*
     * Paints a row if facing west and y location is odd
     */
    public void paintWest(String color) {
        /* ----------------------------------- TO DO -----------------------------------
         * ✅ If the RowPainter object is facing west and its y location is an odd
         * number, paint with the parameter color.
         * -----------------------------------------------------------------------------
         */
        if (getY() % 2 != 0) {
            while (!isFacingWest()) {
                turnLeft();
            }
            while (canMove() && isFacingWest()) {
                paint(color);
                move();
                if(!isOnPaint()){
                    paint(color);
                }
            }
        }
    }

    /*
     * Moves to the next row if unable to move forward
     */
    public void moveToNextRow() {
        if (canMove("south")) {
            if (isFacingEast()) {
                turnRight();
                moveIfCanMove();
                turnRight();
            }
            else {
                turnLeft();
                moveIfCanMove();
                turnLeft();
            }
        }
    }

}