package kr.co.bitmusic.ui;

import java.util.Scanner;

public abstract class BaseBitMusicUI {
	Scanner sc = new Scanner(System.in);
	
	public abstract void service();
	
	public int getInt(String msg) {
		System.out.print("msg");
		return Integer.parseInt(sc.nextLine());
	}
	
	public String getStr(String msg) {
		System.out.print("msg");
		return sc.nextLine();
	}
}
