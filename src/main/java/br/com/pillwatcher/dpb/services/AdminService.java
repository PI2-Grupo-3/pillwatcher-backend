package br.com.pillwatcher.dpb.services;

import br.com.pillwatcher.dpb.entities.Admin;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForUpdate;

public interface AdminService {

    Admin create(AdminDTOForCreate adminDto);

    Admin update(AdminDTOForUpdate adminDtoForUpdate, String document);

    Admin findAdmin(String document);

}
