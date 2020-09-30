package br.com.pillwatcher.dpb.constants;

import br.com.pillwatcher.dpb.entities.Admin;
import br.com.pillwatcher.dpb.entities.User;
import io.swagger.model.AdminDTOForCreate;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class TestConstants {

    public static Admin getAdmin() {

        User user = new User();
        Admin admin = new Admin();

        user.setId(1L);
        user.setName("Logan");
        user.setDocument("41177359693");
        user.setImageUrl("https://s3.images.com/this_is_image_url.jpg");

        admin.setId(1L);
        admin.setUser(user);
        admin.setEmail("logan.warden@email.com");
        admin.setInclusionDate(LocalDateTime.now());

        return admin;
    }

    public static AdminDTOForCreate getAdminDtoForCreate() {

        AdminDTOForCreate admin = new AdminDTOForCreate();

        admin.setName("Logan");
        admin.setDocument("41177359693");
        admin.setEmail("logan.warden@email.com");
        admin.setImageUrl("https://s3.images.com/this_is_image_url.jpg");

        return admin;
    }

    public static String getUrl(String path, int port) {
        return String.format("http://localhost:%s%s", port, path);
    }

    public static String getUrl(String uri, String param, String value) {
        return "http://localhost" + StringUtils.replace(uri, param, value);
    }

}
