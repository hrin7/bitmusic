package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class DeleteUserUI extends BaseBitMusicUI {
	private User saved = Session.getUser();
	
	public void service() {
		User user = new User();
		int result = (getInt("정말 탈퇴하시겠습니까? 1.탈퇴 2.취소 : "));
		if(result == 1) {
			user.setId(saved.getId());
			((UserMapper)Session.getMapper("userMapper")).deleteMyMusicId(user);
			((UserMapper)Session.getMapper("userMapper")).deleteUser(user);
			System.out.println("회원탈퇴가 완료되었습니다.");
			returnToMain();
		}else {
			System.out.println("탈퇴를 취소하였습니다.");
		}
	}

	public void returnToMain() {
		new BitMusicUI().service();
	}
}
