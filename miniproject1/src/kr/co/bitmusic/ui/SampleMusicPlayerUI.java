package kr.co.bitmusic.ui;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
import kr.co.bitmusic.domain.SampleMusic;

public class SampleMusicPlayerUI extends BaseBitMusicUI {
//목록 SHOW
	
//1.전체 2. 한곡씩 

//	List<SampleMusic> list = null;

	public void service() {
//		list = ((SampleMusicMapper)Session.getMapper("sampleMusicMapper")).selectSampleMusicList();
//		for(SampleMusic sm : list) {
//			System.out.print("no."+sm.getNo()+"\t");
//			System.out.print("노래제목 : "+sm.getNo()+"\t");
//			System.out.print("가수 : "+sm.getNo()+"\n");
//		}
//		
		while(true) {
			SampleMusic sm = new SampleMusic();
			switch(menu()) {
			case 1 : //전체 반복
				allPlay();
				break;
			case 2 :
				// 번호 입력 받아서 한 곡씩 선택 
				sm.setNo(getInt("미리듣기할 곡 번호를 입력하세요 : "));
				//듣기
				break;
			case 0 :
				returnToFormerStep();
				break;
			}
		}
	}

//	
//	public void selectSampleMusicList() {
//		System.out.printf("전체 %d개\n", list.size());
//		System.out.println("번호\t가수\t\t\t제목\t\t\t\t발매일");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		for (SampleMusic sm : list) {
//			System.out.printf("%d\t%s\t\t\t%s\t\t\t\t%s\n", sm.getNo(), sm.getSinger(), sm.getTitle(), sdf.format(sm.getRelDate()));
//		}
//	}
//	
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
	
	public void returnToFormerStep() {
		GuestUserUI gui = new GuestUserUI();
		gui.service();
	}
}
