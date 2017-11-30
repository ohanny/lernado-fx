package fr.icodem.lernado.fx.events;

import fr.icodem.lernado.fx.domain.Course;
import javafx.collections.ObservableList;

public class SearchCourseResultEvent {
    private ObservableList<Course> courses;

    public static SearchCourseResultEvent of(ObservableList<Course> courses) {
        SearchCourseResultEvent event = new SearchCourseResultEvent();
        event.courses = courses;

        return event;
    }

    @Override
    public String toString() {
        return "SearchCoursesResultEvent{" +
                "courses=" + courses +
                '}';
    }

    public ObservableList<Course> getCourses() {
        return courses;
    }
}
