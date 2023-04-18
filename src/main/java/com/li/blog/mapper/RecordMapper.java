package com.li.blog.mapper;

import com.li.blog.entity.po.Record;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nine
 * @since 2023-04-12
 */

public interface RecordMapper extends BaseMapper<Record> {

    /**
     * 批量插入 仅适用于mysql
     *
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<Record> entityList);

}
