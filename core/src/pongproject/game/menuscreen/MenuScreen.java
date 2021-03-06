package pongproject.game.menuscreen;

import java.sql.SQLException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import pongproject.game.Pong;
import pongproject.game.tests.eventLogger;


/**
 * 
 * @author Alex Pearce
 *
 */
public class MenuScreen implements Screen{


	//Stage instance to display all scene2d actors
	private Stage stage;
	
	//Necessary text buttons
	private TextButton gameButton;
	private TextButton settingsButton;
	private TextButton highScoreButton;
	private TextButton loginButton;
	private TextButton exitButton;
	private TextButton connectionButton;

	//References the Pong class. The class where the application started.
	private Pong pongGame;
	
	//Labels to display text on the screen.
	private Label loggedInAs;
	private Label connectionMessage;
	private LabelStyle messageStyle;

	private final String title; 
	private final String author;
	
	private Label titleLabel;
	private Label authorLabel;
	
	//Background image texture and sprite
	private Texture background;
	
	private Sprite backgroundSprite;


	
	/**
	 * Constructor initializes all instance variables.
	 * @param pong
	 */
	public MenuScreen(final Pong pong) {
		

		
		pongGame = pong;
		
		stage = new Stage(new StretchViewport(pongGame.getAppWidth(), pongGame.getAppHeight(), pongGame.getCamera()));
		
		
		background = new Texture(Gdx.files.internal("menu.jpg"));
		
		
		backgroundSprite = new Sprite(background);
		backgroundSprite.setSize(pongGame.getAppWidth(), pongGame.getAppHeight());
		
		title = "Pong";
		author = "By Alex Pearce";
		
		
		authorLabel = new Label(author, new LabelStyle(pongGame.getFont20(),Color.WHITE));
		authorLabel.setPosition(pongGame.getAppWidth()/2-authorLabel.getWidth()/2, 500);
		stage.addActor(authorLabel);
		
		titleLabel = new Label(title, new LabelStyle(pongGame.getFont100(),Color.WHITE));
		titleLabel.setPosition(pongGame.getAppWidth()/2-titleLabel.getWidth()/2, 550);
		stage.addActor(titleLabel);
		
		
		//All buttons initialized. Their positions and size are set. They are then added to the stage.
		
		gameButton = new TextButton("Play", pongGame.getSkin());
		gameButton.setSize(100, 30);
		gameButton.setPosition(pongGame.getAppWidth()/2-(gameButton.getWidth()/2), 300);
		stage.addActor(gameButton);	
		
		settingsButton = new TextButton("Settings", pongGame.getSkin());
		settingsButton.setSize(100, 30);
		settingsButton.setPosition(pongGame.getAppWidth()/2-(settingsButton.getWidth()/2), 250);
		stage.addActor(settingsButton);
	
		
		highScoreButton = new TextButton("High Scores", pongGame.getSkin());
		highScoreButton.setSize(100, 30);
		highScoreButton.setPosition(pongGame.getAppWidth()/2-(highScoreButton.getWidth()/2), 200);
		stage.addActor(highScoreButton);
		
		
		
		connectionButton = new TextButton("Retry Connection", pongGame.getSkin());
		connectionButton.setPosition(20, pongGame.getAppHeight()-55);
		connectionButton.setTransform(true);
		connectionButton.setScale(0.75f);
		stage.addActor(connectionButton);
		
		

		
		loginButton = new TextButton("Change User", pongGame.getSkin());
		loginButton.setSize(100, 30);
		loginButton.setPosition(pongGame.getAppWidth()/2-(loginButton.getWidth()/2), 150);
		loginButton.setVisible(false);
		stage.addActor(loginButton);
		
		
		exitButton = new TextButton("Exit", pongGame.getSkin());
		exitButton.setSize(100, 30);
		exitButton.setPosition(pongGame.getAppWidth()/2-(exitButton.getWidth()/2), 100);
		stage.addActor(exitButton);
		
		
		messageStyle = new LabelStyle(pongGame.getFont14(), Color.WHITE);
		
		connectionMessage = new Label("", messageStyle);
		connectionMessage.setPosition(20,pongGame.getAppHeight()-20);
		stage.addActor(connectionMessage);
		
		loggedInAs = new Label("", messageStyle);
		loggedInAs.setPosition(20, 20);
		stage.addActor(loggedInAs);
		
		
		//Click listeners added to buttons. The Listener clicked method will execute when the button is clicked.
		
		gameButton.addListener(new ClickListener() { //Anonymous ClickListener created.
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				super.clicked(event, x, y);
				
				pongGame.getButtonSound().play(pongGame.getGlobalVolume()); //Plays the button sound
				
				try {
						if(pongGame.getFirstConnection()) {
									if(pongGame.getData().checkConnection()) { //If a first connection has been made, check the connection is still working.
										
										if(pongGame.getLoggedIn()) {
											pongGame.setScreen(pongGame.getGameScreen()); 	//If the user is logged in. Skip login screen.
										}
										else {
											
											pongGame.setScreen(pongGame.getLoginScreen());
										}
										
									
									}
									else {
										pongGame.setScreen(pongGame.getGameScreen());
									}
						}
						else {
							pongGame.setScreen(pongGame.getGameScreen());
							
							
						}
				} catch (SQLException e) {
					
					eventLogger.databaseConnectionFailed();
				}
				
			}
		});
		
		
		settingsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				super.clicked(event, x, y);
				pongGame.getButtonSound().play(pongGame.getGlobalVolume());
				pongGame.setScreen(pongGame.getSettingsScreen());
			}
		});
		
		
		highScoreButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				super.clicked(event, x, y);
				
				pongGame.getButtonSound().play(pongGame.getGlobalVolume());
				pongGame.setScreen(pongGame.getHighScoreScreen());
				
				
				
			
				
			}
		});

		connectionButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
				super.clicked(event, x, y);
				try {
					makeConnection();
				} catch (Exception e) {
					
					showRetryConnection();
				}
			}
			
			
		});
		
		loginButton.addListener(new ClickListener() {
			
			@Override
			public void clicked(InputEvent event, float x, float y) {
			
				super.clicked(event, x, y);
				pongGame.getButtonSound().play(pongGame.getGlobalVolume());
				pongGame.setScreen(pongGame.getLoginScreen());
			}
		});
		
		
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				
				super.clicked(event, x, y);
				pongGame.getButtonSound().play(pongGame.getGlobalVolume());
				Gdx.app.exit();
				
			}
		});
		
		
		//Attempts to make a connection to the database
		try {
			makeConnection();
		} catch (Exception e) {
			
			showRetryConnection();
			pongGame.setFirstConnection(false);
		}
		
		
	}

	

	/**
	 * This method is called once every time the Menu screen is set as the current screen. Sets is as the input processor and checks the database connection state.
	 */
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		eventLogger.menuScreen();
		
		
		
		
		try {
			if(pongGame.getFirstConnection()) {
				if(!pongGame.getData().checkConnection()) {
					showRetryConnection();
				}
			}	
		} catch (SQLException e) {
			//Added to desktop application
			showRetryConnection();
		}
		
		if(pongGame.getLoggedIn()) {
			loginButton.setVisible(true);
		}
	
		
		
	}
	
	
	
	/**
	 * Render is called to display every frame. I have set the default fore-ground FPS to 58 so this method should be called approximately 58 times per second.
	 */
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		pongGame.getBatch().begin();
		
		
		
		backgroundSprite.draw(pongGame.getBatch());
	
		
		
		pongGame.getBatch().end();
		
		stage.act(delta);
		stage.draw();
		
	
		
	}
	
	
	/**
	 * Adjusts the stage viewport size when the window is size is changed
	 */
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false); 


	}
	
	
	//Methods that are required to be implemented by the Screen interface but are unused.
	@Override
	public void pause() {}
	@Override
	public void resume() {}
	@Override
	public void hide() {}
	
	
	/**
	 * Releases any resources when the game exits. This is called within Pong.dispose
	 */
	@Override
	public void dispose() {
		background.dispose();
		

		stage.dispose();
		try {
			if(pongGame.getFirstConnection()) {
				pongGame.getData().closeConnection();
				}
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	

	/**
	 * This calls the makeConnection method of the databaseManager and manipulates the Menu screen display dependent on there being a connection or not.
	 * 
	 * @throws SQLException
	 */
	private void makeConnection() throws Exception {  //Changed these two methods to public
		pongGame.getData().makeConnection();
		
		eventLogger.databaseConnectionMade();
		
		connectionMessage.setText("Connection to database successful");
		connectionButton.setVisible(false);
		highScoreButton.setVisible(true);
		pongGame.setFirstConnection(true);
	}
	
	/**
	 * This method is called if there is no connection. The retry connection button is made visible.
	 */
	private void showRetryConnection() {
	
		connectionMessage.setText("Connection to database unsuccessful, your score wont be recorded");
		connectionButton.setVisible(true);
		highScoreButton.setVisible(false);
	}
	
	/**
	 * Sets the label to show the current logged in user.
	 * 
	 * @param username
	 */
	public void setLoggedInAs(String username) {
		loggedInAs.setText("Logged in as " + username);
	}
	
	
}
