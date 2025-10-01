import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


/**
 * Tetris.java  4/30/2014
 *
 * @author - Alisha Hanslod and Devanshi Rautaraya
 * @author - Period 1
 * @author - Id 1082008 1074366
 *
 * @author - I received help from ...
 *
 */

// Represents a Tetris game.
public class Tetris implements ArrowListener
{
	private BoundedGrid<Block> grid;	// The grid containing the Tetris pieces.
	private BlockDisplay display;		// Displays the grid.
	private Tetrad activeTetrad;		// The active Tetrad (Tetris Piece).


	// Constructs a Tetris Game
	public Tetris()
	{

		grid = new BoundedGrid<Block> (20, 10);
		display = new BlockDisplay(grid);
		display.setTitle("Tetris");
		display.setArrowListener(this);
		activeTetrad = new Tetrad(grid);
		display.showBlocks();
		play();
	}

	

	// Implements ArrowListener methods
	//setArrowListener(listener);
	@Override
    public void upPressed() {
		activeTetrad.rotate();
		display.showBlocks();
    }
	@Override
    public void downPressed() {
        activeTetrad.translate(1, 0);
        display.showBlocks();
    }
	@Override
    public void leftPressed() {
        activeTetrad.translate(0, -1);
        display.showBlocks();
    }
	@Override
	public void rightPressed() {
        activeTetrad.translate(0, 1);
        display.showBlocks();
    }
	
	public void spacePressed() {
		while (activeTetrad.translate(1, 0)) {
			activeTetrad.translate(1, 0);
		}
		display.showBlocks();
    }

	// Play the Tetris Game
	public void play()
	{
		 while (true) {
            display.showBlocks();
            activeTetrad.translate(1, 0);
            if (!activeTetrad.translate(1, 0)) {
            	clearCompletedRows();
            	activeTetrad = new Tetrad(grid);
        		display.showBlocks();
        		
        		if (!activeTetrad.translate(1, 0)) {
                    // Game over condition
                    System.out.println("Game Over!");
                    break;
                }
            }
            try {
                Thread.sleep(display.getCurrentSpeedMilliseconds());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
         
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}


	// Precondition:  0 <= row < number of rows
	// Postcondition: Returns true if every cell in the given row
	//                is occupied; false otherwise.
	private boolean isCompletedRow(int row)
	{
		int numCols = grid.getNumCols();
	    
	    for (int col = 0; col < numCols; col++) {
	        Location loc = new Location(row, col);
	        if (grid.get(loc) == null) {
	            return false;
	        }
	    }
	    
	    return true;
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Precondition:  0 <= row < number of rows;
	//                The given row is full of blocks.
	// Postcondition: Every block in the given row has been removed, and
	//                every block above row has been moved down one row.
	private void clearRow(int row)
	{
		for (int col = 0; col < grid.getNumCols(); col++) {
	        Location loc = new Location(row, col);
	        grid.remove(loc);
	    }
	    
	    for (int r = row - 1; r >= 0; r--) {
	        for (int c = 0; c < grid.getNumCols(); c++) {
	            Location currentLoc = new Location(r, c);
	            Location newLoc = new Location(r + 1, c);
	            Block block = grid.get(currentLoc);
	            
	            if (block != null) {
	                block.moveTo(newLoc);
	                grid.put(newLoc, block);
	                grid.remove(currentLoc);
	            }
	        }
	    }
	    
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: All completed rows have been cleared.
	private void clearCompletedRows()
	{
		int numRows = grid.getNumRows();
	    
	    for (int row = 0; row < numRows; row++) {
	        if (isCompletedRow(row)) {
	            clearRow(row);
	            display.addScore();
	        }
	    }
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Sleeps (suspends the active thread) for duration seconds.
	private void sleep(double duration)
	{
		final int MILLISECONDS_PER_SECOND = 1000;

		int milliseconds = (int)(duration * MILLISECONDS_PER_SECOND);

		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			System.err.println("Can't sleep!");
		}
	}


	// Creates and plays the Tetris game.
	public static void main(String[] args)
	{
		//constructor test
		Tetris test1 = new Tetris();
		System.out.println(test1);
		//throw new RuntimeException("INSERT MISSING CODE HERE");

	}
}