import greenfoot.*;
import java.util.Random;
/**
 * GitHub: @Nico7777777
 * 28.12.2021
 */
 
/**
 * The game consists in a surface with 2 players, whose main goal is to flourish by eating as much as possible
 * Some cherries are to spawn randomly on the game surface; moreover there are some speed boosts having the shape of burgers
 *
 */
public class MyWorld extends World
{
    Random randgen = new Random();
    public static int the_width = 900, the_height = 600, cell_size = 1;
    int cadran = 1, cadranTimerCounter = 1, cadranTimer = 250, spawnTimerCounterPrize = 0, spawnTimerPrize = 130, spawnTimerCounterBooster = 0, spawnTimerBooster = 260;
    
    Jucator1 alfa = new Jucator1();
    Jucator2 beta = new Jucator2();
    //-----------------CONSTRUCTOR-------------------------
    public MyWorld()
    {    
        super(the_width, the_height, cell_size);
        prepare();
        continuing();
        
    }
    //--------------FUNCTIA IMPORTANTA 1-------------------
    private void prepare(){
        alfa.cadran = cadran;
        beta.cadran = cadran;
        int x1, y1, x2, y2;
        x1 = 50 + Math.abs( 50 + randgen.nextInt()%51 );
        y1 = 50 + Math.abs( 50 + randgen.nextInt()%51 );
        x2 = 50 + Math.abs( the_width-150 + randgen.nextInt()%51 );
        y2 = 50 + Math.abs( the_height-150 + randgen.nextInt()%51 );
        /// am pozitionat draciile
        
        addObject(alfa, x1, y1);/** alfa apartine: |---------------------------|                 |--------------------------| */
                                /**                | ----------------------    |  beta aprtine:  |                          | */
        addObject(beta, x2, y2);/**                | |50x50               |    |                 |                          | */
                                /**                | |                    |    |                 |   |--------------------| | */
                                /**                | |                    |    |                 |   |750x450             | | */
                                /**                | |             100x100|    |                 |   |            800x500 | | */
                                /**                | ----------------------    |                 |   |                    | | */
                                /**                |                           |                 |   |                    | | */
                                /**                |                           |                 |   ---------------------- | */
                                /**                |___________________________|                 |__________________________| */
        
        
        GreenfootImage bkg = getBackground();
        bkg.setColor(Color.BLACK);
        bkg.fill();
        createStars(300);
    }
    //-----------------FUNCTIA IMPORTANTA 2--------------*/
    private void continuing(){
        setezMuzica();
        //tinScor();
        
    }
    //--------------------FUNCTIA IMPORTANTA 3---------------*/
    public void act(){
        //if( Greenfoot.mouseClicked(null) )
        //    addObject(new prize(), 10 + randgen.nextInt()%(getWidth() - 10 + 1), 10 + randgen.nextInt()%(getHeight() - 10 + 1) );

        checkForSpawningPrizes();
        checkForSpawningBoosters();
        schimbCadran(alfa, beta);
        tinScor();
    }
    private void schimbCadran(Jucator1 premier, Jucator2 deuxieme){
        cadranTimerCounter = (cadranTimerCounter + 1) % cadranTimer;
        if(cadranTimerCounter == 0){
            int old = cadran;
            while(cadran == old)
                cadran = 1 + (randgen.nextInt() % 4);
            premier.cadran = cadran;
            deuxieme.cadran = cadran;
        }
    }
    private void checkForSpawningBoosters(){
        spawnTimerCounterBooster = (spawnTimerCounterBooster  + 1)%spawnTimerBooster;
        if( spawnTimerCounterBooster == 0){
            //addObject(new crispyBooster(), 1 + randgen.nextInt()%900, 1 + randgen.nextInt()%600);
            addObject(new crispyBooster(), 10 + Greenfoot.getRandomNumber( the_width - 2*10 ), 10 + Greenfoot.getRandomNumber( the_height - 2*10 ) );
            if(spawnTimerBooster == 0) spawnTimerBooster = 260; //initial value
        }
    }
    private void checkForSpawningPrizes(){
        spawnTimerCounterPrize = (spawnTimerCounterPrize + 1)%spawnTimerPrize;
        if( spawnTimerCounterPrize == 0 ){
            //addObject(new prize(), 1 + randgen.nextInt()%900,  1 + randgen.nextInt()%600);
            addObject(new prize(), 10 + Greenfoot.getRandomNumber( the_width - 2*10 ), 10 + Greenfoot.getRandomNumber( the_height - 2*10 ) );
            spawnTimerPrize -= 1; //accelereaza ritmul/viteza aparitiilor de cirese
            if(spawnTimerPrize == 0) spawnTimerPrize = 130;//initial value
        }
    }
    private void setezMuzica(){
        /*
         * disclaimer for the song:
         * Skrillex - Scary Monsters And Nice Sprites
         */
        GreenfootSound backgroundMusic = new GreenfootSound("nice_sp.mp3");
        backgroundMusic.setVolume(15);
        backgroundMusic.playLoop();
    }
    private void createStars(int number) 
    {
        GreenfootImage background = getBackground();             
        for (int i=0; i < number; i++)
        {            
             int x = Greenfoot.getRandomNumber( getWidth() );
             int y = Greenfoot.getRandomNumber( getHeight() );
             int color = 150 - Greenfoot.getRandomNumber(120);
             background.setColorAt(x, y, new Color(color,color,color));
        }
    }
    /** restul functiilor */
    private void spawnPrize(){
        if( Greenfoot.mouseClicked(null) ){
            addObject(new prize(), 10 + randgen.nextInt()%(getWidth() - 10 + 1), 10 + randgen.nextInt()%(getHeight() - 10 + 1) );
        }
    }
    private void spawnCrispyBooster(){
        if( Greenfoot.mouseClicked(null) ){
            addObject(new crispyBooster(), 30+randgen.nextInt()%(getWidth() - 60 + 1), 30 + randgen.nextInt()%(getHeight() - 60 + 1) );
        }
    }
    private void tinScor(){
        // JUCATOR 1:
        showText("Jucator 1- scor: " + Jucator1.scor, 100, 577);
        showText("viteza: " + (float)((int)(Jucator1.viteza*1000) )/1000, 70, 593);
        // JUCATOR 2:
        showText("Jucator 2- scor: " + Jucator2.scor, 795, 577);
        showText("viteza: " + (float)((int)(Jucator2.viteza*1000) )/1000, 826, 593);
        
        // ALTELE:
        showText("Cadranul " + cadran, the_width/2, 10);

    }
}
