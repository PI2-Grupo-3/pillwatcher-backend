package br.com.pillwatcher.dpb.services.impl;

import br.com.pillwatcher.dpb.constants.ErrorMessages;
import br.com.pillwatcher.dpb.constants.ValidationConstraints;
import br.com.pillwatcher.dpb.entities.Admin;
import br.com.pillwatcher.dpb.exceptions.AdminException;
import br.com.pillwatcher.dpb.mappers.AdminMapper;
import br.com.pillwatcher.dpb.repositories.AdminRepository;
import br.com.pillwatcher.dpb.services.AdminService;
import io.swagger.model.AdminDTOForCreate;
import io.swagger.model.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository repository;
    private final AdminMapper mapper;

    @Override
    @Transactional
    public Admin create(final AdminDTOForCreate adminDto) {

        log.info("AdminServiceImpl.create - Start - Input {}", adminDto);

        Optional<Admin> adminFound = repository.findAdminByEmail(adminDto.getEmail());

        if (adminFound.isPresent()) {
            log.warn(ValidationConstraints.ADMIN_ALREADY_EXISTS, adminDto.getEmail());
            throw new AdminException(ErrorCodeEnum.ADMIN_ALREADY_EXISTS, ErrorMessages.CONFLICT,
                    StringUtils.replace(ValidationConstraints.ADMIN_ALREADY_EXISTS, "{}", adminDto.getEmail()));
        }

        Admin admin = mapper.toAdminForCreateEntity(adminDto);
        return repository.save(admin);
    }
}
