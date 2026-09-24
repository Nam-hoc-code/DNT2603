package Bai6.Entity;

import Bai6.Entity.Enums.PositionName;

public class Position {
    private Integer idPosition;
    private PositionName positionName;

    public Position() {}

    public Position(Integer idPosition) {
        this.idPosition = idPosition;
    }
    public Position(Integer idPosition, PositionName positionName) {
        this.idPosition = idPosition;
        this.positionName = positionName;
    }

    public Integer getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Integer idPosition) {
        this.idPosition = idPosition;
    }

    public PositionName getPositionName() {
        return positionName;
    }

    public void setPositionName(PositionName positionName) {
        this.positionName = positionName;
    }

    @Override
    public String toString() {
        return "Position{" +
                "idPosition=" + idPosition +
                ", positionName=" + positionName +
                '}';
    }
}