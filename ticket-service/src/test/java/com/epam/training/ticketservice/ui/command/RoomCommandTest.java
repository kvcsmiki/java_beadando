package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.RoomService;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.user.UserService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomCommandTest {

    private final RoomService roomService = mock(RoomService.class);
    private final UserService userService = mock(UserService.class);

    private final RoomCommand underTest = new RoomCommand(roomService,userService);

    @Test
    void testGetRoomListShouldReturnEmpty(){
        String expected = "There are no rooms at the moment";

        String actual =  underTest.getRoomList();

        assertEquals(expected,actual);

    }

    @Test
    void testGetRoomListShouldReturnList(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();
        String expected = roomDto.toString();

        when(roomService.getRoomList()).thenReturn(List.of(roomDto));

        String actual = underTest.getRoomList();

        assertEquals(expected,actual);
        verify(roomService).getRoomList();

    }
    @Test
    void createRoomAlreadyExists(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();
        when(roomService.getRoomByName("Test")).thenReturn(Optional.of(roomDto));
        String expected = "Room " + roomDto.getName() + " already exists, try \"update room\" instead";

        String actual = underTest.createRoom("Test",10,10);
        assertEquals(expected,actual);
        verify(roomService).getRoomByName("Test");
    }
    @Test
    void createRoomNameIsNotDifferent(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();
        when(roomService.getRoomByName("Test")).thenReturn(Optional.of(roomDto));
        String expected = "Room " + roomDto.getName() + " already exists, try \"update room\" instead";

        String actual = underTest.createRoom("Test",30,40);
        assertEquals(expected,actual);
        verify(roomService).getRoomByName("Test");
    }
    @Test
    void createRoomShouldCreate(){
        when(roomService.getRoomByName("Test")).thenReturn(Optional.empty());
        String expected = "Room Test created successfully";

        String actual = underTest.createRoom("Test",10,10);
        assertEquals(expected,actual);
        verify(roomService).getRoomByName("Test");
    }
    @Test
    void deleteMovieDoesntExist(){
        String expected = "Room " + "Test" + " doesn't exist";

        String actual = underTest.deleteRoom("Test");

        assertEquals(expected,actual);
    }

    @Test
    void deleteMovieShouldDelete(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();

        when(roomService.getRoomByName("Test")).thenReturn(Optional.of(roomDto));
        when(roomService.deleteRoom(roomDto)).thenReturn(true);

        String expected = "Room Test deleted successfully";

        String actual = underTest.deleteRoom("Test");

        assertEquals(expected,actual);
        verify(roomService).getRoomByName("Test");
    }
    @Test
    void deleteRoomDeleteFails(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();

        when(roomService.getRoomByName("Test")).thenReturn(Optional.of(roomDto));
        when(roomService.deleteRoom(roomDto)).thenReturn(false);

        String expected = "Deleting " + roomDto.getName() + " failed";

        String actual = underTest.deleteRoom("Test");

        assertEquals(expected,actual);
        verify(roomService).getRoomByName("Test");
    }
    @Test
    void updateRoomDoesntExist(){
        String expected = "Room Test doesn't exist";
        String actual = underTest.updateRoom("Test",30,10);

        assertEquals(expected,actual);
    }

    @Test
    void updateRoomShouldUpdate(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();

        when(roomService.getRoomByName("Test")).thenReturn(Optional.of(roomDto));
        when(roomService.deleteRoom(roomDto)).thenReturn(true);

        String expected = "Room Test updated successfully";

        String actual = underTest.updateRoom("Test",30,20);

        assertEquals(expected,actual);
        verify(roomService).getRoomByName("Test");
        verify(roomService).deleteRoom(roomDto);
    }
    @Test
    void updateRoomUpdateFails(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();

        when(roomService.getRoomByName("Test")).thenReturn(Optional.of(roomDto));
        when(roomService.deleteRoom(roomDto)).thenReturn(false);

        String expected = "Updating " + roomDto.getName() + " failed";

        String actual = underTest.updateRoom("Test",30,20);

        assertEquals(expected,actual);
        verify(roomService).getRoomByName("Test");
        verify(roomService).deleteRoom(roomDto);
    }
}