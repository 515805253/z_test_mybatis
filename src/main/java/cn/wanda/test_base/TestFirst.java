package cn.wanda.test_base;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wanda.entity.Author;
import cn.wanda.entity.User;

public class TestFirst {

	public static void main(String[] args) {
		String resource = "mybatis.xml";
		Reader reader = null;
		SqlSession session;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder()
				.build(reader);
		session = sqlMapper.openSession();//默认情况下事务是开启的，必须commit才可以提交
		try{
			User temp = session.selectOne("user.findById", 51);
			System.out.println(temp);
			
			Author temp2 = session.selectOne("author.findById", 9);
			System.out.println(temp2);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			session.rollback();//回滚
		}
		finally
		{
			session.close();//关闭session
		}
	}
}
