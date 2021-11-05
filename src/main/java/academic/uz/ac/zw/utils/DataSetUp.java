package academic.uz.ac.zw.utils;

import academic.uz.ac.zw.domain.BoardingHouse;
import academic.uz.ac.zw.services.BoardingHouseService;
import academic.uz.ac.zw.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

public class DataSetUp implements CommandLineRunner {
    @Autowired
    private BoardingHouseService boardingHouseService;

    @Autowired
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        List<BoardingHouse> boardingHouseList = boardingHouseService.findAll();
        if(boardingHouseList.isEmpty()){
           boardingHouseList = addBoardingHouse();
            for (BoardingHouse boardingHouse :boardingHouseList) {
                boardingHouseService.save(boardingHouse);
            }
        }

    }

    private List<BoardingHouse> addBoardingHouse() {
        List<BoardingHouse> boardingHouseList = new ArrayList<>();
        BoardingHouse boardingHouseOne = new BoardingHouse();
        boardingHouseOne.setName("Boarding House One");
        boardingHouseOne.setDescription("A spacious 6 bedroomed with a pool and wifi services. Interested students should be comforted sharing a room with another student. There is also a shared kitchen and dining room. This boarding house is located in Mt Plesant, 5Km from UZ campus");
        boardingHouseOne.setAvailableRooms(6);
        boardingHouseOne.setCapacity(6);
        boardingHouseOne.setPricePerRoom(50D);
        boardingHouseOne.setImage("image03.jpg");

        BoardingHouse boardingHouseTwo = new BoardingHouse();
        boardingHouseTwo.setName("Boarding House Two");
        boardingHouseTwo.setDescription("A spacious 8 bedroomed with a pool and wifi services. Interested students should be comforted sharing a room with another student. There is also a shared kitchen and dining room. This boarding house is located in Mt Plesant, 2Km from UZ campus");
        boardingHouseTwo.setAvailableRooms(8);
        boardingHouseTwo.setCapacity(8);
        boardingHouseTwo.setPricePerRoom(65D);
        boardingHouseTwo.setImage("image02.jpg");

        BoardingHouse boardingHouseThree = new BoardingHouse();
        boardingHouseThree.setName("Boarding House Three");
        boardingHouseThree.setDescription("A spacious 4 bedroomed with a pool and wifi services. Interested students should be comforted sharing a room with another student. There is also a shared kitchen and dining room. This boarding house is located in Mt Plesant, 8Km from UZ campus");
        boardingHouseThree.setAvailableRooms(4);
        boardingHouseThree.setCapacity(4);
        boardingHouseThree.setPricePerRoom(40D);
        boardingHouseThree.setImage("image01.jpg");

        boardingHouseList.add(boardingHouseOne);
        boardingHouseList.add(boardingHouseTwo);
        boardingHouseList.add(boardingHouseThree);

        return boardingHouseList;
    }
}
