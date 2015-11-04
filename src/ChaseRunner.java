

/*
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * @author Cay Horstmann
 */


import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.UnboundedGrid;

import java.awt.*;

/**
 * This class runs a world that contains a bug and a rock, added at random
 * locations. Click on empty locations to add additional actors. Click on
 * populated locations to invoke methods on their occupants. <br />
 * To build your own worlds, define your own actors and a runner class. See the
 * BoxBugRunner (in the boxBug folder) for an example. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChaseRunner
{
    public static void main(String[] args)
    {
       // ActorWorld world = new ActorWorld(new UnboundedGrid());
        ActorWorld world = new ActorWorld();
        Bug redBug = new Bug();
        redBug.setColor(Color.red);
        Bug blueBug = new Bug();
        blueBug.setColor(Color.blue);
        world.add(blueBug);
        world.add(redBug);
        world.add(new Rock());
        world.show();
        System.out.println(distance(redBug, blueBug));
        moveBug(2, redBug, blueBug);
    }

    /**
     * Ex. 8.13 calculate distance
     */
    public static double distance(Bug bug1, Bug bug2){
        int y1 = bug1.getLocation().getCol();
        int y2 = bug2.getLocation().getCol();
        int x1 = bug1.getLocation().getRow();
        int x2 = bug2.getLocation().getRow();
        //direction calc
        return Math.sqrt((x1 - x2)^2  + (y1 - y2)^2);
    }

    public static int getAlpha (Bug bug1, Bug bug2){
        int add=0;
        double result;

        int y1 = bug1.getLocation().getCol();
        int y2 = bug2.getLocation().getCol();
        int x1 = bug1.getLocation().getRow();
        int x2 = bug2.getLocation().getRow();
        //get direction angle
        result = Math.toDegrees(Math.atan2((y2 - y1), (x2 - x1))) ;
//        System.out.println(result + "\t" + x1 + "\t" + y1 + "\t" + x2 + "\t" + y2);
        return (int) result ;
    }


    public static void turnToward(Bug bug1, Bug bug2){
        // polar coordinates of bug direction
        // and gridworld coordinates located differently, so get them together
        //with this code
        int alpha = getAlpha(bug1, bug2);
        if(alpha>=0) {
            alpha=180-alpha;
        } else {
            alpha = 180+alpha;
        }
        bug1.setDirection(alpha);
    }

    public static void moveTowards (Bug bug1, Bug bug2) {
        turnToward(bug1, bug2);
        bug1.move();
    }

    public static void moveBug(int n, Bug bug1, Bug bug2){
        for (int i = 0; i < n; i++) {
            moveTowards(bug1, bug2);
        }
    }
}