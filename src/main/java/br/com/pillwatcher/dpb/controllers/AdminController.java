package br.com.pillwatcher.dpb.controllers;

import br.com.pillwatcher.dpb.entities.Admin;
import br.com.pillwatcher.dpb.mappers.AdminMapper;
import br.com.pillwatcher.dpb.services.AdminService;
import io.swagger.api.AdminsApi;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForGet;
import io.swagger.model.AdminDTOForResponse;
import io.swagger.model.AdminDTOForUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static br.com.pillwatcher.dpb.constants.UrlConstants.URI_ADMINS;
import static br.com.pillwatcher.dpb.constants.UrlConstants.URI_ADMINS_CPF;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AdminController implements AdminsApi {

    private final AdminMapper mapper;
    private final AdminService service;

    @Override
    @PostMapping(value = URI_ADMINS)
    public ResponseEntity<AdminDTOForResponse> createAdmin(@Valid @RequestBody final AdminDTOForCreate dtoForCreate) {

        log.info("AdminController.createAdmin - Start - Input - [{}]", dtoForCreate);
        log.debug("AdminController.createAdmin - Start - Input - Order: {}", dtoForCreate);

        Admin admin = service.create(dtoForCreate);

        ResponseEntity<AdminDTOForResponse> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toAdminForResponse(admin));

        log.debug("AdminController.createAdmin - End - Input: {} - Output: {}", dtoForCreate, response);

        return response;
    }

    @Override
    @PutMapping(value = URI_ADMINS_CPF)
    public ResponseEntity<AdminDTOForResponse> updateAdmin(@Valid @RequestBody final AdminDTOForUpdate dtoForUpdate,
                                                      @PathVariable("cpf") final String cpf) {

        log.info("AdminController.updateAdmin - Start - Input - [{}, {}]", dtoForUpdate, cpf);
        log.debug("AdminController.updateAdmin - Start - Input - Order: {} - {}", dtoForUpdate, cpf);

        Admin admin = service.update(dtoForUpdate, cpf);

        ResponseEntity<AdminDTOForResponse> response = ResponseEntity.ok(
                mapper.toAdminForResponse(admin));

        log.debug("AdminController.updateAdmin - End - Input: {} - Output: {}", dtoForUpdate, response);

        return response;
    }

    @Override
    @GetMapping(value = URI_ADMINS_CPF)
    public ResponseEntity<AdminDTOForGet> getAdmin(final String cpf) {

        log.info("AdminController.updateAdmin - Start - Input - [{}]", cpf);
        log.debug("AdminController.updateAdmin - Start - Input - Order: {} ", cpf);

        Admin admin = service.findAdmin(cpf);

        ResponseEntity<AdminDTOForGet> response = ResponseEntity.ok(
                mapper.toAdminForGet(admin));

        log.debug("AdminController.updateAdmin - End - Input: {} - Output: {}", cpf, response);

        return response;

    }
}
