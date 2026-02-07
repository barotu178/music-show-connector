package org.obaro.portfolio.musicshow.controller;

import org.obaro.portfolio.musicshow.dto.ShowRequest;
import org.obaro.portfolio.musicshow.entity.Show;
import org.obaro.portfolio.musicshow.repository.ShowRepository;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    // GET /shows
    @GetMapping
    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    // POST /shows
    @PostMapping
    public ResponseEntity<Show> createShow(
            @Valid @RequestBody ShowRequest request) {

        Show show = new Show();
        show.setArtist(request.getArtist());
        show.setVenue(request.getVenue());
        show.setCity(request.getCity());
        show.setShowDate(request.getShowDate());

        Show savedShow = showRepository.save(show);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedShow);
    }
}
