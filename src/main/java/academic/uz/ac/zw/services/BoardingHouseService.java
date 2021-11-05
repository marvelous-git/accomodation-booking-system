package academic.uz.ac.zw.services;

import academic.uz.ac.zw.domain.BoardingHouse;
import academic.uz.ac.zw.repository.BoardingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardingHouseService {

    @Autowired
    private BoardingHouseRepository boardingHouseRepository;

    public List<BoardingHouse> findAll(){
        return boardingHouseRepository.findAll();
    }

    public Optional<BoardingHouse> findById(Long id) {
        return boardingHouseRepository.findById(id);
    }

    public BoardingHouse save(BoardingHouse boardingHouse) {
        return boardingHouseRepository.save(boardingHouse);
    }
}
