package Bai1;

import java.util.Date;

public class Question {
    private int questionId;
    private CategoryQuestion categoryQuestion; // tên thể loại
    private String content;
    private TypeQuestion typeQuestion; // kiểu câu hỏi
    private Account creator; //  người tạo
    private Date createDate;

    public Question( int questionId,String content,CategoryQuestion categoryQuestion,TypeQuestion typeQuestion,Account creator,Date createDate) {
        this.questionId = questionId;
        this.content = content;
        this.categoryQuestion = categoryQuestion;
        this.typeQuestion = typeQuestion;
        this.creator = creator;
    }

    public Question(int questionId, String content, TypeQuestion typeQuestion) {
        this.questionId = questionId;
        this.content = content;
        this.typeQuestion = typeQuestion;
    }

    public int getQuestionId() {
        return questionId;
    }
    public void printInformation(){
        System.out.println("Question Id : " + questionId);
        System.out.println("Category Id : " + categoryQuestion.getCategoryId());
        System.out.println("Content : " + content);
        System.out.println("Type Id : " + typeQuestion.getTypeId());
        System.out.println("Creator Id : " + creator.getAccountId());
        System.out.println("Create Date : " + createDate);
    }
}
