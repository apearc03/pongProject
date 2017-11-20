package pongproject.game;

import java.sql.SQLException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import pongproject.game.database.databaseManager;
import pongproject.game.gamescreen.GameScreen;
import pongproject.game.highscorescreen.HighScoreScreen;
import pongproject.game.loginscreen.LoginScreen;
import pongproject.game.menuscreen.MenuScreen;
import pongproject.game.tests.screenFunctionalityTest;

public class Pong extends Game {
	
	private screenFunctionalityTest screenTest; //testing only
	
	
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;
	
	private Skin skin;
	
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private HighScoreScreen highScoreScreen;
	private LoginScreen loginScreen;
	
	private  databaseManager data;
	
	/*to do
	 * 
	 * 
	 * Git commit asap.
	 * 
	 * 
	 * Next unit test will be a database unit test, add a row to database, select row from database, then remove row. Along with onscreen database connection message.
	 * 
	 * Need to add table to database for scores.
	 * 
	 * Store passwords as hashes.
	 * 
	 * If no connection is made, put message on menu screen "You can play but your score will not be recorded". With a retry connection button, calls makeConnection in database manager
	 * Disable highscore button with no connection.
	 * If connection is made. Put message in same area. top right or left. "Connected". Only allow player to play if they have logged in.
	 * Public instance of database manager in Pong class.
	 * 
	 * Use two textfields for input, record results in manager. Call account login checks on play button click. 
	 * Might be easier to make a separate loginscreen that is accessed if there is a connection.
	 * 
	 * 
	 * 
	 * After that work on gamescreen.
	 * Start with some sort of countdown. Might need to do this in a newscreen. Try not to tho. Could be a method called in the show method.
	 * Then position assets and move ball.
	 * Use vectors for position and velocity. Rectangles for ball and paddles.
	 * Vector for ball, position and velocity. Float for y position and float for paddles y velocity since it remains on same x coordinate.
	 * When ball hits paddle. Create calculation from ball and paddles velocity.
	 * 
	 * 
	 * 
	 */
	
	//initialises all necessary variables and sets screen to the menu
	
	@Override
	public void create () {
		
		
		data = new databaseManager();
		
		Gdx.graphics.setTitle(Constants.title);
		
		camera = new OrthographicCamera();
		
		batch = new SpriteBatch();
		
		font = new BitmapFont();
		font.getData().setScale(0.7f);
		
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this);
		highScoreScreen = new HighScoreScreen(this);
		loginScreen = new LoginScreen(this);
		
		screenTest = new screenFunctionalityTest(this); //testing purposes
		
		
		this.setScreen(menuScreen);
	}

	//Disposes of resources on game exit
	@Override
	public void dispose() {
		
		super.dispose();
		font.dispose();
		batch.dispose();
		menuScreen.dispose();
		gameScreen.dispose();
		highScoreScreen.dispose();
		loginScreen.dispose();
		
	}
	
	// Getter methods
	
	public SpriteBatch getBatch() {
		return batch;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	
	public HighScoreScreen getHighScoreScreen() {
		return highScoreScreen;
	}
	
	public MenuScreen getMenuScreen() {
		return menuScreen;
	}
	
	public LoginScreen getLoginScreen() {
		return loginScreen;
	}
	
	public screenFunctionalityTest getScreenTest() {
		return screenTest;
	}
	
	public Skin getSkin() {
		return skin;
	}

	public databaseManager getData() {
		return data;
	}
}
