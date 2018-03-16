package kr.co.bitmusic.ui;

import java.util.List;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;

public class DeleteMusicUI extends BaseBitMusicUI {
	private MusicMapper musicMapper;
	
	public DeleteMusicUI() {}
	
	public DeleteMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public void service() {
		while (true) {
			switch (menu()) {
				case 1: deleteMusicTitle(); break;
				case 2: deleteMusicSinger(); break;
				case 0: returnToAdmin(); break;
			}
		}
	}
	
	public int menu() {
		System.out.println("-----------------");
		System.out.println("1. 노래제목으로 삭제하기");
		System.out.println("2. 가수이름으로 삭제하기");
		System.out.println("0. 뒤로가기");
		System.out.println("-----------------");
		return getInt("삭제할 메뉴를 입력하세요 : ");
	}
	
	
	public void deleteMusicTitle() {
		List<Music> list = musicMapper.selectMusicTitle(getStr("검색할 제목 키워드를 입력하세요 : "));
		System.out.printf("검색 된 노래 총 %d개\n", list.size());
		System.out.println("====================");
		for (Music m : list) {
			System.out.printf("%d. %s - %s\n", m.getNo(), m.getSinger(), m.getTitle());
		}
		System.out.println("====================");
		
		deleteMusic();
	}
	
	public void deleteMusicSinger() {
		List<Music> list = musicMapper.selectMusicSinger(getStr("검색할 가수 키워드를 입력하세요 : "));
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
		int result = musicMapper.deleteMusic(no);
		
		if (result == 0) {
			System.out.println("입력된 번호는 존재하지 않습니다.");
		} else {
			System.out.println("노래가 삭제되었습니다.");
		}
	}
	
	public void returnToAdmin() {
		new AdminUI(musicMapper).service();
	}
	
}
