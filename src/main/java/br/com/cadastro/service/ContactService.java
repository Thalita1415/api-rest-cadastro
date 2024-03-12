package br.com.cadastro.service;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.dto.ProfessionalDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ContactService {

      ContactDTO getById(Long id);
    List<ContactDTO> getAll(String query, List<String> fields);
    Integer post(ContactDTO contactDTO);

    String delete(Integer contactId);
    void put(Integer contactId, ContactDTO contactDTO);


}
