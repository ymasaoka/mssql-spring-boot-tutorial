package jp.mappiekochi.sample.firstmssqlproject.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jp.mappiekochi.sample.firstmssqlproject.validation.PhoneValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@ReportAsSingleViolation
public @interface Phone {
    String message() default "電話番号を入力してください";
    boolean onlyNumber() default false;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
