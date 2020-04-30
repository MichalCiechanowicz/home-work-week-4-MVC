package com.poweremabox.homeworkweek4.view;

import com.poweremabox.homeworkweek4.controller.CarService;
import com.poweremabox.homeworkweek4.model.Car;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Route("main-view")
public class ShowCars extends VerticalLayout {

    private CarService carService;
    private ListDataProvider<Car> dataProvider;
    private Grid<Car> grid;

    @Autowired
    public ShowCars(CarService carService) {
        dataProvider = new ListDataProvider<>(carService.getCarList());
        grid = new Grid<>();
        this.carService = carService;
        getCars();
    }

    private void getCars() {

        grid.setDataProvider(dataProvider);

        Grid.Column<Car> makeColumn = grid.addColumn(Car::getMake).setHeader("Make");
        Grid.Column<Car> modelColumn = grid.addColumn(Car::getModel).setHeader("Model");
        Grid.Column<Car> colorColumn = grid.addColumn(Car::getColor).setHeader("Color");
        Grid.Column<Car> engineSizeColumn = grid.addColumn(Car::getEngineCc).setHeader("Engine size");

        HeaderRow filterRow = grid.appendHeaderRow();

        createSearchBar(makeColumn, modelColumn, colorColumn, engineSizeColumn, filterRow);

        grid.addComponentColumn(this::createRemoveButton);
        grid.addComponentColumn(this::createEditButton);

        grid.appendFooterRow().getCell(makeColumn).setComponent(createAddButton());
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        add(grid);

    }

    private void createSearchBar(Grid.Column<Car> makeColumn, Grid.Column<Car> modelColumn,
                                 Grid.Column<Car> colorColumn, Grid.Column<Car> engineSizeColumn, HeaderRow filterRow) {
        TextField carMakeField = new TextField();
        carMakeField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(car.getMake(),
                        carMakeField.getValue())));
        carMakeField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(makeColumn).setComponent(carMakeField);
        carMakeField.setSizeFull();
        carMakeField.setPlaceholder("Search by Make");

        TextField carModelField = new TextField();
        carModelField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(car.getModel(),
                        carModelField.getValue())));
        carModelField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(modelColumn).setComponent(carModelField);
        carModelField.setSizeFull();
        carModelField.setPlaceholder("Search by Model");

        TextField carColorField = new TextField();
        carColorField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(car.getColor(),
                        carColorField.getValue())));
        carColorField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(colorColumn).setComponent(carColorField);
        carColorField.setSizeFull();
        carColorField.setPlaceholder("Search by Color");

        TextField engineSizeField = new TextField();
        engineSizeField.addValueChangeListener(event -> dataProvider.addFilter(
                car -> StringUtils.containsIgnoreCase(String.valueOf(car.getEngineCc()),
                        engineSizeField.getValue())));
        engineSizeField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(engineSizeColumn).setComponent(engineSizeField);
        engineSizeField.setSizeFull();
        engineSizeField.setPlaceholder("Search by Color");
    }

    private Button createEditButton(Car car) {
        VerticalLayout verticalLayout = new VerticalLayout();
        Button button = new Button("Edit", clickEvent -> {
            Dialog dialog = new Dialog();
            Input make = new Input();
            make.setValue(car.getMake());
            Input model = new Input();
            model.setValue(car.getModel());
            Input color = new Input();
            color.setValue(car.getColor());
            Input engineSize = new Input();
            engineSize.setValue(String.valueOf(car.getEngineCc()));

            NativeButton confirmButton = new NativeButton("Confirm", event -> {
                car.setMake(make.getValue());
                car.setModel(model.getValue());
                car.setColor(color.getValue());
                car.setEngineCc(Integer.parseInt(engineSize.getValue()));
                dialog.close();
                dataProvider.refreshAll();
            });
            verticalLayout.add(make, model, color, engineSize, confirmButton);
            dialog.add(verticalLayout);
            dialog.open();

        });

        return button;
    }

    private Button createRemoveButton(Car item) {
        return new Button("Remove", clickEvent -> {
            dataProvider.getItems().remove(item);
            dataProvider.refreshAll();
        });
    }

    private Button createAddButton() {
        Button button = new Button("Add New Car");
        button.addClickListener(e ->
                button.getUI().ifPresent(ui ->
                        ui.navigate("add-car")));
        return button;
    }
}
