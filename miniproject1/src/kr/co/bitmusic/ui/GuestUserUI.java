package kr.co.bitmusic.ui;

import java.util.List;

import kr.co.bitmusic.domain.SampleMusic;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.SampleMusicMapper;

public class GuestUserUI extends BaseBitMusicUI {
	
	private MusicMapper musicMapper;
	public GuestUserUI(MusicMapper musicMapper) {
		this.musicMapper = musicMapper;
	}
	
	private SampleMusicMapper sampleMusicMapper;
	public GuestUserUI(SampleMusicMapper sampleMusicMapper) {
		this.sampleMusicMapper = sampleMusicMapper;
	}
	
	public void service() {
		List<SampleMusic> list = null;
		list = sampleMusicMapper.selectSampleMusicList();
		for(SampleMusic sm : list) {
			System.out.print("no."+sm.getNo()+"\t");
//			System.out.print("노래제목 : "+sm.getTitle()+"\t");
//			System.out.print("가수 : "+sm.getSinger()+"\n");
		}
		
		SampleMusicPlayerUI smup = new SampleMusicPlayerUI(sampleMusicMapper);
		while(true) {
			switch(menu()) {
			case 1 : 
				smup.service();
				break;
			case 0 : 
				returnToMain();
				break;
			}
		}
	}
	
	private int menu() {
		System.out.println("---------------------");
		System.out.println("1. 샘플 음악 미리듣기");
		System.out.println("0. 메인메뉴로 돌아가기");
		return getInt("실행할 번호를 입력하세요 : ");
	}
	
	public void returnToMain() {
		BitMusicUI bui = new BitMusicUI();
		bui.service();
	}
}
