package com.agency.mapper;

import com.agency.dto.CommentLocationDto;
import com.agency.dto.CommentProgramDto;
import com.agency.entity.CommentLocation;
import com.agency.entity.CommentProgram;
import com.agency.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CommentProgramMapper extends AbstractMapper<CommentProgram, CommentProgramDto> {

    private final ModelMapper mapper;
    private final CommentProgramRepository commentRepository;
    private final AccountRepository userRepository;
    private final ProgramRepository programRepository;

    @Autowired
    public CommentProgramMapper(ModelMapper mapper, CommentProgramRepository commentRepository, AccountRepository userRepository,
                                ProgramRepository locationRepository)
    {
        super(CommentProgram.class, CommentProgramDto.class);
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.programRepository=locationRepository;

    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(CommentProgram.class, CommentProgramDto.class)
                .addMappings(m -> m.skip(CommentProgramDto::setUsername)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentProgramDto::setProgramId)).setPostConverter(toDtoConverter())
              //  .addMappings(m -> m.skip(CommentDto::setProgramId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentProgramDto::setAccountId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CommentProgramDto.class, CommentProgram.class)
                .addMappings(m -> m.skip(CommentProgram::setProgram)).setPostConverter(toEntityConverter())
             //   .addMappings(m -> m.skip(Comment::setProgram)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(CommentProgram::setAccount)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(CommentProgram source, CommentProgramDto destination) {
        destination.setUsername(getUsername(source));
        destination.setProgramId(getProgramId(source));
        destination.setAccountId(getAccountId(source));
    }

    private String getUsername(CommentProgram source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getFirstname();
    }

    private Long getProgramId(CommentProgram source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getProgram().getId();
    }

    private Long getAccountId(CommentProgram source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    @Override
    void mapSpecificFields(CommentProgramDto source, CommentProgram destination) {
//        destination.setLocation(foodRepository.findById(source.getLocationId()).orElse(null));
       destination.setProgram(programRepository.findById(source.getProgramId()).orElse(null));
        destination.setAccount(userRepository.findById(source.getAccountId()).orElse(null));
    }
}
