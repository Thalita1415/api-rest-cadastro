package br.com.cadastro.dto;

import java.util.Date;
import java.util.List;

public record ProfessionalDTO(

         Long id,
         String name,
         String office,
         Date createdDate,
         Date birth,
         List<ContactDTO> contacts
) {
}
