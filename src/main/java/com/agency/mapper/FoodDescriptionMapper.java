package com.agency.mapper;

import com.agency.dto.FoodDescriptionDto;
import com.agency.entity.FoodDescription;
import com.agency.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class FoodDescriptionMapper extends AbstractMapper<FoodDescription,FoodDescriptionDto> {

    private final ModelMapper mapper;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodDescriptionMapper(ModelMapper mapper, FoodRepository foodRepository) {

        super(FoodDescription.class, FoodDescriptionDto.class);
        this.mapper = mapper;
        this.foodRepository = foodRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(FoodDescription.class, FoodDescriptionDto.class)
                .addMappings(m -> m.skip(FoodDescriptionDto::setFoodId)).setPostConverter(toDtoConverter());
//                .addMappings(m -> m.skip(TourDescriptionDto::setTourName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(FoodDescriptionDto.class, FoodDescription.class)
                .addMappings(m -> m.skip(FoodDescription::setFood)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(FoodDescription source, FoodDescriptionDto destination) {
        destination.setFoodId(getFoodId(source));
//        destination.setTourName(getTourName(source));
    }

    private Long getFoodId(FoodDescription source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getId();
    }

    private String getFoodName(FoodDescription source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getName();
    }


    @Override
    void mapSpecificFields(FoodDescriptionDto source, FoodDescription destination) {
        destination.setFood(foodRepository.findById(source.getFoodId()).orElse(null));
    }
}
