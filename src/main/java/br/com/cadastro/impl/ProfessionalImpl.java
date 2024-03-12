package br.com.cadastro.impl;
import br.com.cadastro.dto.ProfessionalDTO;
import br.com.cadastro.repository.ContactRepository;
import br.com.cadastro.repository.ProfessionalRepository;
import br.com.cadastro.service.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfessionalImpl implements ProfessionalService {


    @Autowired
    private ProfessionalRepository professionalRepository;

    @Override
    public ProfessionalDTO getById(Long id) {
        return null;
    }

    @Override
    public List<ProfessionalDTO> getAll(String query, List<String> fields) {
        return null;
    }

    @Override
    public Integer post(ProfessionalDTO professionalDTO) {
        return null;
    }

    @Override
    public String delete(Integer professionalId) {
        return null;
    }

    @Override
    public void put(Integer contactId, ProfessionalDTO professionalDTO) {

    }
}
