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
		
//		BaseBitMusicUI ui = 
		for (int i = 0; i < list.size(); i++) {
			User u = list.get(i);
			if (u.getId().equals("admin") && u.getPassword().equals("admin")) {
				System.out.println("ID나 Password를 확인해주세요");
			} else if (u.getId().equals("admin") && u.getPassword().equals("admin")) {
				AdminUI ui = new AdminUI();
				ui.service();
			} else {
				MyMusicUI ui = new MyMusicUI();
				ui.service();
			}
		}
	}
}
