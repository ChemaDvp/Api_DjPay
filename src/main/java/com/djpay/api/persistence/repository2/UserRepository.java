package com.djpay.api.persistence.repository2;

import com.djpay.api.persistence.model2.Peticion;
import com.djpay.api.persistence.model2.Rol;
import com.djpay.api.persistence.model2.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(Rol role);
    User findByEmail(String email);
}
