package cn.wanda.test_base;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wanda.entity.User;

public class TestCUD {

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
			//增加
			
			User user = new User(); 
			user.setUsername("001");
			user.setPassword("123456");
			session.insert("insertUser", user);
			//session.insert("user.insertUser", user);//命名空间的方式
			System.out.println(user.getId());
			
			
			//更新
			/*
			User user = new User();
			user.setUsername("001");
			user.setPassword("666666");
			System.out.println(session.update("updateUser", user)); //返回的是更新的行数
			*/
			
			//删除
			/*
			User user = new User();
			user.setUsername("001");
			System.out.println(session.delete("deleteUser", user)); //返回的是更新的行数
			*/
			
			session.commit();
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
