package lshh.sample4guide.controller.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lshh.sample4guide.common.library.ApiResponse;
import lshh.sample4guide.domain.demo.DemoService;
import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoAddCredit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(DemoController.class)
public class DemoControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    DemoService demoService;

    private  final Logger log = Logger.getLogger(DemoControllerTest.class.getName());
    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    @DisplayName("모든 데모 정보를 조회한다.")
    public void all() throws Exception {
        given(demoService.findAll()).willReturn(List.of());

        MvcResult result = mockMvc.perform(get("/demo/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        log.info(result.getResponse().getContentAsString());
        verify(demoService, times(1)).findAll();
    }

    @Test
    @DisplayName("데모 정보를 생성한다.")
    public void test_create() throws Exception {
        given(demoService.create(any(DemoCreation.class))).willReturn(1L);

        MvcResult result = mockMvc.perform(post("/demo/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "name": "lshh"
                                }
                                """)))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        log.info(responseJson);
        verify(demoService, times(1)).create(any(DemoCreation.class));
        ApiResponse<Integer> response = OBJECT_MAPPER.readValue(responseJson, ApiResponse.class);
        assertEquals(1, response.getPayload());
    }

    @Test
    @DisplayName("데모 크레딧을 추가한다.")
    public void test_addCredit() throws Exception {
        MvcResult result = mockMvc.perform(post("/demo/add/credit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("""
                                {
                                    "userId": "lshh",
                                    "credit": 100
                                }
                                """)))
                .andExpect(status().isOk())
                .andReturn();
        log.info(result.getResponse().getContentAsString());
        verify(demoService, times(1)).addCredit(any(DemoAddCredit.class));
    }
}
