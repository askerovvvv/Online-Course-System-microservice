package com.example.auth.controller;

import com.example.auth.models.requestsDto.GroupRequestDto;
import com.example.auth.models.responsesDto.GroupResponseDto;
import com.example.auth.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/get/groups")
    public ResponseEntity<List<GroupResponseDto>> findAllGroups() {
        return ResponseEntity.status(HttpStatus.OK).body(groupService.findAllGroups());
    }

    @PostMapping("/add/group")
    public ResponseEntity<String> addGroup(@RequestBody GroupRequestDto groupRequestData) {
        groupService.addGroup(groupRequestData);
        return ResponseEntity.status(HttpStatus.OK).body("Group added successfully.");
    }

    @GetMapping("/get/group/by/id")
    public ResponseEntity<GroupResponseDto> findGroupById(@RequestParam("groupId") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(groupService.findGroupById(id));
    }

    @DeleteMapping("/delete/group/by/id")
    public ResponseEntity<String> deleteFacultyById(@RequestParam("groupId") long id) {
        groupService.deleteGroupById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Group deleted successfully.");
    }


}
