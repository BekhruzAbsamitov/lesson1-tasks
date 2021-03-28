package uz.pdp.demo.task2.entiry;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {

    private String message;
    private boolean status;
}
