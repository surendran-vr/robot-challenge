package com.surendran.robotchallenge.handler;

import com.surendran.robotchallenge.enums.Direction;
import com.surendran.robotchallenge.model.RobotPosition;
import com.surendran.robotchallenge.model.TableTop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionChangeHandlerTest {

    PositionChangeHandler positionChangeHandler;
    private final TableTop tableTop = new TableTop(5,5);

    @BeforeEach
    void init(){
        positionChangeHandler = new PositionChangeHandler();
    }

    @Test
    void testIsValidPosition(){
        assertFalse(positionChangeHandler.isValidPosition(6,6, tableTop));
        assertTrue(positionChangeHandler.isValidPosition(5,5, tableTop));
    }

    @Test
    void testChangeDirection(){
        assertEquals(Direction.WEST,positionChangeHandler.changeDirection(Direction.NORTH,-1));
        assertEquals(Direction.EAST,positionChangeHandler.changeDirection(Direction.NORTH,1));
        assertEquals(Direction.NORTH,positionChangeHandler.changeDirection(Direction.WEST,1));
        assertNotEquals(Direction.NORTH,positionChangeHandler.changeDirection(Direction.WEST,-1));
    }

    @Test
    void testGetNewPosition() {
        RobotPosition robotPosition = new RobotPosition(4,4,Direction.NORTH);
        positionChangeHandler.getNewPosition(robotPosition,1,tableTop);
        assertEquals(5,robotPosition.getX());
        assertEquals(4,robotPosition.getY());
        assertEquals(Direction.EAST,robotPosition.getDirection());
        positionChangeHandler.getNewPosition(robotPosition,1,tableTop);
        assertEquals(Direction.SOUTH,robotPosition.getDirection());
        positionChangeHandler.getNewPosition(robotPosition,1,tableTop);
        assertEquals(Direction.WEST,robotPosition.getDirection());
        positionChangeHandler.getNewPosition(robotPosition,1,tableTop);
        assertEquals(Direction.NORTH,robotPosition.getDirection());
        positionChangeHandler.getNewPosition(robotPosition,0,tableTop);
        positionChangeHandler.getNewPosition(robotPosition,0,tableTop);
        assertEquals(5,robotPosition.getY());
        assertEquals(Direction.NORTH,robotPosition.getDirection());
    }

}