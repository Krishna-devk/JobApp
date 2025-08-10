package in.spring.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.spring.app.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import in.spring.app.model.Company;

@Service
@AllArgsConstructor
public class CompanyService {
    private CompanyRepository companyRepository;
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }
    public Company addCompany(Company c){
        return companyRepository.save(c);
    }
    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }
    public Company updateCompanyById(Company c, Long id){
        return companyRepository.findById(id)
        .map(company -> {
            if (c.getName() != null) company.setName(c.getName());
            if(c.getDescription()!=null) company.setName(c.getName());
            if(c.getJobs() != null) company.setJobs(c.getJobs());
            return companyRepository.save(company);
        })
        .orElseThrow(() -> new RuntimeException("Company not found with id: " + id));
    }
    public boolean deleteCompanyById(Long id){
        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
