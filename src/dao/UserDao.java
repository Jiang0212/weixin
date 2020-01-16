package dao;

import domain.User;

public interface UserDao {

	User checkLogin(String username, String password);
}
