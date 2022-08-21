
/**
 * Write a description of class SecondWatersort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.Scanner;

public class WaterSort
{
    static Character red = new Character('r');
    static Character blue = new Character('b');
    static Character green = new Character('g');

    static StackAsMyArrayList bottles[] = new StackAsMyArrayList[5];
    
    static StackAsMyArrayList popBottles[] = new StackAsMyArrayList[3];//Array for colours to choose from

    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);

        bottles[0] = new StackAsMyArrayList<Character>();
        bottles[1] = new StackAsMyArrayList<Character>();
        bottles[2] = new StackAsMyArrayList<Character>();
        bottles[3] = new StackAsMyArrayList<Character>();
        bottles[4] = new StackAsMyArrayList<Character>();
        //Creating the bottles to pop from
        popBottles[0] = new StackAsMyArrayList<Character>();
        popBottles[1] = new StackAsMyArrayList<Character>();
        popBottles[2] = new StackAsMyArrayList<Character>();
        //Filling the pop bottles with colours
        //Colour Red
        popBottles[0].push(red);
        popBottles[0].push(red);
        popBottles[0].push(red);
        popBottles[0].push(red);
        //Colour Blue
        popBottles[1].push(blue);
        popBottles[1].push(blue);
        popBottles[1].push(blue);
        popBottles[1].push(blue);
        //Colour Green 
        popBottles[2].push(green);
        popBottles[2].push(green);
        popBottles[2].push(green);
        popBottles[2].push(green);

        fillBottles();

        do //if some pop bottles still have colours
        {
            fillBottles();
        }
        while(popBottles[0].getStackSize() > 0  || popBottles[1].getStackSize() > 0 || popBottles[2].getStackSize() > 0);
        //execute until they are all empty

        int source = 0;
        int target = 0;

        showBottles();
        
        System.out.println("\nLet the game begin!");
        while(!solved(bottles))
        {
            System.out.println("----------------------------------------------------------");
            System.out.print("\nSelect the source bottle: ");
            source = input.nextInt();
            System.out.print("Select the target bottle: ");
            target = input.nextInt();
            move(source,target);
            showBottles();
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("\nCongratulations!!!");
        System.out.println("Level completed!");
    }

    public static void showBottles()
    {
        System.out.println();
        for(int i = 0; i < bottles.length; i++)
        {
            System.out.println("Bottle[" + (i+1) + "]: " + bottles[i].toString());
        }
    }

    public static void move(int source, int target)
    {
        source--;
        target--;
        if(bottles[source].getStackSize() != 0 && bottles[target].getStackSize() < 4 )
        {
            if(bottles[target].getStackSize() == 0 || bottles[source].peek() == bottles[target].peek())
            {
                bottles[target].push(bottles[source].pop());
            }
        }
    }

    public static boolean solved(StackAsMyArrayList bottles[])
    {
        boolean isSolved = false;
        int count = 0;

        for(int i = 0; i < 5; i++)
        {
            if(bottles[i].checkStackUniform() == true && bottles[i].getStackSize() == 4)
            {
                count++;
            }
        }

        if(count == 3)
        {
            isSolved = true;
        }
        else
            isSolved = false;

        return isSolved;
    }

    public static void fillBottles()
    {
        for(int i = 0; i < 12; i++)
        {
            Random rand = new Random();

            int colour = rand.nextInt(3) + 1;
            int randNum = rand.nextInt(5);

            if(colour == 1)
            {
                if(bottles[randNum].getStackSize() < 4)//if stack is not full
                {
                    if(popBottles[0].getStackSize() > 0)//if there are colours in pop bottle
                    {
                        bottles[randNum].push(popBottles[0].pop());
                    }
                }
            }
            else if(colour == 2)
            {
                if(bottles[randNum].getStackSize() < 4)
                {
                    if(popBottles[1].getStackSize() > 0)
                    {
                        bottles[randNum].push(popBottles[1].pop());
                    }
                }
            }
            else
            {
                if(bottles[randNum].getStackSize() < 4)
                {
                    if(popBottles[2].getStackSize() > 0)
                    {
                        bottles[randNum].push(popBottles[2].pop());
                    }
                }
            }
        }
    }
}
