package com.block.core.ibatis.util;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GenericsUtils {

	/**
	 * 获取当前类的第几层继承
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz, int index) {

		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {

			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {

			throw new RuntimeException("�����������"
					+ (index < 0 ? "����С��0" : "�����˲���������"));
		}
		if (!(params[index] instanceof Class)) {

			return Object.class;
		}
		return (Class<?>) params[index];
	}

	/**
	 * 获取分类
	 * @param clazz
	 * @return
	 */
	public static Class<?> getSuperClassGenricType(Class<?> clazz) {

		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 获取返回值的继承层次
	 * @param method
	 * @param index
	 * @return
	 */
	public static Class<?> getMethodGenericReturnType(Method method, int index) {

		Type returnType = method.getGenericReturnType();

		if (returnType instanceof ParameterizedType) {

			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();

			if (index >= typeArguments.length || index < 0) {

				throw new RuntimeException("�����������"
						+ (index < 0 ? "����С��0" : "�����˲���������"));
			}
			return (Class<?>) typeArguments[index];
		}
		return Object.class;
	}

	/**
	 * 获取分类的继承层次
	 * @param method
	 * @return
	 */
	public static Class<?> getMethodGenericReturnType(Method method) {

		return getMethodGenericReturnType(method, 0);
	}

	/**
	 * 获取方法的参数的类型
	 * @param method
	 * @param index
	 * @return
	 */
	public static List<Class<?>> getMethodGenericParameterTypes(Method method,
			int index) {

		List<Class<?>> results = new ArrayList<Class<?>>();
		Type[] genericParameterTypes = method.getGenericParameterTypes();

		if (index >= genericParameterTypes.length || index < 0) {

			throw new RuntimeException("�����������"
					+ (index < 0 ? "����С��0" : "�����˲���������"));
		}
		Type genericParameterType = genericParameterTypes[index];

		if (genericParameterType instanceof ParameterizedType) {

			ParameterizedType aType = (ParameterizedType) genericParameterType;
			Type[] parameterArgTypes = aType.getActualTypeArguments();
			for (Type parameterArgType : parameterArgTypes) {
				Class<?> parameterArgClass = (Class<?>) parameterArgType;
				results.add(parameterArgClass);
			}
			return results;
		}
		return results;
	}

	/**
	 * 获取第一个参数类型
	 * @param method
	 * @return
	 */
	public static List<Class<?>> getMethodGenericParameterTypes(Method method) {

		return getMethodGenericParameterTypes(method, 0);
	}

	/**
	 * 获取字段的类型
	 * @param field
	 * @param index
	 * @return
	 */
	public static Class<?> getFieldGenericType(Field field, int index) {

		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {

			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			if (index >= fieldArgTypes.length || index < 0) {

				throw new RuntimeException("�����������"
						+ (index < 0 ? "����С��0" : "�����˲���������"));
			}
			return (Class<?>) fieldArgTypes[index];
		}
		return Object.class;
	}

	/**
	 * 获取第一个字段的类型
	 * @param field
	 * @return
	 */
	public static Class<?> getFieldGenericType(Field field) {

		return getFieldGenericType(field, 0);
	}
}
