package in.spring.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.spring.app.model.Company;
import in.spring.app.service.CompanyService;
import lombok.AllArgsConstructor;

@RequestMapping("/company")
@RestController
@AllArgsConstructor
public class CompanyController {
  private CompanyService companyService;
  
  @GetMapping
  public List<Company> getAll(){
    return companyService.getAllCompanies();
  }
  @PostMapping
  public ResponseEntity<?> addCompany(@RequestBody Company c){
    return new ResponseEntity<>(companyService.addCompany(c),HttpStatus.CREATED);
  }
  @GetMapping("/{id}")
  public ResponseEntity<?> getById(@PathVariable Long id){
    return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.FOUND);
  }
  @PutMapping("/{id}")
  public ResponseEntity<?> updateCompanyById(@RequestBody Company c, @PathVariable Long id){
    return new ResponseEntity<>(companyService.updateCompanyById(c,id),HttpStatus.ACCEPTED);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable Long id){
    return new ResponseEntity<>(companyService.deleteCompanyById(id),HttpStatus.NO_CONTENT);
  }
  
}
