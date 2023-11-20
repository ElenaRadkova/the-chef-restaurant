package softuni.TheChefRestaurant.TheChefRestaurant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @GetMapping("/all")
    public String allReservation(){
        return "reservation";
    }

}
