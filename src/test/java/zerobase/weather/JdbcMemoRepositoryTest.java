package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JdbcMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class JdbcMemoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository; //jdbcMemoRepository 클래스를 jdbcMemoRepository에 담아오게 된다.

    @Test
    void insertMemoTest () {
        // newMemeo객체에 아이디 1과 this is new memeo를 넣어준다.
      //  Memo newMemo = new Memo(1,"this is new memo");
        //mysql에 저장한다.
        // newMemo를 jdbcMemoRepository 클래스의 save메서드에 넣어준다.
     //   jdbcMemoRepository.save(newMemo);

        //given : 주어진것(newMemo)
        Memo newMemo = new Memo(2,"insertMemeoTest");

        //when : ~을 했을떄
        jdbcMemoRepository.save(newMemo);

        //then : 이럴것이다.
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "insertMemeoTest");
    }

    @Test
      void findAllMemoTest () {
        //given
        List<Memo> memoList = jdbcMemoRepository.findAll();
        //콘솔창에 출력
        // 객체 자체를 pirnt하기 때문에 객체 주소를 보여줌
        System.out.println(memoList);
        assertNotNull(memoList);

        //when
        //then
    }
}
