package org.example.car.aplication.homework.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RhombusTest {

    @Test
    void getArea() {
        Shape rhombus = new Rhombus(10, 8);
        assertEquals(40.0, rhombus.getArea(), 0.0001);
    }
}