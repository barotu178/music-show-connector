package org.obaro.portfolio.musicshow.repository;

import org.obaro.portfolio.musicshow.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
}
