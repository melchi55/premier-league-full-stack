package com.example.premierLeague.api;

import java.util.UUID;
import com.example.premierLeague.model.FootballClub;
import com.example.premierLeague.service.FootballClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//getting and adding clubs to the file
@RequestMapping("api/v1/footballClub")
@RestController
@CrossOrigin(origins = "*")
public class FootballClubController
 {
    private final FootballClubService footClubService;

    @Autowired
    public FootballClubController(FootballClubService footClubService) {
        this.footClubService = footClubService;
    }


    @GetMapping
    public FootballClub[] getAllClubs(){
        return this.footClubService.getAllClubs();
    }

    @GetMapping(path="{id}")
    public FootballClub getClubById(@PathVariable("id") UUID id){
        return this.footClubService.getClubById(id).orElse(null);
    }


    @PutMapping(path={"{id}"})
    private void updateClubById(@PathVariable("id") UUID id,@RequestBody @Validated @NonNull FootballClub club){
        this.footClubService.updateClubById(id, club);
    }


}
