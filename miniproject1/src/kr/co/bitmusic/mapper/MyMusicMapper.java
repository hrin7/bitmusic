// 선영
package kr.co.bitmusic.mapper;

import java.util.List;
import java.util.Map;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.domain.MyMusic;

public interface MyMusicMapper {
	
	public List<Music> selectMyMusicAll (String userName);

	public int deleteMyMusicOne (Map<String, String> param);
	
	public Music selectMyMusicOne (Map<String, String> param);
	
	public Music searchMyMusic (Map<String, String> param);
	
	public Music searchMyMusicNo (String title);
	
	public MyMusic selectChekcMyMusic(MyMusic param);
	
	public int insertMyMusicNo(MyMusic m);
	
	public int updateMusicMusicGetCnt(int musicNo);
	
}
