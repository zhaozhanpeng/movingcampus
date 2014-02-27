/**
 * 
 */
package edu.hebtu.movingcampus.entity;

/**
 * @author leijie
 * @aim 这是选择具体哪个老师的课程的时候需要的类
 */
public class ChooseCourseEntity {
	private String Teacher;// 教学老师
	private String College;// 开课学院
	private String HourPerWeek;// 周学时
	private String TypeOfExam;// 考核，考试
	private String TimeOfExam;// 考试时间
	private String RoomOfCourse;// 上课地点
	private String Campus;// 校区
	private String Remark;// 备注
	private String MethosOfTeach;// 授课放式
	private String ShortTerm;// 是否短学期
	private String Capacity;// 容量（人数）
	private String TextBook;// 教科书
	private String NativeChoosedNum;// 本专业已选人数
	private String AllChoosedNum;// 所有已选人数
	private String BookOrderId;// 教材征订号
	private String CourseId;// 课程号

	/**
	 * @return the bookOrderdId
	 */
	public String getBookOrderId() {
		return BookOrderId;
	}

	/**
	 * @param bookOrderdId
	 *            the bookOrderdId to set
	 */
	public void setBookOrderId(String bookOrderdId) {
		BookOrderId = bookOrderdId;
	}

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return CourseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(String courseId) {
		CourseId = courseId;
	}

	// 以下是getter 和setter 还有 tostring
	/**
	 * @return the teacher
	 */
	public String getTeacher() {
		return Teacher;
	}

	/**
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(String teacher) {
		Teacher = teacher;
	}

	/**
	 * @return the college
	 */
	public String getCollege() {
		return College;
	}

	/**
	 * @param college
	 *            the college to set
	 */
	public void setCollege(String college) {
		College = college;
	}

	/**
	 * @return the hourPerWeek
	 */
	public String getHourPerWeek() {
		return HourPerWeek;
	}

	/**
	 * @param hourPerWeek
	 *            the hourPerWeek to set
	 */
	public void setHourPerWeek(String hourPerWeek) {
		HourPerWeek = hourPerWeek;
	}

	/**
	 * @return the typeOfExam
	 */
	public String getTypeOfExam() {
		return TypeOfExam;
	}

	/**
	 * @param typeOfExam
	 *            the typeOfExam to set
	 */
	public void setTypeOfExam(String typeOfExam) {
		TypeOfExam = typeOfExam;
	}

	/**
	 * @return the timeOfExam
	 */
	public String getTimeOfExam() {
		return TimeOfExam;
	}

	/**
	 * @param timeOfExam
	 *            the timeOfExam to set
	 */
	public void setTimeOfExam(String timeOfExam) {
		TimeOfExam = timeOfExam;
	}

	/**
	 * @return the roomOfCourse
	 */
	public String getRoomOfCourse() {
		return RoomOfCourse;
	}

	/**
	 * @param roomOfCourse
	 *            the roomOfCourse to set
	 */
	public void setRoomOfCourse(String roomOfCourse) {
		RoomOfCourse = roomOfCourse;
	}

	/**
	 * @return the campus
	 */
	public String getCampus() {
		return Campus;
	}

	/**
	 * @param campus
	 *            the campus to set
	 */
	public void setCampus(String campus) {
		Campus = campus;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return Remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		Remark = remark;
	}

	/**
	 * @return the methosOfTeach
	 */
	public String getMethosOfTeach() {
		return MethosOfTeach;
	}

	/**
	 * @param methosOfTeach
	 *            the methosOfTeach to set
	 */
	public void setMethosOfTeach(String methosOfTeach) {
		MethosOfTeach = methosOfTeach;
	}

	/**
	 * @return the shortTerm
	 */
	public String getShortTerm() {
		return ShortTerm;
	}

	/**
	 * @param shortTerm
	 *            the shortTerm to set
	 */
	public void setShortTerm(String shortTerm) {
		ShortTerm = shortTerm;
	}

	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return Capacity;
	}

	/**
	 * @param capacity
	 *            the capacity to set
	 */
	public void setCapacity(String capacity) {
		Capacity = capacity;
	}

	/**
	 * @return the textBook
	 */
	public String getTextBook() {
		return TextBook;
	}

	/**
	 * @param textBook
	 *            the textBook to set
	 */
	public void setTextBook(String textBook) {
		TextBook = textBook;
	}

	/**
	 * @return the nativeChoosedNum
	 */
	public String getNativeChoosedNum() {
		return NativeChoosedNum;
	}

	/**
	 * @param nativeChoosedNum
	 *            the nativeChoosedNum to set
	 */
	public void setNativeChoosedNum(String nativeChoosedNum) {
		NativeChoosedNum = nativeChoosedNum;
	}

	/**
	 * @return the allChoosedNum
	 */
	public String getAllChoosedNum() {
		return AllChoosedNum;
	}

	/**
	 * @param allChoosedNum
	 *            the allChoosedNum to set
	 */
	public void setAllChoosedNum(String allChoosedNum) {
		AllChoosedNum = allChoosedNum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChooseCourseEntity [Teacher=" + Teacher + ", College="
				+ College + ", HourPerWeek=" + HourPerWeek + ", TypeOfExam="
				+ TypeOfExam + ", TimeOfExam=" + TimeOfExam + ", RoomOfCourse="
				+ RoomOfCourse + ", Campus=" + Campus + ", Remark=" + Remark
				+ ", MethosOfTeach=" + MethosOfTeach + ", ShortTerm="
				+ ShortTerm + ", Capacity=" + Capacity + ", TextBook="
				+ TextBook + ", NativeChoosedNum=" + NativeChoosedNum
				+ ", AllChoosedNum=" + AllChoosedNum + ", BookOrderdId="
				+ BookOrderId + ", CourseId=" + CourseId + "]";
	}

}
