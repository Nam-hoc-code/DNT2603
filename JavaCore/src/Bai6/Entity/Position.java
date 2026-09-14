package Bai6.Entity;

import Bai6.Entity.Enums.PositionName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private Integer idPosition;
    private PositionName positionName;
}