package com.agency.mapper;

import com.agency.dto.DescriptionDto;
import com.agency.entity.Description;
import com.agency.repository.FoodRepository;
import com.agency.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class DescriptionMapper extends AbstractMapper<Description, DescriptionDto>{

    private final ModelMapper mapper;
    private final FoodRepository foodRepository;
   private final LocationRepository locationRepository;

    @Autowired
    public DescriptionMapper(ModelMapper mapper, FoodRepository foodRepository
            , LocationRepository locationRepository
    ) {

        super(Description.class, DescriptionDto.class);
        this.mapper = mapper;
        this.foodRepository = foodRepository;
        this.locationRepository = locationRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Description.class, DescriptionDto.class)
                .addMappings(m -> m.skip(DescriptionDto::setFoodId)).setPostConverter(toDtoConverter());
                //.addMappings(m -> m.skip(TourDescriptionDto::setTourName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(DescriptionDto.class, Description.class)
                .addMappings(m -> m.skip(Description::setFood)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Description source, DescriptionDto destination) {
        destination.setFoodId(getFoodId(source));
       // destination.setLocationId(getLocationId(source));
//        destination.setTourName(getTourName(source));
    }

    private Long getFoodId(Description source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getId();
    }

    private String getName(Description source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getName();
    }

    private Long getLocationId(Description source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLocation().getId();
    }

    private String getNameLocation(Description source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLocation().getName();
    }
    @Override
    void mapSpecificFields(DescriptionDto source, Description destination) {
        destination.setFood(foodRepository.findById(source.getFoodId()).orElse(null));
       destination.setLocation(locationRepository.findById(source.getLocationId()).orElse(null));
    }
}
