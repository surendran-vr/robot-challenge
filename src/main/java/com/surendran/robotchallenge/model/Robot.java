package com.surendran.robotchallenge.model;

import lombok.Getter;

@Getter
public class Robot {

    private final int id;
    private RobotPosition robotPosition;
    private boolean active;

    public Robot(int id, RobotPosition robotPosition){
        this.id = id;
        this.robotPosition = robotPosition;
        this.active = true;
    }

    public void inactivateRobot(){
        this.active = false;
    }

    public void updateRobotPosition(RobotPosition robotPosition){
        this.robotPosition = robotPosition;
    }

    @Override
    public String toString(){
        return String.format("Robot %d: %d, %d, %s", this.getId(), this.getRobotPosition().getX(), this.getRobotPosition().getY(), this.getRobotPosition().getDirection().name());
    }
}
