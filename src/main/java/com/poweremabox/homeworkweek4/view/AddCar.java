package com.poweremabox.homeworkweek4.view;

import com.poweremabox.homeworkweek4.controller.CarService;
import com.poweremabox.homeworkweek4.model.Car;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("add-car")
public class AddCar extends VerticalLayout {

    private CarService carService;

    @Autowired
    public AddCar(CarService carService) {
        this.carService = carService;

        TextField textFieldMake = new TextField("Make");
        TextField textFieldModel = new TextField("Model");
        TextField textFieldColor = new TextField("Color");
        TextField textFieldEngineSize = new TextField("Engine Size");

        NativeButton confirmButton = new NativeButton("Add Car", event -> {
            carService.addCar(new Car(textFieldMake.getValue()
                    , textFieldModel.getValue()
                    , textFieldColor.getValue()
                    , Integer.parseInt(textFieldEngineSize.getValue())));
            getUI().ifPresent(ui ->
                    ui.navigate("main-view"));

        });
        add(textFieldMake, textFieldModel, textFieldColor, textFieldEngineSize, confirmButton);
    }
}
