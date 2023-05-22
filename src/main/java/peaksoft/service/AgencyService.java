package peaksoft.service;

import peaksoft.entity.Agency;

import java.util.List;

public interface AgencyService {
    void saveAgency(Agency agency);
   boolean isAgencyNameExists(String name);
    Agency getAgencyById(Long id);
    List<Agency> getAllAgencies();
    void updateAgency(Long id, Agency agency);
    void deleteAgencyById(Long id);
    List<Agency> searchAgency(String name,String email,String country );
}
