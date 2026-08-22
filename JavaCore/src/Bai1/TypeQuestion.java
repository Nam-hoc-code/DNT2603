package Bai1;

public class TypeQuestion {
    private int typeId;
    private TypeName typeName;

    public TypeQuestion(int typeId, TypeName typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }
    public void printInformation() {
        System.out.println("Type Id : " + typeId);
        System.out.println("Type Name : " + typeName);
    }
}
