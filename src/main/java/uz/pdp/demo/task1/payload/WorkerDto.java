package uz.pdp.demo.task1.payload;

import lombok.Data;

@Data
public class WorkerDto {

    private String name;
    private String phoneNumber;
    private Integer departmentId;
    private Integer addressId;
}
