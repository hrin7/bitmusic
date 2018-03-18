package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class DeleteUserUI extends BaseBitMusicUI {
	
	User saved = Session.getUser();
	
	public void service() {
		User user = new User();
		user.setPassword(getStr("정말 탈퇴하시겠습니까? 비밀번호를 한 번 더 입력해 주세요 : "));
		if(user.getPassword()==saved.getPassword()) {
			user.setId(saved.getId());
			((UserMapper)Session.getMapper("userMapper")).deleteUser(user);
			System.out.println("회원탈퇴가 완료되었습니다.");
			returnToMain();
		}
	}

	public void returnToMain() {
		new BitMusicUI().service();
	}
}
