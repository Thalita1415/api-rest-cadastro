package br.com.cadastro.model;



import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact ")
    private String contact;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;


    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Professional professional;


}
