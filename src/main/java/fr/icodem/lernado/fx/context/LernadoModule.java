package fr.icodem.lernado.fx.context;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import fr.icodem.lernado.fx.services.UserService;
import fr.icodem.lernado.fx.views.LernadoViewFactory;
import fr.icodem.lernado.fx.views.ViewFactory;
import fr.icodem.lernado.fx.views.courses.detail.CourseDetailPresenter;
import fr.icodem.lernado.fx.views.courses.detail.CourseDetailView;
import fr.icodem.lernado.fx.views.courses.play_lesson.PlayLessonPresenter;
import fr.icodem.lernado.fx.views.courses.play_lesson.PlayLessonView;
import fr.icodem.lernado.fx.views.courses.search.SearchCoursePresenter;
import fr.icodem.lernado.fx.views.home.HomePresenter;
import fr.icodem.lernado.fx.views.home.HomeView;
import fr.icodem.lernado.fx.views.login.LoginPresenter;
import fr.icodem.lernado.fx.views.login.LoginView;
import fr.icodem.lernado.fx.views.courses.search.SearchCourseView;
import javafx.stage.Stage;

public class LernadoModule extends AbstractModule {
    private Stage stage; // main app stage

    public LernadoModule(Stage stage) {
        this.stage = stage;
    }

    @Override
    protected void configure() {
        bindConstant()
            .annotatedWith(Names.named("app.name"))
            .to("Lernado");


        // navigation scope
        NavigationScope navigationScope = new NavigationScope();
        bindScope(NavigationScoped.class, navigationScope);
        bind(NavigationScope.class).toInstance(navigationScope);

        bind(LernadoInjector.class).toInstance(LernadoInjector.Instance);
        bind(LernadoContext.class).in(Singleton.class);

        bind(EventBus.class).toInstance(new EventBus());
        bind(NavigationEventBus.class).in(NavigationScoped.class);

        bind(Stage.class).toInstance(stage);
        bind(ViewFactory.class).toInstance(new LernadoViewFactory());
        bind(Router.class).in(Singleton.class);

        // services
        bind(UserService.class).in(Singleton.class);

        // views added to the scene as root should be singletons
        // views added through includes in fxml should not be singletons
        bind(LoginView.class).in(Singleton.class);
        bind(HomeView.class).in(Singleton.class);
        bind(SearchCourseView.class).in(Singleton.class);
        bind(CourseDetailView.class).in(Singleton.class);
        bind(PlayLessonView.class).in(Singleton.class);

        // presenters should not be singletons
        bind(LoginPresenter.class);
        bind(HomePresenter.class);
        bind(SearchCoursePresenter.class);
        bind(CourseDetailPresenter.class);
        bind(PlayLessonPresenter.class);

    }

}
