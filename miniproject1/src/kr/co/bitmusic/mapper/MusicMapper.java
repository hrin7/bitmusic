package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.Music;

public interface MusicMapper {
	List<Music> selectMusicList();
	List<Music> selectMusicListNo();
	List<Music> selectMusicListBySinger();
	List<Music> selectMusicListByTitle();
	List<Music> selectMusicListByRelDate();
	List<Music> selectMusicListByGetCnt();
	
	void insertMusic(Music music);
	
	List<Music> selectMusicTitle(String title);
	List<Music> selectMusicSinger(String singer);
	Music selectMusicNo(int no);
	
	int updateMusicTitle(Music music);
	int updateMusicSinger(Music music);
	int updateMusicGenre(Music music);
	int updateMusicRelDate(Music music);
	int updateMusicMusicPath(Music music);
	
	int deleteMusic(int no);
	void deleteMyMusic(int no);
	
}
