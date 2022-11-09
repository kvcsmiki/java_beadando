package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.movie.MovieService;
import com.epam.training.ticketservice.core.movie.entity.Movie;
import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.user.User;
import com.epam.training.ticketservice.core.user.UserDto;
import com.epam.training.ticketservice.core.user.UserService;
import org.aspectj.apache.bcel.classfile.Module;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MovieCommandTest {

    private final MovieService movieService = mock(MovieService.class);
    private final UserService userService = mock(UserService.class);

    private final MovieCommand underTest = new MovieCommand(movieService,userService);

    @Test
    void testGetMovieListShouldReturnEmpty(){
        String expected = "There are no movies at the moment";

        String actual =  underTest.getMovieList();

        assertEquals(expected,actual);

    }

    @Test
    void testGetMovieListShouldReturnList(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();
        String expected = movieDto.toString();

        when(movieService.getMovieList()).thenReturn(List.of(movieDto));

        String actual = underTest.getMovieList();

        assertEquals(expected,actual);
        verify(movieService).getMovieList();

    }
    @Test
    void createMovieAlreadyExists(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();
        when(movieService.getMovieByName("Test")).thenReturn(Optional.of(movieDto));
        String expected = "Movie " + movieDto.getName() + " already exists, try \"update movie\" instead";

        String actual = underTest.createMovie("Test","theme",10);
        assertEquals(expected,actual);
        verify(movieService).getMovieByName("Test");
    }
    @Test
    void createMovieNameIsNotDifferent(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();
        when(movieService.getMovieByName("Test")).thenReturn(Optional.of(movieDto));
        String expected = "Movie " + movieDto.getName() + " already exists, try \"update movie\" instead";

        String actual = underTest.createMovie("Test","newTheme",30);
        assertEquals(expected,actual);
        verify(movieService).getMovieByName("Test");
    }
    @Test
    void createMovieShouldCreate(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();
        when(movieService.getMovieByName("Test")).thenReturn(Optional.empty());
        String expected = "Movie " + movieDto.getName() + " created successfully";

        String actual = underTest.createMovie("Test","newTheme",30);
        assertEquals(expected,actual);
        verify(movieService).getMovieByName("Test");
    }

    @Test
    void deleteMovieDoesntExist(){
        String expected = "Movie " + "Test" + " doesn't exist";

        String actual = underTest.deleteMovie("Test");

        assertEquals(expected,actual);
    }

    @Test
    void deleteMovieShouldDelete(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();

        when(movieService.getMovieByName("Test")).thenReturn(Optional.of(movieDto));
        when(movieService.deleteMovie(movieDto)).thenReturn(true);

        String expected = "Movie " + movieDto.getName() + " deleted successfully";

        String actual = underTest.deleteMovie("Test");

        assertEquals(expected,actual);
        verify(movieService).getMovieByName("Test");
    }
    @Test
    void deleteMovieDeleteFails(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();

        when(movieService.getMovieByName("Test")).thenReturn(Optional.of(movieDto));
        when(movieService.deleteMovie(movieDto)).thenReturn(false);

        String expected = "Deleting " + movieDto.getName() + " failed";

        String actual = underTest.deleteMovie("Test");

        assertEquals(expected,actual);
        verify(movieService).getMovieByName("Test");
    }

    @Test
    void updateMovieDoesntExist(){
        String expected = "Movie Test doesn't exist";
        String actual = underTest.updateMovie("Test","theme",10);

        assertEquals(expected,actual);
    }

    @Test
    void updateMovieShouldUpdate(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();

        when(movieService.getMovieByName("Test")).thenReturn(Optional.of(movieDto));
        when(movieService.deleteMovie(movieDto)).thenReturn(true);

        String expected ="Movie " + movieDto.getName() + " updated successfully";

        String actual = underTest.updateMovie("Test","newTheme",20);

        assertEquals(expected,actual);
        verify(movieService).getMovieByName("Test");
        verify(movieService).deleteMovie(movieDto);
    }
    @Test
    void updateMovieUpdateFails(){
        MovieDto movieDto = MovieDto.builder().name("Test").theme("theme").length(10).build();

        when(movieService.getMovieByName("Test")).thenReturn(Optional.of(movieDto));
        when(movieService.deleteMovie(movieDto)).thenReturn(false);

        String expected = "Updating " + movieDto.getName() + " failed";

        String actual = underTest.updateMovie("Test","newTheme",20);

        assertEquals(expected,actual);
        verify(movieService).getMovieByName("Test");
        verify(movieService).deleteMovie(movieDto);
    }



}