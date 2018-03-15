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
	private int pos=0;
	private int musicCnt = 0;
	private List<String> musicPath = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	private Player player;
	
	private BaseBitMusicUI ui = null;
	//private MyMusicUI myui = null;
	
	public MyMusicPlayerUI(){
		SqlSession session = MyAppSqlConfig.getSqlSession();
		myMusicMapper = session.getMapper(MyMusicMapper.class);
		musicMapper = session.getMapper(MusicMapper.class);
		this.list = myMusicMapper.selectMyMusicAll(user.getId());
		musicCnt = list.size();
		
	}
	
	public MyMusicPlayerUI(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
		
	
	// 재생메뉴 UI
	public int menu() {
		System.out.println("pos : " + pos);
		System.out.println("--------------------------------------");
		System.out.printf("%s님의 음악목록은 %d개입니다.\n", user.getId(), list.size());
		System.out.println("--------------------------------------");
		System.out.println("가수\t\t제목");
		System.out.println("--------------------------------------");
		for(Music m : list) {
			musicPath.add(m.getMusicPath());
			System.out.printf("%s\t\t%s\n", m.getSinger(), m.getTitle());
		}
		System.out.println("--------------------------------------");
		
		System.out.println("1. 음악재생");
		System.out.println("2. 이전곡");
		System.out.println("3. 다음곡");
		System.out.println("4. 음악정지");
		System.out.println("5. 음악삭제");
		System.out.println("0. 뒤로가기");
		System.out.println("실행할 기능을 입력하세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	
	// 음악 재생
	public void play(String musicPath) {
		Thread t = new Thread() {
			public void run() {
				try {
					Music m = list.get(pos);
					System.out.println("--------------------------------------");
					System.out.printf("현재 재생중인곡은 %s의 %s입니다.\n", m.getSinger(), m.getTitle());
					System.out.println("--------------------------------------");
					BufferedInputStream buffer =
							new BufferedInputStream(new FileInputStream(musicPath));
					player = new Player(buffer);
					player.play();
					System.out.println("pos : " + pos);
					
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
		System.out.println("pos : " + pos);
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
		System.out.println("pos : " + pos);
	}
	
	// 음악 삭제
	public void delete() {
		
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
				case 0: new MyMusicUI().service();
				
			}
		}
	} // execute
		// main
	public void service() {

		MyMusicPlayerUI myMusic = new MyMusicPlayerUI();
		
		while(true) {
			//myMusic.selectMyMusicAll();
			switch(menu()) {
			//case 1: ui = new SelectMusicUI(musicMapper); break;
			//case 1: myMusicMapper.selectMyMusicAll(user.getId());
			//case 1: myMusicMapper.selectMyMusicAll(user.getId());
			case 1: 
			case 2: execute();break;
			case 3:
			case 4: delete();break;
			case 0: returnToMain();
			}
		}
	}
	
	// 뒤로가기
	public void returnToMain() {
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}
}
