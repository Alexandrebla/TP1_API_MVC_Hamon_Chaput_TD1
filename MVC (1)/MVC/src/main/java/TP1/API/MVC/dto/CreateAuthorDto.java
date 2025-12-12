package TP1.API.MVC.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateAuthorDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    private Integer birthYear;

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getBirthYear() { return birthYear; }
}
