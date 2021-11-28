package uz.pdp.task1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String corpName;

    @Column(nullable = false)
    private String directorName;

    @OneToOne
    private Address address;

    public Company(String corpName, String directorName, Address address) {
        this.corpName = corpName;
        this.directorName = directorName;
        this.address = address;
    }
}
