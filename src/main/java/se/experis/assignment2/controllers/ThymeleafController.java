package se.experis.assignment2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import se.experis.assignment2.data_access.ArtistRepository;

/**
 * Controller for the Thymeleaf pages.
 */
@Controller
public class ThymeleafController {
    ArtistRepository artistRepository = new ArtistRepository();

    /**
     * Returns the HTML-homepage.
     * @param model : Model
     * @return : String
     */
    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("artists", artistRepository.getFiveRandomArtists());
        model.addAttribute("tracks", artistRepository.getFiveRandomTracks());
        model.addAttribute("genres", artistRepository.getFiveRandomGenres());
        return "index";
    }

    /**
     * Returns an HTML-page showing the results of a search for tracks based on a given search term.
     * @param searchterm : String
     * @param model : Model
     * @return : String
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchForSong(@RequestParam("searchterm") String searchterm, Model model) {
        model.addAttribute("searchresults", artistRepository.searchForTrack(searchterm));
        model.addAttribute("Query", searchterm);
        return "search";
    }
}
