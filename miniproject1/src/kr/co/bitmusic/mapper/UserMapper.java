package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.Search;
import kr.co.bitmusic.domain.User;

public interface UserMapper {
	
	List<User> selectUser();
	
	void insertUser (User user);
	
	int updateUser (Search search);

	int deleteUser (String id);
	
	List<User> loginUser(User user);
}
