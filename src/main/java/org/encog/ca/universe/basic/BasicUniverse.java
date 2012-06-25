package org.encog.ca.universe.basic;

import org.encog.ca.CellularAutomataError;
import org.encog.ca.universe.Universe;
import org.encog.ca.universe.UniverseCell;
import org.encog.ca.universe.UniverseCellFactory;

public class BasicUniverse implements Universe {
	private UniverseCell[][] data;
	private UniverseCellFactory cellFactory;
	
	public BasicUniverse(int height, int width, UniverseCellFactory theCellFactory)
	{
		this.data = new UniverseCell[height][width];
		this.cellFactory = theCellFactory;
		for(int row = 0; row<getRows(); row++) {
			for(int col=0; col<getColumns(); col++) {
				this.data[row][col] = this.cellFactory.factor();
			}
		}
	}
	
	public void init(boolean discrete) {
		
	}
	
	public Object clone() {
		BasicUniverse result = new BasicUniverse(getRows(),getColumns(),this.cellFactory);
		result.copy(this);
		return result;
	}
	
	@Override
	public void copy(Universe source)
	{
		if( !(source instanceof BasicUniverse) ) {
			throw new CellularAutomataError("Can only copy another BasicUniverse");
		}
		for(int row = 0; row<getRows(); row++) {
			for(int col=0; col<getColumns(); col++) {
				this.data[row][col].copy(source.get(row, col));
			}
		}
	}
	
	@Override
	public double compare(Universe otherWorld) {
		if( !(otherWorld instanceof BasicUniverse) ) {
			throw new CellularAutomataError("Can only compare another BasicUniverse");
		}
		
		int result = 0;
		int total = 0;
		for(int row = 0; row<otherWorld.getRows(); row++) {
			for(int col=0; col<otherWorld.getColumns(); col++) {
				int d1 = Math.abs((int)(255*get(row,col).getAvg()));
				int d2 = Math.abs((int)(255*otherWorld.get(row,col).getAvg()));
				if( Math.abs(d1-d2)>10) {
					result++;
				}
				total++;
			}
		}
		
		return (double)result/(double)total;
	}
	
	
	public int getColumns() {
		return this.data[0].length;
	}
	
	public int getRows() {
		return this.data.length;
	}
	
	public void randomize() {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getColumns(); col++) {
				for (int i = 0; i < 3; i++) {
					this.data[row][col].randomize();
				}
			}
		}
	}


	public UniverseCell get(int row, int col) {
		return this.data[row][col];
	}

	public boolean isValid(int row, int col) {
		if( row<0 || col<0 || row>=getRows() || col>=getColumns()) {
			return false;
		}
		return true;
	}
}
