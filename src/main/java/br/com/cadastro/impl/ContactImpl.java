package br.com.cadastro.impl;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.model.Contact;
import br.com.cadastro.repository.ContactRepository;
import br.com.cadastro.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Override
    public ContactDTO getById(Integer id) {
        Contact contact = contactRepository.findById(id).orElse(null);

        return null;
    }

    @Override
    public List<ContactDTO> findAll() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ContactDTO convertToDTO(Contact contact) {
        return new ContactDTO(
                contact.getId(),
                contact.getName(),
                contact.getContact(),
                contact.getCreatedDate(),
                contact.getProfessional()
                // Você precisa fornecer um ProfessionalDTO aqui, dependendo da sua lógica de negócios
        );
    }
    }


//    @Override
//    public List<ContactDTO> findAll() {
//        return contactRepository.findAll();
//    }



