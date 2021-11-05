package academic.uz.ac.zw.controllers;

import academic.uz.ac.zw.domain.BoardingHouse;
import academic.uz.ac.zw.domain.Student;
import academic.uz.ac.zw.services.BoardingHouseService;
import academic.uz.ac.zw.services.StudentService;
import academic.uz.ac.zw.utils.ApplicationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping
public class BoardingHouseController {

    @Autowired
    private BoardingHouseService boardingHouseService;

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String index(Model model, @ModelAttribute("success") final String success, @ModelAttribute("error") final String error, BindingResult result){
        List<BoardingHouse> boardingHouseList =  boardingHouseService.findAll();
        model.addAttribute("boardingHouseList", boardingHouseList);
        model.addAttribute("success", success);
        model.addAttribute("error", error);
        return "index";
    }

    @GetMapping("/apply/{id}")
    public String showApplicationForm(@PathVariable("id") long id, Model model, ApplicationRequest applicationRequest) {
        Optional<BoardingHouse> boardingHouse=  boardingHouseService.findById(id);
        model.addAttribute("boardingHouse", boardingHouse.get());
        return "apply";
    }

    @PostMapping("/apply")
    public String apply(Model model, ApplicationRequest applicationRequest, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<BoardingHouse> boardingHouse=  boardingHouseService.findById(Long.parseLong(applicationRequest.getId()));
        Optional<Student> student= studentService.findByEmail(applicationRequest.getEmail());
        if(!student.isPresent()){
            redirectAttributes.addFlashAttribute("error", "Invalid Email Entered, Register and Try again!!!" );
            return "redirect:/";
        }
        if(!applicationRequest.getEmail().equals(currentPrincipalName)){
            redirectAttributes.addFlashAttribute("error", "You Entered an Email which does not belongs to you!!!" );
            return "redirect:/";
        }
        int availableRooms = boardingHouse.get().getAvailableRooms();
        Set<Student> students = boardingHouse.get().getStudent();
        if(availableRooms == 0){
            redirectAttributes.addFlashAttribute("success", "No more rooms to book" );
            return "redirect:/";
        }
        boardingHouse.get().setAvailableRooms(availableRooms-1);
        student.get().setBoardingHouse(boardingHouse.get());
        students.add(student.get());
        boardingHouse.get().setStudent(students);
        BoardingHouse boardingHouse1 = boardingHouseService.save(boardingHouse.get());
        redirectAttributes.addFlashAttribute("success", "Application Success" );
        return "redirect:/";
    }
}
