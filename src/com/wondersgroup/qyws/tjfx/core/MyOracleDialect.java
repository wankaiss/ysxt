package com.wondersgroup.qyws.tjfx.core;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.OracleDialect;

@SuppressWarnings("deprecation")
public class MyOracleDialect extends OracleDialect {

	public MyOracleDialect() {
		super();
		registerHibernateType(Types.CHAR, Hibernate.STRING.getName());   
	}

}
