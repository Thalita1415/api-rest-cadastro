package br.com.cadastro.service;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.model.Contact;

import java.util.List;

public interface ContactService {

    ContactDTO getById(Integer id);

    List<Contact> findAll();
}
