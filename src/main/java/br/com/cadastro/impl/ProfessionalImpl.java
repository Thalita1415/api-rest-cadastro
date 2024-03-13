package br.com.cadastro.impl;
import br.com.cadastro.dto.ProfessionalDTO;
import br.com.cadastro.model.Professional;
import br.com.cadastro.repository.ProfessionalRepository;
import br.com.cadastro.service.ProfessionalService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ProfessionalImpl implements ProfessionalService {


    @Autowired
    private ProfessionalRepository professionalRepository;

    @Override
    public List<ProfessionalDTO> getAll(String query, List<String> fields) {
        // Verifica se a lista de campos a serem retornados está vazia
        boolean filterFields = fields != null && !fields.isEmpty();

        // Busca os contatos no repositório com base nos critérios definidos
        List<Professional> professionals;
        if (query != null && !query.isEmpty()) {
            // Se houver um texto de filtro, realiza a busca com o texto
            professionals = professionalRepository.findProfessionalsByQuery(query);
        } else {
            // Se não houver texto de filtro, busca todos os contatos
            professionals = professionalRepository.findAll();
        }

        // Inicializa a lista de DTOs
        List<ProfessionalDTO> professionalDTOs = new ArrayList<>();

        // Converte a lista de contatos para a lista de DTOs
        for (Professional professional : professionals) {
            ProfessionalDTO professionalDTO = convertProfessionalToDTO(professional, fields);
            if (professionalDTO != null) {
                professionalDTOs.add(professionalDTO);
            }
        }

        return professionalDTOs;
    }


    @Override
    public ProfessionalDTO getById(Long id) {
        // Busca o profissional pelo ID no repositório com os contatos carregados
        Optional<Professional> optionalProfessional = professionalRepository.findByIdWithContacts(id);

        // Verifica se o profissional foi encontrado
        if (optionalProfessional.isPresent()) {
            // Converte o profissional encontrado para um DTO e retorna
            Professional professional = optionalProfessional.get();
            return convertProfessionalToDTO(professional);
        } else {
            // Se o profissional não for encontrado, lança uma exceção NoSuchElementException
            throw new NoSuchElementException("Profissional não encontrado com o ID: " + id);
        }
    }

    private ProfessionalDTO convertProfessionalToDTO(Professional professional) {
        if (professional == null) {
            return null;
        }

        // Converte o profissional para um DTO
        return new ProfessionalDTO(
                Math.toIntExact(professional.getId()),
                professional.getName(),
                professional.getOffice(),
                professional.getCreatedDate(),
                professional.getBirth(),
                professional.isActive()

        );
    }

    @Override
    public Integer post(ProfessionalDTO professionalDTO) {
        Professional professional = new Professional();
        professional.setName(professionalDTO.getName());
        professional.setOffice(professionalDTO.getOffice());
        professional.setCreatedDate(professionalDTO.getCreatedDate());
        professional.setBirth(professionalDTO.getBirth());

        Professional savedProfessional = professionalRepository.save(professional);

        return Math.toIntExact(savedProfessional.getId());
    }

    @Override
    public void put(Integer professionalId, ProfessionalDTO professionalDTO) {
        // Verifica se o profissional com o ID fornecido existe
        Optional<Professional> optionalProfessional = professionalRepository.findById(professionalId);

        if (optionalProfessional.isPresent()) {
            // Atualiza os dados do profissional com base no DTO fornecido
            Professional existingProfessional = optionalProfessional.get();
            existingProfessional.setName(professionalDTO.getName());
            existingProfessional.setOffice(professionalDTO.getOffice());
            existingProfessional.setCreatedDate(professionalDTO.getCreatedDate());
            existingProfessional.setBirth(professionalDTO.getBirth());
            existingProfessional.setActive(professionalDTO.isActive());

            // Salva as alterações no repositório
            professionalRepository.save(existingProfessional);
        } else {
            // Se o profissional não for encontrado, lançar uma exceção ou realizar outra ação adequada
            throw new EntityNotFoundException("Profissional não encontrado com o ID: " + professionalId);
        }

    }

    @Override
    public void delete(Integer professionalId) {
        // Verificar se o contato com o ID fornecido existe
        Optional<Professional> optionalProfessional = professionalRepository.findById(professionalId);

        if (optionalProfessional.isPresent()) {
            // Realizar a exclusão lógica (por exemplo, definir um status de ativo/inativo)
            Professional existingProfessional = optionalProfessional.get();
            existingProfessional.setActive(false); // Supondo que você tenha um campo "ativo" na entidade Contact

            // Salvar as alterações no repositório
            professionalRepository.save(existingProfessional);
        } else {
            // Se o contato não for encontrado, lançar uma exceção ou realizar outra ação adequada
            throw new EntityNotFoundException("Contato não encontrado com o ID: " + professionalId);
        }

    }

    private ProfessionalDTO convertProfessionalToDTO(Professional professional, List<String> fields) {
        if (professional == null) {
            return null;
        }

        // Converte o profissional para um DTO com ou sem os campos específicos
        return new ProfessionalDTO(
                Math.toIntExact(professional.getId()),
                filterFields(fields, "name") ? professional.getName() : null,
                filterFields(fields, "office") ? professional.getOffice() : null,
                filterFields(fields, "createdDate") ? professional.getCreatedDate() : null,
                filterFields(fields, "birth") ? professional.getBirth() : null,
                filterFields(fields, "active") ? professional.isActive() : null
                // outros campos do ProfessionalDTO
        );
    }

    private boolean filterFields (List < String > fields, String fieldName){
        // Verifica se a lista de campos está vazia ou contém o campo específico
        return fields == null || fields.isEmpty() || fields.contains(fieldName);

    }

}




