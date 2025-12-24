package org.example.car.aplication.homework.junit;

public class Rhombus implements  Shape{
    private double diagonale1;
    private double diagonale2;

    public Rhombus(double diagonale1, double diagonale2) {
        this.diagonale1 = diagonale1;
        this.diagonale2 = diagonale2;
    }

    @Override
    public double getArea() {
        return 0.5 * diagonale1 * diagonale2;
    }
}
