package com.example.premierLeague.api;

import java.util.ArrayList;
import java.util.UUID;

import com.example.premierLeague.model.Date;
import com.example.premierLeague.model.FootballMatch;
import com.example.premierLeague.service.FootballMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//getting clubs from the file
@RequestMapping("api/v1/footballMatch")
@RestController
@CrossOrigin(origins = "*")
public class FootballMatchController {

    private final FootballMatchService footballMatchService;

    @Autowired
    public FootballMatchController(FootballMatchService footMatchService) {
        this.footballMatchService = footMatchService;
    }

    @PostMapping
    public void addFootballMatch(@RequestBody @Validated @NonNull FootballMatch footballMatch){
        this.footballMatchService.addFootballMatch(footballMatch);
    }

    @GetMapping
    public FootballMatch[] getAllMatches(){
        return this.footballMatchService.getAllMatches();
    }

    @GetMapping(path="{id}")
    public FootballMatch getMatchById(@PathVariable("id") UUID id){
        return this.footballMatchService.getMatchById(id).orElse(null);
    }

    
    @PostMapping(path="date")
    public ArrayList<FootballMatch> getAllMatchesByDate(@RequestBody @Validated @NonNull Date date){
        return this.footballMatchService.getAllClubsByDate(date);
    }






    
}
