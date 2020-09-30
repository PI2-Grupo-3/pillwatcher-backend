package br.com.pillwatcher.dpb.services;

import br.com.pillwatcher.dpb.entities.Admin;
import io.swagger.model.AdminDTOForCreate;

public interface AdminService {

    Admin create(AdminDTOForCreate adminDto);

}
