package kr.co.bitmusic.ui;

public class BitMusicUI extends BaseBitMusicUI{

	public void service() {
		
		try {
			BaseBitMusicUI ui = null;
			while (true) {
				switch (menu()) {
				case 1: ui = new LogInBitMusicUI(); break;
				case 2: ui = new GuestUserUI(); break;
				case 3: ui = new JoinUserUI(); break;
				case 4: ui = new SearchUserUI(); break;
				case 0: quit(); break;
				}
				ui.service();
			}
		} catch (Exception e) {
			System.out.println("존재하지 않는 메뉴번호입니다.");
			returnToBitMusicUI();
		}
	}
	
	private int menu() {
		System.out.println("1. 로그인");
		System.out.println("2. 비회원 접속");
		System.out.println("3. 회원가입");
		System.out.println("4. 아이디/비밀번호 찾기");
		System.out.println("0. 종료");
		System.out.print("실행할 메뉴번호를 입력하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	
	public void quit() {
		System.out.println("BitMusic 프로그램을 종료합니다.");
		System.exit(0);
	}
	
	public void returnToBitMusicUI() {
		BitMusicUI bui = new BitMusicUI();
		bui.service();
	}
}
