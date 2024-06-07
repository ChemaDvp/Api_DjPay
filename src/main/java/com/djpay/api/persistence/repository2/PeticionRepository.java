package com.djpay.api.persistence.repository2;

import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeticionRepository extends JpaRepository<Peticion, Integer> {
}
