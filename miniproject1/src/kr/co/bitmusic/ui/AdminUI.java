package kr.co.bitmusic.ui;

import kr.co.bitmusic.mapper.MusicMapper;

public class AdminUI extends BaseBitMusicUI {
	MusicMapper musicMapper;
	
	public AdminUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public void service() {
		BaseBitMusicUI ui = null;
		while (true) {
			switch (menu()) {
			case 1:  break;
			case 2: ui = new selectMusicUI(musicMapper); break;
			case 3:  break;
			case 4:  break;
			case 5:  break;
			case 0: returnToMain(); break;
			}
			ui.service();
		}
	}
	
	public int menu() {
		System.out.println("1. 회원 목록 확인");
		System.out.println("2. 음악 전체 목록 확인");
		System.out.println("3. 음악 추가");
		System.out.println("4. 음악 수정");
		System.out.println("5. 음악 삭제");
		System.out.println("0. main메뉴로 돌아가기");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}
	
	public void returnToMain() {
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}
}
