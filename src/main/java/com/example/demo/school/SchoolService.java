package com.example.demo.school;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public SchoolDto createNewSchool(
             SchoolDto  dto
    ){
        var school=schoolMapper.toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }

    @GetMapping("/schools")
    public List<SchoolDto> getAllSchool(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDto)
                .collect(Collectors.toList());

    }
}
