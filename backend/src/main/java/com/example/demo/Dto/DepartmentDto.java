package com.example.demo.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
        @NotBlank(message = "name is blank in department")
        @Size(min = 2 , max = 20 , message = "name size should be in department [2,20]")
        private String name;

        @NotBlank(message = "location is blank in department")
        @Size(min = 2 , max = 40 , message = "location size should be [2,40] in department")
        private String location;
}
