package br.com.cadastro.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact ")
    private String contact;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;


    @ManyToOne
    @JsonInclude(JsonInclude.Include.NON_NULL) // Não incluirá no JSON se for nulo
    @JsonProperty("professional_id") // Personaliza o nome do campo na serialização JSON
    @JoinColumn(name = "professional_id")
    private Professional professional;


}

