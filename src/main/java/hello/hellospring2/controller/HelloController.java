package hello.hellospring2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")  // index.html 에서 /hello 로 들어왔을때 이곳을 불러옴
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
//        다른곳에서 date 를 불러오면 hello!! 으로 치환됨
        return "hello";
//        resources 의 hello.html 로 이동됨
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
//    Body부에 해당 리턴을 넣어주겠다 할때 사용
//    해당 문자만 입력됨  사용X
    public String helloString(@RequestParam("name") String name){
        return " hello " + name; //hello spring(name)
    }

    @GetMapping("hello-api")
    @ResponseBody
    //객체 반환 방식
    public Hello helloApi(@RequestParam("name") String name){
//JSON 방식
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }


//프로퍼티 접근방식
    // 자바빈
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
