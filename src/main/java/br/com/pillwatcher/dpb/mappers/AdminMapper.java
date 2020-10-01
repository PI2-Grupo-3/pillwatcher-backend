package br.com.pillwatcher.dpb.mappers;

import br.com.pillwatcher.dpb.entities.Admin;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.AdminDTOForGet;
import io.swagger.model.AdminDTOForResponse;
import io.swagger.model.AdminDTOForUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AdminMapper {

    @Mappings({
            @Mapping(source = "name", target = "user.name"),
            @Mapping(source = "document", target = "user.document"),
            @Mapping(source = "imageUrl", target = "user.imageUrl")
    })
    Admin toAdminForCreateEntity(AdminDTOForCreate adminDTOForCreate);

    AdminDTOForCreate toAdminForCreate(Admin admin);

    @Mappings({
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.imageUrl", target = "imageUrl")
    })
    AdminDTOForUpdate toAdminForUpdate(Admin admin);

    @Mappings({
            @Mapping(source = "user.name", target = "name")
    })
    AdminDTOForResponse toAdminForResponse(Admin admin);

    @Mappings({
            @Mapping(source = "user.name", target = "name"),
            @Mapping(source = "user.document", target = "document"),
            @Mapping(source = "user.imageUrl", target = "imageUrl")
    })
    AdminDTOForGet toAdminForGet(Admin admin);

}
