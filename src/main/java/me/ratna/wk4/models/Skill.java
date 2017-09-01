package me.ratna.wk4.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Skill {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @NotNull
    private String skill;
    @NotNull
    private String rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="person_id")
    private Person person;

    public long getId() {
        return id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;

    }
}