package kr.co.bitmusic.ui;

import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.UserMapper;

public class MyMusicUI extends BaseBitMusicUI {
	
	public MyMusicUI() {
	}

	private UserMapper userMapper;

	public MyMusicUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	private MusicMapper musicMapper;

	public MyMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	
	public void service() {
		BaseBitMusicUI ui = null;
		
		while (true) {
			switch (menu()) {
			case 1:  
				ui = new SelectMusicUI(musicMapper);
				break;
			case 2: 
				ui = new MyMusicPlayerUI(); 
				break;
			case 3:
				ui = new UpdateUserUI(userMapper);
				break;
			case 4:
				ui = new DeleteUserUI(userMapper);
				;
				break;
				
			case 0:
				returnToMain();
				break;
			}
			ui.service();
		}

	}

	private int menu() {
		System.out.println("1. 음악 전체 목록 확인");
		System.out.println("2. 내 음악 목록 확인");
		System.out.println("3. 내 정보 수정");
		System.out.println("4. 회원 탈퇴");
		System.out.println("0. main메뉴로 돌아가기");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}

	public void returnToMain() {
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}

}
