package me.ratna.wk4.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Person {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    @NotNull
    private String name;
    @NotNull
    @Email
    private String email;
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Job> morejob;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Education> moreEducation;
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Skill> moreSkill;

    private ArrayList<Job> jobs;
    private ArrayList<Education> educations;
    private ArrayList<Skill> skills;
    public long getId() {
        return id;
    }
    public void  setId(long id) {this.id = id;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public ArrayList<Education> getEducations() {
        return educations;
    }

    public void setEducations(ArrayList<Education> educations) {
        this.educations = educations;
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }
    public Set<Job> getMorejob() {
        return morejob;
    }

    public void setMorejob(Set<Job> morejob) {
        this.morejob = morejob;
    }

    public Set<Education> getMoreEducation() {
        return moreEducation;
    }

    public void setMoreEducation(Set<Education> moreEducation) {
        this.moreEducation = moreEducation;
    }

    public Set<Skill> getMoreSkill() {
        return moreSkill;
    }

    public void setMoreSkill(Set<Skill> moreSkill) {
        this.moreSkill = moreSkill;
    }
}