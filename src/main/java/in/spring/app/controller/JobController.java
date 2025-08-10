package in.spring.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.spring.app.model.Company;
import in.spring.app.model.Job;
import in.spring.app.service.CompanyService;
import in.spring.app.service.JobService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/job")
@AllArgsConstructor
public class JobController {
    private JobService jobService;
    private CompanyService companyService;

   @PostMapping("/company/{id}")
public ResponseEntity<String> createJob(@RequestBody Job job, @PathVariable Long id) {
    Company company = companyService.getCompanyById(id);

    if (company == null) {
        return new ResponseEntity<>("No Company with Id: " + id + " exists in our database",
                HttpStatus.NOT_FOUND);
    }

    // Associate job with company
    job.setCompany(company);
    
    // Save job
    jobService.createJob(job);

    return new ResponseEntity<>(
            "Job titled: " + job.getTitle() + " added successfully to " + company.getName(),
            HttpStatus.CREATED
    );
}

    @GetMapping
    public ResponseEntity<?> getAllJobs(){
        return new ResponseEntity<>(jobService.findAllJobs(),HttpStatus.FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id){
        return new ResponseEntity<>(jobService.findJobById(id),HttpStatus.FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJobById(@RequestBody Job j, @PathVariable Long id){
        return new ResponseEntity<>(jobService.updateJob(j,id),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobById(@PathVariable Long id){
        jobService.deleteJobById(id);
        return new ResponseEntity<>("Deleted Successfully",HttpStatus.NOT_FOUND);
    }
}
