package kr.co.bitmusic.ui;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;

public class UpdateMusicUI extends BaseBitMusicUI {
	private MusicMapper musicMapper;
	
	public UpdateMusicUI() {}
	
	public UpdateMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public void service() {
		Music music = new Music();
		music.setTitle(getStr("수정할 노래 제목을 입력하세요 : "));
		
	}
	
}
