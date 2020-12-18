package com.unibuc.lab10.mapper;

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);
}
