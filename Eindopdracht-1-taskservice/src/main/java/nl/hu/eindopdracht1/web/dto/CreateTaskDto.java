package nl.hu.eindopdracht1.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateTaskDto {
    private String columnId;
    private String taskDescription;
}
