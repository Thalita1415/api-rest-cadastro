package br.com.cadastro.dto;
import java.util.Date;


public record ProfessionalDTO(

         Integer id,
         String name,
         String office,
         Date createdDate,
         Date birth,
         Boolean active
) {

    public Integer getId() {return this.id; }

    public String getName() {return this.name;}

    public String getOffice() {return this.office;}

    public Date getCreatedDate() {return this.createdDate;}

    public Date getBirth() {return this.birth;}



}
