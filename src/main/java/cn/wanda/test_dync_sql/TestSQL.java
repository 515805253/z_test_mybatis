package cn.wanda.test_dync_sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wanda.entity.Reader;
import cn.wanda.entity.User;

public class TestSQL {

	public static void main(String[] args) {
		String resource = "mybatis.xml";
		java.io.Reader reader = null;
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
			//if
			/*
			Reader oneReader = new Reader();
			//oneReader.setMoney(200);
			List<Reader> list = session.selectList("selectReaderMoney", oneReader);
			for (Reader temp : list) {
				System.out.println("用户ID=" + temp.getId());
			}
			*/
			
			//choose
			/*
			User oneUser=new User();
			oneUser.setId(10);
			oneUser.setUsername("%0%");
			
			List<User> list = session.selectList("selectUserChoose", oneUser);
			for (User temp : list) {
				System.out.println("用户ID=" + temp.getId() + "用户名=" + temp.getUsername());
			}
			*/
			
			//where
			/*
			User oneUser=new User();
			//oneUser.setId(10);
			oneUser.setUsername("%0%");
			List<User> list = session.selectList("selectUserWhere", oneUser);
			for (User temp : list) {
				System.out.println("用户ID=" + temp.getId() + "用户名=" + temp.getUsername());
			}
			*/
			
			//set
			/*
			User oneUser=new User();
			oneUser.setId(10);
			oneUser.setUsername("setchange");
			oneUser.setPassword("8899");
			session.update("updateUserSet",oneUser);
			*/
			
			//trim
			/*
			User oneUser=new User();
			oneUser.setId(60);
			oneUser.setUsername("setchange");
			//oneUser.setPassword("8899");
			session.update("updateUserTrim",oneUser);
			*/
			
			//foreach 循环查询
			/*
			ArrayList<Integer> ides=new ArrayList<Integer>();
			ides.add(10);
			ides.add(8);
			ides.add(9);
			List<User> ap=session.selectList("selectUserForeach", ides);
			for(User temp:ap) {		
				System.out.println("用户ID="+temp.getId()+"用户名="+temp.getUsername());
			}
			*/
			
			//foreach 循环赋值
			ArrayList<User> jkuList = new ArrayList<User>();
			User one = new User("jt1", "8866");
			User two = new User("jt2", "8866");
			jkuList.add(one);
			jkuList.add(two);
			session.insert("insertUserForeach", jkuList);
			
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
