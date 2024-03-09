package br.com.cadastro.controller;

import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.model.Contact;
import br.com.cadastro.repository.ContactRepository;
import br.com.cadastro.service.ContactService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Log4j2
@RestController
@RequestMapping("/api")
public class ContactController {


    @Autowired
    public ContactService contactService;

    @Autowired
    public ContactRepository contactRepository;


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
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
        }
    }

//    @PostMapping("/cadastro")
////    public ResponseEntity<Contact> saveContact(@RequestBody @Validated ContactDTO contactDTO){
////
////        var contact = new Contact();
////        BeanUtils.copyProperties(contactDTO, contact);
////        return ResponseEntity.status(HttpStatus.CREATED).body(contactRepository.save(contact));
////    }


