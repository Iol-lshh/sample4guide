package lshh.sample4guide.controller.demo;

import lombok.RequiredArgsConstructor;
import lshh.sample4guide.domain.demo.DemoService;
import lshh.sample4guide.domain.demo.dto.DemoCreation;
import lshh.sample4guide.domain.demo.dto.DemoCreditAdd;
import lshh.sample4guide.domain.demo.dto.DemoVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/demo")
@RestController
public class DemoController {
    private final DemoService demoService;

    @GetMapping("/all")
    public List<DemoVo> all() {
        return demoService.findAll();
    }

    @PostMapping("/create")
    public Long create(@RequestBody DemoCreation creation) {
        return demoService.create(creation);
    }

    @PostMapping("/add/credit")
    public void addCredit(@RequestBody DemoCreditAdd update) {
        demoService.addCredit(update);
    }
}
