package br.com.cadastro.controller;
import br.com.cadastro.dto.ProfessionalDTO;
import br.com.cadastro.repository.ProfessionalRepository;
import br.com.cadastro.service.ProfessionalService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    @Autowired
    public ProfessionalService professionalService;
    @Autowired
    public ProfessionalRepository professionalRepository;

    @GetMapping
    public ResponseEntity<List<ProfessionalDTO>> getProfessionals(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) List<String> fields
    ) {
        try {
            List<ProfessionalDTO> professionals = professionalService.getAll(q, fields);
            return new ResponseEntity<>(professionals, HttpStatus.OK);
        } catch (Exception e) {
            // Em caso de erro, retornar um status 500 (Internal Server Error)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> getProfessionalById(@PathVariable Long id) {
        try {
            ProfessionalDTO professionalDTO = professionalService.getById(id);
            return new ResponseEntity<>(professionalDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createProfessional(@RequestBody ProfessionalDTO professionalDTO) {
        // Chama o serviço para processar a inserção
        Long generatedId = Long.valueOf(professionalService.post(professionalDTO));

        // Retorna uma resposta de sucesso com o ID gerado
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Sucesso profissional com id " + generatedId + " cadastrado");
    }

    @PutMapping("/{professionalId}")
    public ResponseEntity<String> updateProfessional(@PathVariable Integer professionalId, @RequestBody ProfessionalDTO professionalDTO) {
        professionalService.put(professionalId, professionalDTO);
        return new ResponseEntity<>("Sucesso profissional com id " + professionalId + " atualizado", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProfessional(@PathVariable Integer id) {
        try {
            professionalService.delete(id);
            return new ResponseEntity<>("Sucesso profissional com id " + id + " excluído", HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            // Se o profissional não for encontrado, retornar um status 404 (Not Found)
            return new ResponseEntity<>("Profissional não encontrado com o ID: " + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Em caso de outras exceções, retornar um status 500 (Internal Server Error)
            return new ResponseEntity<>("Erro ao processar a exclusão do profissional", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
