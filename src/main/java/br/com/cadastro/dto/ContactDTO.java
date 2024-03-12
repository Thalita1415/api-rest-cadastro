package br.com.cadastro.dto;
import java.util.Date;

public record ContactDTO(

        Integer id,
        String name,
        String contact,
        Date createdDate,
        ProfessionalDTO professional


) {


    public String getName() {
        return this.name; // Retorna o campo 'name' do registro ContactDTO
    }

    public String getContact() {
            return this.contact; // Retorna o campo 'name' do registro ContactDTO
    }

    public Date getCreatedDate() {
        return this.createdDate; // Retorna o campo 'name' do registro ContactDTO
    }

    public ProfessionalDTO getProfessional() {
        return this.professional; // Retorna o campo 'name' do registro ContactDTO
    }


}
