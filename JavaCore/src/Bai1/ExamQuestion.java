package Bai1;

public class ExamQuestion {
    private int examId;
    private Long questionId;

    public ExamQuestion(int examId, Long questionId) {
        this.examId = examId;
        this.questionId = questionId;
    }
    public void printInformation() {
        System.out.println("Exam Id : " + examId);
        System.out.println("Question Id : " + questionId);
    }
}
