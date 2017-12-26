package fr.icodem.lernado.fx.views;

import com.google.inject.Injector;

import javax.inject.Inject;

public abstract class ViewFactory {

    @Inject private Injector injector;

    public <T extends View> T getView(String name)  {
        //try {
//            String className = getClass().getPackage().getName() + "." +
//                    name.replaceAll("-", "") + "." +
//                    CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, name) + "View";
//
//            Class<?> clazz = Class.forName(className);
            Class<?> clazz = getViewClass(name);
            return injector.getInstance((Class<T>) clazz);
//        } catch (ClassNotFoundException e) {
//            throw new ViewException("Failed instanciating view : " + name, e);
//        }
    }

    protected abstract Class<?> getViewClass(String name);

}
