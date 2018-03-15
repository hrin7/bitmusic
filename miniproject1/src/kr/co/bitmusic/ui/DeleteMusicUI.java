package kr.co.bitmusic.ui;

import kr.co.bitmusic.mapper.MusicMapper;

public class DeleteMusicUI extends BaseBitMusicUI {
	private MusicMapper musicMapper;
	
	public DeleteMusicUI() {}
	
	public DeleteMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public void service() {
		
	}
}
