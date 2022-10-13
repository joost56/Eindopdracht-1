package nl.hu.eindopdracht1.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SwitchTaskDto {
    private String oldColumnId;
    private String newColumnId;
    private String taskId;
}
