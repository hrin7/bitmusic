// 로그인 기능

package kr.co.bitmusic.common;

import kr.co.bitmusic.domain.User;

public class Session {
	private static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		Session.user = user;
	}
	
	
}