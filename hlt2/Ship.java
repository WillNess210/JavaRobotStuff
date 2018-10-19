package hlt2;
public class Ship extends Entity{
	public int halite, turnsAlive;
	public Ship(final PlayerId owner, final EntityId id, final Position position, final int halite){
		super(owner, id, position);
		this.halite = halite;
		this.turnsAlive = 0;
	}
	public boolean isFull(){
		return halite >= Constants.MAX_HALITE;
	}
	public Command makeDropoff(){
		return Command.transformShipIntoDropoffSite(id);
	}
	public Command move(final Direction direction, GameMap gameMap){
		if(this.halite >= gameMap.at(this).halite/10) {
			this.position = this.position.directionalOffset(direction);
			return Command.move(id, direction);
		}else {
			return Command.move(id, Direction.STILL);
		}
	}
	public Command stayStill(){
		return Command.move(id, Direction.STILL);
	}
	public void _update(int x, int y, int halite){
		this.position.x = x;
		this.position.y = y;
		this.halite = halite;
		this.turnsAlive++;
	}
	public void log() {
		this.id.logEntityId();
		Log.addSpace();
		this.position.log();
		Log.log(": ");
		Log.logln(this.turnsAlive + "");
	}
	static Ship _generate(final PlayerId playerId){
		final Input input = Input.readInput();
		final EntityId shipId = new EntityId(input.getInt());
		final int x = input.getInt();
		final int y = input.getInt();
		final int halite = input.getInt();
		return new Ship(playerId, shipId, new Position(x, y), halite);
	}
	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		if(!super.equals(o))
			return false;
		Ship ship = (Ship) o;
		return halite == ship.halite;
	}
	@Override
	public int hashCode(){
		int result = super.hashCode();
		result = 31 * result + halite;
		return result;
	}
}