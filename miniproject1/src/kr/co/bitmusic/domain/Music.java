package kr.co.bitmusic.domain;

import java.util.Date;

public class Music {
    // 고유번호 
    private Integer no;

    // 노래제목 
    private String title;

    // 가수 
    private String singer;

    // 장르 
    private String genre;

    // 가사 
    private String lyrics;

    // 발매일 
    private Date relDate;

    // 노래경로 
    private String musicPath;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
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

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }
}
