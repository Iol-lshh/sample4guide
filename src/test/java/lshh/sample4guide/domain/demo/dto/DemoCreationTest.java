package lshh.sample4guide.domain.demo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import lshh.sample4guide.domain.demo.Demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DemoCreationTest {

    @Test
    @DisplayName("DemoCreation 생성 성공")
    public void toEntity_shouldCreateValidDemoEntity_whenNameIsProvided() {
        String name = "name";

        DemoCreation demoCreation = new DemoCreation(name);

        Demo expectedDemo = Demo.of(name);
        Demo actualDemo = demoCreation.toEntity();

        assertEquals(expectedDemo.getName(), actualDemo.getName());

    }

    @Test
    @DisplayName("DemoCreation 생성 실패 - name null")
    public void demoCreationConstructor_shouldThrowException_whenNameIsNull() {
        String name = null;

        assertThrows(IllegalArgumentException.class, ()->{
            new DemoCreation(name);
        });
    }

    @Test
    @DisplayName("DemoCreation 생성 실패 - name 빈 문자열")
    public void demoCreationConstructor_shouldThrowException_whenNameIsEmpty() {
        String name = "";

        assertThrows(IllegalArgumentException.class, ()->{
            new DemoCreation(name);
        });
    }
}