package cn.originstar.yourjob.core.validation;

import javax.validation.ConstraintValidatorContext;

public interface Validatable {

    public boolean isValid(ConstraintValidatorContext constraintValidatorContext);

}
