// 선영
package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.Music;

public interface MyMusicMapper {

	public List<Music> selectMyMusicAll (String userName);
	
	
	
}
