package zerobase.weather.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "memo") // memo 테이블에서 가져올것이다!
public class Memo {
    @Id // primary key는 id야
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //기본적인 키 생성을 db에 맡기겠다.
    private int id;
    private String text;


}
