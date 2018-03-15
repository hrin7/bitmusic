package kr.co.bitmusic.ui;

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
		List<Music> list = musicMapper.selectMusicUpdateTitle(getStr("검색할 노래 키워드를 입력하세요 : "));
		System.out.println("검색 된 노래 제목");
		for (Music m : list) {
			System.out.printf("%d. %s\n", m.getNo(), m.getTitle());
		}
		
		Music music = new Music();
		music.setNo(getInt("수정할 노래 번호를 입력하세요 : "));
		music.setTitle(getStr("변경할 노래 제목을 입력하세요 : "));
		musicMapper.updateMusic(music);
		
		System.out.println("노래 제목이 수정되었습니다.");
		
	}
	
}
