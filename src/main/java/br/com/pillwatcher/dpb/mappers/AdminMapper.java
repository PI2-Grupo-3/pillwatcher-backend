package br.com.pillwatcher.dpb.mappers;

import br.com.pillwatcher.dpb.entities.Admin;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForResponse;
import org.mapstruct.Mapper;
<<<<<<< HEAD
=======
import org.mapstruct.Mapping;
>>>>>>> be9175342300f64e3764e3e7b88ab8e13dc77c4a
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AdminMapper {

    @Mappings({
<<<<<<< HEAD
            
=======
            @Mapping(source = "document", target = "user.document"),
            @Mapping(source = "imageUrl", target = "user.imageUrl"),
            @Mapping(source = "name", target = "user.name")
>>>>>>> be9175342300f64e3764e3e7b88ab8e13dc77c4a
    })
    Admin toAdminForCreateEntity(AdminDTOForCreate adminDTOForCreate);

    AdminDTOForCreate toAdminForCreate(Admin admin);
    AdminDTOForResponse toAdminForResponse(Admin admin);

}
