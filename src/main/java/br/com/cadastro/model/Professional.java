package br.com.cadastro.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Professional")
public class Professional implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;


    @Column(name ="office " ,  length = 20) // Define o tamanho máximo do campo cargo
    private String office;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;


    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;

    @OneToMany(mappedBy = "professional")
    private List<Contact> contacts;
}
