package edu.ncsu.csc216.wolf_scheduler.scheduler;	
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;
/**
 * Creating the WolfScheduler class, outline of the catalog and schedule. There are many methods
 * in this class that work with the catalog and schedule. There are three fields in this class,
 * the schedule and catalog which are ArrayLists of type Course, and then the title field which
 * is a string, and represents the schedule title.
 * @author David Mond
 */
public class WolfScheduler {
	/** Catalog for all the courses*/
	private ArrayList<Course> catalog = new ArrayList<Course>();
	/** Schedule to see which courses student is enrolled in */
	private ArrayList<Course> schedule = new ArrayList<Course>();
	/** Title of schedule, defaulted to My Schedule */
	private String title;

	/** 
	 * Constructor that finds the correct file and sets title to the schedule in the catalog.
	 * @param title Title of schedule, reads it into the Course if file is found.
	 */
	public WolfScheduler(String title) {
		schedule = new ArrayList<Course>();
		//sets default title to 'My Schedule'.
		this.title = "My Schedule";
		//try to read title
		try {
			catalog = CourseRecordIO.readCourseRecords(title);
		}
		//if file doesn't exist, throw exception.
		catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}
	/**
	 * Get the catalog and set it up with correct rows.
	 * @return 2d string array for catalog with rows of name, section, and title.
	 */
	public String[][] getCourseCatalog() {
		String[][] catArray = new String[catalog.size()][3];
		//if empty catalog, return empty 2d array
		if(catalog.size() == 0) {
			return new String[0][0]; 
		}
		else {
			for(int i = 0; i < catalog.size(); i++) {
				//row 1 name
				catArray[i][0] = catalog.get(i).getName();
				//row 2 section
				catArray[i][1] = catalog.get(i).getSection();
				//row 3 title
				catArray[i][2] = catalog.get(i).getTitle();
			}
		}
		return catArray;
	}
	/**
	 * Get scheduled courses for name, section, and title.
	 * @return 2d string array for schedule with rows of name, section, and title.
	 */
	public String[][] getScheduledCourses() {
		String[][] catArray = new String[schedule.size()][3];
		//if empty schedule, return empty 2d array
		if(schedule.size() == 0) {
			return new String[0][0]; 
		}
		else {
			for(int i = 0; i < schedule.size(); i++) {
				//row 1 name
				catArray[i][0] = schedule.get(i).getName();
				//row 2 section
				catArray[i][1] = schedule.get(i).getSection();
				//row 3 title
				catArray[i][2] = schedule.get(i).getTitle();
			}
		}
		return catArray;
	}
	/**
	 * Get full scheduled courses for name, section, title, credits, instructorId, and meeting string.
	 * @return 2d string array with all the correct implemented rows.
	 */
	public String[][] getFullScheduledCourses() {
		String[][] catArray = new String[schedule.size()][6];
		//if empty schedule, return empty 2d array
		if(schedule.size() == 0) {
			return new String[0][0]; 
		}
		else {
			//create schedule with correct rows
			for(int i = 0; i < schedule.size(); i++) {
				catArray[i][0] = schedule.get(i).getName();
				catArray[i][1] = schedule.get(i).getSection();
				catArray[i][2] = schedule.get(i).getTitle();
				catArray[i][3] = String.valueOf(schedule.get(i).getCredits());
				catArray[i][4] = schedule.get(i).getInstructorId();
				catArray[i][5] = schedule.get(i).getMeetingString();
			}
		}
		return catArray;
	}
	/**
	 * Get course from the catalog
	 * @param name name of the course
	 * @param section section number of the course
	 * @return Course object with correct name and section.
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			//if name and course match in catalog, return it
			if(catalog.get(i).getName().equals(name) && 
					catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		//else name and course don't exist, return null
		return null;
	}
	/**
	 * Add course to schedule with correct name and section.
	 * @param name name of course
	 * @param section section number of course
	 * @return true if course successfully added, false if not.
	 */
	public boolean addCourseToSchedule(String name, String section) {
		
		Course addedCourse = catalog.get(0);
		//if course doesn't exist, return false
		if(getCourseFromCatalog(name, section) == null) {
			return false;
		}
		else {
			for(int i = 0; i < catalog.size(); i++) {
				//if course is in catalog with correct name and section, receive it
				if(catalog.get(i).getName().equals(name) && 
						catalog.get(i).getSection().equals(section)) {
					addedCourse = catalog.get(i);
				}
			}
		}
		for(int i = 0; i < schedule.size(); i++) {
			//if name of course already in schedule, throw exception
			if(schedule.get(i).getName().equals(name)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}	
		}
		//add course to schedule
		schedule.add(addedCourse);
		return true;
	}
	/**
	 * Remove correct course from schedule if it exists.
	 * @param name name of course
	 * @param section section number of course
	 * @return true if course successfully removed, false if course does not exist in schedule.
	 */
	public boolean removeCourseFromSchedule(String name, String section) {
		Course tempCourse = catalog.get(0);
		for(int i = 0; i < schedule.size(); i++) {
			//if course name and section exists in schedule, remove course and return true
			if(schedule.get(i).getName().equals(name) && 
					schedule.get(i).getSection().equals(section)) {
				tempCourse = schedule.get(i);
				schedule.remove(tempCourse);
				return true;
			}
		}
		//else course does not exist in schedule, return false
		return false;
	}
	/**
	 * Get schedule title
	 * @return title of schedule
	 */
	public String getScheduleTitle() {
		//returns schedule title.
		return title;
	}
	/**
	 * Exports schedule based off filename if it exists.
	 * @param filename filename to export schedule
	 */
	public void exportSchedule(String filename) {
		//try if filename exists with schedule
		try {
			CourseRecordIO.writeCourseRecords(filename, schedule);
		}
		//if does not exist, throw exception
		catch(IOException e){
			throw new IllegalArgumentException("The file cannot be saved.");
		}
	}
	/**
	 * Resets the course schedule back to empty Array List.
	 */
	public void resetSchedule() {
		//empty array list
		schedule = new ArrayList<Course>();
		
	}
	/**
	 * Sets the schedule title given it is not null.
	 * @param title name of title schedule, cannot be null.
	 */
	public void setScheduleTitle(String title) {
		//if title is null, throw exception
		if(title == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		//sets title to input title name.
		this.title = title;
	}
	
}
