package ma.ac.emi.bricool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;





    private String projectName;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double budget;
    private String status;

    private String serviceType;
    private String location;
    private String bookingAvailability;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;



    @ElementCollection
    private List<String> photos;

}