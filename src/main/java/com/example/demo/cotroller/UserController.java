package com.example.demo.cotroller;

import java.util.Arrays;

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

import com.example.demo.config.AuthPasswordConfig;
import com.example.demo.exceptions.UserCanNotBeNullException;
import com.example.demo.mappers.UserSaveDtoToUserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.dto.UserSaveDto;
import com.example.demo.services.impl.UserServiceImp;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
class UserController {
    final UserServiceImp userService;
    final AuthPasswordConfig authPasswordConfig;

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "/users/users";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user/id/{id}")
    public String showCurrentUser(Model model, @PathVariable Long id) {
        if (userService.findUserById(id).isPresent()) {
            model.addAttribute("user", userService.findUserById(id).get());
            return "/users/user";
        }else{
            model.addAttribute("error", "Edycja użytkownika o id="+id+" nie jest możliwa");
            model.addAttribute("errorAction", "/users");
     model.addAttribute("return", "Wróć do listy użytkowników");
                 return "/error-page";
        }


    }
    
    @GetMapping("/user/add")
    public String registerUser() {
        return "/users/save-user";
    }

    // edycja:
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        if (userService.findUserById(id).isPresent()) {
            model.addAttribute("user", userService.findUserById(id).get());
            model.addAttribute("userRole", userService.findUserById(id).get().getRole().toString());
            return "/users/save-user";
        }else{
            model.addAttribute("error", "Użytkownik o id="+id+" nie istnieje");
     model.addAttribute("errorAction", "/users");
     model.addAttribute("return", "Wróć do listy użytkowników");
                 return "/error-page";
        }
        
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user/update/{id}")
    public String userUpdate(@PathVariable Long id, @Valid UserSaveDto userSaveDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Authentication authentication){
        if(bindingResult.hasErrors()){
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:/user/edit/" + id;
        } else {
            var user = UserSaveDtoToUserMapper.fromUserDtoToUserEntity(userSaveDto);
            var optionalExistingUser = userService.findUserById(id);

            if(optionalExistingUser.isPresent()){
                var existingUser = optionalExistingUser.get();
                user.setRole(existingUser.getRole());
                userService.updateUser(id, user);

                redirectAttributes.addFlashAttribute("message", "Aktualizacja użytkownika powiodła się");
                return "redirect:/user/id/" + id;

            } else {
                redirectAttributes.addFlashAttribute("error", "Użytkownik nie został znaleziony");
                return "redirect:/error-page";
            }
        }
    }


    @PostMapping("/user/save")
    public String insertUser( @Valid UserSaveDto userSaveDto,BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        var user = UserSaveDtoToUserMapper.fromUserDtoToUserEntity(userSaveDto);
        user.setPassword(authPasswordConfig.passwordEncoder().encode(user.getPassword()));
        if(userService.existByEmail(userSaveDto.getEmail())){
            redirectAttributes.addFlashAttribute("errors","Podany adres e-mail: "+ userSaveDto.getEmail()+" już istnieje");
            user.setEmail(null);
            redirectAttributes.addFlashAttribute("user",user);
            return "redirect:/user/add";
        }else if(bindingResult.hasErrors()){
            var errors= bindingResult.getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            redirectAttributes.addFlashAttribute("errors", errors);
            redirectAttributes.addFlashAttribute("user",user);
            return "redirect:/user/add";
        }else{
            userService.saveUser(user);
            return "redirect:/";
        }
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user/remove/{id}")
    public String removeUser(@PathVariable Long id){
        userService.removeUserById(id);
        return"redirect:/users";
    }
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user/role/{userId}")
    public String showChangeRoleForm(@PathVariable Long userId,Model model ){
        var user = userService.findUserById(userId).orElse(null);
        if(user==null){
            model.addAttribute("error", "Użytkownik o id: "+userId+" nie istnieje");
            model.addAttribute("errorAction", "/users");
            model.addAttribute("return", "Wróć do listy użytkowników");
            return "/error-page";
        }
        model.addAttribute("user", user);
        model.addAttribute("roles", Arrays.asList(Role.values()));
        return "/users/change-role";
    }

    @PostMapping("/user/role/{userId}")
    public  String changeUserRole(@PathVariable Long userId,@RequestParam String newRole,Model model ){
        Role role = Role.valueOf(newRole);
        try {
            userService.changeUserRole(userId, role);
        } catch (UserCanNotBeNullException e) {
            model.addAttribute("error", "Zmiana roli dla użytkownika o id: "+userId+" nie jest możliwa");
            model.addAttribute("errorAction", "/users");
            model.addAttribute("return", "Wróć do listy użytkowników");
            return "/error-page";

        }
        return "redirect:/user/id/"+ userId;
    }
    @GetMapping("/myAccount")
    public String showFormToEditLoggedUser(Model model){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();

        String userEmail= userDetails.getUsername();

        var optionalUser=userService.findUserByEmail(userEmail);
        if(optionalUser.isPresent()){
            var user= optionalUser.get();
            model.addAttribute("user", user);
            model.addAttribute("userRole", user.getRole().toString());
            return "/users/save-user";
        }else{
            return"/error-page";
        }
    }



}
