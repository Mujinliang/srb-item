package com.nawiew.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.nawiew.easyexcel.dto.ExcelStudentDTO;
import com.nawiew.easyexcel.listener.ExcelStudentDTOListener;
import org.junit.Test;

/**
 * @author nawiew
 * @packageName:com.nawiew.easyexcel
 * @ClassName:ExcelReadTest
 * @Description:
 * @date 2021/7/6 11:25
 */
public class ExcelReadTest {
    /**
     *  第一种读（最简单）
     */
    @Test
    public void simpleReadXlsx() {
        String fileName="W:/file/project/srb-file/excel/simpleWrite.xlsx";

        EasyExcel.read(fileName, ExcelStudentDTO.class,new ExcelStudentDTOListener()).sheet().doRead();
    }

    /**
     *  第二种读
     */
    @Test
    public void simpleReadXls() {

        String fileName = "W:/file/project/srb-file/excel/simpleWrite.xlsx";
        EasyExcel.read(fileName, ExcelStudentDTO.class, new ExcelStudentDTOListener()).excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }

}
