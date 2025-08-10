package in.spring.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.spring.app.model.Job;
import in.spring.app.repository.JobRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JobService {

    private JobRepository jobRepository;

    public List<Job> findAllJobs(){
        return jobRepository.findAll();
    }
    public Job findJobById(Long id){
        return jobRepository.findById(id).orElse(null);
    }
    public Job createJob(Job j){
        return jobRepository.save(j);
    }
    public void deleteJobById(Long id){
        jobRepository.deleteById(id);;
    }
   public Job updateJob(Job j, Long id) {
    return jobRepository.findById(id)
        .map(oldJob -> {
            if (j.getDescription() != null) oldJob.setDescription(j.getDescription());
            if (j.getLocation() != null) oldJob.setLocation(j.getLocation());
            if (j.getMaxSalary() != null) oldJob.setMaxSalary(j.getMaxSalary());
            if (j.getMinSalary() != null) oldJob.setMinSalary(j.getMinSalary());
            if (j.getTitle() != null) oldJob.setTitle(j.getTitle());
            return jobRepository.save(oldJob);
        })
        .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
}

    
}