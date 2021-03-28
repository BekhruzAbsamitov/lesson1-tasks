package uz.pdp.demo.task2.payload;

import lombok.Data;

import java.sql.Date;

@Data
public class UserPracticeDto {
    private Integer userId;
    private Integer problemId;
    private String username;
    private String userSolution;
    private Integer score;
    private Date date;
}
