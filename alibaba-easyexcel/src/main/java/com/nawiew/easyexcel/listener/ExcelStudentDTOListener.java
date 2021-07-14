package com.nawiew.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.nawiew.easyexcel.dto.ExcelStudentDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nawiew
 * @packageName:com.nawiew.easyexcel.listener
 * @ClassName:ExcelStudentDTOListener
 * @Description:
 * @date 2021/7/6 11:23
 */
@Slf4j
public class ExcelStudentDTOListener extends AnalysisEventListener<ExcelStudentDTO> {
    /**
     * 这个每一条数据解析都会来调用
     */
    @Override
    public void invoke(ExcelStudentDTO excelStudentDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}",excelStudentDTO);
    }
    /**
     * 所有数据解析完成了 都会来调用
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }
}
