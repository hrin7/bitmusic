// 선영
package kr.co.bitmusic.ui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import javazoom.jl.player.Player;
import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.MyMusicMapper;
import kr.co.bitmusic.mapper.UserMapper;

public class MyMusicPlayerUI extends BaseBitMusicUI{
	
	private MyMusicMapper myMusicMapper;
	private MusicMapper musicMapper;
	private UserMapper userMapper;
	private User user = Session.getUser();
	private List<Music> list;
	
	private int musicCnt=0;

	public MyMusicPlayerUI(){
		SqlSession session = MyAppSqlConfig.getSqlSession();
		myMusicMapper = session.getMapper(MyMusicMapper.class);
		musicMapper = session.getMapper(MusicMapper.class);
		this.list = myMusicMapper.selectMyMusicAll(user.getId());
		musicCnt = list.size();
		for(Music m : list) {
			musicPath.add(m.getMusicPath());
		}
		System.out.println(user.getId());
		System.out.println(list.toString());
	}
	
	private int pos = 0;
	
	private List<String> musicPath = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	private Player player;
	//List<Music> list = myMusicMapper.selectMyMusicAll(userName);
	
	public MyMusicPlayerUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
		
	// 음악 재생
	public void play(String musicName) {
		Thread t = new Thread() {
			public void run() {
				try {
					BufferedInputStream buffer =
							new BufferedInputStream(new FileInputStream(musicName));
					player = new Player(buffer);
					player.play();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	// 재생 정지
	public void stop() {
		player.close();
	}
	
	// 이전곡
	public void prev() {
		stop();
		pos=pos-2;
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(pos <= 0) {
			System.out.println("이전 곡이 없습니다. 처음부터 재생합니다.");
			pos = 0;
		}
		play(musicPath.get(pos));
		
	}
	
	// 다음곡
	public void next() {
		stop();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(pos == musicCnt) {
			System.out.println("재생목록의 마지막입니다. 처음부터 재생합니다.");
			pos = 0;
		}
		play(musicPath.get(pos++));
		
	}
	
	// 회원 뮤직 리스트 가져오기
	public void selectMyMusicAll() {
		new MyMusicPlayerUI().execute();
		//List<Music> list = mapper.selectMyMusicAll();
		System.out.println("------------------------------------");
		System.out.printf("내 음악 : %d개\n", list.size());
		for(Music m : list) {
			System.out.printf("%s\t%s\t%s\n", m.getTitle(), m.getSinger(), m.getGenre());
		}
	}
	
	// 재생메뉴
	public void execute() {
		while (true) {
			switch (menu()) {
				case 1: play(musicPath.get(pos++)); break;
				case 2: prev(); break;
				case 3: next(); break;
				case 4: stop(); break;
				case 5: delete(); break;
				case 0: System.exit(0);;
			}
		}
	}
	
	// 음악 삭제
	public void delete() {
		
	}
	
	// 재생메뉴 UI
	public int menu() {
		System.out.println("-----------------");
		System.out.println("1. 음악재생");
		System.out.println("2. 이전곡");
		System.out.println("3. 다음곡");
		System.out.println("4. 음악정지");
		System.out.println("5. 음악삭제");
		System.out.println("0. 뒤로가기");
		return getInt("실행할 기능을 입력하세요 : ");
	}
	
	// main
	public void service() {
		BaseBitMusicUI ui = null;
		MyMusicPlayerUI myMusic = new MyMusicPlayerUI();
		MyMusicUI myui = null;
		
		while(true) {
			myMusic.selectMyMusicAll();
			switch(menu()) {
			//case 1: ui = new SelectMusicUI(musicMapper); break;
			case 1: myMusicMapper.selectMyMusicAll(user.getId());
			case 2:
			case 3:
			case 4: delete();break;
			case 0: myui.service();
			}
		}
	}
	
	// 뒤로가기
	public void returnToMain() {
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}
}
