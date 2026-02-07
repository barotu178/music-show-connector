package org.obaro.portfolio.musicshow.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ShowRequest {

    @NotBlank(message = "Artist name is required")
    @Size(max = 100)
    private String artist;

    @NotBlank(message = "Venue is required")
    @Size(max = 100)
    private String venue;

    @NotBlank(message = "City is required")
    @Size(max = 50)
    private String city;

    @NotNull(message = "Show date is required")
    private LocalDate showDate;

    // getters & setters
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
    }
}
