package com.exam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("tr_exam")
public class TrExam {
    @TableId(value = t.id,type = IdType.AUTO)
    private int id;
    @TableField(t.question)
    private String question;
    @TableField(t.answer)
    private String answer;

    public TrExam(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public static final class t{
        public static final String id = "id";
        public static final String question = "question";
        public static final String answer = "answer";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "TrExam{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
