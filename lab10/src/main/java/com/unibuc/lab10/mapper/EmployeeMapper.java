package com.unibuc.lab10.mapper;

import com.unibuc.lab10.domain.Employee;
import com.unibuc.lab10.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper
public interface EmployeeMapper extends EntityMapper<EmployeeDto, Employee> {
}
