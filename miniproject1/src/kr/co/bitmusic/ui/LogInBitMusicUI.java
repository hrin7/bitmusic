package kr.co.bitmusic.ui;

import java.util.List;

import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class LogInBitMusicUI extends BaseBitMusicUI {
	UserMapper userMapper;
	

	public LogInBitMusicUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	public LogInBitMusicUI() {}
	
	public void service() {
		User user = new User();
		user.setId(getStr("ID를 입력하세요 : "));
		user.setPassword(getStr("Password를 입력하세요 : "));
		
		List<User> list = userMapper.loginUser(user);
		
		BaseBitMusicUI ui = null;
		for (int i = 0; i < list.size(); i++) {
			User u = list.get(i);
			if (u.getId().equals("admin") && u.getPassword().equals("admin")) {
				ui = new AdminUI();
			} else if (u.getId().equals(user.getId()) && u.getPassword().equals(user.getPassword())) {
				ui = new MyMusicUI();
			} 
			ui.service();
		}
		System.out.println("ID나 Password를 확인해주세요");
	}
}
