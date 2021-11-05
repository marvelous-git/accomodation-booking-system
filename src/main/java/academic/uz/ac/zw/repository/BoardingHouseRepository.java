package academic.uz.ac.zw.repository;

import academic.uz.ac.zw.domain.BoardingHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardingHouseRepository extends JpaRepository<BoardingHouse, Long>, JpaSpecificationExecutor<BoardingHouse> {

    List<BoardingHouse> findAll();
}
