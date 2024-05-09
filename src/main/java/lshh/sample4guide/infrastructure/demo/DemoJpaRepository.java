package lshh.sample4guide.infrastructure.demo;

import lshh.sample4guide.domain.demo.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DemoJpaRepository extends JpaRepository<Demo, Long>{
    Optional<Demo> findByName(String name);
}
