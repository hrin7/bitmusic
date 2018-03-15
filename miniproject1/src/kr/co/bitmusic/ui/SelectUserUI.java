package kr.co.bitmusic.ui;

import java.text.SimpleDateFormat;
import java.util.List;

import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class SelectUserUI extends BaseBitMusicUI {
	private UserMapper userMapper;
	public SelectUserUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public void service() {
		List<User> userList = userMapper.selectUser();
		System.out.printf("전체 %d명\n", userList.size());
		System.out.println("-------------------------");
		System.out.println("아이디\t\t이름\t\t나이\t\t성별\t\t이메일\t\t가입일");
		System.out.println("-------------------------");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		for(User u : userList) {
			System.out.printf("%s\t\t%s\t\t%d\t\t%s\t\t%d\n",
					u.getId(), u.getName(), u.getAge(), u.getGender(), u.getEmail(), sdf.format(u.getJoinDate()));
		}
		if(userList.isEmpty()) {
			System.out.println("회원이 존재하지 않습니다.");
		}
		System.out.println("-------------------------");
	}
}
