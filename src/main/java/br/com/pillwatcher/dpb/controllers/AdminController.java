package br.com.pillwatcher.dpb.controllers;

import br.com.pillwatcher.dpb.entities.Admin;
import br.com.pillwatcher.dpb.mappers.AdminMapper;
import br.com.pillwatcher.dpb.services.AdminService;
import io.swagger.api.AdminsApi;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.com.pillwatcher.dpb.constants.UrlConstants.URI_ADMINS;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AdminController implements AdminsApi {

    private final AdminMapper mapper;
    private final AdminService service;

    @Override
    @PostMapping(value = URI_ADMINS)
    public ResponseEntity<AdminDTOForResponse> createAdmin(@Valid @RequestBody AdminDTOForCreate dtoForCreate) {

        log.info("AdminController.createAdmin - Start - Input - [{}]", dtoForCreate);
        log.debug("AdminController.createAdmin - Start - Input - Order: {}", dtoForCreate);

        Admin admin = service.create(dtoForCreate);

        ResponseEntity<AdminDTOForResponse> response = ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toAdminForResponse(admin));

        log.debug("AdminController.createAdmin - End - Input: {} - Output: {}", dtoForCreate, response);

        return response;
    }
}
