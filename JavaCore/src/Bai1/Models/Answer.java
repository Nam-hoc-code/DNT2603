package Bai1.Models;

public class Answer {
    private Long answerId;
    private String content;
    private Question question;
    private Boolean isCorrect ;

    public Answer(Long answerId,String content,Question question,Boolean isCorrect) {
        this.answerId = answerId;
        this.content = content;
        this.question = question;
        this.isCorrect = isCorrect;
    }



    public void printInformation() {
        System.out.println("Answer Id : " + answerId);
        System.out.println("Content : " + content);
        System.out.println("Question Id : " + question.getQuestionId());
        System.out.println("Correct : " + isCorrect);
    }
}
