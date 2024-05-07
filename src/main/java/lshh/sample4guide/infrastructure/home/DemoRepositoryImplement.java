package lshh.sample4guide.infrastructure.home;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.demo.Demo;
import lshh.sample4guide.domain.demo.DemoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class DemoRepositoryImplement implements DemoRepository {
    private final DemoJpaRepository demoJpaRepository;

    @Override
    public List<Demo> findAll() {
        return demoJpaRepository.findAll();
    }

    @Override
    public Long save(Demo demo) {
        demoJpaRepository.save(demo);
        return demo.getId();
    }
}
