package br.com.pillwatcher.dpb.controllers;

import br.com.pillwatcher.dpb.services.AdminService;
import com.google.gson.Gson;
import io.swagger.model.AdminDTOForCreate;
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

import static br.com.pillwatcher.dpb.constants.TestConstants.getAdminDtoForCreate;
import static br.com.pillwatcher.dpb.constants.UrlConstants.URI_ADMINS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdminService service;

    @BeforeEach
    void setUp() {
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

        AdminDTOForCreate admin = getAdminDtoForCreate();
        String inputInJson = gson.toJson(admin);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(path)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        //when
        MockHttpServletResponse response = this.mockMvc
                .perform(requestBuilder)
                .andReturn()
                .getResponse();

        //then
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

}
