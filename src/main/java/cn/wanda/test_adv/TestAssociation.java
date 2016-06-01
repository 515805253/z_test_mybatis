package cn.wanda.test_adv;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wanda.entity.Author;
public class TestAssociation {

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
			/*
			List<Author> list = session.selectList("selectAuthor");//联合查询，默认构造函数
			for(Author temp:list)
			{
				System.out.println("author="+temp); 
			}*/
			
			/*
			List<Author> list2=session.selectList("selectAuthorByCon");//联合查询，自定义构造函数
			for(Author temp:list2)
			{
				System.out.println("author="+temp); 
			}
			*/
			
			List<Author> list3=session.selectList("selectAuthorBySub");//子查询
			for(Author temp:list3)
			{
				System.out.println("author="+temp.getRealName());
				if (temp.getUser()!=null) {
					System.out.println("user="+temp.getUser().getUsername());
				}
			}
			
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
