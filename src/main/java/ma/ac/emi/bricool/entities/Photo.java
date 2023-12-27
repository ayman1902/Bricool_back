package ma.ac.emi.bricool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long photoId;
    private String placeholder;

    private String url;

    @ManyToOne
    @JoinColumn(name = "seller_id", referencedColumnName = "sellerId")
    private Seller seller;
}
