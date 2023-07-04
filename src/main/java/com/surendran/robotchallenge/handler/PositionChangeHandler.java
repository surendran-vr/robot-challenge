package com.surendran.robotchallenge.handler;

import com.surendran.robotchallenge.enums.Direction;
import com.surendran.robotchallenge.model.Robot;
import com.surendran.robotchallenge.model.RobotPosition;
import com.surendran.robotchallenge.model.TableTop;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class PositionChangeHandler {
    private static final Map<Integer, Direction> directionMap = new HashMap<>();

    static {
        for (Direction direction : Direction.values()) {
            directionMap.put(direction.getDirection(), direction);
        }
    }

    public String moveLeft(Robot activeRobot, TableTop tableTop){
        getNewPosition(activeRobot.getRobotPosition(),-1,tableTop);
        return activeRobot.toString();
    }
    public String moveRight(Robot activeRobot, TableTop tableTop){
        getNewPosition(activeRobot.getRobotPosition(),1,tableTop);
        return activeRobot.toString();
    }
    public String move(Robot activeRobot, TableTop tableTop){
        getNewPosition(activeRobot.getRobotPosition(),0,tableTop);
        return activeRobot.toString();
    }

    public void getNewPosition(RobotPosition currentPosition, int directionChange, TableTop tableTop) {
        Direction directionToMove = changeDirection(currentPosition.getDirection(), directionChange);
        checkAndUpdateRobotPosition(currentPosition, directionToMove, tableTop);
    }

    public Direction changeDirection(Direction currentDirection, int directionChange) {
        int newDirection = currentDirection.getDirection() + directionChange;
        int size = directionMap.size();
        newDirection = newDirection < 0 ?
                size + newDirection :
                (newDirection >= size ? newDirection - size : newDirection);

        return directionMap.get(newDirection);
    }
    private void checkAndUpdateRobotPosition(RobotPosition currentPosition, Direction directionToMove, TableTop tableTop) {

        int newX = currentPosition.getX();
        int newY = currentPosition.getY();

        switch (directionToMove) {
            case SOUTH -> --newY;
            case NORTH -> ++newY;
            case EAST -> ++newX;
            case WEST -> --newX;
        }

        if (isValidPosition(newX, newY, tableTop))
            currentPosition.changeCoordinatesAndDirection(newX, newY, directionToMove);
        else currentPosition.changeDirection(directionToMove);
    }

    /**
     * Method to check if the new position is valid.
     *
     * @param rowPosition    row position of the robot
     * @param columnPosition column position of the robot
     * @return true, if the row, column is within the range of the table top row and column.
     */
    public boolean isValidPosition(int rowPosition, int columnPosition, TableTop tableTop) {
        return rowPosition >= 0 && tableTop.getRow() >= rowPosition && columnPosition >= 0 && tableTop.getColumn() >= columnPosition;
    }
}
