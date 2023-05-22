package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entity.Agency;
import peaksoft.exception.MyException;
import peaksoft.service.AgencyService;

import java.util.List;

@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
public class AgencyApi {

    private final AgencyService agencyService;

    @GetMapping()
    public String getAllAgencies(Model model) {
        model.addAttribute("agencies", agencyService.getAllAgencies());
        return "AgencyPage";
    }

    @GetMapping("/{id}")
    public String getAgencyById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("agencies", agencyService.getAgencyById(id));
        return "agencyInfo";
    }

    @GetMapping("/new")
    public String createAgency(Model model) {
        model.addAttribute("newAgency", new Agency());
        return "newAgency";
    }

    @PostMapping("/save")
    public String saveAgency(@ModelAttribute("newAgency") Agency agency) throws MyException {
        if (agencyService.isAgencyNameExists(agency.getName())) {
//           throw new MyException("An agency with that name already exists");
            return "error1";
        }

        if (agency.getCountry().isEmpty() && agency.getImageAgency().isEmpty()) {
            throw new MyException("Fields should not be null when stored.");
        } else if (agency.getName().isEmpty() && agency.getEmail().isEmpty()) {
            throw new MyException("Fields should not be null when stored.");
        }

        int number = agency.getPhoneNumber();
        if (number == 0) {
            throw new MyException("Fields should not be null when stored.");
        }
        agencyService.saveAgency(agency);
        return "redirect:/agencies";
    }


    @DeleteMapping("/{id}/delete")
    public String deleteAgency(@PathVariable("id") Long id) {
        agencyService.deleteAgencyById(id);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("editAgency", agencyService.getAgencyById(id));
        return "updateAgency";
    }

    @PostMapping("/updateAgency/{id}")
    public String saveUpdate(@ModelAttribute("editAgency") Agency agency,
                             @PathVariable("id") Long id) {
        agencyService.updateAgency(id, agency);
        return "redirect:/agencies";
    }

    @GetMapping("/search")
    public String searchAgency(@RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "country", required = false) String country,
                               Model model) {
        List<Agency> agencies = agencyService.searchAgency(name, email, country);
        model.addAttribute("agencies", agencies);
        return "agencyList";
    }
}