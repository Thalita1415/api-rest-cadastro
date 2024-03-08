package br.com.cadastro.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


public record ContactDTO(

         Long id,
         String name,
         String contact,
         Date createdDate,
         Long professionalId // ID do profissional associado a este contato


) {


}
