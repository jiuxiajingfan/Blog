package com.li.blog.service.impl;

import com.li.blog.bean.R;
import com.li.blog.entity.po.Record;
import com.li.blog.mapper.RecordMapper;
import com.li.blog.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    @Async
    public void saveList(Record record) {
        recordMapper.insert(record);
    }

    @Override
    public R<String> getGuest() {
      return R.ok(redisTemplate.execute((RedisCallback<Long>) con-> con.bitCount("Guest".getBytes())).toString());
    }
}
