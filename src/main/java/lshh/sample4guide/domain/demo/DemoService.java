package lshh.sample4guide.domain.demo;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoView;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DemoService {
    private final DemoRepository demoRepository;

    public List<DemoView> findAll() {
        List<Demo> list = demoRepository.findAll();
        return DemoView.of(list);
    }

    public Long create(DemoCreation creation) {
        Demo demo = creation.toEntity();
        return demoRepository.save(demo);
    }
}
