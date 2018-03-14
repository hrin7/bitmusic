package kr.co.bitmusic.ui;

import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.MyMusicMapper;

public class LogInBitMusicUI extends BaseBitMusicUI {
	private MusicMapper musicMapper;
	private MyMusicMapper myMusicMapper;
	
	public LogInBitMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	public LogInBitMusicUI(MyMusicMapper myMusicMapper) {
		this.myMusicMapper = myMusicMapper;
	}
	
	public void service() {
		User user = new User();
		user.setId(getStr("ID를 입력하세요 : "));
		user.setPassword(getStr("Password를 입력하세요 : "));
		
			BaseBitMusicUI ui = null;
			if(user.getId()=="admin" && user.getPassword()=="admin") {
				ui = new AdminUI(musicMapper);
			} else {
				ui = new MyMusicUI(myMusicMapper);
			}
			ui.service();
		}
}
