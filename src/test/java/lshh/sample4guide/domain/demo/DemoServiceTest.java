package lshh.sample4guide.domain.demo;

import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoAddCredit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class DemoServiceTest {

    @Mock
    DemoRepository demoRepository;
    @InjectMocks
    DemoService demoService;

    @Test
    @DisplayName("demo 전체 조회 테스트")
    void testFindAll() {
        // Arrange
        ArrayList<Demo> demos = new ArrayList<>();
        demos.add(Demo.of(1L, "test1", 100));
        demos.add(Demo.of(2L, "test2", 200));
        demos.add(Demo.of(3L, "test3", 300));
        given(demoRepository.findAll()).willReturn(demos);

        // Act
        var result = demoService.findAll();

        // Assert
        assertEquals(3, result.size());
    }


    @Test
    @DisplayName("demo 생성 테스트")
    void testCreate(){
        // Arrange
        DemoCreation creation = new DemoCreation("test");
        Demo demo = creation.toEntity();
        given(demoRepository.save(any(Demo.class))).willReturn(1L);

        // Act
        var result = demoService.create(creation);

        // Assert
        assertEquals(1L, result);
        assertEquals(0, demo.getCredit());
    }
    @Test
    @DisplayName("addCredit test with existing demo")
    void testAddCredit_ExistentDemo(){
        // Arrange
        Demo demo = Demo.of(1L, "test1", 100);
        given(demoRepository.findById(any(Long.class))).willReturn(Optional.of(demo));
        DemoAddCredit update = new DemoAddCredit(1L, 200);

        // Act
        demoService.addCredit(update);

        // Assert
        assertEquals(300, demo.getCredit());
    }

    @Test
    @DisplayName("addCredit test with non existent demo")
    void testAddCredit_NonExistentDemo() {
        // Arrange
        given(demoRepository.findById(any(Long.class))).willReturn(Optional.empty());
        DemoAddCredit update = new DemoAddCredit(1L, 200);

        // Act
        assertThrows(IllegalArgumentException.class, () -> demoService.addCredit(update));
    }
}
