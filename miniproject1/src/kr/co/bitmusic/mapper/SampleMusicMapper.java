package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.Music;
import kr.co.bitmusic.domain.SampleMusic;

public interface SampleMusicMapper {
	List<Music> selectSampleMusicList();
	void insertSampleMusic(SampleMusic sm);
	void deleteSampleMusic(int no);
}
