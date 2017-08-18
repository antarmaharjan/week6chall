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

import javax.persistence.Id;
import javax.validation.Valid;

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

    @GetMapping("/")
    public String index(Model toSend){
        //welcome to the roboresume 3000.  please enter a name and email to continue
        //click a button to continue
        toSend.addAttribute("newPerson", new Person());

        return "index";
    }
    @PostMapping("/")
    public String index(@Valid @ModelAttribute("newPerson") Person newPerson, Model toSend, BindingResult result){
        System.out.println(result.toString());
        if(result.hasErrors()){
            return"index";
        }
        personRepository.save(newPerson);
        return"confirmperson";
    }
//    @GetMapping("/addeducation")
//    public String addeducationto(Model model)
//    {
//        System.out.println(educationRepository.count());
//
//        if(educationRepository.count()>=10)
//        {
//            return "limit";
//        }
//
//        model.addAttribute("neweducation", new Education());
//        return "addeducation";
//
//    }
//
//    @PostMapping("/addeducation")
//    public String posteducation(@Valid @ModelAttribute("neweducation") Education education, BindingResult bindingResult)
//    {
//        if(bindingResult.hasErrors())
//        {
//            return "addeducation";
//        }
//
//        educationRepository.save(education);
//        return "confirmeducation";
//    }

    @GetMapping("/addeducation")
    public String addEducation(Model toSend){

        toSend.addAttribute("anEducation", new Education());

        return "addeducation";
    }
    @PostMapping("/addeducation")
    public String confirmEducationAndAddMore(@Valid @ModelAttribute("anEducation") Education anEducation,Model toSend, BindingResult result){
        if(result.hasErrors()){
            return "addeducation";
        }
        educationRepository.save(anEducation);
        return "confirmeducation";
    }
    @GetMapping("/addjob")
    public String addwork(Model toSend,@ModelAttribute("newPerson") Person newPerson){
        toSend.addAttribute("aJob", new Job());
        return "addjob";
    }
    @PostMapping("/addjob")
    public String confirmWorkAndAddMore(@Valid @ModelAttribute("aJob") Job aJob, @ModelAttribute("newPerson") Person newPerson, BindingResult result){
        if(result.hasErrors()){
            return "addjob";
        }
        jobRepository.save(aJob);
        return "confirmjob";
    }

    @GetMapping("/addskill")
    public String addskill(Model toSend,@ModelAttribute("newPerson") Person newPerson){
        toSend.addAttribute("aSkill", new Skill());

        return "addskill";
    }
    @PostMapping("/addskill")
    public String confirmSkillAndAddMore(@Valid @ModelAttribute("aSkill") Skill aSkill, @ModelAttribute("newPerson") Person newPerson, BindingResult result){
        if(result.hasErrors()) {
            return "addskill";
        }
        skillRepository.save(aSkill);
        return "confirmskill";
    }
    @GetMapping("/generateresume")
    public String generateResume(Model toSend, @ModelAttribute("newPerson") Person newPerson){
        //maybe have buttons to add more education, work, or skills

        Person myPeep = personRepository.findById(1);
        System.out.println("my friends name is " + myPeep.getName());
        toSend.addAttribute("myPerson",myPeep);

        Iterable<Education> learnz = educationRepository.findAll();
        toSend.addAttribute("myEducation", learnz);
        Iterable<Job> workz = jobRepository.findAll();
        toSend.addAttribute("myWork", workz);
        Iterable<Skill> skillz = skillRepository.findAll();
        toSend.addAttribute("mySkills",skillz);
        return "generateresume";
    }
    //For delete and edit
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id){
        personRepository.delete(id);
        return "redirect:/addskill";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") long id,Model model){
        Person person = personRepository.findOne(id);
        model.addAttribute("newPerson", person);
        return "index";
    }

}