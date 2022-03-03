import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * GitHub: @Nico7777777
 * 28.12.2021
 */
public class Jucator2 extends Actor
{
    static int scor = 0, timerPrizeSound = 0, cadran=1, contorPrizeSound = 10, timerBoosterSound = 0, contorBoosterSound = 10;
    static float viteza = 1;
    static boolean prizeSoundOn = false, boosterSoundOn = false;
    static GreenfootSound prizeSound = new GreenfootSound("cherry_sound(short_buzzer_sound).wav"), boosterSound = new GreenfootSound("burger_sound(sci_fi_drill_alert).wav");
    public void act() 
    {
        control();
        castigaPuncte();
        speedBooster();
    }
    /********************* FUNCTII UTILE*******************/
    private void control(){
        if( Greenfoot.isKeyDown("w") )
            setLocation( getX(), getY() - (int)viteza);
        if( Greenfoot.isKeyDown("s") )
            setLocation( getX(), getY() + (int)viteza);
        if( Greenfoot.isKeyDown("a") )
            setLocation( getX() - (int)viteza, getY() );
        if( Greenfoot.isKeyDown("d") )
            setLocation( getX() + (int)viteza, getY() );
    }
    private void castigaPuncte(){
        Actor premiut = getOneIntersectingObject(prize.class);
        
        if( isTouching(prize.class) ){
            scor++;
            MyWorld world = (MyWorld) getWorld();
            switch(cadran)
            {
                case 1:
                    if( premiut.getX() >= world.the_width/2 && premiut.getY() <= world.the_height/2 ) scor += 2;
                    break;
                case 2:
                    if( premiut.getX() <= world.the_width/2 && premiut.getY() <= world.the_height/2 ) scor += 2;
                    break;
                case 3:
                    if( premiut.getX() <= world.the_width/2 && premiut.getY() >= world.the_height/2 ) scor += 2;
                    break;
                case 4:
                    if( premiut.getX() >= world.the_width/2 && premiut.getY() >= world.the_height/2 ) scor += 2;
                    break;
             }
            getWorld().removeObject( premiut );
            if( !prizeSoundOn )
            {
               prizeSoundOn = true;
               prizeSound.playLoop();
               timerPrizeSound = 1;
            }
            else
                timerPrizeSound = 1;
        }
        else if( prizeSoundOn ){
            timerPrizeSound = (timerPrizeSound+1) % contorPrizeSound;
            if( timerPrizeSound == 0 ){
                prizeSound.stop();
                prizeSoundOn = false;
            }
        }
    }
    private void speedBooster(){
        Actor booster = getOneIntersectingObject(crispyBooster.class);
        if( isTouching(crispyBooster.class) ){
            getWorld().removeObject(booster);
            viteza = 120 * viteza / 100;
            if( !boosterSoundOn )
            {
                boosterSoundOn = true;
                boosterSound.playLoop();
                timerBoosterSound = 1;
            }
            else
                timerBoosterSound = 1;
        }
        else if( boosterSoundOn )
        {
            timerBoosterSound = (timerBoosterSound+1) % contorBoosterSound;
            if( timerBoosterSound == 0 )
            {
                boosterSound.stop();
                boosterSoundOn = false;
            }
        }
    }
}