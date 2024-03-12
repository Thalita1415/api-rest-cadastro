package br.com.cadastro.dto;
import java.util.Date;


public record ProfessionalDTO(

         Long id,
         String name,
         String office,
         Date createdDate,
         Date birth
) {

    public Long getId() {
        return this.id; // Retorna o campo 'name' do registro ContactDTO
    }
}
