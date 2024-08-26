package com.example.demo.cotroller;

import com.example.demo.config.AuthPasswordConfig;
import com.example.demo.exceptions.UserCanNotBeNullException;
import com.example.demo.mappers.PatientSaveDtoToUserMapper;
import com.example.demo.mappers.UserSaveDtoToUserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.dto.PatientSaveDto;
import com.example.demo.model.dto.UserSaveDto;
import com.example.demo.services.PatientService;
import com.example.demo.services.impl.UserServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor

class PatientController {
    final PatientService patientService;
    final AuthPasswordConfig authPasswordConfig;

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_WORKER"})
    @GetMapping("/patients")
    public String showAllPatients(Model model) {
        model.addAttribute("patients", patientService.findAllPatient());
        return "/patients/patients";
    }

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_WORKER"})
    @GetMapping("/patient/id/{id}")
    public String showCurrentPatient(Model model, @PathVariable Long id) {
        if (patientService.findPatientById(id).isPresent()) {
            model.addAttribute("patient", patientService.findPatientById(id).get());
            return "/patients/patient";
        }else{
            model.addAttribute("error", "Edycja pacjenta z id="+id+" nie jest możliwa");
            model.addAttribute("errorAction", "/patients");
     model.addAttribute("return", "Wróć do listy pacjentów");
                 return "/error-page";
        }


    }

    @GetMapping("/patient/add")
    public String registerPatient() {
        return "/patients/save-patient";
    }

    // edycja:
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/patient/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        if (patientService.findPatientById(id).isPresent()) {
            model.addAttribute("patient", patientService.findPatientById(id).get());
            model.addAttribute("patientRole", patientService.findPatientById(id).get().getRole().toString());
            return "/patients/save-patient";
        }else{
            model.addAttribute("error", "Pacjent z  id="+id+" nie istnieje");
     model.addAttribute("errorAction", "/patients");
     model.addAttribute("return", "Wróć do listy pacjentów");
                 return "/error-page";
        }

    }
 @PreAuthorize("isAuthenticated()")
    @PostMapping("/patient/update/{id}")
    public String patientUpdate(@PathVariable Long id, @Valid PatientSaveDto patientSaveDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication){
        if(bindingResult.hasErrors()){
            var errors= bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/patient/edit/"+id;
        }else{
            var patient= PatientSaveDtoToUserMapper.fromUserDtoToPatientEntity(patientSaveDto);
            var optionalExistingPatient=patientService.findPatientById(id);
            if(optionalExistingPatient.isPresent()){
                var existsPatient= optionalExistingPatient.get();
                patient.setRole(existsPatient.getRole());
                patientService.updatePatient(id, patient);
                if(authentication!=null && authentication.getAuthorities().stream().anyMatch(role-> role.getAuthority().equals("ROLE_ADMIN"))){
                    return "redirect:/patient/id/"+id;
                }else{
                    redirectAttributes.addFlashAttribute("message","Aktualizacja pacjenta powiodła się");
                    return  "redirect:/patient/id/"+id;
                }

            }else{
                return"/error-page";
            }

        }

    }

    @PostMapping("/patient/save")
    public String insertPatient (@Valid PatientSaveDto patientSaveDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        var patient = PatientSaveDtoToUserMapper.fromUserDtoToPatientEntity(patientSaveDto);
        if(patientService.existByEmail(patientSaveDto.getEmail()) || patientService.existByPesel(patientSaveDto.getPesel()) ){
            redirectAttributes.addFlashAttribute("errors","Podany adres e-mail: "+ patientSaveDto.getEmail()+" już istnieje");
            patientSaveDto.setEmail(null);
            redirectAttributes.addFlashAttribute("patient",patient);
            return "redirect:/patient/add";
        }else if(bindingResult.hasErrors()){
            var errors= bindingResult.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("patient",patient);
            return "redirect:/patient/add";
        }else{
            patient.setRole(Role.PATIENT);
            patientService.savePatient(patient);
            return "redirect:/patients";
        }
    }
    @GetMapping("/patient/remove/{id}")
    public String removePatient(@PathVariable Long id){
        patientService.removePatientById(id);
        return"redirect:/patients";
    }


}
