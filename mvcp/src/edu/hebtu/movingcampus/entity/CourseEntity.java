package edu.hebtu.movingcampus.entity;

/**
 * @author leijie
 *选课的类
 */
public class CourseEntity {
	private String CourseId;//课程代码
	private String CourseName;//课程名称
	private String CoursePro;//课程性质
	private String CourseGroup;//组或模块
	private String CourseCredit;//学分
	private String HourPerWeek;//周学时
	private String TimeOfExam;//考试时间
	private String CourseIntroduce;//课程介绍
	private String SelectedOrNot;//是否选择了
	private String CourseLeftNum;//课程选课剩余数目
	private String EnglishName; //课程英文名
	//以下是getter 和setter 还有 tostring
	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return EnglishName;
	}
	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		EnglishName = englishName;
	}
	
	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return CourseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return CourseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	/**
	 * @return the coursePro
	 */
	public String getCoursePro() {
		return CoursePro;
	}
	/**
	 * @param coursePro the coursePro to set
	 */
	public void setCoursePro(String coursePro) {
		CoursePro = coursePro;
	}
	/**
	 * @return the courseGroup
	 */
	public String getCourseGroup() {
		return CourseGroup;
	}
	/**
	 * @param courseGroup the courseGroup to set
	 */
	public void setCourseGroup(String courseGroup) {
		CourseGroup = courseGroup;
	}
	/**
	 * @return the courseCredit
	 */
	public String getCourseCredit() {
		return CourseCredit;
	}
	/**
	 * @param courseCredit the courseCredit to set
	 */
	public void setCourseCredit(String courseCredit) {
		CourseCredit = courseCredit;
	}
	/**
	 * @return the hourPerWeek
	 */
	public String getHourPerWeek() {
		return HourPerWeek;
	}
	/**
	 * @param hourPerWeek the hourPerWeek to set
	 */
	public void setHourPerWeek(String hourPerWeek) {
		HourPerWeek = hourPerWeek;
	}
	/**
	 * @return the timeOfExam
	 */
	public String getTimeOfExam() {
		return TimeOfExam;
	}
	/**
	 * @param timeOfExam the timeOfExam to set
	 */
	public void setTimeOfExam(String timeOfExam) {
		TimeOfExam = timeOfExam;
	}
	/**
	 * @return the courseIntroduce
	 */
	public String getCourseIntroduce() {
		return CourseIntroduce;
	}
	/**
	 * @param courseIntroduce the courseIntroduce to set
	 */
	public void setCourseIntroduce(String courseIntroduce) {
		CourseIntroduce = courseIntroduce;
	}
	/**
	 * @return the selectedOrNot
	 */
	public String getSelectedOrNot() {
		return SelectedOrNot;
	}
	/**
	 * @param selectedOrNot the selectedOrNot to set
	 */
	public void setSelectedOrNot(String selectedOrNot) {
		SelectedOrNot = selectedOrNot;
	}
	/**
	 * @return the courseLeftNum
	 */
	public String getCourseLeftNum() {
		return CourseLeftNum;
	}
	/**
	 * @param courseLeftNum the courseLeftNum to set
	 */
	public void setCourseLeftNum(String courseLeftNum) {
		CourseLeftNum = courseLeftNum;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CourseEntity [CourseId=" + CourseId + ", CourseName="
				+ CourseName + ", CoursePro=" + CoursePro + ", CourseGroup="
				+ CourseGroup + ", CourseCredit=" + CourseCredit
				+ ", HourPerWeek=" + HourPerWeek + ", TimeOfExam=" + TimeOfExam
				+ ", CourseIntroduce=" + CourseIntroduce + ", SelectedOrNot="
				+ SelectedOrNot + ", CourseLeftNum=" + CourseLeftNum
				+ ", EnglishName=" + EnglishName + "]";
	}
	
}
