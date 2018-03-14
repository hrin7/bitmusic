package kr.co.bitmusic.domain;

public class Search {
	private String searchType;  // email : 이메일, addr : 주소, name : 이름
	private String searchWord;  // 항목에 해당하는 검색할 내용
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}