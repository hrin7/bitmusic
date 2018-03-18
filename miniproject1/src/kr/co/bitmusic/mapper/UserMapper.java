package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.User;

public interface UserMapper {
	
	List<User> selectUser();
	
	void insertUser (User user);
	
	void updateUserPassword(User password);

	void updateUserPasswordHint(User passwordHint);
	
	void updateUserEmail(User email);

	void deleteUser (User delete);
	
	User loginUser(User user);
	
	User selectUserSearchId(User user);

	User selectUserSearchPassword(User user);

}
