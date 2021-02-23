
import java.util.*;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    
    private void setUpGame() {

    
    DotCom temp = new DotCom();
        int loc = (int) (Math.random() *3 +2); 
        dotComsList.add(temp.createDot("Pets.com", loc));
        loc = (int) (Math.random() *3 +2); 
        dotComsList.add(temp.createDot("eToys.com", loc));
        loc = (int) (Math.random() *3 +2); 
        dotComsList.add(temp.createDot("Go2.com", loc));
        
        System.out.println("Your goal is to sink three dot coms.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in the fewest number of guesses");
        
        for (DotCom dotComSet : dotComsList) {
            ArrayList<String> newLocation = helper.placeDotCom(dotComSet.getSize());
            dotComSet.setLocationCells(newLocation);
             System.out.println(newLocation);
        }

        dotComsList.get(0).setOtherLoc(dotComsList.get(1).getCells(), dotComsList.get(2).getCells());
        dotComsList.get(1).setOtherLoc(dotComsList.get(0).getCells(), dotComsList.get(2).getCells());
        dotComsList.get(2).setOtherLoc(dotComsList.get(0).getCells(), dotComsList.get(1).getCells());
    
    
    }
    
    private void startPlaying() {
        while (!dotComsList.isEmpty()) {
            String userGuess = GameHelper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }
    
    private void checkUserGuess(String userGuess)
    {
        numOfGuesses++;
        String result = "miss";
        
        for (DotCom dotComToTest : dotComsList)
        {
            result = dotComToTest.checkYourself(userGuess);
            if (result.equals("hit"))
            {
                break;
            }
            if (result.equals("kill"))
            {
                dotComsList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }
    

     
    private void finishGame() {
        System.out.println("All Dot Coms are dead!  Your stock is now worthless");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses");
            System.out.println("You got out before your options sank.");
        }
        else
        {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }
    
    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
    
    
}
