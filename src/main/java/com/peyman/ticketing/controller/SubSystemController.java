package com.peyman.ticketing.controller;

import com.peyman.ticketing.dto.SubSystemRequest;
import com.peyman.ticketing.dto.SubSystemResponse;
import com.peyman.ticketing.service.SubSystemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subsystems")
public class SubSystemController {
    private final SubSystemService subSystemService;
    public SubSystemController(SubSystemService subSystemService) {
        this.subSystemService = subSystemService;
    }

    @PostMapping("/{systemId}")
    public SubSystemResponse createSubSystem(@PathVariable("systemId") Long systemId, @RequestBody SubSystemRequest subSystem) {
        return subSystemService.create(subSystem,systemId);
    }
    @GetMapping("/{id}")
    public SubSystemResponse getSubSystemById(@PathVariable("id") Long id) {
        return subSystemService.getById(id);
    }
    @GetMapping("/systems/{systemId}")
    public List<SubSystemResponse> getSubSystemBySystem(@PathVariable("systemId") Long systemId) {
        return subSystemService.getBySystem(systemId);
    }
    @PatchMapping("/toggle/{id}")
    public void toggleSubSystem(@PathVariable("id") Long id) {
        subSystemService.toggleActive(id);
    }
}
