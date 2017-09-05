package me.ratna.wk4.models;;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
//import java.time.LocalDate;

@Entity
public class Employment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Size(min=1, max=50)
    private String positionTitle;

    @NotEmpty
    @Size(min=1, max=50)
    private String organisation;
    @DateTimeFormat(pattern = "MMM,yyyy")
    private String startDate;
    @DateTimeFormat(pattern = "MMM,yyyy")
    private String endDate;

    // Start date (employment with the organisation)
//    @NotNull
//    @DateTimeFormat(pattern = "yyyy/MM/dd")
//    private LocalDate startDate;
//
//    // End date (employment with the organisation)
//    @DateTimeFormat(pattern = "yyyy/MM/dd")

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

//    private LocalDate endDate;

    // If person has just one duty for his position:
    @NotEmpty
    @Size(min=1, max=50)
    private String duty1;
    @NotEmpty
    @Size(min=1, max=50)
    private String duty2;
    // Add Relationship - Person and Employment
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    private Person person;

    // ==== Setters and Getters ====

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

//    public LocalDate getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(LocalDate startDate) {
//        this.startDate = startDate;
//    }
//
//    public LocalDate getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(LocalDate endDate) {
//        this.endDate = endDate;
//    }

    public String getDuty1() {
        return duty1;
    }

    public void setDuty1(String duty1) {
        this.duty1 = duty1;
    }

    public String getDuty2() {
        return duty2;
    }

    public void setDuty2(String duty2) {
        this.duty2 = duty2;
    }

    // Setter and Getter for Relationship - Person and Employment

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
