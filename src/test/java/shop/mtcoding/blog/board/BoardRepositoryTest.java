package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        boardRepository.deleteById(id); // delete query 발동함

        // then
        System.out.println("deleteById_test : " + boardRepository.findAll().size());

    }

    @Test
    public void findAllV2_test(){
        List<Board> boardList = boardRepository.findAllV2();
        System.out.println("findAllV2_test : 조회완료 쿼리 2번");
        boardList.forEach(board -> {
            System.out.println(board);
        });
    }

    @Test
    public void randomquery_test(){
        // int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        int[] userIds = {1,2};

        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList();

        String q2 = "select u from User u where u.id in (";

        for (int i=0; i<userIds.length; i++){
            if(i==userIds.length-1){
                q2 = q2 + "?)";
            }else{
                q2 = q2 + "?, ";
            }
        }
        System.out.println("^ q2 : " + q2);
    }

    @Test
    public void findAll_custom_inquery_test(){
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for(int i : userIds){
            System.out.println(i);
        }

        // select * from user_tb where id in (3,2,1,1); -> 1을 한 번만 쓰도록 distinct...
        // select * from user_tb where id in (3,2,1);
    }

    @Test
    public void findAll_lazyloading_test(){
        // given


        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername()); // lazy loading
        });

        // then


    }

    @Test
    public void findAll_test(){
        //given

        //when
        boardRepository.findAll();

        //then

    }

    @Test
    public void findById_test() {
        int id = 1;

        boardRepository.findById(id);
    }
}