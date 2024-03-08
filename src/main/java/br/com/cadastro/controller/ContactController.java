package br.com.cadastro.controller;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.model.Contact;
import br.com.cadastro.service.ContactService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api")
public class ContactController {


    @Autowired
    public ContactService contactService;


    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Integer id) {
        ContactDTO contactDTO = contactService.getById(id);

        if (contactDTO != null) {
            return new ResponseEntity<>(contactDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.findAll();

        if (!contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
