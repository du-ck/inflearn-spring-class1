package hello.hellospring.domain;


import javax.persistence.*;

/**
 * @Entity 를 선언하면
 * jpa가 관리하는 entity가 됨
 */
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //고객의 아이디가 아닌 시스템이 저장하는 id값
    //@Column(name = "username")    //DB컬럼명을 선언 가능
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
