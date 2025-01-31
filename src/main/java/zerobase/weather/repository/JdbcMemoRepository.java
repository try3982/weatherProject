package zerobase.weather.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import zerobase.weather.domain.Memo;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcMemoRepository {
    private final JdbcTemplate jdbcTemplate;

    /**
     * appication.properties에 datasource 정보들을 datasource객체에 담기게 되고
     *  이를 활용해서 jdbcTemplete을 만들어서 jdbctemplete 변수에 넣어준다.
     */
    @Autowired
    public JdbcMemoRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //메모라는 객체를 조회하고 저장수 있어야 한다.
    //memo 클래스의 값을 저장하면 mysql에 저장이 되고 반환값은 그 memo를 반환한다.
    public Memo save(Memo memo) {
        //jdcb 특징 : 쿼리문을 직접 써야 한다.

        //memo라는 테이블에 넣어준다
        String sql = "INSERT INTO memo VALUES (?, ?)";

//          jdbc 템플릿을 위에 물음표엘 드어갈 데이터를 업테이트 해준다.
//
        jdbcTemplate.update(sql, memo.getId(), memo.getText());

        // save해준 memo를 리턴해준다.
        return memo;
    }
    // 전체 메모들을 찾아 볼거니까 반환형식은  List<Memo> 이다.
    public List<Memo> findAll() {
        // memo 테이블에 있는 모든 객체가 반환됨
        String sql = "SELECT * FROM memo";
        // 이걸 스프링에서 가능하게 하기 위해서
        //jdbcTemplate이 mysql DB에 가서 SELECT * FROM memo를 던지고
        // 던졌을 때 반환된 데이터들을 (resultSet형태) memeoRowMapper함수를 이용해서 memo 객체로 가져왔다.
        return jdbcTemplate.query(sql, memoRowMapper());

    }
    //Optional 은 id가 3인 객체를 찾는다고 했을 때 3이 없는 경우에
    // Optional 객체로 랩핑해줘서 혹시 모를 null값ㅇ르 처리하기 쉽개 해두는 자바의 함수이다.
    public Optional<Memo> findById(int id) {
        String sql = "SELECT * FROM memo WHERE id =?";
        return jdbcTemplate.query(sql, memoRowMapper(), id).stream().findFirst();
    }

    /**
     * Row Mapper란?
     * jdbc를 통해서 mysql 데이터베이스에서 데이터를 가져오면 그 값은 resultset 형식이다.
     * ReulstSet은 - > {id=1,text='this is memo!'} 형식이다.
     * 이런 형식을 springboot에 만들어둔 memo 클래스에 대입시켜야 한다.
     * reusltSet을 memo라는 형식으로 맵핑해주는것을 로우라고 한다.
     *
     */
    private RowMapper<Memo> memoRowMapper() {
        //rs는 resultset줄임

        return (rs,rowNum) -> new Memo( // rs과 rowNum을 가지고 memo객체를 반환해 준다.
                rs.getInt("id"), // memo 객체는 컬럼라벨의 id를 가져오고
                rs.getString("text") // 메모는 스트링값을 가져오고 이걸 텍스트에  넣어주겠다.
        );

    }

}
