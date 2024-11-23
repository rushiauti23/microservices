package com.rushi.healthprovider.models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.List;

@Data
@Document(collation = "health_provider")
public class HealthProvider {

    @Id
    private String id;

    @NotBlank(message = "Health Provider name is required")
    private String hpName;

    @NotBlank(message = "Health provider department is required")
    private String hpDepartment;

    @NotNull(message = "Availability status is required")
    private boolean isDailyAvailable;

    @Size(max = 7, message = "daysAvailable should not exceed 7 days")
    private List<Long> daysAvailable;

    @NotNull(message = "dutyStartTime is required")
    private LocalTime dutyStartTime;

    @NotNull(message = "dutyEndTime is required")
    private LocalTime dutyEndTime;

}
