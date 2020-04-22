package com.agency.mapper;

import com.agency.dto.ProgramDto;
import com.agency.entity.Program;
import com.agency.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ProgramMapper extends AbstractMapper<Program,ProgramDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;

    @Autowired
    public  ProgramMapper(ModelMapper mapper, AccountRepository accountRepository) {
        super(Program.class, ProgramDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Program.class, ProgramDto.class)
                .addMappings(m -> m.skip(ProgramDto::setCompanyId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ProgramDto.class,Program.class)
                .addMappings(m -> m.skip(Program::setCompany)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Program source, ProgramDto destination) {
        destination.setCompanyId(getAccountId(source));
    }

    private Long getAccountId(Program source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCompany().getId();
    }

    @Override
    void mapSpecificFields(ProgramDto source, Program destination) {
        destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));
    }
}
