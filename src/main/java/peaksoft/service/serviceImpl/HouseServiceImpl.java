package peaksoft.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entity.House;
import peaksoft.repository.HouseRepository;
import peaksoft.service.HouseService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository repository;
    @Override
    public void saveHouse(House house) {
        repository.saveHouse(house);
    }

    @Override
    public House getHouseById(Long id) {
        return repository.getHouseById(id);
    }

    @Override
    public List<House> getAllHouses() {
        return repository.getAllHouses();
    }

    @Override
    public void updateHouse(Long id, House house) {
       repository.updateHouse(id, house);
    }

    @Override
    public void deleteHouseById(Long id) {
       repository.deleteHouseById(id);
    }
}
