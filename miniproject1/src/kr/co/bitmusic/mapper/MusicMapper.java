package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.Music;

public interface MusicMapper {
	List<Music> selectMusicList();
	List<Music> selectMusicListBySinger();
	List<Music> selectMusicListByTitle();
	List<Music> selectMusicListByRelDate();
	void insertMusic(Music music);
	
	List<Music> selectMusicUpdateTitle(String title);
	void updateMusicTitle(Music music);
	
	List<Music> selectMusicUpdateSinger(String singer);
	void updateMusicSinger(Music music);
	
	List<Music> selectMusicUpdateGenre(String genre);
	void updateMusicGenre(Music music);
	
	List<Music> selectMusicUpdateRelDate(String relDate);
	void updateMusicRelDate(Music music);
	
	List<Music> selectMusicUpdateMusicPath(String musicPath);
	void updateMusicMusicPath(Music music);
}
