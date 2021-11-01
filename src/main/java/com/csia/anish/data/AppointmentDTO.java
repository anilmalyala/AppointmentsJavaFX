package com.csia.anish.data;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

    private Long id;
    private String appointmentDate;
    private String time;
    private String appointmentType;
    private String coordinatorName;
    private Long studentId;
    private String studentName;
}
