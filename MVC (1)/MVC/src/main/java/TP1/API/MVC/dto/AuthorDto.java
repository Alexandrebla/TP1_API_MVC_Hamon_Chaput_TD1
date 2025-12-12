package TP1.API.MVC.dto;

public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer birthYear;

    public AuthorDto(Long id, String firstName, String lastName, Integer birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Integer getBirthYear() { return birthYear; }
}
