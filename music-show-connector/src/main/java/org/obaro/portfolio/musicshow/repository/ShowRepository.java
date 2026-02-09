package org.obaro.portfolio.musicshow.repository;

import org.obaro.portfolio.musicshow.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Long> {

    List<Show> findByCityIgnoreCase(String city);

    List<Show> findByArtistIgnoreCase(String artist);

    List<Show> findByCityIgnoreCaseAndArtistIgnoreCase(String city, String artist);
}
