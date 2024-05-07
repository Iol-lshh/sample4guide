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
}
