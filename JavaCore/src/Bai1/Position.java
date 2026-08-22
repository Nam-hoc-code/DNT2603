package Bai1;

import Bai1.Enums.PositionName;

public class Position {
    private Integer positionId;
    private PositionName positionName;

    public Position(Integer positionId, PositionName positionName) {
        this.positionId = positionId;
        this.positionName = positionName;

    }

    public Integer getPositionId() {
        return positionId;
    }
    public void printInformation() {
        System.out.println("Position Id : " + positionId);
        System.out.println("Position Name : " + positionName);
    }
}
