package moduleTest;

import java.util.Arrays;

import com.lectopia.chunbangsave.vo.Weather;

public class DiaryDTO {
	private String[]registerDate;
	private String diaryTitle;
	private String diaryContent;
	private Weather weather;
	
	public DiaryDTO() {
		super();
	}
	public DiaryDTO(String[] registerDate, String diaryTitle,
			String diaryContent, Weather weather) {
		super();
		this.registerDate = registerDate;
		this.diaryTitle = diaryTitle;
		this.diaryContent = diaryContent;
		this.weather = weather;
	}
	public String[] getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String[] registerDate) {
		this.registerDate = registerDate;
	}
	public String getDiaryTitle() {
		return diaryTitle;
	}
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	public String getDiaryContent() {
		return diaryContent;
	}
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	public Weather getWeather() {
		return weather;
	}
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	@Override
	public String toString() {
		return "DiaryDTO [registerDate=" + Arrays.toString(registerDate)
				+ ", diaryTitle=" + diaryTitle + ", diaryContent="
				+ diaryContent + ", weather=" + weather + "]";
	}
	
}
