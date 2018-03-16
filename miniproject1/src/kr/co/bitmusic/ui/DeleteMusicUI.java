package kr.co.bitmusic.ui;

import java.util.List;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.SampleMusicMapper;

public class DeleteMusicUI extends BaseBitMusicUI {
	
	public void service() {
		while (true) {
			switch (menu()) {
			case 1: deleteMusicNo(); break;
			case 2: deleteMusicTitle(); break;
			case 3: deleteMusicSinger(); break;
			case 0: returnToAdmin(); break;
			}
		}
	}
	
	public int menu() {
		System.out.println("-----------------");
		System.out.println("1. 노래번호로 삭제하기");
		System.out.println("2. 노래제목으로 삭제하기");
		System.out.println("3. 가수이름으로 삭제하기");
		System.out.println("0. 뒤로가기");
		System.out.println("-----------------");
		return getInt("삭제할 메뉴를 입력하세요 : ");
	}
	
	public void deleteMusicNo() {
		int no = getInt("삭제할 노래 번호를 입력하세요 : ");
		Music m = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicNo(no);
		System.out.printf("%d. %s - %s ", no, m.getSinger(), m.getTitle());
		int delNo = getInt("노래를 삭제하시겠습니까?(1-예  2-아니오) ");
		if (delNo == 1) {
			((SampleMusicMapper)Session.getMapper("sampleMusicMapper")).deleteSampleMusic(no);
			int result = ((MusicMapper)Session.getMapper("musicMapper")).deleteMusic(no);
			if (result == 0) {
				System.out.println("입력된 번호는 존재하지 않습니다.");
			} else {
				System.out.println("노래가 삭제되었습니다.");
			}
		} else {
			System.out.println("이전메뉴로 돌아갑니다.");
		}
	}
	
	public void deleteMusicTitle() {
		List<Music> list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicTitle(getStr("검색할 제목 키워드를 입력하세요 : "));
		System.out.printf("검색 된 노래 총 %d개\n", list.size());
		System.out.println("====================");
		for (Music m : list) {
			System.out.printf("%d. %s - %s\n", m.getNo(), m.getSinger(), m.getTitle());
		}
		System.out.println("====================");
		
		deleteMusic();
	}
	
	public void deleteMusicSinger() {
		List<Music> list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicSinger(getStr("검색할 가수 키워드를 입력하세요 : "));
		System.out.printf("검색 된 노래 총 %d개\n", list.size());
		System.out.println("====================");
		for (Music m : list) {
			System.out.printf("%d. %s - %s\n", m.getNo(), m.getSinger(), m.getTitle());
		}
		System.out.println("====================");
		
		deleteMusic();
	}
	
	public void deleteMusic() {
		int no = getInt("삭제할 노래 번호를 입력하세요 : ");
		((SampleMusicMapper)Session.getMapper("sampleMusicMapper")).deleteSampleMusic(no);
		int result = ((MusicMapper)Session.getMapper("musicMapper")).deleteMusic(no);
		
		if (result == 0) {
			System.out.println("입력된 번호는 존재하지 않습니다.");
		} else {
			System.out.println("노래가 삭제되었습니다.");
		}
	}
	
	public void returnToAdmin() {
		new AdminUI().service();
	}
	
}
