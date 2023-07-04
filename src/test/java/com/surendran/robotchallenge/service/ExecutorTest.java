package com.surendran.robotchallenge.service;

import com.surendran.robotchallenge.exception.RobotChallengeException;
import com.surendran.robotchallenge.model.TableTop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExecutorTest {

    Executor executor;

    @BeforeEach
    void initialize() {
        executor = new Executor();
        executor.initialize(new TableTop(5,5));
    }

    @Test
    void validateAndExecuteCommand() {
        assertEquals("Robot 1: 0, 0, NORTH",executor.validateAndExecuteCommand("PLACE 0,0,NORTH"));
        assertEquals("Robot 1: 0, 1, NORTH",executor.validateAndExecuteCommand("MOVE"));
        assertEquals("Robot 1: 0, 2, NORTH",executor.validateAndExecuteCommand("MOVE"));
        assertEquals("Robot 1: 1, 2, EAST",executor.validateAndExecuteCommand("RIGHT"));
        assertEquals("Robot 1: 1, 3, NORTH",executor.validateAndExecuteCommand("LEFT"));
        assertEquals("Robot 1: 1, 3, NORTH",executor.validateAndExecuteCommand("REPORT"));
    }

    @Test
    void validateAndExecuteCommandMultipleRobots() {
        assertEquals("Robot 1: 0, 0, NORTH",executor.validateAndExecuteCommand("PLACE 0,0,NORTH"));
        assertEquals("Robot 1: 0, 1, NORTH",executor.validateAndExecuteCommand("MOVE"));
        assertEquals("Robot 1: 0, 2, NORTH",executor.validateAndExecuteCommand("MOVE"));
        assertEquals("Robot 2: 0, 0, NORTH",executor.validateAndExecuteCommand("PLACE 0,0,NORTH"));
        assertEquals("Robot 2: 1, 0, EAST",executor.validateAndExecuteCommand("RIGHT"));
        assertEquals("Robot 2: 1, 1, NORTH",executor.validateAndExecuteCommand("LEFT"));
        assertEquals("Robot 2: 1, 1, NORTH",executor.validateAndExecuteCommand("REPORT"));
        assertEquals("Robot 1: 0, 2, NORTH",executor.validateAndExecuteCommand("ROBOT 1"));
        assertEquals("Robot 1: 1, 2, EAST",executor.validateAndExecuteCommand("RIGHT"));
        assertEquals("Robot 1: 1, 1, SOUTH",executor.validateAndExecuteCommand("RIGHT"));
        assertEquals("Robot 1: 2, 1, EAST",executor.validateAndExecuteCommand("LEFT"));
        assertEquals("Robot 1: 2, 1, EAST",executor.validateAndExecuteCommand("REPORT"));
        assertEquals("Robot 2: 1, 1, NORTH",executor.validateAndExecuteCommand("ROBOT 2"));

    }

    @Test
    void validateAndExecuteCommandErrorHandler() {
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("PLCAE"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("PLACE"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("PLACE 0,0"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("PLACE J,0"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("PLACE 0,0,NORTHEAST"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("PLACE 6,0,NORTH"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("ROBOT"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("ROBOT J"));
        assertThrows(RobotChallengeException.class, () -> executor.validateAndExecuteCommand("ROBOT 0"));
        assertDoesNotThrow(() -> executor.validateAndExecuteCommand("PLACE 0,0,NORTH"));
        assertDoesNotThrow(() -> executor.validateAndExecuteCommand("ROBOT 1"));
    }


}