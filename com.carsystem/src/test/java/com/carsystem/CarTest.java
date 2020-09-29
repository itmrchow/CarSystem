package com.carsystem;

import org.junit.Test;


import static org.junit.Assert.assertThrows;

public class CarTest {

    @Test
    public void testBigCarException() {
        // 大車
        assertThrows(CarException.class, () -> new Car("xxx", 115, 100));

    }

    @Test
    public void testSmallCarException() {
        // 小車
        assertThrows(CarException.class, () -> new Car("xxx", 130, 110));


    }
}
