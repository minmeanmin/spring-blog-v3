package shop.mtcoding.blog.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class) // IoC 등록코드
@DataJpaTest // Datasource(connection pool), EntityManager(PC(영속성) 관리 매니저)
public class UserRepositoryTest {
    @Autowired // DI
    private UserRepository userRepository;

    @Test
    public void findByUsernameAndPassword_test(){
        // given
        UserRequest.LoginDTO loginDTO = new UserRequest.LoginDTO();
        loginDTO.setUsername("ssar");
        loginDTO.setPassword("1234");

        // when
        User user = userRepository.findByUsernameAndPassword(loginDTO);

        // then
        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");

    }
}
