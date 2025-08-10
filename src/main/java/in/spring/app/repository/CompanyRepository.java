package in.spring.app.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.spring.app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{

}
