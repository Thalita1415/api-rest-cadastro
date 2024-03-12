package br.com.cadastro.service;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.dto.ProfessionalDTO;

import java.util.List;

public interface ProfessionalService {

    ProfessionalDTO getById(Long id);
    List<ProfessionalDTO> getAll(String query, List<String> fields);
    Integer post(ProfessionalDTO professionalDTO);
    String delete(Integer professionalId);
    void put(Integer contactId, ProfessionalDTO professionalDTO);
}
