package org.obaro.portfolio.musicshow.controller;

import org.obaro.portfolio.musicshow.dto.ShowRequest;
import org.obaro.portfolio.musicshow.entity.Show;
import org.obaro.portfolio.musicshow.exception.ResourceNotFoundException;
import org.obaro.portfolio.musicshow.repository.ShowRepository;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    // --------------------
    // READ ALL + SEARCH
    // GET /shows
    // GET /shows?city=Milwaukee
    // GET /shows?artist=Burning%20Spear
    // GET /shows?city=Milwaukee&artist=Burning%20Spear
    // --------------------
    @GetMapping
    public List<Show> getShows(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String artist) {

        if (city != null && artist != null) {
            return showRepository
                    .findByCityIgnoreCaseAndArtistIgnoreCase(city, artist);
        }

        if (city != null) {
            return showRepository.findByCityIgnoreCase(city);
        }

        if (artist != null) {
            return showRepository.findByArtistIgnoreCase(artist);
        }

        return showRepository.findAll();
    }

    // --------------------
    // READ ONE
    // GET /shows/{id}
    // --------------------
    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Show not found with id " + id
                        )
                );
    }

    // --------------------
    // CREATE
    // POST /shows
    // --------------------
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

    // --------------------
    // UPDATE
    // PUT /shows/{id}
    // --------------------
    @PutMapping("/{id}")
    public Show updateShow(
            @PathVariable Long id,
            @Valid @RequestBody ShowRequest request) {

        Show show = showRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Show not found with id " + id
                        )
                );

        show.setArtist(request.getArtist());
        show.setVenue(request.getVenue());
        show.setCity(request.getCity());
        show.setShowDate(request.getShowDate());

        return showRepository.save(show);
    }

    // --------------------
    // DELETE
    // DELETE /shows/{id}
    // --------------------
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteShow(@PathVariable Long id) {

        if (!showRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Show not found with id " + id
            );
        }

        showRepository.deleteById(id);
    }
}
