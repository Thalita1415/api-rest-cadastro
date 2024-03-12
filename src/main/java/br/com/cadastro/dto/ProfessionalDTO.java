package br.com.cadastro.dto;
import br.com.cadastro.model.Office;

import java.util.Date;


public record ProfessionalDTO(

         Integer id,
         String name,
         Office office,
         Date createdDate,
         Date birth,
         Boolean active
) {

    public Integer getId() {return this.id; }

    public String getName() {return this.name;}

    public Office getOffice() {return this.office;}

    public Date getCreatedDate() {return this.createdDate;}

    public Date getBirth() {return this.birth;}



}
