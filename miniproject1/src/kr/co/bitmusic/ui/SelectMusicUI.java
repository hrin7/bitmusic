package kr.co.bitmusic.ui;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.domain.MyMusic;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.MyMusicMapper;

public class SelectMusicUI extends BaseBitMusicUI {

	User user = Session.getUser();
	List<Music> list = null;

	public void service() {
		list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicList();
		while (true) {
			switch (menu()) {
			case 1: selectMusicList(); break;
			case 2: selectMusicListBySinger(); break;
			case 3: selectMusicListByTitle(); break;
			case 4: selectMusicListByRelDate(); break;
			case 5: selectMusicListByGetCnt(); break;
			case 6: buyMusic(); break;
			case 0: returnToAdmin(); break;
			}
			selectMusicList();
		}
	}

	public int menu() {
		System.out.println();
		System.out.println("[1.번호순] [2.가수이름순] [3.노래제목순] [4.발매일순] [5.인기순] [6.노래담기] [0.뒤로가기]");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}

	public void selectMusicList() {
		list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicList();
		System.out.println();
		System.out.printf("전체 %d개\n", list.size());
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("번호\t가수\t\t\t제목\t\t\t\t발매일\t\t\t\t인기순");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Music m : list) {
			System.out.printf("%d\t%s\t\t\t%s\t\t\t\t%s\t\t\t%d\n", m.getNo(), m.getSinger(), m.getTitle(), sdf.format(m.getRelDate()), m.getMusicGetCnt());
		}
		if (list.isEmpty()) {
			System.out.println("노래가 존재하지 않습니다.");
		}
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
	}
	
	public void selectMusicListBySinger() {
		list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicListBySinger();
		selectMusicList();
	}
	
	public void selectMusicListByTitle() {
		list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicListByTitle();
		selectMusicList();
	}
	
	public void selectMusicListByGetCnt() {
		list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicListByGetCnt();
		selectMusicList();
	}
	
	public void selectMusicListByRelDate() {
		list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicListByRelDate();
		selectMusicList();
	}

	public void buyMusic() {
		String title = getStr("내 음악에 저장할 노래제목을 입력하세요 : ");
		
		// 중복 체크
		Map<String, String> param = new HashMap<>();
		param.put("id", user.getId());
		param.put("title", title);
		Music check = (((MyMusicMapper)Session.getMapper("myMusicMapper")).searchMyMusic(param));
		if(check != null) {
			System.out.println("이미 리스트에 추가되어 있습니다.");
			return;
		}
		
		// 제목으로 노래 번호 확인
		Music m = (((MyMusicMapper)Session.getMapper("myMusicMapper")).searchMyMusicNo(title));

		if(m == null) {
			System.out.println("입력하신 노래가 없습니다.");
			System.out.println();
		} else {
			MyMusic my = new MyMusic();
			my.setNo(m.getNo());
			my.setId(user.getId());
			// 내음악에 번호와 아이디 담기
			((MyMusicMapper)Session.getMapper("myMusicMapper")).insertMyMusicNo(my);
			// 음악 구매 카운트 증가
			((MyMusicMapper)Session.getMapper("myMusicMapper")).updateMusicMusicGetCnt(m.getNo());
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			System.out.printf("%s의 %s이(가) 내음악 목록에 추가되었습니다.\n", m.getSinger(), m.getTitle());
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			System.out.println();
		}

	} // buyMusic

	public void returnToAdmin() {
		if (user.getId().equals("admin")) {
			new AdminUI().service();
		} else {
			new MyMusicUI().service(); 
		}
	}

}
