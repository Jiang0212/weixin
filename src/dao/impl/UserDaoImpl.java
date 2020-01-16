package dao.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import dao.UserDao;
import domain.User;
import util.JDBCUtils;

public class UserDaoImpl implements UserDao{
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	/**
	 * 验证用户信息
	 */
	@Override
	public User checkLogin(String username,String password) {
		String sql = "select * from admin where user = ? and password = ?";
		try {
			List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
			User user = null;
			// 判断集合对象是否为null 并且长度大于0
	        if (users != null && users.size() > 0) {
	            user = users.get(0); // 取第一个值
	        }
	        return user;
			
		} catch (EmptyResultDataAccessException e) {
			System.out.println("false");
            return null;
		}
	}

}
