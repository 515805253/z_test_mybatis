package cn.wanda.test_adv;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wanda.entity.User;
import cn.wanda.entity.Visit;
/**
 * 测试集合查询
 * @author wsdhr
 *
 */
public class TestCollection {

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
			
			List<User> list = session.selectList("selectVisit");
			for (User temp : list) {
				System.out.println("用户名=" + temp);
				for (Visit one : temp.getVisitList()) {
					System.out.println(one);
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
