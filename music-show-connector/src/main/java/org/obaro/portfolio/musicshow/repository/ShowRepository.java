package org.obaro.portfolio.musicshow.repository;

import org.obaro.portfolio.musicshow.entity.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ShowRepository extends JpaRepository<Show, Long> {

    Page<Show> findByCityIgnoreCase(String city, Pageable pageable);

    Page<Show> findByArtistIgnoreCase(String artist, Pageable pageable);

    Page<Show> findByCityIgnoreCaseAndArtistIgnoreCase(
            String city,
            String artist,
            Pageable pageable
    );

    Page<Show> findByShowDateBetween(
            LocalDate from,
            LocalDate to,
            Pageable pageable
    );
}
