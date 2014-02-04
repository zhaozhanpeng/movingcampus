package edu.hebtu.movingcampus.entity;

/**
 * @author leijie
 * @aim 介绍课程的实体类
 */
public class CourseIntroEntity {
	private String CourseId;//课程代码
	private String ChineseName; //课程中文名
	private String EnglishName; //课程英文名
	private String CourseCredit; //学分
	private String HourPerWeek; //周学时
	private String AllStudyTime; //总学时
	private String Type; //课程类别
	private String CoursePro; //课程性质
	private String College; //开课学院
	private String SuiltabelPeople; //使用对象
	private String CourseBefore; //选修课程
	private String CourseBelongTo; //课程归属
	private String AimAndFunc; //目的作用
	private String Intro; //课程简介
	private String TextBook; //教材
	private String Books; //参考书
	
	//以下是getter 和setter 还有 tostring
	/**
	 * @return the chineseName
	 */
	public String getChineseName() {
		return ChineseName;
	}
	/**
	 * @param chineseName the chineseName to set
	 */
	public void setChineseName(String chineseName) {
		ChineseName = chineseName;
	}
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
	 * @return the allStudyTime
	 */
	public String getAllStudyTime() {
		return AllStudyTime;
	}
	/**
	 * @param allStudyTime the allStudyTime to set
	 */
	public void setAllStudyTime(String allStudyTime) {
		AllStudyTime = allStudyTime;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		Type = type;
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
	 * @return the college
	 */
	public String getCollege() {
		return College;
	}
	/**
	 * @param college the college to set
	 */
	public void setCollege(String college) {
		College = college;
	}
	/**
	 * @return the suiltabelPeople
	 */
	public String getSuiltabelPeople() {
		return SuiltabelPeople;
	}
	/**
	 * @param suiltabelPeople the suiltabelPeople to set
	 */
	public void setSuiltabelPeople(String suiltabelPeople) {
		SuiltabelPeople = suiltabelPeople;
	}
	/**
	 * @return the courseBefore
	 */
	public String getCourseBefore() {
		return CourseBefore;
	}
	/**
	 * @param courseBefore the courseBefore to set
	 */
	public void setCourseBefore(String courseBefore) {
		CourseBefore = courseBefore;
	}
	/**
	 * @return the courseBelongTo
	 */
	public String getCourseBelongTo() {
		return CourseBelongTo;
	}
	/**
	 * @param courseBelongTo the courseBelongTo to set
	 */
	public void setCourseBelongTo(String courseBelongTo) {
		CourseBelongTo = courseBelongTo;
	}
	/**
	 * @return the aimAndFunc
	 */
	public String getAimAndFunc() {
		return AimAndFunc;
	}
	/**
	 * @param aimAndFunc the aimAndFunc to set
	 */
	public void setAimAndFunc(String aimAndFunc) {
		AimAndFunc = aimAndFunc;
	}
	/**
	 * @return the intro
	 */
	public String getIntro() {
		return Intro;
	}
	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		Intro = intro;
	}
	/**
	 * @return the textBook
	 */
	public String getTextBook() {
		return TextBook;
	}
	/**
	 * @param textBook the textBook to set
	 */
	public void setTextBook(String textBook) {
		TextBook = textBook;
	}
	/**
	 * @return the books
	 */
	public String getBooks() {
		return Books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(String books) {
		Books = books;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CourseIntroEntity [CourseId=" + CourseId + ", ChineseName="
				+ ChineseName + ", EnglishName=" + EnglishName
				+ ", CourseCredit=" + CourseCredit + ", HourPerWeek="
				+ HourPerWeek + ", AllStudyTime=" + AllStudyTime + ", Type="
				+ Type + ", CoursePro=" + CoursePro + ", College=" + College
				+ ", SuiltabelPeople=" + SuiltabelPeople + ", CourseBefore="
				+ CourseBefore + ", CourseBelongTo=" + CourseBelongTo
				+ ", AimAndFunc=" + AimAndFunc + ", Intro=" + Intro
				+ ", TextBook=" + TextBook + ", Books=" + Books + "]";
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
	
	
	
	
}
