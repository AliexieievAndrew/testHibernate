package config;


import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    // запускается при старте приложения
    public void onStartup(ServletContext servletContext) throws ServletException {
        // в этом объекте нужно регестрировать config-класы !!!!!!!!!!!
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class,WebConfig.class);
        context.setServletContext(servletContext);

        // создаем диспатчер-сервлет
        // Could not resolve view with name 'index' in servlet with name 'dispatcher' - ПРОВЕРИТЬ СТРУКТУРУ ПРОЕКТА
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}