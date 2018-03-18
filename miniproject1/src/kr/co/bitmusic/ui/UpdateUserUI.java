package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class UpdateUserUI extends BaseBitMusicUI {
	
	User saved = Session.getUser();
	
	public void service() {
			while (true) {
				User user = new User();
				switch (menu()) {
				case 1:
					user.setId(saved.getId());
					user.setPassword(getStr("변경할 비밀번호를 입력하세요 : "));
					((UserMapper)Session.getMapper("userMapper")).updateUserPassword(user);
					break;
				case 2: 
					user.setId(saved.getId());
					user.setPasswordHint(getStr("변경할 비밀번호 힌트를 입력하세요 : "));
					((UserMapper)Session.getMapper("userMapper")).updateUserPasswordHint(user);
					break;
				case 3: 
					user.setId(saved.getId());
					user.setEmail(getStr("변경할 이메일을 입력하세요 : "));
					((UserMapper)Session.getMapper("userMapper")).updateUserEmail(user);
					break;
				case 0: returnToFormerStep(); break;
				}
				System.out.println("회원정보가 수정되었습니다");
				selectUser();
			}
	}
	
	private int menu() {
		System.out.println();
		System.out.println("[1.비밀번호 변경] [2.비밀번호 힌트 변경] [3.이메일 변경] [0.뒤로가기]");
		return getInt("수정할 메뉴번호를 입력하세요 : ");
	}
	
	public void returnToFormerStep() {
		MyMusicUI mui = new MyMusicUI();
		mui.service();
	}
	
	public void selectUser() {
		SelectUserUI sui = new SelectUserUI();
		sui.service();
	}

}
