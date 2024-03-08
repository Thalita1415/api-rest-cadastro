package br.com.cadastro.impl;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.model.Contact;
import br.com.cadastro.repository.ContactRepository;
import br.com.cadastro.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }




}
