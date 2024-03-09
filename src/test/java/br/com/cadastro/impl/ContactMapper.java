package br.com.cadastro.impl;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.model.Contact;


import java.util.List;

public interface ContactMapper {

    ContactDTO toModel(Contact entity);

    Contact toEntity(ContactDTO model);

    List<ContactDTO> listToModel(List<Contact> document);


    List<Contact> listToEntity(List<ContactDTO> model);
}
