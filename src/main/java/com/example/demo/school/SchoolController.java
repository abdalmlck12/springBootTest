package com.example.demo.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SchoolController {
    private final SchoolService schoolService;


    public SchoolController( SchoolService schoolService) {
        this.schoolService = schoolService;

    }
    @PostMapping("/schools")
    public SchoolDto createNewSchool(
            @RequestBody SchoolDto  dto
    ){
        return schoolService.createNewSchool(dto);
    }

    @GetMapping("/schools")
    public List<SchoolDto> getAllSchool(){
        return schoolService.getAllSchool();

    }
}
