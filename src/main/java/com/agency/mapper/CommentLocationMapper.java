package com.agency.mapper;

import com.agency.dto.CommentLocationDto;
import com.agency.entity.CommentLocation;
import com.agency.repository.AccountRepository;
import com.agency.repository.CommentLocationRepository;
import com.agency.repository.FoodRepository;
import com.agency.repository.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CommentLocationMapper extends AbstractMapper<CommentLocation, CommentLocationDto> {

    private final ModelMapper mapper;
    private final CommentLocationRepository commentRepository;
    private final AccountRepository userRepository;
    private final LocationRepository foodRepository;

    @Autowired
    public CommentLocationMapper(ModelMapper mapper, CommentLocationRepository commentRepository, AccountRepository userRepository, LocationRepository locationRepository)
    {
        super(CommentLocation.class, CommentLocationDto.class);
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.foodRepository=locationRepository;

    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(CommentLocation.class, CommentLocationDto.class)
                .addMappings(m -> m.skip(CommentLocationDto::setUsername)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentLocationDto::setLocationId)).setPostConverter(toDtoConverter())
              //  .addMappings(m -> m.skip(CommentDto::setProgramId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentLocationDto::setAccountId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CommentLocationDto.class, CommentLocation.class)
                .addMappings(m -> m.skip(CommentLocation::setLocation)).setPostConverter(toEntityConverter())
             //   .addMappings(m -> m.skip(Comment::setProgram)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(CommentLocation::setAccount)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(CommentLocation source, CommentLocationDto destination) {
        destination.setUsername(getUsername(source));
        destination.setLocationId(getLocationId(source));
        destination.setAccountId(getAccountId(source));
    }

    private String getUsername(CommentLocation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getFirstname();
    }

    private Long getLocationId(CommentLocation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getLocation().getId();
    }

    private Long getAccountId(CommentLocation source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    @Override
    void mapSpecificFields(CommentLocationDto source, CommentLocation destination) {
        destination.setLocation(foodRepository.findById(source.getLocationId()).orElse(null));
//       destination.setProgram(programRepository.findById(source.getProgramId()).orElse(null));
        destination.setAccount(userRepository.findById(source.getAccountId()).orElse(null));
    }
}
