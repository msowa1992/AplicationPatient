package com.example.demo.cotroller;



import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.exceptions.UserCanNotBeNullException;
import com.example.demo.services.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChangePasswordController {
    final private UserService userService;

    @GetMapping("/changePassword/{id}")
    public String changePassword(@PathVariable Long id, Model model)
    {
        model.addAttribute("title", "Zmień hasło użytkownika");
        return"/password/change-password";
    }

    @PostMapping("/change-password")
    public String changeUserPassword(@RequestParam("password") String newPassword, @RequestParam("newPassword") String confirmPassword, RedirectAttributes redirectAttributes) throws UserCanNotBeNullException {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        var optionalUser = userService.findUserByEmail(email);

        if (optionalUser.isPresent()) {
            var user = optionalUser.get();

            if ((userService.arePasswordTheSame(newPassword,confirmPassword) )&& (userService.isNewPasswordCorrect(newPassword))) {
                userService.changePassword(email,newPassword);
                redirectAttributes.addFlashAttribute("message", "Hasło zostało zmienione prawidłowo.");
                return "redirect:/";
            } else {
                redirectAttributes.addFlashAttribute("message", "Hasło nie pasuje, bądź jest niepoprawne.");
                return "redirect:/changePassword/" + user.getId();
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Błędny token");
            return "/error-page";
        }


    }

}
