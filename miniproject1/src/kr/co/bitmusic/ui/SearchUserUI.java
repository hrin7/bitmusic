package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class SearchUserUI extends BaseBitMusicUI {
	
	public void service() {
		User user = new User();
		try {
			while(true) {
				switch(menu() ) {
				case 1 :
					System.out.println();
					System.out.println("ID를 찾기 위해 이름과 이메일을 입력해 주세요");
					user.setName(getStr("이름 : "));
					user.setEmail(getStr("이메일 : "));
					User id = ((UserMapper)Session.getMapper("userMapper")).selectUserSearchId(user);
					System.out.println("아이디는 " + id.getId() + "입니다.");
					break;
				case 2 :
					System.out.println();
					System.out.println("비밀번호를 찾기 위해 아이디와 이메일을 입력해 주세요");
					user.setId(getStr("아이디 : "));
					user.setEmail(getStr("이메일 : "));
					User pw = ((UserMapper)Session.getMapper("userMapper")).selectUserSearchPassword(user);
					System.out.println("비밀번호는 " + pw.getPassword() + "입니다.");
					break;
				case 0 :
					new BitMusicUI().service();
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("잘못입력하였습니다. 다시 입력해 주세요.");
			menu();
		}
	}

	private int menu() {
		System.out.println();
		System.out.println("[1.ID 찾기]  [2.비밀번호 찾기]  [0.뒤로가기]");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}

}
