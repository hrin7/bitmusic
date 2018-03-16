// 선영
package kr.co.bitmusic.ui;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import javazoom.jl.player.Player;
import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MyMusicMapper;

public class MyMusicPlayerUI extends BaseBitMusicUI{

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
		//this.list = myMusicMapper.selectMyMusicAll(user.getId());
		this.list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		musicCnt = list.size();
	}

	// main
	public void service() {
		//BaseBitMusicUI ui = null;
		if(list.size() == 0) {
			System.out.println("구매하신 곡이 없습니다.");
		}else {
			while(true) {
				switch(menu()) {
				case 1: subMenu();break;
				case 0: returnToMain();break;
				}
				//ui.service();
			}
		}
	}

	public int playMenu() {
		System.out.println("------------------------------------------------");
		System.out.println("[1.재생][2.이전곡][3.다음곡][4.정지][5.전곡반복][6.랜덤반복][7.노래삭제][8.재생중인곡의 가사보기][0.뒤로가기]");
		System.out.println("------------------------------------------------");
		return getInt("재생 메뉴를 입력하세요 : ");
	} // playMenu


	public void subMenu() {
		while (true) {
			switch (playMenu()) {
			case 1: play();	break;
			case 2: prev(); break;
			case 3: next(); break;
			case 4: stop(); break;
			case 5: playAll(); break;
			case 6: playShuffle(); break;
			case 7: delete(); break;
			case 8: lyrics();break;
			case 0: new MyMusicUI().service();
			}

		}
	} // subMenu


	// 재생메뉴 UI
	public int menu() {
		System.out.println("------------------------------------------------");
		//list = myMusicMapper.selectMyMusicAll(user.getId());
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());


		System.out.printf("%s님의 음악목록은 %d개입니다.\n", user.getId(), list.size());
		System.out.println("------------------------------------------------");
		System.out.println("가수\t\t제목\t\t장르");
		System.out.println("------------------------------------------------");

		for(Music m : list) {
			musicPath.add(m.getMusicPath());
			System.out.printf("%s\t\t%s\t\t%s\n", m.getSinger(), m.getTitle(), m.getGenre());
		}

		subMenu();


		return getInt("실행할 메뉴번호를 입력하세요 : ");
	} // menu


	// 음악 재생
	public void play() {
		Music m = list.get(pos);

		System.out.println("------------------------------------------------");
		System.out.printf("♬♬ 현재 재생중인곡은 %s의 %s입니다. ♬♬\n", m.getSinger(), m.getTitle());

		Thread t = new Thread() {
			public void run() {
				try {
					player.close();
				} catch (Exception e) {}
				try {
					BufferedInputStream buffer =
							new BufferedInputStream(new FileInputStream(m.getMusicPath()));
					player = new Player(buffer);
					player.play();
					player.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // run
		}; // Thread
		t.start();

	} // play

	// 재생 정지
	public void stop() {
		player.close();
	} // stop

	// 이전곡
	public void prev() {
		stop();
		pos = pos - 2;

		if(pos <= 0) {
			System.out.println("이전 곡이 없습니다. 처음부터 재생합니다.");
			pos = 0;
		}
		play();
	} // prev

	// 다음곡
	public void next() {
		stop();
		++pos;
		if(pos == musicCnt) {
			System.out.println("재생목록의 마지막입니다. 처음부터 재생합니다.");
			pos = 0;
		}
		play();
	} // next


	public void playAll(){
		//list = myMusicMapper.selectMyMusicAll(user.getId());
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		
		System.out.println("------------------------------------------------");
		System.out.printf("♬♬ 순차재생중입니다. ♬♬\n");
		
		Thread t = new Thread() {
			public void run() {
				try {
					while(true){
						Music m = list.get(pos);
						try {player.close();} catch (Exception e) {}
						BufferedInputStream buffer =
								new BufferedInputStream(new FileInputStream(m.getMusicPath()));
						player = new Player(buffer);
						player.play();
						pos++;
						if(pos == list.size()) {
							pos = 0;
						}
						
						player.close();
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			} // run
		}; // Thread
		t.start();
		
	} // playAll


	public void playShuffle() {
		//list = myMusicMapper.selectMyMusicAll(user.getId());
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		System.out.printf("♬♬ 랜덤재생중입니다. ♬♬\n");
		Collections.shuffle(list);

		Thread t = new Thread() {
			public void run() {
				while(true){
					Music m = list.get(pos);
					System.out.println("------------------------------------------------");
					try {player.close();} catch (Exception e) {}
					try {
						BufferedInputStream buffer =
								new BufferedInputStream(new FileInputStream(m.getMusicPath()));
						player = new Player(buffer);
						player.play();
						pos++;
						if(pos == list.size()) {
							pos = 0;
						}
						player.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} // run
		}; // Thread
		t.start();


	} // playShuffle


	public void lyrics() {
		//list = myMusicMapper.selectMyMusicLyrics(user.getId());
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		/*
		List<Music> lyrics = null;
		for (int i=0; i<list.size();i++) {
			Music m = list.get(i);
			System.out.printf("[번호]\t[가수]\t[제목]", i, m.getSinger(), m.getTitle());
			lyrics.add(m);
		}

		int no = getInt("가사보기할 노래 번호를 입력하세요 : ");
		String string = lyrics.get(no).getLyrics();

		System.out.println(string);
		*/
		try { Desktop.getDesktop().browse(new URI("C:java-lec/git/bitmusic/miniproject1/lyrics/test.txt"));
		} catch (IOException e) { e.printStackTrace();
		} catch (URISyntaxException e) { e.printStackTrace(); }

		

	}


	// 음악 삭제
	public void delete() {
		Map<String, String> param = new HashMap<>();
		int anwer=0;

		param.put("id", user.getId());
		param.put("title", getStr("삭제할 노래 제목을 입력하세요 : "));

		Music m = ((MyMusicMapper)Session.getMapper("myMusicMapper")).searchMyMusic(param);
		//Music m = myMusicMapper.searchMyMusic(param);

		if(m==null) {
			System.out.println("입력하신 노래 제목을 찾을 수 없습니다.");
		} else {
			System.out.printf("%s 노래를 정말 삭제하시겠습니까? 1.삭제 2.취소", m.getTitle());
			anwer = Integer.parseInt(sc.nextLine());
		}
		if(anwer == 1) {
			int result = ((MyMusicMapper)Session.getMapper("myMusicMapper")).deleteMyMusicOne(param);
			if(result == 0) {
				getStr("입력하신 제목의 노래가 없습니다.");
			}else {
				System.out.println("노래가 삭제되었습니다.");
			}
		}else if(anwer==2) {
			System.out.println("삭제를 취소하였습니다.");
		}
	} // delete

	// 뒤로가기
	public void returnToMain() {
		user.setId(null);
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}
}
