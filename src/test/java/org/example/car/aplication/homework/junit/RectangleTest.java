package org.example.car.aplication.homework.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    void getArea() {
        Shape rectangle = new Rectangle(8, 4);
        assertEquals(32.0, rectangle.getArea(), 0.0001);
    }
}