package Centric.ShoppingStoreTestComponents;

import java.lang.reflect.Constructor;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.github.dockerjava.transport.DockerHttpClient.Request.Method;

public class TestRetryAnalyzerListener implements IAnnotationTransformer {
	
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(Retry.class);
	}

}
