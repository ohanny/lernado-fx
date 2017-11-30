package fr.icodem.lernado.fx.events;

import fr.icodem.lernado.fx.domain.Course;

public class ShowCourseDetailEvent {
    private Course course;

    private ShowCourseDetailEvent(Course course) {
        this.course = course;
    }

    public static ShowCourseDetailEvent of(Course course) {
        return new ShowCourseDetailEvent(course);
    }

    public Course getCourse() {
        return course;
    }
}
