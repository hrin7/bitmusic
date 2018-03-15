package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.UserMapper;

public class LogInBitMusicUI extends BaseBitMusicUI {
	UserMapper userMapper;
	MusicMapper musicMapper;
	
	public LogInBitMusicUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	public LogInBitMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	public LogInBitMusicUI() {}
	
	public void service() {
		User user = new User();
		user.setId(getStr("ID를 입력하세요 : "));
		user.setPassword(getStr("Password를 입력하세요 : "));
		if (user.getId().equals("admin") && user.getPassword().equals("admin")) {
			new AdminUI(userMapper, musicMapper).service();
			Session.setUser(user);
			return;
		}
		
		User u = userMapper.loginUser(user);
		if(u == null) {
			System.out.println("ID나 Password를 확인해주세요");
			return;
		}
		
		Session.setUser(u);
		new MyMusicUI(userMapper).service();
		
	}
}
