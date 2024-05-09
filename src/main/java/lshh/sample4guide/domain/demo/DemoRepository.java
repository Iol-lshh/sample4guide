package lshh.sample4guide.domain.demo;

import java.util.List;
import java.util.Optional;

public interface DemoRepository {

    List<Demo> findAll();

    Long save(Demo demo);

    Optional<Demo> findById(Long id);

    Optional<Demo> findByName(String name);
}
