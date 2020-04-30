package com.agency.mapper;

import com.agency.dto.BookingProgramDto;

import com.agency.entity.BookingProgram;

import com.agency.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class BookingProgramMapper extends AbstractMapper<BookingProgram, BookingProgramDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;
    private final ProgramRepository programRepository;
  //  private final CompanyRepository companyRepository;
 //   private final LocationRepository locationRepository;

    @Autowired
    public BookingProgramMapper(ModelMapper mapper, AccountRepository accountRepository, ProgramRepository programRepository) {
        super(BookingProgram.class, BookingProgramDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.programRepository = programRepository;
        //this.companyRepository=companyRepository;
       // this.locationRepository=locationRepository;
    }
    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(BookingProgram.class, BookingProgramDto.class)
                .addMappings(m -> m.skip(BookingProgramDto::setAccountId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(BookingProgramDto::setNameProgram)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(BookingProgramDto::setProgramId)).setPostConverter(toDtoConverter());
               // .addMappings(m -> m.skip(BookingProgramDto::setCompanyId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(BookingProgramDto.class, BookingProgram.class)
                .addMappings(m -> m.skip(BookingProgram::setAccount)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(BookingProgram::setProgram)).setPostConverter(toEntityConverter());
            //    .addMappings(m -> m.skip(BookingProgram::setCompany)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(BookingProgram source, BookingProgramDto destination) {
        destination.setAccountId(getAccountId(source));
        destination.setNameProgram(getProgramName(source));
        destination.setProgramId(getProgramId(source));
      //  destination.setCompanyId(getCompanyId(source));
    }

    private Long getAccountId(BookingProgram source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    private Long getProgramId(BookingProgram source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getProgram().getId();
    }

  //  private Long getCompanyId(BookingProgram source) {
    //    return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCompany().getId();
    //}

    private String getProgramName(BookingProgram source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getProgram().getName();
    }


    @Override
    void mapSpecificFields(BookingProgramDto source, BookingProgram destination) {
        destination.setAccount(accountRepository.findById(source.getAccountId()).orElse(null));
      destination.setProgram(programRepository.findById(source.getProgramId()).orElse(null));
       // destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));

    }
}
