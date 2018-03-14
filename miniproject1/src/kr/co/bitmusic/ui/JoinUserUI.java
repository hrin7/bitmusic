package kr.co.bitmusic.ui;

import kr.co.bitmusic.domain.User;
import kr.co.bitmusic.mapper.UserMapper;

public class JoinUserUI extends BaseBitMusicUI {
	private UserMapper mapper;
	public JoinUserUI(UserMapper mapper) {
		this.mapper = mapper;
	}
	
	public void service() {
		User userVO = new User();
		userVO.setId(getStr("Id를 설정하세요 : "));
		userVO.setPassword(getStr("비밀번호를 설정하세요 : "));
		userVO.setPasswordHint(getStr("비밀번호 힌트를 입력하세요(ex.좋아하는 책, 좋아하는 영화 등) : "));
		userVO.setName(getStr("이름을 입력하세요 : "));
		userVO.setAge(getInt("나이를 입력하세요 : "));
		userVO.setGender(getStr("성별을 입력하세요(남 or 여) : "));
		userVO.setEmail(getStr("이메일을 입력하세요 : "));
		mapper.insertUser(userVO);
		System.out.println("회원가입이 완료되었습니다.");
	}

}