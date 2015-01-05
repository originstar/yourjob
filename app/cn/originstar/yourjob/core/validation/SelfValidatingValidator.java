package cn.originstar.yourjob.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SelfValidatingValidator implements ConstraintValidator<SelfValidating, Validatable> {

    public void initialize(SelfValidating constraintAnnotation) {}

    public boolean isValid(Validatable value, ConstraintValidatorContext constraintValidatorContext) {
        return value.isValid(constraintValidatorContext);
    }

}
