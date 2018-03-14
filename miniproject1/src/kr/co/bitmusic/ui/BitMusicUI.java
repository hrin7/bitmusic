package kr.co.bitmusic.ui;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.MyMusicMapper;
import kr.co.bitmusic.mapper.UserMapper;

public class BitMusicUI extends BaseBitMusicUI {
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
			case 1: ui = new AdminUI(musicMapper); break;
			case 2: ui = new SortOptionMusicUI(myMusicMapper); break;
			case 3: ui = new LogInBitMusicUI(userMapper); break;
			case 4: ui = new JoinUserUI(userMapper); break;
			case 5: ui = new SearchUserUI(userMapper); break;
			case 0: quit(); break;
			}
		}
	}
	
	public int menu() {
		System.out.println("1. 관리자 로그인");
		System.out.println("2. 비회원 접속");
		System.out.println("3. 회원 로그인");
		System.out.println("4. 회원가입");
		System.out.println("5. 아이디/비밀번호 찾기");
		System.out.println("0. 종료");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}
	
	public void quit() {
		System.out.println("BitMusic 프로그램을 종료합니다.");
		System.exit(0);
	}
	
}
