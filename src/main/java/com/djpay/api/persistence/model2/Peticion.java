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
    @JoinColumn(name = "authorId")
    private User authorId;

    @Column(name = "contenido")
    private String contenido;

    @Column(name = "estado")
    private String estado;

    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Madrid")
    @Column(name = "hora")
    private LocalDateTime horaPeticion;
}
