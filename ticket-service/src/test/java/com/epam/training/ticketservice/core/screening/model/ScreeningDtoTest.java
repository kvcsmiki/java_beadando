package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.entity.Room;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ScreeningDtoTest {

    private MovieDto movieDto = mock(MovieDto.class);
    private RoomDto roomDto = mock(RoomDto.class);
    private LocalDateTime start = LocalDateTime.now();
    private LocalDateTime end = LocalDateTime.now().plusMinutes(10);

    private ScreeningDto underTest = new ScreeningDto(movieDto,roomDto,start,end);

    @Test
    void testNotEquals(){
        ScreeningDto empty = ScreeningDto.builder().build();

        assertNotEquals(empty,underTest);
    }
    @Test
    void testEquals(){
        ScreeningDto same = ScreeningDto.builder().movie(movieDto).room(roomDto).start(start).end(end).build();

        assertEquals(same,underTest);
    }
    @Test
    void testEqualsNotSame(){
        ScreeningDto notSame = ScreeningDto.builder().movie(movieDto).room(roomDto).start(start).end(start).build();

        assertNotEquals(notSame,underTest);
    }


}