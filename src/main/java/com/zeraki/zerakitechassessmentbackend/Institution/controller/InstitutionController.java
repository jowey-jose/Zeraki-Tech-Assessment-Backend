package com.zeraki.zerakitechassessmentbackend.Institution.controller;

import com.zeraki.zerakitechassessmentbackend.Institution.model.Institution;
import com.zeraki.zerakitechassessmentbackend.Institution.repository.InstitutionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller // Controller Relies on Some Spring MVC's key features to display the views.
public class InstitutionController {

    private InstitutionRepository institutionRepository;

    // Displays the institution form to add a new institution.
    @GetMapping("/addInstitutionForm")
    public String showAddInstitutionForm(Institution institution) {
        return "add-institution";
    }

    // Persists a new entity to the DB after validating the constrained fields.
    @PostMapping("/addInstitution")
    public String addInstitution(@Valid Institution institution, BindingResult result, Model model) {
        // If the entity doesn't pass the validation the institution form will be redisplayed.
        if (result.hasErrors()) {
            return "add-institution";
        }

        // Otherwise, the entity is saved and redirects to the index page to return a view of all persisted entities.
        institutionRepository.save(institution);
        return "redirect:/index";
    }

    // Listing of all persisted entities in the index view.
    @GetMapping("/index")
    public String showOrganizationList(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "index";
    }

    // Fetches the InstitutionEntity that matches the supplied id from the Db.
    @GetMapping("/edit/{id}")
    public String showUpdateInstitutionForm(@PathVariable("id") long id,Model model) {
        // If the entity exists, it will be passed on as a model attribute to update the form view.
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Institution Id:" + id));

        // The form is then populated with value of the institution_name field.
        model.addAttribute("institution", institution);
        return "update-institution";

    }

    // Persist the updated entity in the Db
    @PostMapping("/updateInstitution/{id}")
    public String updateInstitution (@PathVariable("id") long id, @Valid Institution institution, BindingResult result, Model model){
        if (result.hasErrors()){
            institution.setId(id);
            return  "update-institution";
        }

        institutionRepository.save(institution);
        return "redirect:/index";
    }

    // Remove the given entity from the Db.
    @GetMapping("/deleteInstitution/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Institution institution = institutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
        institutionRepository.delete((institution));
        return "redirect:/index";
    }
}
