package kr.co.bitmusic.ui;

import java.text.SimpleDateFormat;
import java.util.List;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;

public class selectMusicUI extends BaseBitMusicUI {
	MusicMapper musicMapper;
	
	public selectMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public void service() {
		while (true) {
			switch (menu()) {
			case 1: selectMusicListBySinger(); break;
			case 2: selectMusicListByTitle(); break;
			case 3:  break;
			case 4:  break;
			case 0: returnToAdmin(); break;
			}
		}
	}
	
	public int menu() {
		System.out.println("--------------------");
		System.out.println("1. 가수 이름 순");
		System.out.println("2. 노래 제목 순");
		System.out.println("3. 발매일순");
		System.out.println("4. 인기순");
		System.out.println("0. 관리자메뉴로 돌아가기");
		System.out.println("--------------------");
		return getInt("정렬할 메뉴번호를 입력하세요 : ");
	}
	
	public void selectMusicListBySinger() {
		List<Music> list = musicMapper.selectMusicListBySinger();
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
	
	public void selectMusicListByTitle() {
		List<Music> list = musicMapper.selectMusicListBySinger();
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
