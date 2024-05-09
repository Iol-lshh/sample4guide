package lshh.sample4guide.infrastructure.demo;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.demo.Demo;
import lshh.sample4guide.domain.demo.DemoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Demo> findById(Long id) {
        return demoJpaRepository.findById(id);
    }

    @Override
    public Optional<Demo> findByName(String name) {
        return demoJpaRepository.findByName(name);
    }
}
