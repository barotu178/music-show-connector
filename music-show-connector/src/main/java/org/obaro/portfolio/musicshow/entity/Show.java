package org.obaro.portfolio.musicshow.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String artist;

    @Column(nullable = false)
    private LocalDate showDate;

    @Column(nullable = false)
    private String venue;

    private String city;

    // Required by JPA
    public Show() {
    }

    public Show(String artist, LocalDate showDate, String venue, String city) {
        this.artist = artist;
        this.showDate = showDate;
        this.venue = venue;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDate getShowDate() {
        return showDate;
    }

    public void setShowDate(LocalDate showDate) {
        this.showDate = showDate;
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
}
