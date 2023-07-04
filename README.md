# Robot Challenge
This is a command line application which is a simulation of a set of Robots moving around an NxN table top.
The aim is to not allow the robots move beyond the co-ordinate specified.

## Description

- This application is to simulate the movement of set of Robots on a Table top.
- The Robots are free to roam around the table specified the coordinates are wuthin the specification of the Table top.
- Application will accept commands until EXIT keyword is specified.
- Below are the allowed COMMANDS the robot can react to

      PLACE X,Y,DIRECTION
      LEFT
      RIGHT
      MOVE
      ROBOT
      REPORT

      EXIT

- PLACE Places the robot in the specified x & y co-ordinate and facing the specified DIRECTION (Allowed direcctions are NORTH, EAST, SOUTH, WEST)
- LEFT, RIGHT Moves the Robot 1 position LEFT or RIGHT from current position (provided the new position is within the specified table top co-ordinates).
  ELSE the movement will be ignored but the DIRECTION will be changed according to the COMMAND
- MOVE Moves the Robot 1 position on smae direction from current position (provided the new position is within the specified table top co-ordinates).
  ELSE the movement will be ignored, no change in direction
- ROBOT Will make thr specified Robot as active and mark others inactive.
- REPORT Reports the active Robot along with the current co-ordinates.

Example Input and Output:

    a) Input with a Square table top Dimension of 5*5 and output requested for all statements.

    Please enter the Dimension of Square Table top: 5
    Do you like to see Output for all Commands (Y/N): Y
    Enter the execution command: <Command can be one of [PLACE, MOVE, LEFT, RIGHT, REPORT, ROBOT] or EXIT >
    PLACE 0,0,NORTH
    OUTPUT: Robot 1: 0, 0, NORTH
    MOVE
    OUTPUT: Robot 1: 0, 1, NORTH
    MOVE
    OUTPUT: Robot 1: 0, 2, NORTH
    REPORT
    OUTPUT: Robot 1: 0, 2, NORTH
    EXIT
    
    b) Input with a Square table top Dimension of 5*5 and output not requested for all statements.

    Please enter the Dimension of Square Table top: 5
    Do you like to see Output for all Commands (Y/N): N
    Enter the execution command: <Command can be one of [PLACE, MOVE, LEFT, RIGHT, REPORT, ROBOT] or EXIT >
    PLACE 0,0,NORTH
    MOVE
    MOVE
    LEFT
    RIGHT
    REPORT
    OUTPUT: Robot 1: 0, 3, NORTH
