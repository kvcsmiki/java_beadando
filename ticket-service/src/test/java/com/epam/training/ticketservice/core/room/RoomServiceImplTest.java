package com.epam.training.ticketservice.core.room;

import com.epam.training.ticketservice.core.room.entity.Room;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import com.epam.training.ticketservice.core.room.repository.RoomRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomServiceImplTest {

    final RoomRepository roomRepository = mock(RoomRepository.class);
    final RoomService underTest = new RoomServiceImpl(roomRepository);

    @Test
    void testGetRoomListShouldReturnEmpty(){

        List<RoomDto> expected = List.of();

        List<RoomDto> actual = underTest.getRoomList();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRoomListShouldReturnValue(){
        Room room = new Room("Test",10,10);

        List<Room> expected = List.of(room);
        when(roomRepository.findAll()).thenReturn(List.of(room));

        List<RoomDto> actual = underTest.getRoomList();
        assertEquals(expected.size(),actual.size());
        for(int i=0; i<actual.size();i++){
            assertEquals(expected.get(i).getName(), actual.get(i).getName());
            assertEquals(expected.get(i).getRows(), actual.get(i).getRows());
            assertEquals(expected.get(i).getColumns(), actual.get(i).getColumns());
        }
        verify(roomRepository).findAll();

    }

    @Test
    void testCreateRoomShouldCallSaveWhenInputIsValid(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();
        underTest.createRoom(roomDto);
        Room room = new Room(roomDto.getName(),roomDto.getRows(),roomDto.getColumns());

        verify(roomRepository).save(room);
    }

    @Test
    void testDeleteShouldReturnFalse(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();
        boolean expected = false;
        boolean actual = underTest.deleteRoom(roomDto);

        assertEquals(expected,actual);
    }
    @Test
    void testDeleteShouldReturnTrue(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();
        Room room = new Room(roomDto.getName(),roomDto.getRows(),roomDto.getColumns());
        when(roomRepository.findByName(room.getName())).thenReturn(Optional.of(room));
        boolean expected = true;
        boolean actual = underTest.deleteRoom(roomDto);

        assertEquals(expected,actual);
        verify(roomRepository).delete(room);
    }

    @Test
    void testGetRoomByNameShouldReturnEmpty(){
        Optional<RoomDto> expected = Optional.empty();

        Optional<RoomDto> actual = underTest.getRoomByName("Test");

        assertEquals(expected, actual);
        verify(roomRepository).findByName("Test");
    }

    @Test
    void testGetRoomByNameShouldReturnValue(){
        RoomDto roomDto = RoomDto.builder().name("Test").rows(10).columns(10).build();
        Room room = new Room(roomDto.getName(),roomDto.getRows(),roomDto.getColumns());
        Optional<RoomDto> expected = Optional.of(roomDto);
        when(roomRepository.findByName("Test")).thenReturn(Optional.of(room));

        Optional<RoomDto> actual = underTest.getRoomByName("Test");

        assertEquals(expected, actual);
        verify(roomRepository).findByName("Test");

    }
}