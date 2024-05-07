package lshh.sample4guide.domain.demo;

import java.util.List;

public interface DemoRepository {

    List<Demo> findAll();

    Long save(Demo demo);
}
