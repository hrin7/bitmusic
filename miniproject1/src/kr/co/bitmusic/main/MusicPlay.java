package kr.co.bitmusic.main;

import java.io.FileInputStream;

import javazoom.jl.player.Player;
import kr.co.bitmusic.ui.BitMusicUI;

public class MusicPlay {
	public static void main(String[] args) {
		
		BitMusicUI ui = new BitMusicUI();
		try {
			Player p = new Player(new FileInputStream("sound/welcom.mp3"));
			System.out.println("♬  비트 뮤직에 오신걸 환영합니다 ♬");
			p.play();
			p.close();
			
			ui.service();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
