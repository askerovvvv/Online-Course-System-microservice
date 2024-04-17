package com.example.auth.models.requestsDto;

import com.example.auth.models.entity.Group;
import com.example.auth.models.entity.StudentAddress;
import com.example.auth.models.responsesDto.TeacherResponseDto;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegisterRequestDto extends BaseAuthRequest {

    private int credit;
    private String citizenship;
    private Long studentGroupId;

    private StudentAddress studentAddress;

}
