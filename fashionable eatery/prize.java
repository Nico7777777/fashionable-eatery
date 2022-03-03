import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * GitHub: @Nico7777777
 * 28.12.2021
 */
public class prize extends Actor
{
    int speed = 3;
    public void act() 
    {
        move(speed);
        if(isAtEdge()) turn(20);
    }    
}
