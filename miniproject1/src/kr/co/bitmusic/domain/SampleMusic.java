package kr.co.bitmusic.domain;

public class SampleMusic {
	
	private int no; 
	
	private String sampleMusicPath;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSampleMusicPath() {
		return sampleMusicPath;
	}

	public void setSampleMusicPath(String sampleMusicPath) {
		this.sampleMusicPath = sampleMusicPath;
	}
	

	@Override
	public String toString() {
		return "SampleMusic [no=" + no + ", sampleMusicPath=" + sampleMusicPath + "]";
	}

}
