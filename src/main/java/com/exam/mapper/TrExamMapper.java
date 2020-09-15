package com.exam.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.exam.entity.TrExam;
import org.apache.ibatis.annotations.Select;

public interface TrExamMapper extends BaseMapper<TrExam> {
    @Select("SELECT max(id) AS id FROM tr_exam")
    public String selectMaxId();
}
