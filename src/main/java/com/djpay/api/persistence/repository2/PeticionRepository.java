package com.djpay.api.persistence.repository2;

import com.djpay.api.persistence.model2.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeticionRepository extends JpaRepository<Peticion, Integer> {
}
