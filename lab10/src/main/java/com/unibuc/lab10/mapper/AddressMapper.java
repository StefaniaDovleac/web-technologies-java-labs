package com.unibuc.lab10.mapper;

import com.unibuc.lab10.domain.Address;
import com.unibuc.lab10.dto.AddressDto;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper extends EntityMapper<AddressDto, Address> {
}
