package Bai1.Models;

import Bai1.Enums.PositionName;

public class Position {
    private Integer positionId;
    private PositionName positionName;

    public Position () {}

    public Position(PositionName positionName) {
        this.positionName = positionName;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public PositionName getPositionName() {
        return positionName;
    }

    public void setPositionName(PositionName positionName) {
        this.positionName = positionName;
    }

    public void printInformation() {
        System.out.println("Position Id : " + positionId);
        System.out.println("Position Name : " + positionName);
    }
}
