package com.li.blog.service;

import com.li.blog.bean.R;
import com.li.blog.entity.po.Article;
import com.li.blog.entity.po.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.scheduling.annotation.Async;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */
public interface RecordService extends IService<Record> {

    void saveList(Record record);

    R<String> getGuest();
}
