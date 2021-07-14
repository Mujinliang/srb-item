package com.nawiew.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author nawiew
 * @packageName:com.atguigu.easyexcel.dto
 * @ClassName:ExcelStudentDTO
 * @Description:
 * @date 2021/7/6 11:13
 */
@Data
public class ExcelStudentDTO {
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("生日")
    private Date birthday;

    @ExcelProperty("薪资")
    private Double salary;
}
