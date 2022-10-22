package nl.hu.eindopdracht1.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AssignUserToTaskDto {
    private String username;
    private Long taskId;
}
