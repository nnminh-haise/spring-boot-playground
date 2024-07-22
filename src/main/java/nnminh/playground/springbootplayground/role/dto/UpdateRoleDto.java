package nnminh.playground.springbootplayground.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateRoleDto extends CreateRoleDto {
    private Integer id;
}
