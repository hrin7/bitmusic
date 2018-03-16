package kr.co.bitmusic.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;

public class UpdateMusicUI extends BaseBitMusicUI {
	private int updateNo;
	
	public void service() {
		musicList();
		while (true) {
			switch (menu()) {
				case 1: updateMusicTitle(); break;
				case 2: updateMusicSinger(); break;
				case 3: updateMusicGenre(); break;
				case 4: updateMusicRelDate(); break;
				case 5: updateMusicMusicPath(); break;
				case 0: returnToAdmin(); break;
			}
		}
	}
	
	public void musicList() {
		List<Music> list = ((MusicMapper)Session.getMapper("musicMapper")).selectMusicList();
		System.out.printf("전체 %d개\n", list.size());
		System.out.println("번호\t가수\t\t\t제목\t\t\t\t발매일");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (Music m : list) {
			System.out.printf("%d\t%s\t\t\t%s\t\t\t\t%s\n", m.getNo(), m.getSinger(), m.getTitle(), sdf.format(m.getRelDate()));
		}
		if (list.isEmpty()) {
			System.out.println("노래가 존재하지 않습니다.");
		}
		updateNo = getInt("수정할 노래 번호를 입력하세요 : ");
	}
	
	public int menu() {
		System.out.println("-----------------");
		System.out.println("1. 노래제목 수정");
		System.out.println("2. 가수이름 수정");
		System.out.println("3. 장르 수정");
		System.out.println("4. 발매일 수정");
		System.out.println("5. 파일경로 수정");
		System.out.println("0. 뒤로가기");
		System.out.println("-----------------");
		return getInt("수정할 메뉴를 입력하세요 : ");
	}
	
	public void updateMusicTitle() {
		Music music = new Music();
		music.setNo(updateNo);
		music.setTitle(getStr("변경할 노래 제목을 입력하세요 : "));
		int result = ((MusicMapper)Session.getMapper("musicMapper")).updateMusicTitle(music);
		if (result == 0) {
			System.out.println("입력하신 번호는 존재하지 않습니다.");
		} else {
			System.out.println("노래 제목이 수정되었습니다.");
		}
	}
	
	public void updateMusicSinger() {
		Music music = new Music();
		music.setNo(updateNo);
		music.setSinger(getStr("변경할 가수 이름을 입력하세요 : "));
		int result = ((MusicMapper)Session.getMapper("musicMapper")).updateMusicSinger(music);
		if (result == 0) {
			System.out.println("입력하신 번호는 존재하지 않습니다.");
		} else {
			System.out.println("가수 이름이 수정되었습니다.");
		}
	}
	
	public void updateMusicGenre() {
		Music music = new Music();
		music.setNo(updateNo);
		music.setGenre(getStr("변경할 장르를 입력하세요 : "));
		int result = ((MusicMapper)Session.getMapper("musicMapper")).updateMusicGenre(music);
		if (result == 0) {
			System.out.println("입력하신 번호는 존재하지 않습니다.");
		} else {
			System.out.println("장르가 수정되었습니다.");
		}
	}
	
	public void updateMusicRelDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Music music = new Music();
		music.setNo(updateNo);
		Date d = null;
		try {
			d = sdf.parse(getStr("변경할 발매일을 입력하세요(yyyy-mm-dd) : "));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		music.setRelDate(d);
		int result = ((MusicMapper)Session.getMapper("musicMapper")).updateMusicRelDate(music);
		if (result == 0) {
			System.out.println("입력하신 번호는 존재하지 않습니다.");
		} else {
			System.out.println("가수 발매일이 수정되었습니다.");
		}
	}
	
	public void updateMusicMusicPath() {
		Music music = new Music();
		music.setNo(updateNo);
		music.setMusicPath(getStr("변경할 노래 경로를 입력하세요 : "));
		int result = ((MusicMapper)Session.getMapper("musicMapper")).updateMusicMusicPath(music);
		if (result == 0) {
			System.out.println("입력하신 번호는 존재하지 않습니다.");
		} else {
			System.out.println("노래 경로가 수정되었습니다.");
		}
	}
	
	public void returnToAdmin() {
		new AdminUI().service();
	}
}
