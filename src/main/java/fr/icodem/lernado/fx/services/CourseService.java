package fr.icodem.lernado.fx.services;

import fr.icodem.lernado.fx.domain.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.concurrent.CompletableFuture;

public class CourseService {

    public CompletableFuture<ObservableList<Course>> searchCourse(String query) {

        return CompletableFuture.completedFuture(FXCollections.observableArrayList(
                Course.of("abcd", "Java in Action", "This course Java ..."),
                Course.of("abcd", "Hibernate in Practice", "This course Hibernate ..."),
                Course.of("abcd", "Java EE : Starter to Ninja", "This course Java EE ...")
        ));
    }
}
