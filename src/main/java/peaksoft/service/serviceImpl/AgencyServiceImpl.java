package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.Agency;
import peaksoft.repository.AgencyRepository;
import peaksoft.service.AgencyService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;

    @Override
    public void saveAgency(Agency agency) {
        agencyRepository.saveAgency(agency);
    }

    @Override
    public boolean isAgencyNameExists(String name) {
        return agencyRepository.isAgencyNameExists(name);
    }

    @Override
    public Agency getAgencyById(Long id) {
        return agencyRepository.getAgencyById(id);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.getAllAgencies();
    }

    @Override
    public void updateAgency(Long id, Agency agency) {
        agencyRepository.updateAgency(id, agency);
    }

    @Override
    public void deleteAgencyById(Long id) {
        agencyRepository.deleteAgencyById(id);
    }

    @Override
    public List<Agency> searchAgency(String name, String email, String country) {
        return agencyRepository.searchAgency(name, email, country);
    }

}
