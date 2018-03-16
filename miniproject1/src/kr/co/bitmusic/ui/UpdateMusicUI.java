package kr.co.bitmusic.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;

public class UpdateMusicUI extends BaseBitMusicUI {
	private MusicMapper musicMapper;
	
	public UpdateMusicUI() {}
	
	public UpdateMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public void service() {
		while (true) {
			switch (menu()) {
				case 1: updateMusicTitle(); break;
				case 2: updateMusicSinger(); break;
				case 3: updateMusicGenre(); break;
				case 4:  break;
				case 5:  break;
			}
		}
		
	}
	
	public void updateMusicTitle() {
		List<Music> list = musicMapper.selectMusicUpdateTitle(getStr("검색할 제목 키워드를 입력하세요 : "));
		System.out.println("검색 된 노래 제목");
		for (Music m : list) {
			System.out.printf("%d. %s\n", m.getNo(), m.getTitle());
		}
		
		Music music = new Music();
		music.setNo(getInt("수정할 노래 번호를 입력하세요 : "));
		music.setTitle(getStr("변경할 노래 제목을 입력하세요 : "));
		musicMapper.updateMusicTitle(music);
		
		System.out.println("노래 제목이 수정되었습니다.");
	}
	
	public void updateMusicSinger() {
		List<Music> list = musicMapper.selectMusicUpdateSinger(getStr("검색할 가수 키워드를 입력하세요 : "));
		System.out.println("검색 된 노래 제목");
		for (Music m : list) {
			System.out.printf("%d. %s\n", m.getNo(), m.getSinger());
		}
		
		Music music = new Music();
		music.setNo(getInt("수정할 노래 번호를 입력하세요 : "));
		music.setSinger(getStr("변경할 가수 이름을 입력하세요 : "));
		musicMapper.updateMusicSinger(music);
		
		System.out.println("가수 이름이 수정되었습니다.");
	}
	
	public void updateMusicGenre() {
		List<Music> list = musicMapper.selectMusicUpdateGenre(getStr("검색할 장르 키워드를 입력하세요 : "));
		System.out.println("검색 된 장르");
		for (Music m : list) {
			System.out.printf("%d. %s\n", m.getNo(), m.getGenre());
		}
		
		Music music = new Music();
		music.setNo(getInt("수정할 노래 번호를 입력하세요 : "));
		music.setGenre(getStr("변경할 장르를 입력하세요 : "));
		musicMapper.updateMusicGenre(music);
		
		System.out.println("장르가 수정되었습니다.");
	}
	
	public void updateMusicRelDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<Music> list = musicMapper.selectMusicUpdateRelDate(getStr("검색할 발매일 키워드를 입력하세요 : "));
		System.out.println("검색 된 발매일");
		for (Music m : list) {
			System.out.printf("%d. %s\n", m.getNo(), m.getRelDate());
		}
		
		Music music = new Music();
		music.setNo(getInt("수정할 노래 번호를 입력하세요 : "));
		Date d = null;
		try {
			d = sdf.parse(getStr("변경할 발매일을 입력하세요(yyyy-mm-dd) : "));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		music.setRelDate(d);
		musicMapper.updateMusicRelDate(music);
		
		System.out.println("가수 발매일이 수정되었습니다.");
	}
	
	public void updateMusicMusicPath() {
		List<Music> list = musicMapper.selectMusicUpdateMusicPath(getStr("검색할 노래 경로 키워드를 입력하세요 : "));
		System.out.println("검색 된 노래 경로");
		for (Music m : list) {
			System.out.printf("%d. %s\n", m.getNo(), m.getMusicPath());
		}
		
		Music music = new Music();
		music.setNo(getInt("수정할 노래 번호를 입력하세요 : "));
		music.setMusicPath(getStr("변경할 노래 경로를 입력하세요 : "));
		musicMapper.updateMusicMusicPath(music);
		
		System.out.println("노래 경로가 수정되었습니다.");
	}
	

	
	public int menu() {
		System.out.println("-----------------");
		System.out.println("1. 노래제목 수정");
		System.out.println("2. 가수이름 수정");
		System.out.println("3. 장르 수정");
		System.out.println("4. 발매일 수정");
		System.out.println("5. 파일경로 수정");
		System.out.println("-----------------");
		return getInt("수정할 메뉴를 입력하세요 : ");
	}
	
}
