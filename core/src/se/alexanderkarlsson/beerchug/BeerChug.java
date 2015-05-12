package se.alexanderkarlsson.beerchug;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import se.alexanderkarlsson.beerchug.beerchugcontroller.BottleBeerChugController;
import se.alexanderkarlsson.beerchug.beerchugmodel.BottleBeerChug;
import se.alexanderkarlsson.beerchug.beerchugview.BottleBeerChugView;

public class BeerChug extends Game {
	private BottleBeerChug model;
	private BottleBeerChugController controller;
	private BottleBeerChugView view;

	@Override
	public void create() {
		model = new BottleBeerChug();
		view = new BottleBeerChugView(model);
		controller = new BottleBeerChugController(model);
		Gdx.input.setInputProcessor(controller);
		this.setScreen(view);
	}

	@Override
	public void render(){
		super.render();
	}
}
