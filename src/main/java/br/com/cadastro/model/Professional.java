package br.com.cadastro.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @Enumerated(EnumType.STRING)
    @Column(name ="office " ,  length = 20) // Define o tamanho máximo do campo cargo
    private Office office;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;


    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(name = "active")
    private boolean active = true; // Inicializado como ativo por padrão

    // Getter e Setter para ativo

    // Método para desativar (exclusão lógica)
    public void inactive() {
        this.active = false;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "professional", fetch = FetchType.LAZY)
    private List<Contact> contacts;
}
