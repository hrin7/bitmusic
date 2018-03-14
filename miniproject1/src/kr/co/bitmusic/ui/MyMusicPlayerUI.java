// 선영
package kr.co.bitmusic.ui;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.MyMusicMapper;

public class MyMusicPlayerUI extends BaseBitMusicUI{

	MyMusicMapper mapper;
	MusicMapper musicMapper;
	
	public MyMusicPlayerUI(){
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mapper = session.getMapper(MyMusicMapper.class);
		musicMapper = session.getMapper(MusicMapper.class);
	}
	
	public void selectMyMusicAll() {
		List<Music> list = mapper.selectMyMusicAll();
		System.out.println("------------------------------------");
		System.out.printf("내 음악 : %s개\n", list.size());
		for(Music m : list) {
			System.out.printf("%s\t%s\t%s\n", m.getTitle(), m.getSinger(), m.getGenre());
		}
	}
	public int menu() {
		System.out.println("1. 음악 전체목록");
		System.out.println("2. 내 음악 목록");
		System.out.println("3. 내정보");
		System.out.println("4. 탈퇴");
		System.out.println("0. 메인");
		return getInt("실행할 기능을 입력하세요 : ");
	}
	
	public void service() {
		BaseBitMusicUI ui = null;
		MyMusicPlayerUI myMusic = new MyMusicPlayerUI();

		
		while(true) {
			
			switch(menu()) {
			case 1:ui = new selectMusicUI(musicMapper); break;
			case 2:myMusic.selectMyMusicAll();break;
			case 3:
			case 4:
			case 0:returnToMain();
			}
			
		}
		
		
		
		
	}
	
	public void returnToMain() {
		BitMusicUI ui = new BitMusicUI();
		ui.service();
	}
}
