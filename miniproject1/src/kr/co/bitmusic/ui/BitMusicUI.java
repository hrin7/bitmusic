package kr.co.bitmusic.ui;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.MyMusicMapper;
import kr.co.bitmusic.mapper.UserMapper;

public class BitMusicUI extends BaseBitMusicUI{
	 MusicMapper musicMapper;
	 MyMusicMapper myMusicMapper;
	 UserMapper userMapper;
	
	public BitMusicUI() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		musicMapper = session.getMapper(MusicMapper.class);
		myMusicMapper = session.getMapper(MyMusicMapper.class);
		userMapper = session.getMapper(UserMapper.class);
	}
		
	
	public void service() {
		BaseBitMusicUI ui = null;
		while (true) {
			switch (menu()) {
			case 1: ui = new LogInBitMusicUI(userMapper); break;
//			case 2:  break;
			case 4: ui = new JoinUserUI(userMapper); break;
//			case 5: ui = new SearchUserUI(userMapper); break;
			case 0: quit(); break;
			}
			ui.service();
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
	
}
