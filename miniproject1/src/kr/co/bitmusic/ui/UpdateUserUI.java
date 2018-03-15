package kr.co.bitmusic.ui;

import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class UpdateUserUI extends BaseBitMusicUI {
	
	private UserMapper userMapper;
	public UpdateUserUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public void service() {
			BaseBitMusicUI ui = null;
			BitMusicUI bmu = null;
			while (true) {
				switch (menu()) {
				case 1: UpdateUserUI(SearchType = id); break;
				case 2: ; break;
				case 3: ; break;
				case 4: ; break;
				case 0: bmu.service(); break;
				}
				ui.service();
			}
	}
	
	private int menu() {
		System.out.println("1. Id 변경");
		System.out.println("2. Password 변경");
		System.out.println("3. Password Hint 변경");
		System.out.println("4. 이메일 변경");
		System.out.println("0. main메뉴로 돌아가기");
		return getInt("수정할 메뉴번호를 입력하세요 : ");
	}

}
