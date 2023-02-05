package com.solvd.app.designpatterns.factory;

import com.solvd.app.designpatterns.factory.exampleclasses.SomeService;
import com.solvd.app.designpatterns.factory.exampleclasses.SomeServiceFirstImpl;
import com.solvd.app.designpatterns.factory.exampleclasses.SomeServiceSecondImpl;
import com.solvd.app.utils.exceptions.IncorrectTypeException;

public class ServiceFactory {

    public SomeService getService(String type) {
        if (type == null || type.isEmpty()) {
            throw new IncorrectTypeException("Service type should be declared");
        }
        switch (type) {
            case "first":
                return new SomeServiceFirstImpl();
            case "second":
                return new SomeServiceSecondImpl();
            default:
                throw new IncorrectTypeException("Unsupported service type. 'first' and 'second' types supported only");
        }
    }
}