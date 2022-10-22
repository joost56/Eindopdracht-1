package nl.hu.eindopdracht1.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class EditTaskDto {
    private Long taskId;
    private String taskDescription;
}
