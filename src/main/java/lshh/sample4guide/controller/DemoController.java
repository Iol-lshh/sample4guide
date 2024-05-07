package lshh.sample4guide.controller;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.demo.DemoService;
import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/demo")
@RestController
public class DemoController {
    private final DemoService demoService;

    @GetMapping("/all")
    public List<DemoView> all() {
        return demoService.findAll();
    }

    @PostMapping("/create")
    public Long create(@RequestBody DemoCreation creation) {
        return demoService.create(creation);
    }
}
