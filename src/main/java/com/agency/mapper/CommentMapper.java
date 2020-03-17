//package com.agency.mapper;
//
//import com.agency.dto.CommentDto;
//import com.agency.entity.Account;
//import com.agency.entity.Comment;
//import com.agency.repository.AccountRepository;
//import com.agency.repository.CommentRepository;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Objects;
//
//@Component
//public class CommentMapper extends AbstractMapper<Comment, CommentDto> {
//
//    private final ModelMapper mapper;
//    private final CommentRepository commentRepository;
//    private final AccountRepository userRepository;
//   // private final TourRepository tourRepository;
//
//    @Autowired
//    public CommentMapper(ModelMapper mapper, CommentRepository commentRepository, AccountRepository userRepository,
//                         TourRepository tourRepository) {
//        super(Comment.class, CommentDto.class);
//        this.mapper = mapper;
//        this.commentRepository = commentRepository;
//        this.userRepository = userRepository;
//        this.tourRepository = tourRepository;
//    }
//
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Comment.class, CommentDto.class)
//                .addMappings(m -> m.skip(CommentDto::setUsername)).setPostConverter(toDtoConverter())
//                .addMappings(m -> m.skip(CommentDto::setTourId)).setPostConverter(toDtoConverter())
//                .addMappings(m -> m.skip(CommentDto::setuserId)).setPostConverter(toDtoConverter());
//        mapper.createTypeMap(CommentDto.class, Comment.class)
//                .addMappings(m -> m.skip(Comment::setTour)).setPostConverter(toEntityConverter())
//                .addMappings(m -> m.skip(Comment::setuser)).setPostConverter(toEntityConverter());
//
//    }
//
//    @Override
//    public void mapSpecificFields(Comment source, CommentDto destination) {
//        destination.setUsername(getUsername(source));
//        destination.setTourId(getTourId(source));
//        destination.setuserId(getuserId(source));
//    }
//
//    private String getUsername(Comment source) {
//        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getuser().getName();
//    }
//
//    private Long getTourId(Comment source) {
//        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getTour().getId();
//    }
//    private Long getuserId(Comment source) {
//        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getuser().getId();
//    }
//
//    @Override
//    void mapSpecificFields(CommentDto source, Comment destination) {
//        destination.setTour(tourRepository.findById(source.getTourId()).orElse(null));
//        destination.setuser(userRepository.findById(source.getuserId()).orElse(null));
//    }
//}
