package demo.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//@WebListener
public class CountListener2 implements HttpSessionListener {
    private int count;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        count++;
        httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
        System.out.println("新增在线人数，当前session在线人数: "+count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        count--;
        httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
        System.out.println("删减在线人数，当前session在线人数: "+count);
    }
}
