package Bai1;

public class Group {
    private Long groupId ;
    private GroupName groupName ;

    public Group(Long groupId, GroupName groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
    public void printInfromation() {
        System.out.println("Department Id: " + groupId);
        System.out.println("Department Name: " + groupName);
    }
}
