package kr.co.bitmusic.ui;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javazoom.jl.player.Player;
import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.SampleMusic;
import kr.co.bitmusic.mapper.SampleMusicMapper;

public class SampleMusicPlayerUI extends BaseBitMusicUI {

	public void service() {

		while(true) {
			switch(menu()) {
			case 1 : 
				allPlay();
				break;
			case 2 :
				chooseToPlay();
				break;
			case 0 :
				returnToFormerStep();
				break;
			}
		}
	}

	private int menu( ) {
		System.out.println("--------------------");
		System.out.println("1. 미리듣기 전체 재생");
		System.out.println("2. 미리듣기 선택 재생");
		//3. 노래 멈춤
		System.out.println("0. 이전 메뉴로 돌아가기");
		return getInt("실행할 메뉴번호를 입력하세요 : ");
	}
	
	private Player player;
	public void allPlay() {
		File file = new File("samplemusic");
		File[] files = file.listFiles();
		for(File f : files) {
			try {
				BufferedInputStream buffer =
						new BufferedInputStream(new FileInputStream(f));
				player = new Player(buffer);
				player.play();
			} catch (Exception e) {
				System.out.println("음악 재생 중 오류가 발생하였습니다.");
			}
		}
	}
	
	public void chooseToPlay() {
		int no = getInt("미리듣기할 곡 번호를 입력하세요 : ");
		SampleMusic chooseToPlay = ((SampleMusicMapper)Session.getMapper("sampleMusicMapper")).sampleMusicChooseToPlay(no);
		System.out.println(chooseToPlay);
			try {
				BufferedInputStream buffer =
						new BufferedInputStream(new FileInputStream(chooseToPlay.getSampleMusicPath()));
				player = new Player(buffer);
				player.play();
			} catch (Exception e) {
				System.out.println("음악 재생 중 오류가 발생하였습니다.");
				e.printStackTrace();
			}
	}

	public void returnToFormerStep() {
		GuestUserUI gui = new GuestUserUI();
		gui.service();
	}
}
