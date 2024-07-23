package com.qhkj.nettychatserver.bean.request;

import com.qhkj.nettychatserver.bean.domain.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends User {

    //页数
    private Integer pageNum = 1;

    //条数
    private Integer pageSize = 10;
    private String createTimeBegin;
    private String createTimeEnd;

}
