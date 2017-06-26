package org.hibernate

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.SimpleType
import groovy.transform.CompileStatic
import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.usertype.ParameterizedType
import org.hibernate.usertype.UserType

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types

@CompileStatic
class JsonUserType implements UserType, ParameterizedType {

	private static final int[] SQL_TYPES = [Types.LONGVARCHAR] as int[]
	private Class<?> returnedClass;

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		} else if (x == null || y == null) {
			return false;
		} else {
			return x.equals(y);
		}
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return null == x ? 0 : x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if(value == null) {
			st.setNull(index, Types.VARCHAR)
		} else {
			String s = convertObjectToJson(value)
			st.setString(index, s)
		}
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner) throws HibernateException, SQLException {
		String value = rs.getString(names[0])
		def result = null
		if (value != null && !value.equals("")) {
			try {
				result = convertJsonToObject(value)
			} catch (IOException e) {
				throw new HibernateException("Exception deserializing value " + value, e);
			}
		}
		return result;
	}

	Object convertJsonToObject(String content) {
		if ((content == null) || (content.isEmpty())) {
			return null;
		}
		try {
			ObjectMapper mapper = new ObjectMapper()
			mapper.enableDefaultTyping()
			JavaType type = createJavaType(mapper)
			if (type == null)
				return mapper.readValue(content, returnedClass);

			return mapper.readValue(content, type);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	String convertObjectToJson(Object object) {
		try {
			ObjectMapper mapper = new ObjectMapper()
			mapper.enableDefaultTyping()
			mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		String json = convertObjectToJson(value);
		return convertJsonToObject(json);
	}

	/**
	 * Optionnal
	 */
	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return deepCopy(original);
	}

	/**
	 * (optional operation)
	 *
	 * @param value
	 *
	 * @throws HibernateException
	 */
	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) deepCopy(value);
	}

	/**
	 * (optional operation)
	 *
	 * @param cached
	 * @param owner
	 *
	 * @return the instance cached
	 *
	 * @throws HibernateException
	 */
	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return deepCopy(cached);
	}

	/**
	 * By default we are expecting to use a simple object / not a collection (Set, List)
	 *
	 * @param mapper : instance jackson object mapper
	 *
	 * @return A jackson JavaType to specify wich object represent the json string representation
	 *
	 */
	public JavaType createJavaType(ObjectMapper mapper) {
		try {
			return SimpleType.construct(returnedClass());
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public void setParameterValues(Properties parameters) {
			this.returnedClass = Class.forName(parameters.getProperty('clazz'))

	}

	@Override
	public Class<?> returnedClass() {
		return this.returnedClass;
	}

}