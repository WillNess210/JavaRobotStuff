package hlt;
import java.util.ArrayList;

public class Position{
	public int x, y;
	public Position(final int x, final int y){
		this.x = x;
		this.y = y;
	}
	ArrayList<Position> getEmptyNeighbours(GameMap gameMap){
		ArrayList<Position> toReturn = new ArrayList<Position>();
		Position[] ns = this.getNeighbours(gameMap);
		for(Position n : ns){
			if(gameMap.at(n).canMoveOn()){
				toReturn.add(n);
			}
		}
		return toReturn;
	}
	Position[] getNeighbours(GameMap gameMap){
		Position[] ns = new Position[4];
		ns[0] = this.directionalOffset(Direction.NORTH, gameMap);
		ns[1] = this.directionalOffset(Direction.EAST, gameMap);
		ns[2] = this.directionalOffset(Direction.SOUTH, gameMap);
		ns[3] = this.directionalOffset(Direction.WEST, gameMap);
		return ns;
	}
	Position directionalOffset(final Direction d, GameMap gameMap){
		final int dx;
		final int dy;
		switch(d){
			case NORTH:
				dx = 0;
				dy = -1;
				break;
			case SOUTH:
				dx = 0;
				dy = 1;
				break;
			case EAST:
				dx = 1;
				dy = 0;
				break;
			case WEST:
				dx = -1;
				dy = 0;
				break;
			case STILL:
				dx = 0;
				dy = 0;
				break;
			default:
				throw new IllegalStateException("Unknown direction " + d);
		}
		Position next = new Position(x + dx, y + dy);
		if(next.x < 0){
			next.x = gameMap.width - 1;
		}else if(next.x >= gameMap.width){
			next.x = 0;
		}
		if(next.y < 0){
			next.y = gameMap.height - 1;
		}else if(next.y >= gameMap.height){
			next.y = 0;
		}
		return next;
	}
	Position directionalOffset(final Direction d){
		final int dx;
		final int dy;
		switch(d){
			case NORTH:
				dx = 0;
				dy = -1;
				break;
			case SOUTH:
				dx = 0;
				dy = 1;
				break;
			case EAST:
				dx = 1;
				dy = 0;
				break;
			case WEST:
				dx = -1;
				dy = 0;
				break;
			case STILL:
				dx = 0;
				dy = 0;
				break;
			default:
				throw new IllegalStateException("Unknown direction " + d);
		}
		return new Position(x + dx, y + dy);
	}
	public void logln(){
		Log.logln(this.toString());
	}
	public void log(){
		Log.log(this.toString());
	}
	public String toString(){
		return "(" + this.x + ", " + this.y + ")";
	}
	public int distanceTo(Position b){
		return Math.abs(b.x - this.x) + Math.abs(b.y - this.y);
	}
	public boolean samePosition(Position b){
		return this.x == b.x && this.y == b.y;
	}
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Position position = (Position) o;
		if(x != position.x)
			return false;
		return y == position.y;
	}
	@Override
	public int hashCode(){
		int result = x;
		result = 31 * result + y;
		return result;
	}
}