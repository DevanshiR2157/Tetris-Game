/**
 * Tetrad.java  4/30/2014
 *
 * @author - Alisha Hanslod and Devanshi Rautaraya
 * @author - Period 1
 * @author - Id 1082008 1074366
 *
 * @author - I received help from ...
 *
 */

import java.awt.Color;
import java.util.ArrayList;

// Represents a Tetris piece.
public class Tetrad
{
	private Block[] blocks;	// The blocks for the piece.

	//Constructs a Tetrad.
	
	public Tetrad(BoundedGrid<Block> grid)
	{
		blocks = new Block[4];
		int ranNum = (int) (Math.random()*7);
		if (ranNum == 0) {
			Color tempCol = Color.RED;
			Location[] tempLoc = new Location[4];
			tempLoc[1] = new Location(0,4);
			tempLoc[0] = new Location(0,3);
			tempLoc[2] = new Location(0,5);
			tempLoc[3] = new Location(0,6);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block();
				blocks[i].setColor(tempCol);
			}
			addToLocations(grid, tempLoc);
		} else if(ranNum == 1){
			Color tempCol = Color.GRAY;
			Location[] tempLoc = new Location[4];
			tempLoc[1] = new Location(0,4);
			tempLoc[0] = new Location(0,3);
			tempLoc[2] = new Location(0,5);
			tempLoc[3] = new Location(1,4);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block();
				blocks[i].setColor(tempCol);
			}
			addToLocations(grid, tempLoc);
		}else if(ranNum == 2){
			Color tempCol = Color.CYAN;
			Location[] tempLoc = new Location[4];
			tempLoc[0] = new Location(0,3);
			tempLoc[1] = new Location(0,4);
			tempLoc[2] = new Location(1,3);
			tempLoc[3] = new Location(1,4);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block();
				blocks[i].setColor(tempCol);
			}
			addToLocations(grid, tempLoc);
		}else if(ranNum == 3){
			Color tempCol = Color.YELLOW;
			Location[] tempLoc = new Location[4];
			tempLoc[1] = new Location(0,4);
			tempLoc[0] = new Location(0,3);
			tempLoc[2] = new Location(0,5);
			tempLoc[3] = new Location(1,3);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block();
				blocks[i].setColor(tempCol);
			}
			addToLocations(grid, tempLoc);
		}else if(ranNum == 4){
			Color tempCol = Color.MAGENTA;
			Location[] tempLoc = new Location[4];
			tempLoc[1] = new Location(0,4);
			tempLoc[0] = new Location(0,3);
			tempLoc[2] = new Location(0,5);
			tempLoc[3] = new Location(1,5);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block();
				blocks[i].setColor(tempCol);
			}
			addToLocations(grid, tempLoc);
		}else if(ranNum == 5){
			Color tempCol = Color.BLUE; 
			Location[] tempLoc = new Location[4];
			tempLoc[0] = new Location(0,4);
			tempLoc[1] = new Location(0,5);
			tempLoc[2] = new Location(1,3);
			tempLoc[3] = new Location(1,4);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block();
				blocks[i].setColor(tempCol);
			}
			addToLocations(grid, tempLoc);
		}else{
			Color tempCol = Color.GREEN;
			Location[] tempLoc = new Location[4];
			tempLoc[1] = new Location(0,4);
			tempLoc[0] = new Location(0,3);
			tempLoc[2] = new Location(1,4);
			tempLoc[3] = new Location(1,5);
			for (int i = 0; i < blocks.length; i++) {
				blocks[i] = new Block();
				blocks[i].setColor(tempCol);
			}
			addToLocations(grid, tempLoc);
		}
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}


	// Postcondition: Attempts to move this tetrad deltaRow rows down and
	//						deltaCol columns to the right, if those positions are
	//						valid and empty.
	//						Returns true if successful and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		BoundedGrid<Block> grid = blocks[0].getGrid();
		Location[] oldLocations = removeBlocks();
	    Location[] newLocations = new Location[oldLocations.length];
	    for (int i = 0; i < oldLocations.length; i++) {
	        int newRow = oldLocations[i].getRow() + deltaRow;
	        int newCol = oldLocations[i].getCol() + deltaCol;
	        newLocations[i] = new Location(newRow, newCol);
	        if (!grid.isValid(newLocations[i])) {
	        	addToLocations(grid, oldLocations);
	        	return false;
	        }
	    }
	    if (areEmpty(grid, newLocations)) {
	        addToLocations(grid, newLocations);
	        return true;
	    } else {
	        addToLocations(grid, oldLocations);
	        return false;
	    }
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
	//                about its center, if the necessary positions are empty.
	//                Returns true if successful and false otherwise.
	public boolean rotate()
	{
		BoundedGrid<Block> grid = blocks[1].getGrid();
		Location[] oldLocations = removeBlocks();
	    Location[] newLocations = new Location[oldLocations.length];
	    newLocations[0] = oldLocations[0];
	    for (int i = 1; i < oldLocations.length; i++) {
	        int newRow = oldLocations[0].getRow() - oldLocations[0].getCol() + oldLocations[i].getCol();
	        int newCol = oldLocations[0].getRow() + oldLocations[0].getCol() - oldLocations[i].getRow();
	        newLocations[i] = new Location(newRow, newCol);
	        if (!grid.isValid(newLocations[i])) {
	        	addToLocations(grid, oldLocations);
	        	return false;
	        }
	    }
	    if (areEmpty(grid, newLocations)) {
	        addToLocations(grid, newLocations);
	        return true;
	    } else {
	        addToLocations(grid, oldLocations);
	        return false;
	    }
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}


	// Precondition:  The elements of blocks are not in any grid;
	//                locs.length = 4.
	// Postcondition: The elements of blocks have been put in the grid
	//                and their locations match the elements of locs.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
	{
		for (int i = 0; i < blocks.length; i++) {
			 Block block = blocks[i];
		     Location loc = locs[i];
		     block.putSelfInGrid(grid, loc);
		}
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Precondition:  The elements of blocks are in the grid.
	// Postcondition: The elements of blocks have been removed from the grid
	//                and their old locations returned.
	private Location[] removeBlocks()
	{
		Location[] oldLocs = new Location[4];
		for (int i = 0; i < blocks.length; i++) {
			Block block = blocks[i];
	        Location oldLoc = block.getLocation();
	        block.removeSelfFromGrid();
	        oldLocs[i] = oldLoc;
	    }
	    return oldLocs;
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: Returns true if each of the elements of locs is valid
	//                and empty in grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
	{
		boolean currStatus = true;
		for (int i = 0; i < locs.length; i++) {
			if(!grid.isValid(locs[i]) || grid.get(locs[i]) != null ) {
				currStatus = false;
			}
		}
		return currStatus; 
		//throw new RuntimeException("INSERT MISSING CODE HERE");
	}
}
