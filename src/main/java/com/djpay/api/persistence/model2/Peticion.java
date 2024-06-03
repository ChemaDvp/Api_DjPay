package com.djpay.api.persistence.model2;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "djpay_peticiones")
public class Peticion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idPeticion;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "estado")
    private Boolean estado;

}
