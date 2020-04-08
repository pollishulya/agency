package com.agency.mapper;

import com.agency.dto.FoodDto;
import com.agency.entity.Food;
import com.agency.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class FoodMapper extends AbstractMapper<Food, FoodDto> {

    private final ModelMapper mapper;
    private final AccountRepository accountRepository;

    @Autowired
    public FoodMapper(ModelMapper mapper, AccountRepository accountRepository) {
        super(Food.class, FoodDto.class);
        this.mapper = mapper;
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Food.class, FoodDto.class)
                .addMappings(m -> m.skip(FoodDto::setCompanyId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(FoodDto.class, Food.class)
                .addMappings(m -> m.skip(Food::setCompany)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Food source, FoodDto destination) {
        destination.setCompanyId(getAccountId(source));
    }

    private Long getAccountId(Food source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getCompany().getId();
    }

    @Override
    void mapSpecificFields(FoodDto source, Food destination) {
        destination.setCompany(accountRepository.findById(source.getCompanyId()).orElse(null));
    }
}
