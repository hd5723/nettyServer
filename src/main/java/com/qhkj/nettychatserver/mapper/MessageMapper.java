package com.qhkj.nettychatserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qhkj.nettychatserver.bean.domain.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
