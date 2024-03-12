package br.com.cadastro.service;
import br.com.cadastro.dto.ProfessionalDTO;
import java.util.List;

public interface ProfessionalService {

    List<ProfessionalDTO> getAll(String query, List<String> fields);
    ProfessionalDTO getById(Long id);
    Integer post(ProfessionalDTO professionalDTO);

    void put(Integer contactId, ProfessionalDTO professionalDTO);
    void delete(Integer professionalId);

}
