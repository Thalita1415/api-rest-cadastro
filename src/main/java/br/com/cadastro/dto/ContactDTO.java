package br.com.cadastro.dto;
import java.util.Date;



public record ContactDTO(

        Integer id,
        String name,
        String contact,
        Date createdDate,
        ProfessionalDTO professional


) {

    public String getName() {return this.name;}

    public String getContact() {return this.contact; }

    public Date getCreatedDate() {return this.createdDate;}

    public ProfessionalDTO getProfessional() {return this.professional;}


}
