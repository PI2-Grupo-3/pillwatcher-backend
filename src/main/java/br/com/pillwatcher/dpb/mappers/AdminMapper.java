package br.com.pillwatcher.dpb.mappers;

import br.com.pillwatcher.dpb.entities.Admin;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AdminMapper {

    @Mappings({
            @Mapping(source = "document", target = "user.document"),
            @Mapping(source = "imageUrl", target = "user.imageUrl"),
            @Mapping(source = "name", target = "user.name")
    })
    Admin toAdminForCreateEntity(AdminDTOForCreate adminDTOForCreate);

    AdminDTOForCreate toAdminForCreate(Admin admin);
    AdminDTOForResponse toAdminForResponse(Admin admin);

}
