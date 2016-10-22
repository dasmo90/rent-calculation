package de.dasmo90.business.rc.web.spring;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebappInitializer implements WebApplicationInitializer {

	public static final String PROFILE_DEVELOP = "development";
	public static final String PROFILE_PRODUCTION = "production";
	private static final Logger LOGGER = LoggerFactory.getLogger(WebappInitializer.class);
	private static final String[] PROFILES = new String[]{PROFILE_DEVELOP, PROFILE_PRODUCTION};

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		String profile = System.getProperty("wacken.profile");
		if (!ArrayUtils.contains(PROFILES, profile)) {
			LOGGER.warn("Jukeox profile \"{}\" not available.", profile);
			profile = PROFILE_DEVELOP;
		}
		servletContext.setInitParameter("spring.profiles.active", profile);

		WebApplicationContext context = getContext();
		servletContext.addListener(new ContextLoaderListener(context));
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"DispatcherServlet", new DispatcherServlet(context));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/*");

	}

	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(SpringWebConfiguration.class);
		return context;
	}
}
