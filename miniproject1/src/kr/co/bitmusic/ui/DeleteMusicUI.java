package kr.co.bitmusic.ui;

import java.text.SimpleDateFormat;
import java.util.List;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.SampleMusicMapper;

public class DeleteMusicUI extends BaseBitMusicUI {
	
	public void service() {
		musicList();
		while (true) {
			switch (menu()) {
			case 1: deleteMusicNo(); break;
			case 2: deleteMusicSinger(); break;
			case 3: deleteMusicTitle(); break;
			case 0: returnToAdmin(); break;
			}
		}
	}
	
	public int menu() {
		System.out.println();
		System.out.println("[1.노래번호로 삭제] [2.가수이름으로 삭제] [3.노래제목으로 삭제] [0.뒤로가기]");
		return getInt("삭제할 메뉴를 입력하세요 : ");
	}
	
	public void musicList() {
		List<Music> list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicList();
		System.out.println();
		System.out.printf("전체 %d개\n", list.size());
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("번호\t가수\t\t\t제목\t\t\t\t발매일");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Music m : list) {
			System.out.printf("%d\t%s\t\t\t%s\t\t\t\t%s\n", m.getNo(), m.getSinger(), m.getTitle(), sdf.format(m.getRelDate()));
		}
		if (list.isEmpty()) {
			System.out.println("노래가 존재하지 않습니다.");
		}
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
	}
	
	public void deleteMusicNo() {
		int no = getInt("삭제할 노래 번호를 입력하세요 : ");
		List<Music> list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicList();
		boolean flag = false;
		for (int i = 0; i < list.size(); i++) {
			if (no == list.get(i).getNo()) {
				flag = true;
				Music m = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicNo(no);
				System.out.printf("%d. %s - %s ", no, m.getSinger(), m.getTitle());
				int delNo = getInt("노래를 삭제하시겠습니까?(1-예  2-아니오) ");
				if (delNo == 1) {
					((MusicMapper)Session.getMapper("musicMapper")).deleteMyMusic(no);
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
				musicList();
				return;
			}
		}
		if (flag == false) {
			System.out.println("입력된 번호는 존재하지 않습니다.");
		}
	}
	
	public void deleteMusicTitle() {
		List<Music> list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicTitle(getStr("검색할 제목 키워드를 입력하세요 : "));
		System.out.println();
		System.out.printf("검색 된 노래 총 %d개\n", list.size());
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		if (list.size() == 0) {
			System.out.println("검색된 결과가 없습니다.");
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			return;
		}
		for (Music m : list) {
			System.out.printf("%d. %s - %s\n", m.getNo(), m.getSinger(), m.getTitle());
		}
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		
		deleteMusic();
		musicList();
	}
	
	public void deleteMusicSinger() {
		List<Music> list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicSinger(getStr("검색할 가수 키워드를 입력하세요 : "));
		System.out.println();
		System.out.printf("검색 된 노래 총 %d개\n", list.size());
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		if (list.size() == 0) {
			System.out.println("검색된 결과가 없습니다.");
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			return;
		}
		for (Music m : list) {
			System.out.printf("%d. %s - %s\n", m.getNo(), m.getSinger(), m.getTitle());
		}
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		
		deleteMusic();
		musicList();
	}
	
	public void deleteMusic() {
		int no = getInt("삭제할 노래 번호를 입력하세요 : ");
		((MusicMapper)Session.getMapper("musicMapper")).deleteMyMusic(no);
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
