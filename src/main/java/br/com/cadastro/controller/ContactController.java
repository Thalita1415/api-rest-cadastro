package br.com.cadastro.controller;
import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.repository.ContactRepository;
import br.com.cadastro.service.ContactService;
import io.swagger.annotations.ApiOperation;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Log4j2
@RestController
@RequestMapping("/contacts")
public class ContactController {


    @Autowired
    public ContactService contactService;
    @Autowired
    public ContactRepository contactRepository;



    @ApiOperation("Obtém a lista de contatos")
    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) List<String> fields) {

        List<ContactDTO> contacts = contactService.getAll(q, fields);

        if (contacts != null && !contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Obtém a lista de contatos por Id")
    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Integer id) throws ChangeSetPersister.NotFoundException {
        ContactDTO contactDTO = contactService.getById(Long.valueOf(id));

        if (contactDTO != null) {
            return new ResponseEntity<>(contactDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Insere uma lista de contatos")
    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody ContactDTO contactDTO) {
        Integer newContactId = contactService.post(contactDTO); // Assumindo que o método save retorna o ID do novo contato


        String mensagem = "Sucesso! Contato com id " + newContactId + " cadastrado.";

        // Retornar uma resposta com status HTTP 200 OK e a mensagem
        return ResponseEntity.status(HttpStatus.OK).body(mensagem);
    }

    @ApiOperation("Atualiza um contato por Id")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateContact(@PathVariable Integer id, @RequestBody ContactDTO contactDTO) {
        try {
            contactService.put(id, contactDTO);
            return ResponseEntity.ok("Sucesso cadastrado alterado");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar contato: " + e.getMessage());
        }
    }

    @ApiOperation("Realiza deleção de um contatos")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable Integer id) {
        try {
            contactService.delete(id);
            return ResponseEntity.ok("Sucesso contato excluído");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contato não encontrado com o ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir contato: " + e.getMessage());
        }
    }


}

