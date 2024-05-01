package com.example.leedsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDto {

    private String firstname;
    private String lastname;
    private String title;
    private String division;
    private String building;
    private String room;
}
