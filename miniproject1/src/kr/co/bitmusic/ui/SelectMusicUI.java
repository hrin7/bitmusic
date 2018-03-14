package kr.co.bitmusic.ui;

import java.text.SimpleDateFormat;
import java.util.List;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;

public class SelectMusicUI extends BaseBitMusicUI {
	MusicMapper musicMapper;
	
	public SelectMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	List<Music> list = null;
	public void service() {
		while (true) {
			switch (menu()) {
			case 1: list = musicMapper.selectMusicList(); break;
			case 2: list = musicMapper.selectMusicListBySinger(); break;
			case 3: list = musicMapper.selectMusicListByTitle(); break;
			case 4: list = musicMapper.selectMusicListByRelDate(); break;
			case 5:  break;
			case 0: returnToAdmin(); break;
			}
			selectMusicList();
		}
	}
	
	public int menu() {
		System.out.println("--------------------");
		System.out.println("1. 번호 순");
		System.out.println("2. 가수 이름 순");
		System.out.println("3. 노래 제목 순");
		System.out.println("4. 발매일순");
		System.out.println("5. 인기순");
		System.out.println("0. 관리자메뉴로 돌아가기");
		System.out.println("--------------------");
		return getInt("정렬할 메뉴번호를 입력하세요 : ");
	}
	
	public void selectMusicList() {
		System.out.printf("전체 %d개\n", list.size());
		System.out.println("번호\t가수\t\t\t제목\t\t\t\t발매일");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Music m : list) {
			System.out.printf("%d\t%s\t\t\t%s\t\t\t\t%s\n", m.getNo(), m.getSinger(), m.getTitle(), sdf.format(m.getRelDate()));
		}
		if (list.isEmpty()) {
			System.out.println("노래가 존재하지 않습니다.");
		}
	}
	
	public void returnToAdmin() {
		AdminUI ui = new AdminUI();
		ui.service();
	}
	
}
