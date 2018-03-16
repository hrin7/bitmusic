package kr.co.bitmusic.ui;

import java.util.List;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.SampleMusicMapper;

public class GuestUserUI extends BaseBitMusicUI {

	public void service() {
		List<Music> list = null;
		list = ((SampleMusicMapper)Session.getMapper("sampleMusicMapper")).selectSampleMusicList();
		System.out.println();
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.printf("번호\t가수\t\t제목\n");
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		for(Music sm : list) {
			System.out.printf("%02d.\t%s\t\t%s\n", sm.getNo(), sm.getSinger(), sm.getTitle());
		}
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		
		SampleMusicPlayerUI smup = new SampleMusicPlayerUI();
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
		System.out.println();
		System.out.println("1. 샘플 음악 미리듣기");
		System.out.println("0. 메인메뉴로 돌아가기");
		return getInt("실행할 번호를 입력하세요 : ");
	}
	
	public void returnToMain() {
		BitMusicUI bui = new BitMusicUI();
		bui.service();
	}
}
