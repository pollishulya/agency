package com.agency.mapper;

import com.agency.dto.BaseDto;
import org.dom4j.tree.AbstractEntity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Objects;
import java.util.Optional;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

public class AbstractMapper<E extends AbstractEntity, D extends BaseDto> extends Mapper<E, D> {

    @Autowired
    ModelMapper mapper;

    private Class<E> entityClass;
    private Class<D> dtoClass;

    AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {

        if(!Objects.isNull(dto)){
            return mapper.map(dto, entityClass);
        }

        return null;
//        return Objects.isNull(dto)
//                ? null
//                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {

        if (!Objects.isNull(entity)) {
            return mapper.map(entity, dtoClass);
        }

        return null;
    }

    Converter<E, D> toDtoConverter() {
        return context -> {
            E source = context.getSource();
            D destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<D, E> toEntityConverter() {
        return context -> {
            D source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(E source, D destination) {
    }

    void mapSpecificFields(D source, E destination) {
    }
}
