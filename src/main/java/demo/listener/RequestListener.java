package demo.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class RequestListener implements ServletRequestListener {

    private int count;

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        count--;
        System.out.println("request销毁");
        servletRequestEvent.getServletContext().setAttribute("count",count);
        System.out.println("当前request请求数: "+count);
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        count++;
        System.out.println("request初始化");
        servletRequestEvent.getServletContext().setAttribute("count",count);
        System.out.println("当前request请求数: "+count);
    }
}
