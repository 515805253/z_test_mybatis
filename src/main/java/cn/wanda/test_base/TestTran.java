package cn.wanda.test_base;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wanda.entity.Author;
import cn.wanda.entity.User;
/**
 * 事务测试
 * @author wsdhr
 *
 */
public class TestTran {

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
		session = sqlMapper.openSession(false);//不使用自动提交，默认也是这种形式
		try{
			
			User one = new User();
			one.setUsername("001");
			one.setPassword("123456");
			session.insert("insertUser", one);
			System.out.println("新加入的user id=" + one.getId());

			Author author = new Author();
			author.setUser(one);
			author.setRealName("一个大牛");
			session.insert("insertAuthor", author);
			System.out.println("新加入的author id=" + author.getId());
			
			session.commit();//事务提交

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
