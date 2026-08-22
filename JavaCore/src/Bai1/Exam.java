package Bai1;

import java.sql.Date;
import java.time.LocalDate;

public class Exam {
    private int examId;
    private String code;
    private String title;
    private int categoryId;
    private int duration;
    private Long creatorId;
    private Date creationDate;


    public Exam( int examId,String code,String title) {
        this.examId = examId;
        this.code = code;
        this.title = title;
    }
    public void printInformation() {
        System.out.println("Exam Id : " + examId);
        System.out.println("Code : " + code);
        System.out.println("Title : " + title);
        System.out.println("CategoryId : " + categoryId);
        System.out.println("Duration : " + duration);
        System.out.println("CreatorId : " + creatorId);
        System.out.println("CreationDate : " + creationDate);
    }
}
