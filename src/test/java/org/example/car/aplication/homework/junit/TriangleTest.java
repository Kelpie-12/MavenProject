package org.example.car.aplication.homework.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void getArea() {
        Shape triangle = new Triangle(10, 5);
        assertEquals(25.0, triangle.getArea(), 0.0001);
    }
}