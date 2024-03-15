package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;

//    @PostMapping("/login")
//    public String login(UserRequest.LoginDTO requestDTO) {
//        User sessionUser = userRepository.findByUsernameAndPassword();
//
//        if(sessionUser == null) {
//            return "redirect:/login-form";
//        }
//
//        session.setAttribute("sessionUser", sessionUser);
//        return "redirect:/";
//    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO){
        User sessionUser = userRepository.save(requestDTO.toEntity());

        session.setAttribute("sessionUser", sessionUser); // 회원가입 시, 자동 로그인
        return "redirect:/login-form";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
