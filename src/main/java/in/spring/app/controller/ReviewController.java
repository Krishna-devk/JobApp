package in.spring.app.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import in.spring.app.model.Review;
import in.spring.app.service.ReviewService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/company/{companyId}")
@AllArgsConstructor
public class ReviewController {
    private ReviewService reviewService;
    @GetMapping("/reviews")
    public ResponseEntity<?> getAllEntries(@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId),HttpStatus.FOUND);
    }
    @PostMapping("/reviews")
    public ResponseEntity<?> addReview(@RequestBody Review r, @PathVariable Long companyId){
        if(reviewService.saveReview(companyId, r))
            return new ResponseEntity<>("Added review Successfully",HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("No company with id:"+companyId+" exist",HttpStatus.NOT_FOUND);

    }
    @GetMapping("/review/{revId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long revId,@PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.getReviewById(companyId,revId),HttpStatus.FOUND);
    }
    @PutMapping("/review/{revId}")
    public ResponseEntity<?> updateReview(@PathVariable Long companyId,@PathVariable Long revId, @RequestBody Review r){
        boolean updateReview = reviewService.updateReview(r, companyId, revId);
        if(updateReview){
        return new ResponseEntity<>(updateReview,HttpStatus.ACCEPTED);
        } else{
            return new ResponseEntity<>(updateReview,HttpStatus.BAD_REQUEST);

        }
    }
    @DeleteMapping("/review/{revId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long companyId,@PathVariable Long revId){
        return new ResponseEntity<>(reviewService.deleteReview(companyId, revId),HttpStatus.NO_CONTENT);
    }


}
