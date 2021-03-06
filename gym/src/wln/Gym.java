package wln;
import java.io.IOException;
import org.json.JSONException;

public class Gym{
	public Game game;
	public ResultsParser rp;
	public boolean printIndividualGames = false;
	public Gym(Bot a){
		this.game = new Game(a);
		rp = new ResultsParser(1);
	}
	public Gym(Bot a, Bot b){
		this.game = new Game(a, b);
		rp = new ResultsParser(2);
	}
	public Gym(Bot a, Bot b, Bot c, Bot d){
		this.game = new Game(a, b, c, d);
		rp = new ResultsParser(4);
	}
	public Gym(Bot[] bots) {
		this.game = new Game(bots);
		rp = new ResultsParser(game.players.length);
	}
	public void compileBots() throws IOException{
		MyProcessBuilder builder = new MyProcessBuilder();
		game.compileBots(builder);
	}
	public void runSingleGameWithCompile() throws IOException, JSONException{
		this.compileBots();
		this.runSingleGame();
		this.feedToRP();
	}
	public void runSingleGame() throws IOException, JSONException{
		MyProcessBuilder builder = new MyProcessBuilder();
		builder.addGameRun(game);
		builder.runAndLoadGame(game);
		if(this.printIndividualGames)
			game.printGameResults();
		this.feedToRP();
	}
	public void feedToRP(){
		rp.addGameResults(this.game);
	}
	public void runGames(int num) throws IOException, JSONException{
		this.compileBots();
		for(int i = 1; i <= num; i++){
			if(this.printIndividualGames)
				System.out.println("===GAME " + i + "===");
			this.runSingleGame();
		}
		this.printResults();
	}
	public void printResults(){
		if(this.printIndividualGames)
			this.rp.printCurrentResults();
	}
}
