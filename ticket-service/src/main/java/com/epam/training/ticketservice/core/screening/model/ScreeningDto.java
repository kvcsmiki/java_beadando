package com.epam.training.ticketservice.core.screening.model;

import com.epam.training.ticketservice.core.movie.model.MovieDto;
import com.epam.training.ticketservice.core.room.model.RoomDto;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Value
@Builder
@EqualsAndHashCode
public class ScreeningDto {

    private final MovieDto movie;
    private final RoomDto room;
    private final LocalDateTime start;
    private final LocalDateTime end;

    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("00");
        String start = this.start.getYear() + "-" + format.format(this.start.getMonthValue())
                + "-" + format.format(this.start.getDayOfMonth())
                + " " + format.format(this.start.getHour())
                + ":" + format.format(this.start.getMinute());
        return movie.getName() + " (" + movie.getTheme() + ", " + movie.getLength()
                + " minutes), screened in room " + room.getName() + ", at " + start;
    }
}
