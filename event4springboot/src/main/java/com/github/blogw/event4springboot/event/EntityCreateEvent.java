package com.github.blogw.event4springboot.event;

import lombok.Value;
import org.springframework.core.ResolvableType;
import org.springframework.core.ResolvableTypeProvider;

@Value
public class EntityCreateEvent<T> implements ResolvableTypeProvider {
    T entity;

    @Override
    public ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(
                getClass(),
                ResolvableType.forClass(entity.getClass()));
    }
}
