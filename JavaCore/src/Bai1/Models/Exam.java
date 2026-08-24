package Bai1.Models;

import java.util.Date;

public class Exam {
    private int examId;
    private String code;
    private String title;
    private CategoryQuestion categoryQuestion;  // categoryId
    private int duration;
    private Account account;// creatorId
    private Date creationDate;


    public Exam(int examId, String code, String title,Date creationDate) {
        this.examId = examId;
        this.code = code;
        this.title = title;
        this.creationDate = creationDate;
    }

    public int getExamID() {
        return examId;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
    public int getDuration() {
        return duration;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void printInformation() {
        System.out.println("Exam Id : " + examId);
        System.out.println("Code : " + code);
        System.out.println("Title : " + title);
        System.out.println("CategoryId : " + categoryQuestion.getCategoryId());
        System.out.println("Duration : " + duration);
        System.out.println("CreatorId : " + account.getAccountId());
        System.out.println("CreationDate : " + creationDate);
    }
}
