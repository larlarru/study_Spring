package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	
	// 클래스가 로딩되는거라 객체가 만들어진 시점이랑 전혀 다름 그래서 static해줌
	static SqlSessionFactory sqlSessionFactory;
	
	// class 로딩시 실행되는 블럭
	static {
		try {
			String resource = "kr/or/ddit/config/mybatis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSession( ) {
		return sqlSessionFactory.openSession();
	}
	
}
