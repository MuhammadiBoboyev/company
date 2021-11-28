package uz.pdp.task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    @NotNull(message = "name bo'sh bo'lmasligi kerak")
    private String name;

    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kerak")
    private String phoneNumber;

    @NotNull(message = "strret bo'sh bo'lmasligi kerak")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'lmasligi kerak")
    private String homeNumber;

    @NotNull(message = "departmentId bo'sh bo'lmasligi kerak")
    private int departmentId;
}
