package com.poweremabox.homeworkweek4.controller;

import com.poweremabox.homeworkweek4.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private List<Car> carList;

    public CarService() {
        this.carList = new ArrayList<>();

        carList.add(new Car("Audi", "A3","Silver",2000));
        carList.add(new Car("Bmw", "X3","Black",3000));
        carList.add(new Car("Fiat", "500","White",1400));

    }

    public void addCar(Car car){
        carList.add(car);
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
