package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InterceptorController {
    @RequestMapping("/testInterceptor")
    public ModelAndView testInterceptor(){
        System.out.println("进入controller");
        ModelAndView mv = new ModelAndView();

        mv.setViewName("interceptor");
        System.out.println("即将返回modelAndView");
        return mv;
    }
}
