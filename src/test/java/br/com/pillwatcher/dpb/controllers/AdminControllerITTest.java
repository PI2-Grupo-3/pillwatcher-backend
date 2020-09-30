package br.com.pillwatcher.dpb.controllers;

import br.com.pillwatcher.dpb.DpbApplication;
import br.com.pillwatcher.dpb.entities.Admin;
import br.com.pillwatcher.dpb.mappers.AdminMapper;
import br.com.pillwatcher.dpb.repositories.AdminRepository;
import io.swagger.model.AdminDTOForCreate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static br.com.pillwatcher.dpb.constants.TestConstants.getAdmin;
import static br.com.pillwatcher.dpb.constants.TestConstants.getUrl;
import static br.com.pillwatcher.dpb.constants.UrlConstants.URI_ADMINS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = DpbApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminControllerITTest {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private AdminMapper mapper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private Admin admin;

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
        this.repository.flush();
    }

    @Test
    void createAdminShouldReturnCreatedStatus() {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_ADMINS)
                .build()
                .getPath();

        Admin admin = getAdmin();
        AdminDTOForCreate adminDto = mapper.toAdminForCreate(admin);

        HttpEntity<AdminDTOForCreate> entity = new HttpEntity<>(adminDto);

        ResponseEntity<Void> response = this.testRestTemplate.exchange(
                getUrl(path, this.port),
                HttpMethod.POST,
                entity,
                Void.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        List<Admin> adminList = this.repository.findAll();
        assertEquals(1, adminList.size());

    }
}
