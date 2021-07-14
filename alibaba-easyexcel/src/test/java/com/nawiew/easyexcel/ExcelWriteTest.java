package com.nawiew.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.nawiew.easyexcel.dto.ExcelStudentDTO;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author nawiew
 * @packageName:com.nawiew.easyexcel
 * @ClassName:ExcelWriteTest
 * @Description:
 * @date 2021/7/6 11:14
 */
public class ExcelWriteTest {
    /**
     * 第一种写法
     */
    @Test
    public void simpleWriteXlsx(){

        String fileName="W:/file/project/srb-file/excel/simpleWrite.xlsx";

        EasyExcel.write(fileName, ExcelStudentDTO.class).sheet("模板").doWrite(data());
    }

    /**
     * 第二种写法
     */
    @Test
    public void simpleWriteXls() {

        String fileName = "W:/file/project/srb-file/excel/simpleWrite.xlsx";
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, ExcelStudentDTO.class).excelType(ExcelTypeEnum.XLS).sheet("模板").doWrite(data());
    }

    //辅助方法
    private List<ExcelStudentDTO> data(){
        List<ExcelStudentDTO> list =new ArrayList<>();

        for (int i=0;i<65535;i++){
            ExcelStudentDTO data=new ExcelStudentDTO();
            data.setName("木子"+i);
            data.setBirthday(new Date());
            data.setSalary(123456.123456);
            list.add(data);
        }
        return list;
    }
}
