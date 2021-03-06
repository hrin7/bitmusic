package kr.co.bitmusic.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.domain.SampleMusic;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.SampleMusicMapper;

public class InsertMusicUI extends BaseBitMusicUI {

	public void service() {
		Music m = new Music();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println();
		m.setSinger(getStr("가수 : "));
		m.setTitle(getStr("노래 제목 : "));
		m.setGenre(getStr("장르 : "));
		m.setLyrics(getStr("가사 : "));
		Date d = null;
		try {
			d = sdf.parse(getStr("발매일(yyyy-MM-dd) : "));
		} catch (ParseException e) {
			System.out.println("잘못 입력되었습니다. 다시입력해주세요");
			service();
		}
		m.setRelDate(d);
		m.setMusicPath(getStr("음악 경로 : "));
		
		SampleMusic sm = new SampleMusic();
		sm.setSampleMusicPath("sample" + m.getMusicPath());
		
		((MusicMapper) Session.getMapper("musicMapper")).insertMusic(m);
		sm.setNo(m.getNo());

		try {
			((SampleMusicMapper) Session.getMapper("sampleMusicMapper")).insertSampleMusic(sm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.printf("%s-%s 노래가 추가되었습니다.\n", m.getSinger(), m.getTitle());
	}
	
}
