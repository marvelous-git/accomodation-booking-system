package academic.uz.ac.zw.controllers;

import academic.uz.ac.zw.configs.SecurityConfig;
import academic.uz.ac.zw.domain.BoardingHouse;
import academic.uz.ac.zw.domain.Student;
import academic.uz.ac.zw.services.BoardingHouseService;
import academic.uz.ac.zw.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class RegisterController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private BoardingHouseService boardingHouseService;


    @GetMapping("/register")
    public String getRegistrationForm(Student student){
        return "register";
    }

    @GetMapping("/profile")
    public String getProfileForm(Student student, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<Student> profile = studentService.findByEmail(currentPrincipalName);
        model.addAttribute("profile", profile);
        return "profile";
    }

    @PostMapping("/profile")
    public String submitProfileForm(Student profile, BindingResult result, Model model, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentPrincipalName = authentication.getName();
            Optional<Student> profileOld = studentService.findByEmail(currentPrincipalName);
            model.addAttribute("profile", profileOld);
            return "/profile";
        }
        Optional<Student> existingStudent = studentService.findByEmail(profile.getEmail());
        existingStudent.get().setFirstName(profile.getFirstName());
        existingStudent.get().setLastName(profile.getLastName());
        existingStudent.get().setPhone(profile.getPhone());
        studentService.save(existingStudent.get());
        redirectAttributes.addFlashAttribute("success", "Profile Successfully Updated" );
        return "redirect:/";
    }

    @GetMapping("/login")
    public String getLoginForm(){
        return "login";
    }

    @PostMapping("/register")
    public String submit(Student student, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "register";
        }
        studentService.save(student);
        redirectAttributes.addFlashAttribute("success", "Account Successfully Created Please login using your email & password" );
        return "redirect:/";
    }
}

