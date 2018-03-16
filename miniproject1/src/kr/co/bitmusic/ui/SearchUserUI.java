package kr.co.bitmusic.ui;

import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class SearchUserUI extends BaseBitMusicUI {
	private UserMapper userMapper;

	public SearchUserUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public void service() {

		try {
			User user = new User();
			while (true) {
				switch (menu()) {
				case 1:
					System.out.println("ID를 찾기 위해 이름과 이메일을 입력해 주세요 : ");
					user.setName(getStr("이름 : "));
					user.setEmail(getStr("이메일 : "));
					User id = userMapper.selectUserSearchId(user);
//					if(user.getName().equals(anObject)) {
//						System.out.println("존재하지 않는 이름입니다.");
//					} 
//					if(id.getEmail()==null) {
//						System.out.println("존재하지 않는 이메일입니다.");
//					}
					System.out.println("아이디는 " + id.getId() + "입니다.");
					break;
				case 2:
					System.out.println("비밀번호를 찾기 위해 아이디와 이메일을 입력해 주세요 : ");
					user.setId(getStr("아이디 : "));
					user.setEmail(getStr("이메일 : "));
					User pw = userMapper.selectUserSearchPassword(user);
					if(pw.getId()==null) {
						System.out.println("존재하지 않는 아이디입니다.");
					} 
					if(pw.getEmail()==null) {
						System.out.println("존재하지 않는 이메일입니다.");
					}
					System.out.println("비밀번호는 " + pw.getPassword() + "입니다.");
					break;
				}
				returnToMain();
			}
		} catch (Exception e) {
			System.out.println("잘못입력하였습니다. 다시 입력해 주세요.");
			returnToFormerStep();
		}
	}

	private int menu() {
		System.out.println("1. ID 찾기");
		System.out.println("2. 비밀번호 찾기");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}

	public void returnToMain() {
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}

	public void returnToFormerStep() {
		SearchUserUI sui = new SearchUserUI(userMapper);
		sui.service();
	}

}
