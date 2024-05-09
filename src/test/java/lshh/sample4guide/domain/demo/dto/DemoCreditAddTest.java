package lshh.sample4guide.domain.demo.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DemoCreditAddTest {

    @Test
    @DisplayName("DemoCreditUpdate 생성 성공")
    public void testDemoCreditUpdate_success() {
        Long id = 1L;
        Integer credit = 100;
        DemoCreditAdd demoCreditUpdate = new DemoCreditAdd(id, credit);
        
        assertNotNull(demoCreditUpdate);
        assertEquals(id, demoCreditUpdate.getId());
        assertEquals(credit, demoCreditUpdate.getCredit());
    }

    @Test
    @DisplayName("DemoCreditUpdate 생성 실패 - credit null")
    public void testDemoCreditUpdate_failure_nullCredit() {
        Long id = 1L;
        assertThrows(IllegalArgumentException.class, () -> new DemoCreditAdd(id, null));
    }

    @Test
    @DisplayName("DemoCreditUpdate 생성 실패 - credit 음수")
    public void testDemoCreditUpdate_failure_negativeCredit() {
        Long id = 1L;
        Integer credit = -10;
        assertThrows(IllegalArgumentException.class, () -> new DemoCreditAdd(id, credit));
    }

    @Test
    @DisplayName("DemoCreditUpdate 생성 실패 - credit 0")
    public void testDemoCreditUpdate_failure_zeroCredit() {
        Long id = 1L;
        Integer credit = 0;
        assertThrows(IllegalArgumentException.class, () -> new DemoCreditAdd(id, credit));
    }
}