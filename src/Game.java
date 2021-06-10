
public class Game {
    /**
     * We'll need to pass this instance variable to each Tile so that we can access
     * the faceButton and flag counter
     */
    private Program instance;
    private int flagsRemaining;

    /**
     * The actually board width and height have padding so that we can perform
     * certain actions easier without over indexing on the array
     * 
     * BOMBS WILL NOT SPAWN IN THE PADDING
     */
    private int height, width;

    private Tile[][] board;

    private TimerThread timerThread;

    public Game(Program gameInstance, int height, int width, int mines) {
	this.timerThread = new TimerThread(gameInstance, this);
	this.instance = gameInstance;
	this.flagsRemaining = mines;
	this.height = height + 2;
	this.width = width + 2;
	this.board = new Tile[this.height][this.width];
	for (int row = 0; row < this.height; row++) {
	    for (int col = 0; col < this.width; col++) {
		board[row][col] = new Tile(gameInstance);
	    }
	}
    }

    private class TimerThread extends Thread {
	private Program programInstance;
	private Game gameInstance;

	public TimerThread(Program programInstance, Game gameInstance) {
	    this.programInstance = programInstance;
	    this.gameInstance = gameInstance;
	}

	public void run() {
	    long startTime = System.currentTimeMillis();
	    long deltaTime = 0;
	    long deltaTimeSeconds = 0;
	    while (true) {
		deltaTime = System.currentTimeMillis() - startTime;
		deltaTimeSeconds = deltaTime / 1000;
		if (deltaTimeSeconds <= 999) // max time to prevent formatting issues
		    programInstance.setTimerText(deltaTimeSeconds);
	    }
	}
    }

    /** GETTERS & SETTERS **/

    public Tile[][] getBoard() {
	return board;
    }

    public void setBoard(Tile[][] board) {
	this.board = board;
    }

    public String getFlagsRemaining() {
	String remaining = Integer.toString(flagsRemaining);
	while (remaining.length() < 3)
	    remaining = "0" + remaining;
	return remaining;
    }

    public void setFlagsRemaining(int flagsRemaining) {
	this.flagsRemaining = flagsRemaining;
    }
}
