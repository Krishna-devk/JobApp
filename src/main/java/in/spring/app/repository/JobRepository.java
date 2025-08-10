package in.spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.spring.app.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>{
    
}
