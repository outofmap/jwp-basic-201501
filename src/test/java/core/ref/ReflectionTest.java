package core.ref;

import next.model.Question;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionTest {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
	// Question 클래스의 모든 필드, 생성자, 메소드를 출력한다.
	// src/test/java 의 core.ref.ReflectionTest의 showClass() 메서드를 구현한다.

	@Test
	public void showClass() {
		Class<Question> clazz = Question.class;
		logger.debug(clazz.getName());
		// All field of Question
		Field[] fields = clazz.getDeclaredFields();
		Constructor[] constructors = clazz.getConstructors();
		Method[] methods = clazz.getMethods();
		
		for(int i=0; i < fields.length; i++){
			logger.debug("field"+(i+1)+":"+fields[i].toString());
			
		}
		
		for(int i=0; i < constructors.length; i++){
			logger.debug("constructor"+(i+1)+":"+constructors[i].toString());
			
		}
		for(int i=0; i < methods.length; i++){
			logger.debug("method"+(i+1)+":"+methods[i].toString());
			
		}
	}
}
