package com.example.premierLeague.api;

import com.example.premierLeague.model.FootballMatch;
import com.example.premierLeague.service.AddMatchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//adding match to the file
@RequestMapping("api/v1/addMatch")
@RestController
@CrossOrigin(origins = "*")
public class AddMatchController {
    
    private final AddMatchService addMatchService;

    @Autowired
    public AddMatchController(AddMatchService addMatchService) {
        this.addMatchService = addMatchService;
    }

    @GetMapping
    public FootballMatch addMatch(){
        return this.addMatchService.addMatch();
    }

}
