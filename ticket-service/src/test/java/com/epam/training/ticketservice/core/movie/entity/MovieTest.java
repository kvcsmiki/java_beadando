package com.epam.training.ticketservice.core.movie.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    private Movie underTest = new Movie("Test","theme",10);

    @Test
    void testNotEqualsWithEmpty(){
        Movie empty = new Movie();

        assertNotEquals(empty,underTest);
    }

    @Test
    void testEqualsWithSame(){
        Movie same = new Movie("Test","theme",10);

        assertEquals(same,underTest);
    }
    @Test
    void testEqualsWithNotSame(){
        Movie notSame = new Movie("Test","theme",30);

        assertNotEquals(notSame,underTest);
    }
}