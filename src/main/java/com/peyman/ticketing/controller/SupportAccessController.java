package com.peyman.ticketing.controller;

import com.peyman.ticketing.model.SupportAccess;
import com.peyman.ticketing.service.SubSystemService;
import com.peyman.ticketing.service.SupportAccessService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/access")
public class SupportAccessController {
    private final SupportAccessService supportAccessService;
    public SupportAccessController(SupportAccessService supportAccessService) {
        this.supportAccessService = supportAccessService;
    }

    @PostMapping("/{userId}/{subSystemId}")
    public SupportAccess addSupportAccess(@PathVariable Long userId, @PathVariable Long subSystemId) {
        return supportAccessService.grantAccess(userId, subSystemId);
    }
    @GetMapping("/access/subSystem/{userId}")
    public List<SupportAccess> getSupportAccess(@PathVariable Long userId) {
        return supportAccessService.getSupportAccessByUser_Id(userId);
    }
    @GetMapping("/subSystem/{subSystemId}")
    public List<SupportAccess> getSupportAccessBySybSystemId(@PathVariable Long subSystemId) {
        return supportAccessService.getSupportAccessBySubSystem_Id(subSystemId);
    }
    @DeleteMapping("/{userid}/subsystem/{subsystemId}")
    public void deleteSupportAccess(@PathVariable Long userid, @PathVariable Long subsystemId) {
        supportAccessService.revokeAccess(userid, subsystemId);
    }

}
