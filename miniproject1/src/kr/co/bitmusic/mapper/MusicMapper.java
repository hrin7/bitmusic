package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.Music;

public interface MusicMapper {
	List<Music> selectMusicList();
	List<Music> selectMusicListBySinger();
	List<Music> selectMusicListByTitle();
	List<Music> selectMusicListByRelDate();
	void insertMusic(Music music);
}
