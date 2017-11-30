package fr.icodem.lernado.fx.views;

import fr.icodem.lernado.fx.views.courses.detail.CourseDetailView;
import fr.icodem.lernado.fx.views.courses.search.SearchCourseView;
import fr.icodem.lernado.fx.views.home.HomeView;
import fr.icodem.lernado.fx.views.login.LoginView;

import java.util.HashMap;
import java.util.Map;

public class LernadoViewFactory extends ViewFactory {

    private Map<String, Class<?>> nameToViewClass = new HashMap<>();

    public LernadoViewFactory() {
        nameToViewClass.put("home", HomeView.class);
        nameToViewClass.put("login", LoginView.class);
        nameToViewClass.put("search-course", SearchCourseView.class);
        nameToViewClass.put("course-detail", CourseDetailView.class);
    }

    @Override
    protected Class<?> getViewClass(String name) {
        return nameToViewClass.get(name);
    }
}
