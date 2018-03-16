package kr.co.bitmusic.domain;

import java.util.Date;

public class SampleMusic {
	
	private int no; 
	
	private String title;
	
	private String singer;
	
	private String genre;
	
	private String lyrics;
	
	private Date relDate;
	
	private String sampleMusicPath;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Date getRelDate() {
		return relDate;
	}

	public void setRelDate(Date relDate) {
		this.relDate = relDate;
	}

	public String getSampleMusicPath() {
		return sampleMusicPath;
	}

	public void setSampleMusicPath(String sampleMusicPath) {
		this.sampleMusicPath = sampleMusicPath;
	}

	@Override
	public String toString() {
		return "SampleMusic [no=" + no + ", title=" + title + ", singer=" + singer + ", genre=" + genre + ", lyrics="
				+ lyrics + ", relDate=" + relDate + ", sampleMusicPath=" + sampleMusicPath + "]";
	}

}
