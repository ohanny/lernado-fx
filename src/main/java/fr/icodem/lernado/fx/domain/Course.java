package fr.icodem.lernado.fx.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    private StringProperty id = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty description = new SimpleStringProperty();

    public static Course of(String id, String title, String description) {
        Course course = new Course();
        course.setId(id);
        course.setTitle(title);
        course.setDescription(description);

        return course;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title=" + title +
                ", description=" + description +
                '}';
    }

    // getters and setters
    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
