package in.spring.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.spring.app.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>{
    Optional<List<Review>> findByCompanyId(Long id);
}
