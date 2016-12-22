package towerdefense;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.ANDEvent;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;

/**
 *
 * Diese Klasse repraesentiert das Menuefenster, indem ein neues
 * Spiel gestartet werden kann und das gesamte Spiel beendet 
 * werden kann.
 */
public class MainMenuState extends BasicGameState {

	private int stateID; 							// Identifier von diesem BasicGameState
	private StateBasedEntityManager entityManager; 	// zugehoeriger entityManager
	
	private final int distance = 100;
    private final int start_Position = 180;
    
    MainMenuState( int sid ) {
       stateID = sid;
       entityManager = StateBasedEntityManager.getInstance();
    }
    
    /**
     * Wird vor dem (erstmaligen) Starten dieses State's ausgefuehrt
     */
    @Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
    	// Hintergrund laden
    	Entity background = new Entity("menu");	// Entitaet fuer Hintergrund
    	background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
    	background.addComponent(new ImageRenderComponent(new Image("/assets/menu.jpeg"))); // Bildkomponente
    	background.setScale(0.43f);
    	    	
    	// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
    	entityManager.addEntity(stateID, background);
    	
    	/* Neues Spiel starten-Entitaet */
    	String new_Game = "Neues Spiel starten";
    	Entity new_Game_Entity = new Entity(new_Game);
    	
    	// Setze Position und Bildkomponente
    	new_Game_Entity.setPosition(new Vector2f(218, 190));
    	new_Game_Entity.setScale(0.28f);
    	new_Game_Entity.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action new_Game_Action = new ChangeStateInitAction(TowerDefense.GAMEPLAY_STATE);
    	mainEvents.addAction(new_Game_Action);
    	new_Game_Entity.addComponent(mainEvents);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	entityManager.addEntity(this.stateID, new_Game_Entity);
    	
    	/* Beenden-Entitaet */
    	Entity load_Entity = new Entity("Spielstand laden");
    	
    	// Setze Position und Bildkomponente
    	load_Entity.setPosition(new Vector2f(218, 290));
    	load_Entity.setScale(0.28f);
    	load_Entity.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents_l = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action load_Action = new ChangeStateInitAction(TowerDefense.LOAD_STATE);
    	mainEvents_l.addAction(load_Action);
    	load_Entity.addComponent(mainEvents_l);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	entityManager.addEntity(this.stateID, load_Entity);

    	/* Optionen-Entitaet */
    	String options = "Optionen";
    	Entity options_Entity = new Entity(options);
    	
    	// Setze Position und Bildkomponente
    	options_Entity.setPosition(new Vector2f(218, 390));
    	options_Entity.setScale(0.28f);
    	options_Entity.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents_o = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action options_Action = new ChangeStateInitAction(TowerDefense.OPTIONS_STATE);
    	mainEvents_o.addAction(options_Action);
    	options_Entity.addComponent(mainEvents_o);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	entityManager.addEntity(this.stateID, options_Entity);

    	/* Beenden-Entitaet */
    	Entity quit_Entity = new Entity("Beenden");
    	
    	// Setze Position und Bildkomponente
    	quit_Entity.setPosition(new Vector2f(218, 490));
    	quit_Entity.setScale(0.28f);
    	quit_Entity.addComponent(new ImageRenderComponent(new Image("assets/entry.png")));
    	
    	// Erstelle das Ausloese-Event und die zugehoerige Action
    	ANDEvent mainEvents_q = new ANDEvent(new MouseEnteredEvent(), new MouseClickedEvent());
    	Action quit_Action = new QuitAction();
    	mainEvents_q.addAction(quit_Action);
    	quit_Entity.addComponent(mainEvents_q);
    	
    	// Fuege die Entity zum StateBasedEntityManager hinzu
    	entityManager.addEntity(this.stateID, quit_Entity);	
    }

    /**
     * Wird vor dem Frame ausgefuehrt
     */
    @Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}
    
    /**
     * Wird mit dem Frame ausgefuehrt
     */
	@Override
	public void render(GameContainer container, StateBasedGame game, 
												Graphics g) throws SlickException {
		entityManager.renderEntities(container, game, g);
		
		int counter = 0;
		
		g.drawString("Neues Spiel", 110, start_Position+counter*distance); counter++;
		g.drawString("Spiel laden", 110, start_Position+counter*distance); counter++;
		g.drawString("Optionen", 110, start_Position+counter*distance); counter++;
		g.drawString("Beenden", 110, start_Position+counter*distance); counter++;
	}

	@Override
	public int getID() {
		return stateID;
	}
	
}