package com.barclaycardus.hackathon.hocoders.service.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by abhishek on 11/06/16.
 */
public class ServiceInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.scan("com.barclaycardus.hackathon.hocoders.service");

        servletContext.addListener(new ContextLoaderListener(root));
    }

}
