package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.entity.House;
import peaksoft.exception.MyException;
import peaksoft.service.HouseService;

@Controller
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseApi {

    private final HouseService houseService;

    @GetMapping()
    public String getAllHouses(Model model) {
        model.addAttribute("houses", houseService.getAllHouses());
        return "HousePage";
    }

    @GetMapping("/{id}")
    public String getHouseById(@PathVariable ("id")Long id,Model model) {
        model.addAttribute("houses", houseService.getHouseById(id));
        return "houseInfo";
    }

    @GetMapping("/new")
    public String createHouse(Model model) {
        model.addAttribute("newHouse", new House());
        return "newHouse";
    }

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute("newHouse")House house) throws MyException {
        if(house.getCountry() == null){
//            throw new MyException("Fields should not be null when stored.");
        return "error4";
        }
        if(house.getAddress() ==null && house.getDescription() == null){
            throw new MyException("Fields should not be null when stored.");
//            return "error4";
        }
        if(house.getIsBooked() ==null){
            throw new MyException("Fields should not be null when stored.");
//            return "error4";
        }
        double price = house.getPrice();
        int room = house.getRoom();
        if(price == 0){
            throw new MyException("Fields should not be null when stored.");
//            return "error4";
        } if(room == 0){
            throw new MyException("Fields should not be null when stored.");
//
//            return "error4";
        }

        Long agencyId = house.getAgency().getId();
        if(agencyId == 0){
            throw new MyException("Fields should not be null when stored.");
//           return "error4";
        }

        houseService.saveHouse(house);
        return "redirect:/houses";
    }
    @DeleteMapping("/{id}/delete")
    public String deleteHouse(@PathVariable("id") Long id){
        houseService.deleteHouseById(id);
        return "redirect:/houses";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("editHouse", houseService.getHouseById(id));
        return "updateHouse";
    }

    @PostMapping("/updateHouse/{id}")
    public String saveUpdate(@ModelAttribute("editHouse")House house,
                             @PathVariable("id") Long id) {
        houseService.updateHouse(id, house);
        return "redirect:/houses";
    }
}
