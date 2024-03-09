package br.com.cadastro.dto;
import java.util.Date;


public record ContactDTO(

        Integer id,
        String name,
        String contact,
        Date createdDate,
        br.com.cadastro.model.Professional professional  // ID do profissional associado a este contato


) {



}
