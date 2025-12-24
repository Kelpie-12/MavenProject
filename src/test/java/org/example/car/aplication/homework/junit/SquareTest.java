package org.example.car.aplication.homework.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SquareTest {

    @Test
    void getArea() {
        Shape square = new Square(6);
        assertEquals(36.0, square.getArea(), 0.0001);
    }
}