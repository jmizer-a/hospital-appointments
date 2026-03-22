package pl.edu.pw.bd.hospital.entity.constraints;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class ArcValidator implements ConstraintValidator<Arc, Object> {

    private Method getter1;
    private Method getter2;
    private Class<?> class_;

    @Override
    public void initialize(Arc constraintAnnotation) {
        this.class_ = constraintAnnotation.value();
        try {
            this.getter1 = this.class_.getMethod(constraintAnnotation.field1());
            this.getter2 = this.class_.getMethod(constraintAnnotation.field2());
        } catch (NoSuchMethodException e) { throw new RuntimeException(); }
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintContext) {
        if ( object == null ) {
            return true;
        }
        Object value1, value2;
        try {
            value1 = getter1.invoke(object);
            value2 = getter2.invoke(object);
        } catch (IllegalAccessException e) { throw new RuntimeException(); }
        catch (InvocationTargetException e) { throw new RuntimeException(); }
        return value1 != null && value2 == null
            || value1 == null && value2 != null;
    }
}