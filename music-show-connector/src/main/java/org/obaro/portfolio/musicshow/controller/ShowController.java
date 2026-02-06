package org.obaro.portfolio.musicshow.controller;

import org.obaro.portfolio.musicshow.entity.Show;
import org.obaro.portfolio.musicshow.repository.ShowRepository;
import org.springframework.web.bind.annotation.*;

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
    public Show createShow(@RequestBody Show show) {
        return showRepository.save(show);
    }
}
