package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;
    @NotNull(message = "companyId bo'sh bo'lmasligi kerak")
    private int companyId;
}
