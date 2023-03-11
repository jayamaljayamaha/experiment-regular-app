package com.experiment.regular.annotation.implementations;



import com.experiment.regular.annotation.annotations.DeviceValidator;
import com.experiment.regular.common.Device;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValidateDevice implements ConstraintValidator<DeviceValidator, String> {

    @Override
    public boolean isValid(String capturedBy, ConstraintValidatorContext context) {
        return Arrays.stream(Device.values()).map(Enum::name).toList().contains(capturedBy);
    }
}
