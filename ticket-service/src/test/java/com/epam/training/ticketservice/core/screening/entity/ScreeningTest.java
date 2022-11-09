package com.epam.training.ticketservice.core.screening.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScreeningTest {

    private final LocalDateTime start = LocalDateTime.now();
    private final LocalDateTime end = LocalDateTime.now().plusMinutes(10);
    private Screening underTest = new Screening("TestRoom","TestMovie", start, end);

    @Test
    void testEqualsFalse(){
        Screening empty = new Screening();

        assertNotEquals(empty,underTest);

    }
    @Test
    void testEqualsTrue(){
        Screening same = new Screening("TestRoom","TestMovie",start, end);
        assertEquals(same,underTest);
    }

}