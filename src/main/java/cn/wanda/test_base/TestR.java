package cn.wanda.test_base;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.wanda.entity.User;

/**
 * 测试简单查询
 * 
 * @author wsdhr
 *
 */
public class TestR {

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
		session = sqlMapper.openSession();
		try {
			
			// hashmap传入参数测试
			/*
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("username", "001");
			hm.put("password", "123456");
			User temp = session.selectOne("loginSelect", hm);
			System.out.println(temp);
			if (temp != null) {
				System.out.println("登陆成功！");
			}
			*/
			
			// 对象传入参数测试
			
			User user = new User();
			user.setUsername("001");
			user.setPassword("123456");
			User temp2 = session.selectOne("loginSelect2", user);
			if (temp2 != null) {
				System.out.println("登陆成功！");
			}
			

			// 多行记录
			List<User> listUser = null;
			
			listUser=session.selectList("selectUserList");
			for (User one : listUser) {
				System.out.println("id=" + one.getId());
				System.out.println("username=" + one.getUsername());
				System.out.println("password=" + one.getPassword());
			}
			
			
			listUser = session.selectList("selectUserList2");
			for (User one : listUser) {
				System.out.println("id=" + one.getId());
				System.out.println("username=" + one.getUsername());
				System.out.println("password=" + one.getPassword());
			}
			
			
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();// 回滚
		} finally {
			session.close();// 关闭session
		}
	}

}
