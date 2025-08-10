package in.spring.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.spring.app.model.Company;
import in.spring.app.model.Review;
import in.spring.app.repository.ReviewRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyService companyService;
    
    public List<Review> getAllReviews(Long id){
        return reviewRepository.findByCompanyId(id).orElse(null);
    }
    public boolean saveReview(Long companyId,Review r){
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            r.setCompany(company);
            reviewRepository.save(r);
            return true;
        }
        return false;

        
    }
    public Review getReviewById(Long cmpId,Long revId){
        Optional<List<Review>> cmpReviews = reviewRepository.findByCompanyId(cmpId);
        return cmpReviews.get().stream()
                        .filter(x->x.getId().equals(revId))
                        .findFirst()
                        .orElse(null);
    }
   
    public void deleteReviewById(Long id){
        reviewRepository.deleteById(id);
    }
   
    public boolean updateReview(Review r, Long companyId,Long revId){
        Company company = companyService.getCompanyById(companyId);
        if (company!=null) {
            r.setCompany(company);
            r.setId(revId);
            reviewRepository.save(r);
            return true;
        }
        return false;
        
    }

    public boolean deleteReview(Long companyId, Long revId) {
    Company company = companyService.getCompanyById(companyId);
    if (company == null) {
        return false; 
    }

    Optional<Review> reviewOpt = company.getReviews()
        .stream()
        .filter(r -> r.getId().equals(revId))
        .findFirst();

    if (reviewOpt.isPresent()) {
        reviewRepository.delete(reviewOpt.get());
        return true;
    }

    return false; 
}

    
}
