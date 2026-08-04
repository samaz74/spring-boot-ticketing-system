package com.peyman.ticketing.service;

import com.peyman.ticketing.exeption.DuplicateResourceException;
import com.peyman.ticketing.exeption.InvalidOperationException;
import com.peyman.ticketing.exeption.ResourceNotFoundException;
import com.peyman.ticketing.model.SubSystem;
import com.peyman.ticketing.model.SupportAccess;
import com.peyman.ticketing.model.User;
import com.peyman.ticketing.repository.SupportAccessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupportAccessService {
    private final SupportAccessRepository supportAccessRepository;
    private final UserService userService;
    private final SubSystemService subSystemService;
    private SupportAccessService(SupportAccessRepository supportAccessRepository, UserService userService, SubSystemService subSystemService) {
        this.supportAccessRepository = supportAccessRepository;
        this.userService = userService;
        this.subSystemService = subSystemService;
    }
    public SupportAccess grantAccess(Long userId, Long subSystemId) {
        User user= userService.getById(userId).get();
        SubSystem subSystem= subSystemService.getById(subSystemId).get();
        if(!hasAccess(userId, subSystemId)){
            SupportAccess supportAccess= new SupportAccess();
            supportAccess.setUser(user);
            supportAccess.setSubSystem(subSystem);
            return supportAccessRepository.save(supportAccess);
        }else throw new DuplicateResourceException("دسترسی اعلامی تکراری است");
    }
    public void revokeAccess(Long userId, Long subSystemId) {
        if(hasAccess(userId, subSystemId)) {
            User user = userService.getById(userId).get();
            SupportAccess supportAccess = supportAccessRepository.getSupportAccessByUser_IdAndSubSystem_Id(userId, subSystemId).getFirst();
            supportAccessRepository.delete(supportAccess);
        } else throw new InvalidOperationException("دسترسی یافت نشد.");
    }
    public List<SupportAccess> getSupportAccessByUser_Id(Long userId) {
        return supportAccessRepository.getSupportAccessByUser_Id(userId);
    }
    public List<SupportAccess> getSupportAccessBySubSystem_Id(Long subSystemId) {
        return supportAccessRepository.getSupportAccessBySubSystem_Id(subSystemId);
    }
    public Boolean hasAccess(Long userId, Long subSystemId) {
        User user = userService.getById(userId).get();
        SubSystem subSystem= subSystemService.getById(subSystemId).get();
        return supportAccessRepository.existsByUserAndSubSystem(user, subSystem);

    }

}
