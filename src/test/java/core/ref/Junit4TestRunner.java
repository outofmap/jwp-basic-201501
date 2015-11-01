package core.ref;

import java.lang.reflect.Method;

import org.junit.Test;

public class Junit4TestRunner {
	@Test
	public void run() throws Exception {
		Class<Junit4Test> clazz = Junit4Test.class;
		Object obj = clazz.newInstance();
		Method[] methods = clazz.getMethods();
		
		for(Method method : methods){
			MyTest annotation = method.getAnnotation(MyTest.class);
			if(annotation != null){
				method.invoke(obj, null);
			}
		}

	}
}
