package com.agency.mapper;

import com.agency.dto.ReservationDto;
import com.agency.entity.Reservation;
import com.agency.repository.AccountRepository;
import com.agency.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class ReservationMapper extends com.agency.mapper.AbstractMapper<Reservation, ReservationDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public ReservationMapper(ModelMapper mapper, AccountRepository accountRepository, FoodRepository foodRepository) {
        super(Reservation.class, ReservationDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
        this.foodRepository = foodRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Reservation.class, ReservationDto.class)
                .addMappings(m -> m.skip(ReservationDto::setAccountId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setNameFood)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ReservationDto::setFoodId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ReservationDto.class, Reservation.class)
                .addMappings(m -> m.skip(Reservation::setAccount)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Reservation::setFood)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Reservation source, ReservationDto destination) {
        destination.setAccountId(getAccountId(source));
        destination.setNameFood(getFoodName(source));
        destination.setFoodId(getFoodId(source));
    }

    private Long getAccountId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    private Long getFoodId(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getId();
    }

    private String getFoodName(Reservation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getName();
    }


    @Override
    void mapSpecificFields(ReservationDto source, Reservation destination) {
        destination.setAccount(accountRepository.findById(source.getAccountId()).orElse(null));
        destination.setFood(foodRepository.findById(source.getFoodId()).orElse(null));
    }
}
