package lshh.sample4guide.controller.demo;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.common.library.ApiResponse;
import lshh.sample4guide.domain.demo.DemoService;
import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoAddCredit;
import lshh.sample4guide.domain.demo.dto.DemoVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/demo")
@RestController
public class DemoController {
    private final DemoService demoService;

    @GetMapping("/all")
    public ApiResponse<List<DemoVo>> all() {
        return ApiResponse.ok(demoService.findAll());
    }

    @PostMapping("/create")
    public ApiResponse<Long> create(@RequestBody DemoCreation creation) {
        Long result = demoService.create(creation);
        return ApiResponse.ok(result);
    }

    @PostMapping("/add/credit")
    public ApiResponse<?> addCredit(@RequestBody DemoAddCredit update) {
        demoService.addCredit(update);
        return ApiResponse.ok();
    }
}
