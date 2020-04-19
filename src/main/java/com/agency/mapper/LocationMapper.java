package com.agency.mapper;


import com.agency.dto.LocationDto;

import com.agency.entity.Location;
import com.agency.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class LocationMapper extends AbstractMapper<Location,LocationDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;

    @Autowired
    public  LocationMapper(ModelMapper mapper, AccountRepository accountRepository) {
        super(Location.class, LocationDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Location.class, LocationDto.class)
                .addMappings(m -> m.skip(LocationDto::setCompanyId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(LocationDto.class, Location.class)
                .addMappings(m -> m.skip(Location::setCompany)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Location source, LocationDto destination) {
        destination.setCompanyId(getAccountId(source));
    }

    private Long getAccountId(Location source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCompany().getId();
    }

    @Override
    void mapSpecificFields(LocationDto source, Location destination) {
        destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));
    }
}
