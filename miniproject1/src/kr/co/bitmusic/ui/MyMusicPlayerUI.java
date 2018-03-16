// 선영
package kr.co.bitmusic.ui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
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

	// main
	public void service() {
		//BaseBitMusicUI ui = null;
		while(true) {
			switch(menu()) {
			//case 1: 
			case 2: subMenu();break;
			//case 3:
			//case 4: delete();break;
			case 0: returnToMain();break;
			}
			//ui.service();
		}
	}

	public int playMenu() {
		System.out.println("------------------------------------------------");
		System.out.println("1.재생\t2.이전곡\t3.다음곡\t4.정지\t5.삭제\t0.뒤로가기 ");
		/*System.out.println("2. 이전곡");
		System.out.println("3. 다음곡");
		System.out.println("4. 음악정지");
		System.out.println("5. 음악삭제");
		System.out.println("0. 뒤로가기");
		 */System.out.println("------------------------------------------------");
		 return getInt("재생 메뉴를 입력하세요 : ");
	}


	public void subMenu() {
		while (true) {
			switch (playMenu()) {
			case 1: play();	break;
			case 2: prev(); break;
			case 3: next(); break;
			case 4: stop(); break;
			case 5: delete(); break;
			case 0: new MyMusicUI().service();
			}

		}
	}


	// 재생메뉴 UI
	public int menu() {
		System.out.println("------------------------------------------------");
		list = myMusicMapper.selectMyMusicAll(user.getId());
		System.out.printf("%s님의 음악목록은 %d개입니다.\n", user.getId(), list.size());
		System.out.println("------------------------------------------------");
		System.out.println("가수\t\t제목\t\t장르");
		System.out.println("------------------------------------------------");
		for(Music m : list) {
			musicPath.add(m.getMusicPath());
			System.out.printf("%s\t\t%s\t\t%s\n", m.getSinger(), m.getTitle(), m.getGenre());
		}

		subMenu();

		return getInt("실행할 기능을 입력하세요 : ");
	}


	// 음악 재생
	public void play() {
		Music m = list.get(pos);
		System.out.println("------------------------------------------------");
		System.out.printf("♬♬ 현재 재생중인곡은 %s의 %s입니다. ♬♬\n", m.getSinger(), m.getTitle());

		Thread t = new Thread() {
			public void run() {
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
		System.out.println("재생을 멈추었습니다.");
	}

	// 이전곡
	public void prev() {
		stop();
		pos = pos - 2;

		if(pos <= 0) {
			System.out.println("이전 곡이 없습니다. 처음부터 재생합니다.");
			pos = 0;
		}
		play();
	}

	// 다음곡
	public void next() {
		stop();
		++pos;
		if(pos == musicCnt) {
			System.out.println("재생목록의 마지막입니다. 처음부터 재생합니다.");
			pos = 0;
		}
		play();
		System.out.println("다음곡을 재생합니다.");
	}

	// 음악 삭제
	public void delete() {
		Map<String, String> param = new HashMap<>();
		int anwer=0;

		param.put("id", user.getId());
		param.put("title", getStr("삭제할 노래 제목을 입력하세요 : "));

		Music m = myMusicMapper.searchMyMusic(param);

		if(m==null) {
			System.out.println("입력하신 노래 제목을 찾을 수 없습니다.");
		} else {
			System.out.printf("%s 노래를 정말 삭제하시겠습니까? 1.삭제 2.취소", m.getTitle());
			anwer = Integer.parseInt(sc.nextLine());
		}
		if(anwer == 1) {
			int result = myMusicMapper.deleteMyMusicOne(param);
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
