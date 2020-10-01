package br.com.pillwatcher.dpb.controllers;

import br.com.pillwatcher.dpb.entities.Admin;
import br.com.pillwatcher.dpb.mappers.AdminMapper;
import br.com.pillwatcher.dpb.services.AdminService;
import com.google.gson.Gson;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;

import static br.com.pillwatcher.dpb.constants.TestConstants.*;
import static br.com.pillwatcher.dpb.constants.UrlConstants.URI_ADMINS;
import static br.com.pillwatcher.dpb.constants.UrlConstants.URI_ADMINS_CPF;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminMapper mapper;

    @MockBean
    private AdminService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createAdminShouldReturnStatusCreated() throws Exception {
        //given
        Gson gson = new Gson();

        String path = UriComponentsBuilder
                .fromUriString(URI_ADMINS)
                .build()
                .getPath();

        assert path != null;

        AdminDTOForCreate admin = getAdminDtoForCreate();
        String inputInJson = gson.toJson(admin);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = this.mockMvc
                .perform(requestBuilder)
                .andReturn()
                .getResponse();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void updateAdminShouldReturnStatusOk() throws Exception {
        //given
        Gson gson = new Gson();

        String path = UriComponentsBuilder
                .fromUriString(URI_ADMINS_CPF)
                .buildAndExpand(FAKE_CPF)
                .getPath();

        assert path != null;

        Admin admin = getAdmin();
        AdminDTOForUpdate adminDto = getAdminDtoForUpdate();

        String inputInJson = gson.toJson(adminDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put(path)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        when(this.service.update(any(AdminDTOForUpdate.class), anyString()))
                .thenReturn(admin);

        //then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(admin.getUser().getName())))
                .andExpect(jsonPath("$.email", equalTo(admin.getEmail())));
    }

    @Test
    public void findAdminShouldReturnStatusOk() throws Exception {
        //given
        String path = UriComponentsBuilder
                .fromUriString(URI_ADMINS_CPF)
                .buildAndExpand(FAKE_CPF)
                .getPath();

        assert path != null;

        Admin admin = getAdmin();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(path)
                .accept(MediaType.APPLICATION_JSON);

        //when
        when(this.service.findAdmin(anyString()))
                .thenReturn(admin);

        //then
        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(admin.getUser().getName())))
                .andExpect(jsonPath("$.email", equalTo(admin.getEmail())));
    }

}
