package lshh.sample4guide.domain.demo;

import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoCreditAdd;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DemoServiceIntegrationTest {
    @Autowired
    private DemoService demoService;
    @Autowired
    private DemoRepository demoRepository;

    @Test
    @DisplayName("credit 추가 테스트 단일 성공")
    public void addCredit(){
        // given
        DemoCreation creation = new DemoCreation("test");
        demoService.create(creation);
        Optional<Demo> target = demoRepository.findByName("test");
        assertTrue(target.isPresent());
        Long id = target.get().getId();
        Integer credit = 100;
        DemoCreditAdd creditUpdate = new DemoCreditAdd(id, credit);

        // when
        demoService.addCredit(creditUpdate);

        // then
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Demo not found"));
        assertEquals(credit, demo.getCredit());
    }

    //@Test
    @DisplayName("credit 추가 동시성 테스트")
    public void addCreditConcurrent() throws InterruptedException {
        // given
        DemoCreation creation = new DemoCreation("test");
        demoService.create(creation);
        Optional<Demo> target = demoRepository.findByName("test");
        assertTrue(target.isPresent());
        Long id = target.get().getId();
        Integer originCredit = target.get().getCredit();
        Integer credit = 100;
        DemoCreditAdd creditAdd = new DemoCreditAdd(id, credit);

        // when
        int testCnt = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(testCnt);
        IntStream.range(0, testCnt)
                .forEach(i -> executorService.submit(() -> {
                    demoService.addCredit(creditAdd);
                }));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        // then
        Demo demo = demoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Demo not found"));
        assertEquals(originCredit + credit * testCnt, demo.getCredit());
    }
}
