package com.agency.mapper;
//
//
//import com.agency.dto.OrderDto;
//import com.agency.entity.Order;
//import com.agency.repository.AccountRepository;
//import com.agency.repository.FoodRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.Objects;
//
//@Component
//public class OrderMapper extends AbstractMapper<Order, OrderDto> {
//
//    private final ModelMapper mapper;
//    private final AccountRepository accountRepository;
//    private final FoodRepository foodRepository;
//
//    @Autowired
//    public OrderMapper(ModelMapper mapper, AccountRepository accountRepository, FoodRepository foodRepository) {
//        super(Order.class, OrderDto.class);
//        this.mapper = mapper;
//        this.accountRepository = accountRepository;
//        this.foodRepository = foodRepository;
//    }
//
//    @PostConstruct
//    public void setupMapper() {
//        mapper.createTypeMap(Order.class, OrderDto.class)
//                .addMappings(m -> m.skip(OrderDto::setAccountId)).setPostConverter(toDtoConverter())
//                .addMappings(m -> m.skip(OrderDto::setNameFood)).setPostConverter(toDtoConverter())
//                .addMappings(m -> m.skip(OrderDto::setFoodId)).setPostConverter(toDtoConverter());
//        mapper.createTypeMap(OrderDto.class, Order.class)
//                .addMappings(m -> m.skip(Order::setAccount)).setPostConverter(toEntityConverter())
//                .addMappings(m -> m.skip(Order::setFood)).setPostConverter(toEntityConverter());
//
//    }
//
//    @Override
//    public void mapSpecificFields(Order source, OrderDto destination) {
//        destination.setAccountId(getAccountId(source));
//        destination.setNameFood(getTourName(source));
//        destination.setFoodId(getTourId(source));
//    }
//
//    private Long getAccountId(Order source) {
//        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getAccount().getId();
//    }
//
//    private Long getTourId(Order source) {
//        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getId();
//    }
//
//    private String getTourName(Order source) {
//        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getFood().getName();
//    }
//
//
//    @Override
//    void mapSpecificFields(OrderDto source, Order destination) {
//        destination.setAccount(accountRepository.findById(source.getAccountId()).orElse(null));
//        destination.setFood(foodRepository.findById(source.getFoodId()).orElse(null));
//    }
//}
