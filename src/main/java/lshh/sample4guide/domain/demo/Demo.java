package lshh.sample4guide.domain.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
public class Demo {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Integer credit;

    // for create
    public static Demo of(String name){
        Demo demo = new Demo();
        demo.name = name;
        demo.credit = 0;
        return demo;
    }

    public static Demo of(Long id, String name, Integer credit){
        Demo demo = new Demo();
        demo.id = id;
        demo.name = name;
        demo.credit = credit;
        return demo;
    }

    public void addCredit(Integer credit){
        this.credit += credit;
    }
}
