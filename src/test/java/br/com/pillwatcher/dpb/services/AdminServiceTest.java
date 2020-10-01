package br.com.pillwatcher.dpb.services;

import br.com.pillwatcher.dpb.entities.Admin;
import br.com.pillwatcher.dpb.exceptions.AdminException;
import br.com.pillwatcher.dpb.mappers.AdminMapper;
import br.com.pillwatcher.dpb.repositories.AdminRepository;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForUpdate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static br.com.pillwatcher.dpb.constants.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService service;

    @Autowired
    private AdminMapper mapper;

    @MockBean
    private AdminRepository repository;

    @Test
    public void createAdminShouldReturnCreatedStatus() {
        //given
        AdminDTOForCreate admin = getAdminDtoForCreate();

        //when
        when(repository.findAdminByEmail(anyString()))
                .thenReturn(Optional.empty());

        when(repository.save(any(Admin.class)))
                .thenReturn(mapper.toAdminForCreateEntity(admin));

        //then
        Admin saved = service.create(admin);
        assertNotNull(saved);
    }

    @Test
    public void createAdminShouldReturnAlreadyExistsStatus() {
        Assertions.assertThrows(AdminException.class, () -> {
            //given
            AdminDTOForCreate admin = getAdminDtoForCreate();

            //when
            when(repository.findAdminByEmail(anyString()))
                    .thenReturn(Optional.of(getAdmin()));

            //then
            Admin saved = service.create(admin);
        });
    }

    @Test
    public void updateAdminShouldReturnOkStatus() {
        //given
        AdminDTOForUpdate admin = getAdminDtoForUpdate();

        //when
        when(repository.findAdminByUserDocument(anyString()))
                .thenReturn(Optional.of(getAdmin()));

        //then
        Admin updated = service.update(admin, FAKE_CPF);
    }

    @Test
    public void updateAdminShouldReturnNotFoundStatus() {
        Assertions.assertThrows(AdminException.class, () -> {
            //given
            AdminDTOForUpdate admin = getAdminDtoForUpdate();

            //when
            when(repository.findAdminByEmail(anyString()))
                    .thenReturn(Optional.empty());

            //then
            Admin updated = service.update(admin, FAKE_CPF);
        });
    }

    @Test
    public void getAdminShouldReturnOkStatus() {
        //when
        when(repository.findAdminByUserDocument(anyString()))
                .thenReturn(Optional.of(getAdmin()));

        //then
        Admin updated = service.findAdmin(FAKE_CPF);
    }

    @Test
    public void getAdminShouldReturnNotFoundStatus() {
        Assertions.assertThrows(AdminException.class, () -> {
            //when
            when(repository.findAdminByEmail(anyString()))
                    .thenReturn(Optional.empty());

            //then
            Admin updated = service.findAdmin(FAKE_CPF);
        });
    }

    @Test
    public void deleteAdminShouldReturnOkStatus() {
        //when
        when(repository.findAdminByUserDocument(anyString()))
                .thenReturn(Optional.of(getAdmin()));

        //then
        service.deleteAdmin(FAKE_CPF);
    }

    @Test
    public void deleteAdminShouldReturnNotFoundStatus() {
        Assertions.assertThrows(AdminException.class, () -> {
            //when
            when(repository.findAdminByUserDocument(anyString()))
                    .thenReturn(Optional.empty());

            //then
            service.deleteAdmin(FAKE_CPF);
        });
    }
}
