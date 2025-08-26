package pe.leboulevard.demo.infrastructure.estadocivil.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estado_civil")
@Getter
@Setter
public class EstadoCivilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_civil")
    private Integer idEstadoCivil;

    @Column(name = "descripcion")
    private String descripcion;
}