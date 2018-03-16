// 선영
package kr.co.bitmusic.ui;

import kr.co.bitmusic.common.Session;
import kr.co.bitmusic.domain.MyMusic;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MyMusicMapper;

public class BuyMusicUI extends BaseBitMusicUI  {

	private User user = Session.getUser();
	private BaseBitMusicUI ui = null;
	
	public void service() {
		

		System.out.println(user.getId());
		MyMusic m = new MyMusic();
		m.setNo(getInt("구입할 음악 번호를 입력하세요 : "));
		m.setId(user.getId());
		int result = ((MyMusicMapper)Session.getMapper("myMusicMapper")).insertMyMusicNo(m);
		if(result != 0) {
			System.out.println("음악 구매가 완료되었습니다.");
		}
		
		
		
		
	}
	
	
	
	
	
}
