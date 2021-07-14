package com.nawiew.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.nawiew.srb.core.mapper.DictMapper;
import com.nawiew.srb.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nawiew
 * @packageName:com.nawiew.srb.core.listener
 * @ClassName:ExcelDictDTOListener
 * @Description:
 * @date 2021/7/13 19:55
 */
@NoArgsConstructor
@Slf4j
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {

    private DictMapper dictMapper;

    //数据列表
    private List<ExcelDictDTO> list=new ArrayList<>();

    private static final  int BATCH_COUNT=5;

    //传入mapper对象
    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据：{}",excelDictDTO);
        //将数据存入数据列表
        list.add(excelDictDTO);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size()>=BATCH_COUNT){
            saveDate();
            list.clear();
        }
    }

    private void saveDate() {
        log.info("{}条数据被存储到数据库......",list.size());
        //调用mapper层的save
        dictMapper.insertBatch(list);
        log.info("{}条数据被存储到数据库成功！",list.size());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveDate();
        log.info("所有数据解析完成！");
    }
}
