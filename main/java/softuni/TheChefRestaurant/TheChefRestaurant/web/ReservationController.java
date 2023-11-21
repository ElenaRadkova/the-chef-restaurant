package softuni.TheChefRestaurant.TheChefRestaurant.web;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.TheChefRestaurant.TheChefRestaurant.model.binding.AddReservationBindingModel;
import softuni.TheChefRestaurant.TheChefRestaurant.service.ReservationService;
import softuni.TheChefRestaurant.TheChefRestaurant.util.LoggedUser;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final LoggedUser loggedUser;

    public ReservationController(ReservationService reservationService, LoggedUser loggedUser) {
        this.reservationService = reservationService;
        this.loggedUser = loggedUser;
    }

//    @GetMapping("/your")
//    public String yourReservation(){
//
//        return "reservation";
//    }

    @GetMapping("/add")
    public String addReservation(){
        if(loggedUser.getId() == null) {
            return "redirect:/users/registration";
        }
        return "add-reservation";
    }
    @PostMapping("/add")
    public String addConfirm(@Valid AddReservationBindingModel addReservationBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){

        }
        return "redirect:/reservation";
    }

    @ModelAttribute
    public AddReservationBindingModel addReservationBindingModel(){
        return new AddReservationBindingModel();
    }


}
