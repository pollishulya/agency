package com.agency.mapper;

import org.dom4j.tree.AbstractEntity;
import com.agency.dto.BaseDto;


public abstract class Mapper<E extends AbstractEntity, D extends BaseDto> {

   abstract E toEntity(D dto);

    abstract D toDto(E entity);
}
