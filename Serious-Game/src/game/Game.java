// Andrew Smith

package game;

import java.io.*;
import java.util.*;
import javax.swing.*;

public class Game
{
  private static Grid grid;
  private int userRow;
  private static int msElapsed;
  private static int timesGet;
  private static int timesAvoid;
  private static ArrayList<String> list = new ArrayList<String>(); // to store questions
  private String temp,name;
  private static int questions=0; //questions correct
  private static int test = 0; // reset confirmation
  private static String HS = ""; // scores string
  PrintStream out = new PrintStream(new FileOutputStream("Scores.txt",true)); // updates scores file
  Scanner scanner = new Scanner(new File("Scores.txt")); // reads scores file
  static Heap heap = new Heap();
  private static double qTotal = 0;
 // private JLabel label = new JLabel();
  
  public Game() throws FileNotFoundException
  {
    grid = new Grid(9, 16);
    userRow = 4;
    msElapsed = 0;
    timesGet = 0;
    timesAvoid = 0;
    updateTitle();
    grass(); // fills grid with grass
    grid.setImage(new Location(userRow, 0), "pokeball.gif"); // move player
    Scanner s = new Scanner(new File("Questions.txt")); // read questions
    while(s.hasNext())
    {
    	temp=s.nextLine();
    	list.add(temp.substring(0,temp.indexOf('-')));
    	list.add(temp.substring(temp.indexOf('-')+1,temp.indexOf('?')+1));
    	list.add(temp.substring(temp.indexOf('?')+2,temp.length()));	
    }//while - fills array list with questions
    list.add(null);list.add(null);list.add(null); //adds empty slots at the end of the list for determining end of game
  }//Game
  
  public static boolean obj(double right, double total)
  {
	  if((total>=10)&&(right/total>0.7))
		  return true;
	  else
		  return false;
  }
  
  public void play() throws FileNotFoundException
  {
	  // displays beginning dialog
	  name = JOptionPane.showInputDialog("What is your name?");
	  JOptionPane.showMessageDialog(null, name + ", Pokemon are running away\nfrom their trainers. You must catch as\nmany as possible before they get away.");
	  JOptionPane.showMessageDialog(null, "How To Play\nUse the UP and DOWN Arrows Keys to move.\nHitting 3 ROCKS will end the game.");
	  JOptionPane.showMessageDialog(null, "You will have to add money every 4 Pokemon.\nRemember to put  a $ in your answer.");
	  JOptionPane.showMessageDialog(null, "You must answer more than 10 questions and\nget 70% right to meet the learning goal.");
    
	while (!isGameOver()) // run while not game over
    {
      grid.pause(100); // determines update frequency
      if (msElapsed % 600 == 0) // move field every 10 updates
      {
        scrollLeft();
        populateRightEdge();
      }//if
      handleKeyPress();
      updateTitle();
      msElapsed += 100;
    }//while
    heap.insert(name + " - " + questions + " - " + getScore()+"\n",getScore()); // score of current player
    System.setOut(out); // out variable
    while(!heap.isEmpty())
    	out.append((String)heap.remove()); // append current score to file
    out.close();
    JOptionPane.showMessageDialog(null, name + "'s Score: \nQuestions Correct "+questions+"\nPokemon caught "+getScore()); // show player their score
    if(obj((double)questions, qTotal)==true)
    	JOptionPane.showMessageDialog(null, "Congratulations!\nYou met the learning goal!");
    else
    	JOptionPane.showMessageDialog(null, "Sorry, you did not meet the learning goal.");
    if(JOptionPane.showConfirmDialog(null, "Would you like to see other scores?")==0) // ask to see past scores
    {
    	getScores();
    }//if
  }//play
  
  public static void getScores() throws FileNotFoundException
  {
  	Scanner scan = new Scanner(new File("Scores.txt")); //scanner to read list
	heap.insert(scan.nextLine()+"\n",9999);
	heap.insert(scan.nextLine()+"\n",9998);
	while(scan.hasNext()) // while file has more
	{
		try 
		{
  		String priorityS = scan.nextLine();
  		String scorez = priorityS+"\n";
  		priorityS = priorityS.substring(priorityS.indexOf("-")+1,priorityS.length());
  		priorityS = priorityS.substring(priorityS.indexOf("-")+2, priorityS.length());
			heap.insert(scorez, Integer.parseInt(priorityS));// concatenate next player score
  		}catch(NoSuchElementException z) {}
  	}//while
  	PrintStream out = new PrintStream(new FileOutputStream("Scores.txt"));
  	String HS = HS(heap);
  	out.append(HS);
  	JOptionPane.showMessageDialog(null, HS); // show scores
  }
  
  public static String HS(Heap heap)
  {
	  HS =""; // reset to store string of all scores
	  while(!heap.isEmpty()) // while file has more
	  {
	      try
	      {
	      HS += heap.remove();// concatenate next player score
	      }catch(NoSuchElementException z) {}
	  }//while
  	return HS;
  }
  
  public void handleKeyPress()
  {
	  int key = grid.checkLastKeyPressed();
	  grid.setImage(new Location(userRow, 0), null);
	  if(key == 38 && userRow > 0) //if up arrow
	  {
		  grid.setImage(new Location(userRow, 0),"grass.gif"); //set old location to background
		  userRow--; // move user up
	  }//if
	  if(key == 40 && userRow < grid.getNumRows()-1) // if down arrow
	  {
		  grid.setImage(new Location(userRow, 0),"grass.gif"); //set old location to background
		  userRow++; //move user down
	  }//if
	  handleCollision(new Location(userRow, 0)); //check for collision
	  grid.setImage(new Location(userRow, 0), "pokeball.gif"); // place user where requested
  }//handlekeypress
  
  public void populateRightEdge() // randomly places obstacles and goals
  {
	  //instantiate random numbers
	  int pop = 0,pop2=0,pop3=0;
	  //get value for pop2 <= 10
	  pop2 = (int) (Math.random()*10);
	  
	  for(int j = 0;j<pop2*2/3;j++) // determines how often to place object
	  {
		  pop = (int) (Math.random()*grid.getNumRows());
		  grid.setImage(new Location(pop, grid.getNumCols()-1), "stone.gif");
		  pop3 = (int) (Math.random()*grid.getNumRows());
		  if(pop2%2==1)
			  grid.setImage(new Location(pop3, grid.getNumCols()-1), "pikachu.gif");
	  }//for
  }//populaterightedge
  
  public void scrollLeft()
  {
	  for(int i=0;i<grid.getNumCols()-1;i++) // choose column
	  {
		  handleCollision(new Location(userRow, 0));
		  
		  setCol(i); // set column to next
	  }//for
  }//scrollleft
  
  public static void grass() // sets default grid to grass background
  {
	  for(int i = 0;i<grid.getNumRows();i++)
		  for(int j = 0;j<grid.getNumCols();j++)
			  grid.setImage(new Location(i,j),"grass.gif");
  }//grass
  
  public void setCol(int n) // moves columns left - n is column
  {
	  for(int i=0;i<grid.getNumRows();i++) // moves each location
	  {
		  if(!(i==userRow && n+1==0))// not the user
		  {
			  grid.setImage(new Location(i,n),grid.getImage(new Location(i,n+1)));
			  grid.setImage(new Location(i,n+1),"grass.gif");
		  }//if
	  }//for
  }//setCol
  
  public void handleCollision(Location loc)
  {
	  if(grid.getImage(loc)==null)
		  return;
	  if(grid.getImage(loc).equals("pikachu.gif"))
	  {
		  timesGet++;
		  if(timesGet % 4 == 0)
		  {
			  qTotal++;
			  temp = JOptionPane.showInputDialog(list.get(1+(3*(timesGet/4-1))));
			  if(temp.equals(list.get(2+(3*(timesGet/4-1)))))
			  {
				  JOptionPane.showMessageDialog(null, "CORRECT!");
				  questions++;
			  }//if
			  else
				  JOptionPane.showMessageDialog(null, "Sorry, the right answer is "+list.get(2+(3*(timesGet/4-1))));
		  }//if
	  }//if
	  if(grid.getImage(loc).equals("stone.gif"))
	  {
		  timesAvoid++;
	  }//if
	  grid.setImage(loc, null);
  }//handleCollision
  
  public int getScore()
  {
    return timesGet;
  }//getScore
  
  public static void reset() // returns variables and grid to starting values
  {
	  grass();
	  msElapsed = 0;
	  timesGet = 0;
	  timesAvoid = 0;
	  questions=0; 
	  qTotal=0;
  }//reset
  public void updateTitle() // show title, pokemon caught, and how many lives left
  {
    grid.setTitle("Pikachu Run   Pokemon Caught:  " + getScore()+"   Pokeballs Left:  " + (3-timesAvoid));
  }//updateTitle
  
  public boolean isGameOver() throws ArrayIndexOutOfBoundsException, IndexOutOfBoundsException
  {
	  if(list.get(3*(timesGet/4))==null)
		  return true;
	  if(timesAvoid == 3)	  
		  return true;
    return false;
  }//isGameOver
  
  public static void test() throws FileNotFoundException
  {
	  while(test==0)
	  {
	    Game game = new Game();
	    game.play();
	    while(test==0) 
	    {
	    	test = JOptionPane.showConfirmDialog(null,"Play Again?");
	    	if(test==0)
	    	{
	    		reset();
	    		game.play();
	    	}//if
	    }//while
	    if(test==1)
	    {
	    	System.exit(1);
	    }//if
	  }//while
  }//test
  
  public static void main(String[] args) throws FileNotFoundException
  {
    test();
  }//main
}//Game Class