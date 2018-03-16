package kr.co.bitmusic.mapper;

import java.util.List;

import kr.co.bitmusic.domain.SampleMusic;

public interface SampleMusicMapper {
	List<SampleMusic> selectSampleMusicList();
	void insertSampleMusic(SampleMusic sm);
}
