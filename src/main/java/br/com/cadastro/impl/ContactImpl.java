package br.com.cadastro.impl;
import br.com.cadastro.dto.ContactDTO;
import br.com.cadastro.dto.ProfessionalDTO;
import br.com.cadastro.model.Contact;
import br.com.cadastro.model.Professional;
import br.com.cadastro.repository.ContactRepository;
import br.com.cadastro.repository.ProfessionalRepository;
import br.com.cadastro.service.ContactService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;


@Component
public class ContactImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;


    @Override
    public List<ContactDTO> getAll(String query, List<String> fields) {
        // Verifica se a lista de campos a serem retornados está vazia
        boolean filterFields = fields != null && !fields.isEmpty();

        // Busca os contatos no repositório com base nos critérios definidos
        List<Contact> contacts;
        if (query != null && !query.isEmpty()) {
            // Se houver um texto de filtro, realiza a busca com o texto
            contacts = contactRepository.findByQuery(query);
        } else {
            // Se não houver texto de filtro, busca todos os contatos
            contacts = contactRepository.findAll();
        }

        // Inicializa a lista de DTOs
        List<ContactDTO> contactDTOs = new ArrayList<>();

        // Converte a lista de contatos para a lista de DTOs
        for (Contact contact : contacts) {
            ContactDTO contactDTO = convertContactToDTO(contact, fields);
            if (contactDTO != null) {
                contactDTOs.add(contactDTO);
            }
        }

        return contactDTOs;
    }

    @Override
    public ContactDTO getById(Long id) {
        // Busca o contato pelo ID no repositório com o relacionamento professional carregado
        Optional<Contact> optionalContact = contactRepository.findByIdWithProfessional((long) Math.toIntExact(id));

        // Verifica se o contato foi encontrado
        if (optionalContact.isPresent()) {
            // Converte o contato encontrado para um DTO e retorna
            Contact contact = optionalContact.get();
            return new ContactDTO(
                    contact.getId(),
                    contact.getName(),
                    contact.getContact(),
                    contact.getCreatedDate(),
                    convertProfessionalToDTOWithoutContacts(contact.getProfessional())
                    // Outros campos do ContactDTO
            );
        } else {
            // Se o contato não for encontrado, lança uma exceção NoSuchElementException
            throw new NoSuchElementException("Contato não encontrado com o ID: " + id);
        }
    }



    @Override
    public Integer post(ContactDTO contactDTO) {
        // Mapeia o DTO para a entidade Contact
        Contact contact = new Contact();
        contact.setName(contactDTO.getName());
        contact.setContact(contactDTO.getContact());
        contact.setCreatedDate(contactDTO.getCreatedDate());

        // Verifica se o objeto Professional foi fornecido
        if (contactDTO.getProfessional() != null) {
            // Obtém o ID do Professional do objeto ProfessionalDTO
            Integer professionalId = Math.toIntExact(contactDTO.getProfessional().getId());
            if (professionalId != null) {
                // Recupera o objeto Professional do banco de dados pelo ID
                Optional<Professional> optionalProfessional = professionalRepository.findById(professionalId);
                if (optionalProfessional.isPresent()) {
                    // Define o Professional no Contact
                    contact.setProfessional(optionalProfessional.get());
                } else {
                    // Se o Professional não for encontrado, lança uma exceção ou realiza alguma outra ação, como logar um aviso
                    throw new RuntimeException("Professional not found with id: " + professionalId);
                }
            } else {
                // Se o ID do Professional não for fornecido, lança uma exceção ou realiza alguma outra ação, como logar um aviso
                throw new IllegalArgumentException("Professional ID is required");
            }
        } else {
            // Se o objeto Professional não for fornecido, lança uma exceção ou realiza alguma outra ação, como logar um aviso
            throw new IllegalArgumentException("Professional is required");
        }

        // Salva o contato no repositório
        Contact savedContact = contactRepository.save(contact);

        // Retorna o ID do contato salvo
        return savedContact.getId();
    }

    @Override
    public void put(Integer contactId, ContactDTO contactDTO) {
        // Verificar se o contato com o ID fornecido existe
        Optional<Contact> optionalContact = contactRepository.findById(contactId);

        if (optionalContact.isPresent()) {
            // Atualizar os dados do contato com base no DTO fornecido
            Contact existingContact = optionalContact.get();
            existingContact.setName(contactDTO.getName());
            existingContact.setContact(contactDTO.getContact());
            existingContact.setCreatedDate(contactDTO.getCreatedDate());

            // Verificar se o ProfessionalDTO está presente no ContactDTO
            if (contactDTO.getProfessional() != null) {
                // Criar uma instância de Professional a partir do ProfessionalDTO
                ProfessionalDTO professionalDTO = contactDTO.getProfessional();
                Professional professional = new Professional();
                professional.setId(professionalDTO.getId());  // Certifique-se de que você tem um método setId() em Professional
                // Configurar outros atributos conforme necessário

                // Atualizar o campo professional_id
                existingContact.setProfessional(professional);
            }

            // Salvar as alterações no repositório
            contactRepository.save(existingContact);
        } else {
            // Se o contato não for encontrado, lançar uma exceção ou realizar outra ação adequada
            throw new EntityNotFoundException("Contato não encontrado com o ID: " + contactId);
        }
    }

    @Override
    public String delete(Integer contactId) {
        // Verifica se o contato com o ID fornecido existe no banco de dados
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (optionalContact.isPresent()) {
            // Se o contato existe, exclui-o do banco de dados
            contactRepository.deleteById(contactId);
            // Retorna uma mensagem de sucesso
            return "Sucesso contato excluído";
        } else {
            // Se o contato não existe, lança uma exceção ou realiza alguma outra ação adequada, como logar um aviso
            throw new EntityNotFoundException("Contato não encontrado com o ID: " + contactId);
        }
    }


    private ContactDTO convertContactToDTO(Contact contact, List<String> fields) {
        if (contact == null) {
            return null;
        }

        // Converte o contato para um DTO com ou sem os campos específicos
        return new ContactDTO(
                contact.getId(),
                filterFields(fields, "name") ? contact.getName() : null,
                filterFields(fields, "contact") ? contact.getContact() : null,
                filterFields(fields, "createdDate") ? contact.getCreatedDate() : null,
                convertProfessionalToDTOWithoutContacts(contact.getProfessional())
                // outros campos do ContactDTO
        );
    }

    private boolean filterFields (List < String > fields, String fieldName){
        // Verifica se a lista de campos está vazia ou contém o campo específico
        return fields == null || fields.isEmpty() || fields.contains(fieldName);

    }


    private ProfessionalDTO convertProfessionalToDTOWithoutContacts(Professional professional) {
        if (professional == null) {
            return null;
        }

        return new ProfessionalDTO(
                professional.getId(),
                professional.getName(),
                professional.getOffice(),
                professional.getCreatedDate(),
                professional.getBirth()
                // Remova a lista de contatos daqui
        );
    }

}







