package com.example.proov.tasks;

import com.example.proov.MathUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MathUtilTest {
    @Test
    public void min() {
        assertEquals(2, MathUtil.min(2, 3));
    }
    @Test
    public void doubles(){
        double a = 1.01;
        double b = 1;
        assertEquals(a, b, 0.00000001);
    }

    @Test
    public void max() {
        assertEquals(5, MathUtil.max(3, 5));
    }

    @Test
    public void abs() {
        assertEquals(7, MathUtil.abs(-7));
    }

    @Test
    public void isEven(){
        assertFalse(MathUtil.isEven(9));
    }




}
