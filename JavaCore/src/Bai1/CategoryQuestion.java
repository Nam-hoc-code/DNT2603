package Bai1;

public class CategoryQuestion {
    private int categoryId;
    private CategoryName catetoryName;

    public CategoryQuestion(int categoryId, CategoryName catetoryName) {
        this.categoryId = categoryId;
        this.catetoryName = catetoryName;
    }
    public void printInformation() {
        System.out.println("Category Id : " + categoryId);
        System.out.println("Category Name : " + catetoryName);
    }

}
