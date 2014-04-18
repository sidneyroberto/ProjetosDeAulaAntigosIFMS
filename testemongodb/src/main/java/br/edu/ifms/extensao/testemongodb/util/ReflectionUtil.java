package br.edu.ifms.extensao.testemongodb.util;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Classe com utilidades de reflex�o de classes e m�todos.
 * 
 * @author Sidney Sousa
 */
public class ReflectionUtil {

	/**
	 * Retorna a classe gen�rica apontada pelo �ndice <code>index</code> do
	 * objeto <code>obj</code>.
	 * 
	 * @param obj
	 *            Objeto o qual se quer obter sua classe gen�rica.
	 * @param index
	 *            �ndice da classe gen�rica do objeto.
	 * @return A classe gen�rica do objeto no �ndice indicado.
	 */
	public static Class<?> getGenericClass(Object obj, int index) {
		return getGenericClass(obj.getClass(), index);
	}

	/**
	 * Retorna a classe gen�rica apontada pelo �ndice <code>index</code> da
	 * classe <code>clazz</code>.
	 * 
	 * @param clazz
	 *            Classe a qual se quer obter sua classe gen�rica.
	 * @param index
	 *            �ndice da classe gen�rica da classe consultada.
	 * @return A classe gen�rica do classe consultada no �ndice indicado.
	 */
	public static Class<?> getGenericClass(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (genType instanceof ParameterizedType) {
			ParameterizedType pramType = (ParameterizedType) genType;
			Type[] params = pramType.getActualTypeArguments();
			if ((params != null) && (params.length > index)) {
				return (Class<?>) params[index];
			}
		}
		return null;
	}

	/**
	 * Retorna o m�todo getter do campo <code>fieldName</code> da classe
	 * <code>clazz</code>.
	 * 
	 * @param clazz
	 *            Classe do m�todo getter a ser retornado.
	 * @param fieldName
	 *            Nome do campo referente ao m�todo getter.
	 * @return O m�todo getter referente.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method getGetterMethod(Class<?> clazz, String fieldName)
			throws NoSuchMethodException, SecurityException {
		String name = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		return clazz.getMethod(name);
	}

	/**
	 * Retorna o m�todo setter do campo <code>fieldName</code> da classe
	 * <code>clazz</code>, o qual recebe os par�metros indicados em
	 * <code>paramTypes</code>.
	 * 
	 * @param clazz
	 *            Classe do m�todo setter a ser retornado.
	 * @param fieldName
	 *            Nome do campo referente ao m�todo setter.
	 * @param paramTypes
	 *            Os tipos de par�metros o qual o m�todo setter recebe.
	 * @return O m�todo setter referente.
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static Method getSetterMethod(Class<?> clazz, String fieldName,
			Class<?>... paramTypes) throws NoSuchMethodException,
			SecurityException {
		String name = "set" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		return clazz.getMethod(name, paramTypes);
	}

}
