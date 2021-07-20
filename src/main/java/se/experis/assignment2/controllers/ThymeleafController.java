package se.experis.assignment2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import se.experis.assignment2.data_access.ThymeleafRepository;
import se.experis.assignment2.models.Track;

import java.util.ArrayList;

@Controller
public class ThymeleafController {
    ThymeleafRepository thymeleafRepository = new ThymeleafRepository();

    /*@GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }*/
    @GetMapping("/")
    public String getHomePage(Model model){
        model.addAttribute("artists", thymeleafRepository.getFiveRandomArtists());
        model.addAttribute("tracks", thymeleafRepository.getFiveRandomTracks());
        model.addAttribute("genres", thymeleafRepository.getFiveRandomGenres());
        return "index";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchForSong(@RequestParam("searchterm") String searchterm, Model model) {
        model.addAttribute("searchresults", thymeleafRepository.searchForTrack(searchterm));
        return "search";
    }
}
