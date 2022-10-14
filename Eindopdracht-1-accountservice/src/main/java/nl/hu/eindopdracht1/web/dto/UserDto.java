package nl.hu.eindopdracht1.web.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserDto {
    private String username;
    private String password;
}
