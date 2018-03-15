package kr.co.bitmusic.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;

public class InsertMusicUI extends BaseBitMusicUI {
	private MusicMapper musicMapper;
	
	public InsertMusicUI() {}

	public InsertMusicUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	public void service() {
		Music m = new Music();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		m.setSinger(getStr("가수 : "));
		m.setTitle(getStr("노래 제목 : "));
		m.setGenre(getStr("장르 : "));
		m.setLyrics(getStr("가사 : "));
		Date d = null;
		try {
			d = sdf.parse(getStr("발매일(yyyy-MM-dd) : "));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		m.setRelDate(d);
		m.setMusicPath(getStr("경로 : "));
		musicMapper.insertMusic(m);
		System.out.printf("%s-%s노래가 추가되었습니다.\n", m.getSinger(), m.getTitle());
	}
	
}
