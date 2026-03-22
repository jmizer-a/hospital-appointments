package pl.edu.pw.bd.hospital.entity.constraints;

import java.lang.annotation.*;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ArcValidator.class)
@Documented
public @interface Arc {

    String message() default "{pl.edu.pw.bd.hospital.entity.constraints.Arc message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<?> value();

    String field1();

    String field2();

    @Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Arc[] value();
    }
}