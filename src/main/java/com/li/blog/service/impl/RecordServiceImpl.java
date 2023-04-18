package com.li.blog.service.impl;

import com.li.blog.bean.R;
import com.li.blog.entity.po.Record;
import com.li.blog.mapper.RecordMapper;
import com.li.blog.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;

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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private LinkedBlockingDeque<Record> cashDeque  = new LinkedBlockingDeque<>(2000);

    @Override
    @Async
    public void saveList(Record record) {
        this.putDeque(record);
    }

    @Override
    public R<String> getGuest() {
      return R.ok(redisTemplate.execute((RedisCallback<Long>) con-> con.bitCount("Guest".getBytes())).toString());
    }

    @SneakyThrows
    public void putDeque(Record record){
        cashDeque.put(record);
    }

    @Scheduled(fixedDelay = 60000)
    public void putData(){
        log.info("日志入库开始");
        ArrayList<Record> records = new ArrayList<>();
        int num = 0 ;
        while(!cashDeque.isEmpty()) {
            while (!cashDeque.isEmpty()&&num <= 200) {
                records.add(cashDeque.poll());
                num ++ ;
            }
            Integer integer = recordMapper.insertBatchSomeColumn(records);
            log.info("日志入库{}",integer);
            records.clear();
            num = 0;
        }
    }
}
