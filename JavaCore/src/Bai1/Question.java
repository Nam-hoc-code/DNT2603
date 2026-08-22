package Bai1;

import java.util.Date;

public class Question {
    private Long questionId;
    private int categoryId;
    private String content;
    private int typeId;
    private Long creatorId;
    private Date createDate;

    public Question( Long questionId,String content,int categoryId) {
        this.questionId = questionId;
        this.content = content;
        this.categoryId = categoryId;
    }

    public void printInformation(){
        System.out.println("Question Id : " + questionId);
        System.out.println("Category Id : " + categoryId);
        System.out.println("Content : " + content);
        System.out.println("Type Id : " + typeId);
        System.out.println("Creator Id : " + creatorId);
        System.out.println("Create Date : " + createDate);
    }
}
