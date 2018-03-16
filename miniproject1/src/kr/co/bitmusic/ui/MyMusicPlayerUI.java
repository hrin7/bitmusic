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

import javazoom.jl.player.Player;
import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MyMusicMapper;

public class MyMusicPlayerUI extends BaseBitMusicUI{

	private User user = Session.getUser();
	private List<Music> list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
	private int musicCnt = list.size();
	private int pos=0;
	private List<String> musicPath = new ArrayList<>();
	private Scanner sc = new Scanner(System.in);
	private Player player;

	private BaseBitMusicUI ui = null;
	//private MyMusicUI myui = null;

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
		System.out.println();
		System.out.println("[1.재생][2.이전곡][3.다음곡][4.정지][5.전곡재생]");
		System.out.println("[6.랜덤재생][7.노래삭제][8.내음악 가사보기][0.뒤로가기]");
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
			//case 9: new BuyMusicUI().service();
			case 0: new MyMusicUI().service();break;
			}

		}
	} // subMenu


	// 재생메뉴 UI
	public int menu() {
		//list = myMusicMapper.selectMyMusicAll(user.getId());
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		System.out.println();
		System.out.printf("%s님의 음악목록은 %d개입니다.\n", user.getId(), list.size());
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("가수\t\t제목\t\t장르");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

		for(Music m : list) {
			musicPath.add(m.getMusicPath());
			System.out.printf("%s\t\t%s\t\t%s\n", m.getSinger(), m.getTitle(), m.getGenre());
		}
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

		subMenu();


		return getInt("실행할 메뉴번호를 입력하세요 : ");
	} // menu


	// 음악 재생
	public void play() {
		Music m = list.get(pos);
		System.out.println();
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.printf("♬♬ 현재 재생중인곡은 %s의 %s입니다. ♬♬\n", m.getSinger(), m.getTitle());
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

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
					System.out.println("재생할 음악 파일이 없습니다.");
					new MyMusicUI().service();
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
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		System.out.println();
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.printf("♬♬ 순차재생을 시작합니다. ♬♬\n");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");

		Thread t = new Thread() {
			public void run() {
				try {
					if(list.size() != 0){
						Music m = list.get(pos);
						try {player.close();} catch (Exception e) {}
						BufferedInputStream buffer =
								new BufferedInputStream(new FileInputStream(m.getMusicPath()));
						player = new Player(buffer);
						System.out.printf("♬♬ 현재 재생중인 곡은 [%s]의 [%s]입니다. ♬♬\n", m.getSinger(), m.getTitle());
						player.play();
						pos++;
						player.close();
					}
				} catch (Exception e) {
					System.out.println("재생할 음악 파일이 없습니다.");
					new MyMusicUI().service();
				}
			} // run
		}; // Thread
		t.start();

	} // playAll


	public void playShuffle() {
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		System.out.println();
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.printf("♬♬ 랜덤재생을 시작합니다. ♬♬\n");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		Collections.shuffle(list);

		Thread t = new Thread() {
			public void run() {
				if(list.size() != 0){
					Music m = list.get(pos);
					System.out.println();
					System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
					try {player.close();} catch (Exception e) {}
					try {
						BufferedInputStream buffer =
								new BufferedInputStream(new FileInputStream(m.getMusicPath()));
						player = new Player(buffer);
						System.out.printf("♬♬ 현재 재생중인 곡은 [%s]의 [%s]입니다. ♬♬\n", m.getSinger(), m.getTitle());
						System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
						player.play();
						pos++;
						player.close();
					} catch (Exception e) {
						System.out.println("재생할 음악 파일이 없습니다.");
						new MyMusicUI().service();
					}
				}
			} // run
		}; // Thread
		t.start();


	} // playShuffle


	public void lyrics() {
		list = ((MyMusicMapper)Session.getMapper("myMusicMapper")).selectMyMusicAll(user.getId());
		System.out.println();
		for(int i=0 ; i<list.size(); i++) {
			System.out.printf("%d. %s %s\n", i+1, list.get(i).getSinger(), list.get(i).getTitle());
		}
		String title = getStr("가사보기를 할 노래 제목을 입력하세요 : ");
		Music m = ((MyMusicMapper)Session.getMapper("myMusicMapper")).searchMyMusicNo(title);

		if(m == null) {
			System.out.println("입력하신 제목을 찾을 수 없습니다.");
			subMenu();
		}

		int lyricsNo = m.getNo(); 
		String url = "C:/java-lec/git/bitmusic/miniproject1/lyrics/" + lyricsNo + ".txt";
		try { Desktop.getDesktop().browse(new URI(url));
		} catch (IOException e) { System.out.println("가사 파일이 없습니다.");
		new MyMusicUI().service();
		} catch (URISyntaxException e) { System.out.println("가사 파일이 없습니다.");
		new MyMusicUI().service();}
		subMenu();
	}

	// 음악 삭제
	public void delete() {

		Map<String, String> param = new HashMap<>();
		int anwer=0;
		System.out.println();
		param.put("id", user.getId());
		param.put("title", getStr("삭제할 노래 제목을 입력하세요 : "));

		Music m = ((MyMusicMapper)Session.getMapper("myMusicMapper")).searchMyMusic(param);

		if(m==null) {
			System.out.println("입력하신 노래 제목을 찾을 수 없습니다.");
		} else {
			System.out.printf("%s 노래를 정말 삭제하시겠습니까? 1.삭제 2.취소 : ", m.getTitle());
			anwer = Integer.parseInt(sc.nextLine());
		}
		if(anwer == 1) {
			int result = ((MyMusicMapper)Session.getMapper("myMusicMapper")).deleteMyMusicOne(param);
			System.out.println("노래가 삭제되었습니다.");
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
