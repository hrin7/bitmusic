package kr.co.bitmusic.ui;

public class MyMusicUI extends BaseBitMusicUI {
	
	public void service() {
		BaseBitMusicUI ui = null;
		
		while (true) {
			switch (menu()) {
			case 1:  
				ui = new SelectMusicUI();
				break;
			case 2: 
				ui = new MyMusicPlayerUI(); 
				break;
			case 3:
				ui = new UpdateUserUI();
				break;
			case 4:
				ui = new DeleteUserUI();
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
