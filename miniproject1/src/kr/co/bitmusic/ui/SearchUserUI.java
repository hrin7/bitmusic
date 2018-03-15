package kr.co.bitmusic.ui;

import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class SearchUserUI extends BaseBitMusicUI {
	private UserMapper userMapper;
	public SearchUserUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public void service() {
		User user = new User();
		while(true) {
			switch(menu() ) {
			case 1 :
				System.out.println("ID를 찾기 위해 이름과 이메일을 입력해 주세요 : ");
				user.setName(getStr("이름 : "));
				user.setEmail(getStr("이메일 : "));
				User id = userMapper.selectUserSearchId(user);
				System.out.println("아이디는 " + id.getId() + "입니다.");
				break;
			case 2 :
				System.out.println("비밀번호를 찾기 위해 아이디와 이메일을 입력해 주세요 : ");
				user.setId(getStr("아이디 : "));
				user.setEmail(getStr("이메일 : "));
				User pw = userMapper.selectUserSearchPassword(user);
				System.out.println("비밀번호는 " + pw.getPassword() + "입니다.");
				break;
			}
		}
	}
	
	private int menu() {
		System.out.println("1. ID 찾기");
		System.out.println("2. 비밀번호 찾기");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}
	
	public void returnToMain() {
		BitMusicUI bmu = new BitMusicUI();
		bmu.service();
	}

}
