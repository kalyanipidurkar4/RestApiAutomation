package utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation,
                          Class testClass,
                          Constructor testConstructor,
                          Method testMethod) {



        // Attach RetryAnalyzer to all tests if not already set
        if (annotation.getRetryAnalyzerClass()== null) {
            annotation.setRetryAnalyzer(FailRetry.class);
        }
    }
}
