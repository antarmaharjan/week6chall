package me.ratna.wk4.controller;

import me.ratna.wk4.models.Education;
import me.ratna.wk4.models.Job;
import me.ratna.wk4.models.Person;
import me.ratna.wk4.models.Skill;
import me.ratna.wk4.repositories.EducationRepository;
import me.ratna.wk4.repositories.JobRepository;
import me.ratna.wk4.repositories.PersonRepository;
import me.ratna.wk4.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Set;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class HomeController {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    EducationRepository educationRepository;
    @Autowired
    JobRepository jobRepository;
    @Autowired
    SkillRepository skillRepository;

    @RequestMapping("/login")
    public String logon() {
        return "login";
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newPerson", new Person());
        return "index";
    }

    @PostMapping("/")
    public String index(@Valid @ModelAttribute("newPerson") Person newPerson, Model toSend, BindingResult result) {
        System.out.println(result.toString());
        if (result.hasErrors()) {
            return "index";
        }
        personRepository.save(newPerson);
        return "confirmperson";
    }

    @GetMapping("/addeducation")
    public String addEducation(Model model) {

        model.addAttribute("anEducation", new Education());
        return "addeducation";
    }

    @PostMapping("/addeducation")
    public String confirmEducation(@Valid @ModelAttribute("anEducation") Education anEducation, Model model, BindingResult result) {
        if (result.hasErrors()) {
            return "addeducation";
        }
        educationRepository.save(anEducation);
        //additional line
        model.addAttribute("numberOfEdu", educationRepository.count());
        return "confirmeducation";
    }

    @GetMapping("/addjob")
    public String addwork(Model model, @ModelAttribute("newPerson") Person newPerson) {
        model.addAttribute("aJob", new Job());
        return "addjob";
    }

    @PostMapping("/addjob")
    public String confirmjob(@Valid @ModelAttribute("aJob") Job aJob, @ModelAttribute("newPerson") Person newPerson, BindingResult result) {
        if (result.hasErrors()) {
            return "addjob";
        }
        jobRepository.save(aJob);
        return "confirmjob";
    }

    @GetMapping("/addskill")
    public String addskill(Model model, @ModelAttribute("newPerson") Person newPerson) {
        model.addAttribute("aSkill", new Skill());

        return "addskill";
    }

    @PostMapping("/addskill")
    public String confirmSkill(@Valid @ModelAttribute("aSkill") Skill aSkill, @ModelAttribute("newPerson") Person newPerson, BindingResult result) {
        if (result.hasErrors()) {
            return "addskill";
        }
        skillRepository.save(aSkill);
        return "confirmskill";
    }

    @GetMapping("/generateresume")
    public String generateResume(Model model) {

        Person person = personRepository.findAll().iterator().next();
        //System.out.println("name is " + person.getName());
        model.addAttribute("myPerson", person);

        Iterable<Education> edu = educationRepository.findAll();
        model.addAttribute("myEducation", edu);
        Iterable<Job> job = jobRepository.findAll();
        model.addAttribute("myWork", job);
        Iterable<Skill> skill = skillRepository.findAll();
        model.addAttribute("mySkills", skill);
        return "generateresume";
    }

    @GetMapping("/index/update/{id}")
    public String updatePerson(@PathVariable("id") long id, Model model) {
        Person person = personRepository.findOne(id);
        model.addAttribute("newPerson", person);
        return "index";
    }

    @GetMapping("/updateeducation/{id}")
    public String updateEducation(@PathVariable("id") long id, Model model) {
        model.addAttribute("anEducation", educationRepository.findOne(id));
        return "addeducation";
    }

    @GetMapping("/deleteeducation/{id}")
    public String deleteEducation(@PathVariable("id") long id) {
        educationRepository.delete(id);
        return "redirect:/generateresume";
    }

    @GetMapping("/delete")
    public String deleteperson(Model model) {
        jobRepository.deleteAll();
        skillRepository.deleteAll();
        educationRepository.deleteAll();
        personRepository.deleteAll();
        model.addAttribute("newPerson", new Person());
        return "index";
    }

    @GetMapping("/confirmeducation/update/{id}")
    public String updateeducation(@PathVariable("id") long id, Model model) {
        Education education = educationRepository.findOne(id);
        model.addAttribute("newEducation", education);
        return "addeducation";
    }

    @GetMapping("/addeducation/delete/{id}")
    public String delete1(@PathVariable("id") long id) {
        educationRepository.delete(id);
        //return "redirect:/addjob";
        return "confirmeducation";
    }
//    @ManyToOne(fetch = FetchType.EAGER)
// +    @JoinColumn(name="eduofperson_id")
// +    private Person person;
// +
//         +    public Person getPerson() {
//        +        return person;
//        +    }
// +
//         +    public void setPerson(Person person) {
//        +        this.person = person;
//     private ArrayList<Education> education;
// +    @OneToMany(mappedBy = "", cascade = CascadeType.ALL, fetch= FetchType.EAGER)
// +    private Set<Education> educations;
//
// -    private ArrayList<Skill> skill;
// +    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
// +    private Set<Skill> skills;
//
// -    private ArrayList<Job> experience;
// +    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
// +    private Set<Job> experiences;
//}
}

