package me.ratna.wk4.models;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Education {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @NotNull
    private long id;
    @NotNull
    private String school;
    @NotNull
    private String degree;
    @NotNull
    @Range(min = 1950,max = 2099)
    //@DateTimeFormat(pattern = "yyyy")
    private long gradyear;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    public long getGradyear() {
        return gradyear;
    }
    public void setGradyear(long gradyear) {
        this.gradyear = gradyear;
    }
    public long getId() {
        return id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {

            this.degree = degree;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Person getPerson() {
        return person;
    }

}
