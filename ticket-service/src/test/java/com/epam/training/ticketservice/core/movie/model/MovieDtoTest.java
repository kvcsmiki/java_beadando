package com.epam.training.ticketservice.core.movie.model;

import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.screening.model.ScreeningDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDtoTest {


    private MovieDto underTest = new MovieDto("Test","theme",10);

    @Test
    void testNotEquals(){
        MovieDto empty = MovieDto.builder().build();

        assertNotEquals(empty,underTest);
    }
    @Test
    void testEquals(){
        MovieDto same = MovieDto.builder().name("Test").theme("theme").length(10).build();

        assertEquals(same,underTest);
    }
    @Test
    void testEqualsNotSame(){
        MovieDto notSame = MovieDto.builder().name("Test").theme("theme").length(30).build();

        assertNotEquals(notSame,underTest);
    }
}