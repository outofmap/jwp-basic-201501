package core.ref;

import java.lang.reflect.Method;

import org.junit.Test;

public class Junit3TestRunner {
	@Test
	public void run() throws Exception {
		Class<Junit3Test> clazz = Junit3Test.class;
		Object obj = clazz.newInstance();
		Method[] methods = clazz.getMethods();
		String methodName = null;
		
		for(int i =0; i<methods.length; i++){
			methodName = methods[i].getName();
			if(methodName.indexOf("test") == 0){
				methods[i].invoke(obj, null);
			}
		}

	}
}
