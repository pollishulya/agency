package com.agency.mapper;

import com.agency.dto.CommentDto;
import com.agency.entity.Account;
import com.agency.entity.Comment;
import com.agency.repository.AccountRepository;
import com.agency.repository.CommentRepository;

import com.agency.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class CommentMapper extends AbstractMapper<Comment, CommentDto> {

    private final ModelMapper mapper;
    private final CommentRepository commentRepository;
    private final AccountRepository userRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public CommentMapper(ModelMapper mapper, CommentRepository commentRepository, AccountRepository userRepository,
                         FoodRepository foodRepository) {
        super(Comment.class, CommentDto.class);
        this.mapper = mapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.foodRepository = foodRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Comment.class, CommentDto.class)
                .addMappings(m -> m.skip(CommentDto::setUsername)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentDto::setFoodId)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(CommentDto::setAccountId)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(CommentDto.class, Comment.class)
                .addMappings(m -> m.skip(Comment::setFood)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(Comment::setAccount)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(Comment source, CommentDto destination) {
        destination.setUsername(getUsername(source));
        destination.setFoodId(getFoodId(source));
        destination.setAccountId(getAccountId(source));
    }

    private String getUsername(Comment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getName();
    }

    private Long getFoodId(Comment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getId();
    }
    private Long getAccountId(Comment source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
    }

    @Override
    void mapSpecificFields(CommentDto source, Comment destination) {
        destination.setFood(foodRepository.findById(source.getFoodId()).orElse(null));
        destination.setAccount(userRepository.findById(source.getAccountId()).orElse(null));
    }
}
