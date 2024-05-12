package lshh.sample4guide.domain.demo;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.common.library.lock.AdvisoryLock;
import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoCreditAdd;
import lshh.sample4guide.domain.demo.dto.DemoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DemoService {
    private final DemoRepository demoRepository;

    @Transactional(readOnly = true)
    public List<DemoVo> findAll() {
        List<Demo> list = demoRepository.findAll();
        return DemoVo.of(list);
    }

    @Transactional
    public Long create(DemoCreation creation) {
        Demo demo = creation.toEntity();
        return demoRepository.save(demo);
    }

//    @AdvisoryLock(key = "addCredit_"+"#update")
    @Transactional
    public void addCredit(DemoCreditAdd update) {
        Demo demo = demoRepository.findById(update.getId())
                .orElseThrow(() -> new IllegalArgumentException("Demo not found"));
        demo.addCredit(update.getCredit());
        demoRepository.save(demo);
    }
}
