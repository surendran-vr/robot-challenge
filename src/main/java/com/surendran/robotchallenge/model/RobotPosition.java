package com.surendran.robotchallenge.model;

import com.surendran.robotchallenge.enums.Direction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RobotPosition {
    private int x;
    private int y;
    private Direction direction;

    public void changeCoordinatesAndDirection(int x, int y, Direction direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void changeDirection(Direction direction){
        this.direction = direction;
    }

}
