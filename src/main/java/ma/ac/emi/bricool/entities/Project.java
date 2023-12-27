package ma.ac.emi.bricool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor

public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = ("projectId"))
    private List<Photo> photo;



    public Project() {

    }

}