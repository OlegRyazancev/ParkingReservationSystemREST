package ru.ryazancev.parkingreservationsystem.web.mappers;

import org.mapstruct.Mapper;
import ru.ryazancev.parkingreservationsystem.models.car.Car;
import ru.ryazancev.parkingreservationsystem.web.dto.CarDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDTO toDTO(Car car);

    List<CarDTO> toDTO(List<Car> cars);

    Car toEntity(CarDTO carDTO);
}
