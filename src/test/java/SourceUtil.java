import lombok.SneakyThrows;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class SourceUtil {
	@SneakyThrows
	public static void main(String[] args) {
		printClassInfoToFile(ClassLoader.getSystemClassLoader().getClass(), new File("_test/ClassLoader.txt"));
	}
	
	public static void printClassInfoToFile(Class<?> clazz, File file) throws IOException {
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		} else file.delete();
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.append(getAnnotationsFromClass(clazz));
		fileWriter.append("\n");
		fileWriter.append(getClassDeclaration(clazz));
		fileWriter.append("\n");
		fileWriter.append(getFieldsFromClass(clazz));
		fileWriter.append("\n");
		fileWriter.append(getConstructorsFromClass(clazz));
		fileWriter.append("\n");
		fileWriter.append(getMethodsFromClass(clazz));
		fileWriter.append("\n}");
		fileWriter.flush();
		fileWriter.close();
	}
	
	public static String getConstructorsFromClass(Class<?> clazz) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Constructor c : clazz.getConstructors()) {
			stringBuilder.append("	");
			stringBuilder.append(Modifier.toString(c.getModifiers())).append(" ");
			stringBuilder.append(c.getName()).append("(");
			for (Parameter p : c.getParameters()) {
				stringBuilder.append(p.getType().getSimpleName()).append(" ").append(p.getName());
			}
			stringBuilder.append(");\n");
		}
		return (stringBuilder.toString());
	}
	
	public static String getAnnotationsFromClass(Class<?> clazz) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Annotation a : clazz.getAnnotations()) {
			stringBuilder.append("@"+a.getClass().getSimpleName()+"(?)\n");
		}
		return (stringBuilder.toString());
	}
	
	public static String getClassDeclaration(Class<?> clazz) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Modifier.toString(clazz.getModifiers())).append(" "+clazz.getSimpleName());
		if (clazz.getInterfaces().length > 0)
			stringBuilder.append(" implements");
		for(Class<?> i : clazz.getInterfaces()) {
			stringBuilder.append(" , "+i.getSimpleName());
		}
		if (clazz.getSuperclass() != null && !clazz.getSuperclass().getTypeName().equals("java.lang.Object"))
			stringBuilder.append(" extends "+clazz.getSuperclass().getTypeName());
		
		stringBuilder.append("{ ");
		return (stringBuilder.toString().replace("implements , ", "implements "));
	}
	
	public static String getMethodsFromClass(Class<?> clazz) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Method m : clazz.getMethods()) {
//			m.setAccessible(true);
			stringBuilder.append("	");
			stringBuilder.append(Modifier.toString(m.getModifiers())).append(" ");
			stringBuilder.append(m.getName()).append("(");
			for (Parameter p : m.getParameters()) {
				stringBuilder.append(p.getParameterizedType().getTypeName()).append(" ").append(p.getName()+", ");
			}
			stringBuilder.append(") : ");
			stringBuilder.append(m.getReturnType().getSimpleName()).append(";\n");
		}
		return (stringBuilder.toString());
	}

	public static String getFieldsFromClass(Class<?> clazz) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Field f : clazz.getFields()) {
			f.setAccessible(true);
			stringBuilder.append("	");
			stringBuilder.append(Modifier.toString(f.getModifiers())).append(" ");
			stringBuilder.append(f.getType().getSimpleName()).append(" ");
			stringBuilder.append(f.getName());
			stringBuilder.append(";\n");
		}
		return (stringBuilder.toString());
	}
}
