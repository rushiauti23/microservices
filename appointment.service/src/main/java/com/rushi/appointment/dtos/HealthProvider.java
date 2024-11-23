package com.rushi.appointment.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthProvider {

    private String id;
    private String hpName;
    private String hpDepartment;
    private Boolean isDailyAvailable;
    private List<Long> daysAvailable;
    private LocalTime dutyStartTime;
    private LocalTime dutyEndTime;

}