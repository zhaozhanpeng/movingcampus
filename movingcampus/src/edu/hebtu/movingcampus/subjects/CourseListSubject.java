package edu.hebtu.movingcampus.subjects;

import java.util.ArrayList;

import android.app.Activity;
import edu.hebtu.movingcampus.entity.Course;
import edu.hebtu.movingcampus.subject.base.Subject;

public class CourseListSubject extends Subject {

	private ArrayList<ArrayList<Course>> courseList;

	@Override
	public String getTag() {
		return CourseListSubject.class.getName();
	}

	public ArrayList<ArrayList<Course>> getCourseList(){
		if(!mesureChange(null))
			return courseList;
		else return null;
	}

	public void setCourseList(ArrayList<ArrayList<Course>> courselist ){
		this.courseList=courselist;;
	}
	//是否过期
	@Override
	public Boolean mesureChange(Activity context) {
		return null;
	}

}
