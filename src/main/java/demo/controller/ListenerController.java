package demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/test")
public class ListenerController {

    @GetMapping(value="/testListenerLogin")
    @ResponseBody
    public String testListenerLogin(HttpServletRequest request){
        System.out.println("当前在线: "+request.getSession().getId());
        System.out.println("当前在线人数: "+request.getSession().getServletContext().getAttribute("count"));
        return "hello ";
    }

    @GetMapping("/testRequestListener")
    @ResponseBody
    public String testRequestListener(HttpServletRequest request){
        System.out.println("当前request: "+request);
        System.out.println("当前request数: "+request.getServletContext().getAttribute("count"));
        System.out.println("即将返回mv");
        return "hello hi";
    }
}
