package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class SearchUserUI extends BaseBitMusicUI {
	
	public void service() {
			while(true) {
				switch(menu() ) {
				case 1 :
					searchWithId();
					break;
				case 2 :
					searchWithPassword();
					break;
				case 0 :
					new BitMusicUI().service();
					break;
				}
			}
	}

	private int menu() {
		System.out.println();
		System.out.println("[1.ID 찾기]  [2.비밀번호 찾기]  [0.뒤로가기]");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}
	
	public void searchWithId() {
		try {
			User user = new User();
			System.out.println();
			System.out.println("ID를 찾기 위해 이름과 이메일을 입력해 주세요");
			user.setName(getStr("이름 : "));
			user.setEmail(getStr("이메일 : "));
			User id = ((UserMapper)Session.getMapper("userMapper")).selectUserSearchId(user);
			System.out.println("아이디는 " + id.getId() + "입니다.");
		} catch (Exception e) {
			System.out.println("다시 입력해 주세요.");
			searchWithId();
		}
	}
	
	public void searchWithPassword() {
		try {
			User user = new User();
			System.out.println();
			System.out.println("비밀번호를 찾기 위해 아이디와 이메일을 입력해 주세요");
			user.setId(getStr("아이디 : "));
			user.setPasswordHint(getStr("비밀번호 힌트 : "));
			User pw = ((UserMapper)Session.getMapper("userMapper")).selectUserSearchPassword(user);
			System.out.println("비밀번호는 " + pw.getPassword() + "입니다.");
		} catch (Exception e) {
			System.out.println("다시 입력해 주세요.");
			searchWithPassword();
		}
	}

}
