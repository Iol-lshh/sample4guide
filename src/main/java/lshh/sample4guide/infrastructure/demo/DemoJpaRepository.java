package lshh.sample4guide.infrastructure.demo;

import lshh.sample4guide.domain.demo.Demo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoJpaRepository extends JpaRepository<Demo, Long>{
}