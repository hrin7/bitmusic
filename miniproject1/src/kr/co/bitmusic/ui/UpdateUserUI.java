package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class UpdateUserUI extends BaseBitMusicUI {
	
	private User saved = Session.getUser();
	private UserMapper userMapper;
	public UpdateUserUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public void service() {
		 User user = new User();
			while (true) {
				switch (menu()) {
				case 1:
					user.setId(saved.getId());
					user.setPassword(getStr("변경할 비밀번호를 입력하세요 : "));
					userMapper.updateUserPassword(user);
					break;
				case 2: 
					user.setId(saved.getId());
					user.setPasswordHint(getStr("변경할 비밀번호 힌트를 입력하세요 : "));
					userMapper.updateUserPasswordHint(user);
					break;
				case 3: 
					user.setId(saved.getId());
					user.setEmail(getStr("변경할 이메일을 입력하세요 : "));
					userMapper.updateUserEmail(user);
					break;
				case 0: returnToMain(); break;
				}
				System.out.println("회원정보가 수정되었습니다");
			}
	}
	
	private int menu() {
		System.out.println("1. 비밀번호 변경");
		System.out.println("2. 비밀번호 힌트 변경");
		System.out.println("3. 이메일 변경");
		System.out.println("0. 메인메뉴로 돌아가기");
		return getInt("수정할 메뉴번호를 입력하세요 : ");
	}
	
	public void returnToMain() {
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}

}
