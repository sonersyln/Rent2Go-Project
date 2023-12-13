package com.example.rent2gojavaproject.services.concretes;

import com.example.rent2gojavaproject.core.utilities.mappers.ModelMapperService;
import com.example.rent2gojavaproject.models.Car;
import com.example.rent2gojavaproject.repositories.CarRepository;
import com.example.rent2gojavaproject.services.abstracts.CarService;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.AddCarRequest;
import com.example.rent2gojavaproject.services.dtos.requests.carRequest.UpdateCarRequest;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarListResponse;
import com.example.rent2gojavaproject.services.dtos.responses.carResponse.GetCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final CarRepository carRepository;

    private ModelMapperService mapperService;


    @Override
    public List<GetCarListResponse> getAllCars() {
        List<Car> cars = this.carRepository.findAll();
        List<GetCarListResponse> responses = cars.stream().map(car -> this.mapperService
                        .forResponse()
                        .map(car, GetCarListResponse.class))
                .collect(Collectors.toList());


        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        Car car = this.carRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find car id"));

        GetCarResponse response = this.mapperService.forResponse().map(car, GetCarResponse.class);


        return response;
    }

    @Override
    public String createCar(AddCarRequest addCarRequest) {
        String licensePlate = addCarRequest.getPlate().replace(" ", "").toUpperCase();
        addCarRequest.setPlate(licensePlate);
        boolean result = this.carRepository.existsByPlate(addCarRequest.getPlate());
        if (result) {
            throw new IllegalArgumentException("Car Plate already exists! : " + addCarRequest.getPlate());
        }
        Car car = this.mapperService.forRequest().map(addCarRequest, Car.class);


        this.carRepository.save(car);

        return "Transactional Successfull";
    }

    @Override
    public String updateCar(UpdateCarRequest updateCarRequest) {
        return null;
    }

    @Override
    public String deleteCar(int id) {
        return null;
    }
}
