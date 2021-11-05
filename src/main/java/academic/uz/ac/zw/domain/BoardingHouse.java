package academic.uz.ac.zw.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "boarding_house")
public class BoardingHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price_per_room")
    private Double pricePerRoom;

    @Column(name = "available_rooms")
    private int availableRooms;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "boardingHouse", cascade = CascadeType.ALL)
    private Set<Student> student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPricePerRoom() {
        return pricePerRoom;
    }

    public void setPricePerRoom(Double pricePerRoom) {
        this.pricePerRoom = pricePerRoom;
    }

    public int getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(int availableRooms) {
        this.availableRooms = availableRooms;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Student> getStudent() {
        return student;
    }

    public void setStudent(Set<Student> student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "BoardingHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pricePerRoom=" + pricePerRoom +
                ", availableRooms=" + availableRooms +
                ", capacity=" + capacity +
                ", image='" + image + '\'' +
                ", student=" + student +
                '}';
    }
}
