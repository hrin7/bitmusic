// 로그인 기능

package kr.co.bitmusic.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import common.db.MyAppSqlConfig;
import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.MusicMapper;
import kr.co.bitmusic.mapper.MyMusicMapper;
import kr.co.bitmusic.mapper.SampleMusicMapper;
import kr.co.bitmusic.mapper.UserMapper;

public class Session {
	private static User user;
	private static Map<String, Object> mappers = new HashMap<>();

	static {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mappers.put("sampleMusicMapper", session.getMapper(SampleMusicMapper.class));
		mappers.put("musicMapper", session.getMapper(MusicMapper.class));
		mappers.put("userMapper", session.getMapper(UserMapper.class));
		mappers.put("myMusicMapper", session.getMapper(MyMusicMapper.class));
	}
	
	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Session.user = user;
	}
	
	public static Object getMapper(String name) {
		return mappers.get(name);
	}
	
}
