package softuni.TheChefRestaurant.TheChefRestaurant.web;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.TheChefRestaurant.TheChefRestaurant.model.binding.AddReservationBindingModel;
import softuni.TheChefRestaurant.TheChefRestaurant.model.service.ReservationServiceModel;
import softuni.TheChefRestaurant.TheChefRestaurant.service.ReservationService;
import softuni.TheChefRestaurant.TheChefRestaurant.util.LoggedUser;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public ReservationController(ReservationService reservationService, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.reservationService = reservationService;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(){

        return "add-reservation";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddReservationBindingModel addReservationBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
          redirectAttributes.addFlashAttribute("addReservationBindingModel", addReservationBindingModel);
          redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addReservationBindingModel", bindingResult);
          return "redirect:add";
        }

//       за да запишем нашия модел в базата трябва да го закачим за сървис
       reservationService.addReservation(modelMapper.map(addReservationBindingModel, ReservationServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public AddReservationBindingModel addReservationBindingModel(){
        return new AddReservationBindingModel();
    }


}
