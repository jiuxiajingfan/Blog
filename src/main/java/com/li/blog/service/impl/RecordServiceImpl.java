package com.li.blog.service.impl;

import com.li.blog.entity.po.Record;
import com.li.blog.mapper.RecordMapper;
import com.li.blog.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
@Service
@Slf4j
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Resource
    private RecordMapper recordMapper;

    @Override
    @Async
    public void saveList(Record record) {
        log.info("xieru");
        recordMapper.insert(record);
    }
}
