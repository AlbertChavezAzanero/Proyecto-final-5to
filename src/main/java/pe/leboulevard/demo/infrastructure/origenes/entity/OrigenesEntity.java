package pe.leboulevard.demo.infrastructure.origenes.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "origenes")
@Getter
@Setter
public class OrigenesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_origen")
    private Integer idOrigen;

    @Column(name = "descripcion", nullable = false, length = 50)
    private String descripcion;
}