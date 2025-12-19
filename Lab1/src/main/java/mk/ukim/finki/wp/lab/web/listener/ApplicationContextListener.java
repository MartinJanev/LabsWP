//package mk.ukim.finki.wp.lab.web.listener;
//
//import jakarta.servlet.ServletContextEvent;
//import jakarta.servlet.ServletContextListener;
//import jakarta.servlet.annotation.WebListener;
//
//import java.util.HashMap;
//
//@WebListener
//public class ApplicationContextListener implements ServletContextListener {
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ServletContextListener.super.contextInitialized(sce);
//        sce.getServletContext().setAttribute("hashMapBooks", new HashMap<String, Integer>());
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//        ServletContextListener.super.contextDestroyed(sce);
//        sce.getServletContext().removeAttribute("hashMapBooks");
//    }
//}