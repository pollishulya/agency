package com.agency.mapper;

import com.agency.dto.BookingLocationDto;
import com.agency.dto.BookingProgramDto;
import com.agency.entity.BookingLocation;
import com.agency.entity.BookingProgram;
import com.agency.repository.AccountRepository;
import com.agency.repository.LocationRepository;
import com.agency.repository.ProgramRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class BookingLocationMapper extends AbstractMapper<BookingLocation, BookingLocationDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;
    private final LocationRepository programRepository;
  //  private final CompanyRepository companyRepository;
 //   private final LocationRepository locationRepository;

    @Autowired
    public BookingLocationMapper(ModelMapper mapper, AccountRepository accountRepository, LocationRepository programRepository) {
        super(BookingLocation.class, BookingLocationDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.programRepository = programRepository;
        //this.companyRepository=companyRepository;
       // this.locationRepository=locationRepository;
    }
    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(BookingLocation.class, BookingLocationDto.class)
                .addMappings(m -> m.skip(BookingLocationDto::setAccountId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(BookingLocationDto::setNameLocation)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(BookingLocationDto::setLocationId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(BookingLocationDto::setCompanyId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(BookingLocationDto.class, BookingLocation.class)
                .addMappings(m -> m.skip(BookingLocation::setAccount)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(BookingLocation::setLocation)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(BookingLocation::setCompany)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(BookingLocation source, BookingLocationDto destination) {
        destination.setAccountId(getAccountId(source));
        destination.setNameLocation(getLocationName(source));
        destination.setLocationId(getLocationId(source));
       destination.setCompanyId(getCompanyId(source));
    }

    private Long getAccountId(BookingLocation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    private Long getLocationId(BookingLocation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLocation().getId();
    }

    private Long getCompanyId(BookingLocation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCompany().getId();
    }

    private String getLocationName(BookingLocation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLocation().getName();
    }


    @Override
    void mapSpecificFields(BookingLocationDto source, BookingLocation destination) {
        destination.setAccount(accountRepository.findById(source.getAccountId()).orElse(null));
      destination.setLocation(programRepository.findById(source.getLocationId()).orElse(null));
        destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));

    }
}
