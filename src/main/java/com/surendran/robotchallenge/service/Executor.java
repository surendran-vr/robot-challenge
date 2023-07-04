package com.surendran.robotchallenge.service;

import com.surendran.robotchallenge.enums.Command;
import com.surendran.robotchallenge.enums.Direction;
import com.surendran.robotchallenge.exception.RobotChallengeException;
import com.surendran.robotchallenge.handler.PositionChangeHandler;
import com.surendran.robotchallenge.model.Robot;
import com.surendran.robotchallenge.model.RobotPosition;
import com.surendran.robotchallenge.model.TableTop;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Executor {
    private Map<Integer, Robot> robots;
    private TableTop tableTop;
    private Robot activeRobot;
    private final PositionChangeHandler handler = new PositionChangeHandler();

    /**
     * Init Method, initializes the robots Map (which holds the details of all Robots)
     * and  the tabletop on which the robots be placed
     *
     * @param tableTop Table-top on which the robots be placed.
     */
    public void initialize(TableTop tableTop) {
        this.robots = new HashMap<>();
        this.tableTop = tableTop;
    }

    /**
     * Entry point for the execution.
     * Validate the commands and parameters and executes if all are valid.
     *
     * @param commandLine command input from commandLine.
     * @return the output of the execution.
     */
    public String validateAndExecuteCommand(String commandLine) {
        String[] commandWParams = commandLine.split(" ");
        Command command;
        try {
            command = Command.valueOf(commandWParams[0]);
        } catch (IllegalArgumentException e) {
            throw new RobotChallengeException(String.format("Invalid Command!! <Command should be one of: %s or EXIT>", Arrays.toString(Command.values())));
        }
        return executeCommand(command, commandWParams);
    }

    /**
     * Command executor
     * @param command Command to be executed
     * @param commandWParams command with Parameters
     * @return Output of the command
     */
    public String executeCommand(Command command, String[] commandWParams) {
        return switch (command) {
            case PLACE -> {
                if (commandWParams.length < 2)
                    throw new RobotChallengeException("No co-ordinates provided to place the Robot ");
                String[] params = commandWParams[1].split(",");
                if (params.length < 3)
                    throw new RobotChallengeException(String.format("Insufficient parameters to place the Robot (Expected: 3, Actual: %d)", params.length));
                try {
                    int x = Integer.parseInt(params[0]);
                    int y = Integer.parseInt(params[1]);
                    Direction direction = Direction.valueOf(params[2]);

                    yield place(x, y, direction);
                } catch (NumberFormatException nfe) {
                    throw new RobotChallengeException(String.format("Non-numeric Co-ordinates: %s, %s", params[0], params[1]));
                } catch (IllegalArgumentException iae) {
                    throw new RobotChallengeException(String.format("Invalid direction: %s (Direction should be one of %s)", params[2], Arrays.toString(Direction.values())));
                }
            }
            case LEFT -> handler.moveLeft(activeRobot, tableTop);
            case RIGHT -> handler.moveRight(activeRobot, tableTop);
            case MOVE -> handler.move(activeRobot, tableTop);
            case REPORT -> activeRobot.toString();
            case ROBOT -> {
                if (commandWParams.length < 2)
                    throw new RobotChallengeException("No Identifier provided to activate the Robot ");
                try {
                    int id = Integer.parseInt(commandWParams[1]);
                    yield robot(id);
                } catch (NumberFormatException nfe) {
                    throw new RobotChallengeException("Invalid Robot Identifier (Expected: Numeric, Actual: Alpha-numeric)");
                }
            }
        };
    }

    /**
     * Method to place the Robot on the Table top
     * @param x x coordinate
     * @param y y coordinate
     * @param direction facing direction
     * @return Robot details and position
     */
    public String place(int x, int y, Direction direction) {
        if (!handler.isValidPosition(x, y, tableTop)) {
            throw new RobotChallengeException(String.format("Robot cannot be placed at row %d and column %d", x, y));
        }

        int id = robots.size() + 1;
        RobotPosition position = new RobotPosition(x, y, direction);
        Robot robot = new Robot(id, position);
        if (activeRobot != null) inactivateCurrentRobot();
        activeRobot = robot;
        robots.put(id, robot);

        return activeRobot.toString();
    }

    /**
     * Activates the requested Robot and inactivates other robots.
     * If not found an exception will be thrown
     * @param id id of the robot to be activated.
     * @return Robot with Parameters
     */
    public String robot(int id) {
        if (robots.containsKey(id)) {
            inactivateCurrentRobot();
            activeRobot = robots.get(id);
        } else {
            throw new RobotChallengeException(String.format("Robot with id %d cannot be found ", id));
        }
        return activeRobot.toString();
    }

    /**
     * Inactivate the current active robot before activating the new Robot.
     */
    void inactivateCurrentRobot() {
        robots.get(activeRobot.getId()).updateRobotPosition(activeRobot.getRobotPosition());
        robots.get(activeRobot.getId()).inactivateRobot();
    }

}
