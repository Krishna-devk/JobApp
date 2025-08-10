package in.spring.app.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    private String name;
    private String description;
    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    private List<Job> jobs=new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "company")
    private List<Review> reviews=new ArrayList<>();
}
