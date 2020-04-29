package com.agency.mapper;

import com.agency.dto.ReservationDto;
import com.agency.entity.FoodCompany;
import com.agency.entity.Reservation;
import com.agency.repository.AccountRepository;
import com.agency.repository.CompanyRepository;
import com.agency.repository.FoodRepository;
import com.agency.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ReservationMapper extends AbstractMapper<Reservation, ReservationDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;
    private final FoodRepository tourRepository;
  //  private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public ReservationMapper(ModelMapper mapper, AccountRepository accountRepository, FoodRepository tourRepository,
                             CompanyRepository companyRepository, LocationRepository locationRepository) {
        super(Reservation.class, ReservationDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.tourRepository = tourRepository;
   //     this.companyRepository=companyRepository;
        this.locationRepository=locationRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Reservation.class, ReservationDto.class)
                .addMappings(m -> m.skip(ReservationDto::setAccountId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setNameFood)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setFoodId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setLocationId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setCompanyId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ReservationDto.class, Reservation.class)
                .addMappings(m -> m.skip(Reservation::setAccount)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Reservation::setFood)).setPostConverter(toEntityConverter())

                .addMappings(m -> m.skip(Reservation::setLocation)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Reservation::setCompany)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Reservation source, ReservationDto destination) {
        destination.setAccountId(getAccountId(source));
        destination.setNameFood(getFoodName(source));
        destination.setFoodId(getFoodId(source));
        destination.setLocationId(getLocationId(source));
        destination.setCompanyId(getCompanyId(source));
    }

    private Long getAccountId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    private Long getFoodId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getId();
    }

    private Long getLocationId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getId();
    }

    private Long getCompanyId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCompany().getId();
    }

    private String getFoodName(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getName();
    }


    @Override
    void mapSpecificFields(ReservationDto source, Reservation destination) {
        destination.setAccount(accountRepository.findById(source.getAccountId()).orElse(null));
        //destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));
      destination.setFood(tourRepository.findById(source.getFoodId()).orElse(null));
 //      destination.setLocation(locationRepository.findById(source.getLocationId()).orElse(null));
        destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));

    }
}
