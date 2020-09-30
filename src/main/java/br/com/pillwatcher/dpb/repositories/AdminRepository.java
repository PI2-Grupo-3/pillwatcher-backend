package br.com.pillwatcher.dpb.repositories;

import br.com.pillwatcher.dpb.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminByEmail(String email);

}
