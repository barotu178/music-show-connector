package org.obaro.portfolio.musicshow.controller;

import org.obaro.portfolio.musicshow.dto.ShowRequest;
import org.obaro.portfolio.musicshow.entity.Show;
import org.obaro.portfolio.musicshow.exception.ResourceNotFoundException;
import org.obaro.portfolio.musicshow.repository.ShowRepository;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    // -------------------------------------------------
    // READ ALL + SEARCH + PAGINATION + SORTING + DATE RANGE
    //
    // GET /shows
    // GET /shows?page=0&size=5
    // GET /shows?sort=showDate,desc
    // GET /shows?city=Milwaukee
    // GET /shows?artist=Burning%20Spear
    // GET /shows?city=Milwaukee&artist=Burning%20Spear
    // GET /shows?from=2026-04-01&to=2026-05-01
    // -------------------------------------------------
    @GetMapping
    public Page<Show> getShows(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String artist,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to,
            @PageableDefault(sort = "showDate", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {

        // Date range filter
        if (from != null && to != null) {
            return showRepository.findByShowDateBetween(
                    LocalDate.parse(from),
                    LocalDate.parse(to),
                    pageable
            );
        }

        // City + Artist filter
        if (city != null && artist != null) {
            return showRepository
                    .findByCityIgnoreCaseAndArtistIgnoreCase(city, artist, pageable);
        }

        // City only
        if (city != null) {
            return showRepository.findByCityIgnoreCase(city, pageable);
        }

        // Artist only
        if (artist != null) {
            return showRepository.findByArtistIgnoreCase(artist, pageable);
        }

        // No filters
        return showRepository.findAll(pageable);
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
