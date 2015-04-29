package edu.chl.loc.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.models.characters.AbstractCharacter;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IGameView;
import edu.chl.loc.view.core.IView;

/**
 * Created by maxim on 15-04-28.
 * @author Maxim Goretskyy
 */
public class CharacterView implements IView {
    private AbstractCharacter absCharacter;
    private Texture charTexture;
    private SpriteBatch spriteBatch;

    public CharacterView(AbstractCharacter absCharacter, Texture texture){
        this.absCharacter = absCharacter;
        this.charTexture = texture;
        this.spriteBatch = GameView.getSpriteBatch();

    }


    public void render(){
        spriteBatch.begin();
        spriteBatch.draw(charTexture, absCharacter.getPosition().getX() * IGameView.GRID_SIZE, absCharacter.getPosition().getY()*IGameView.GRID_SIZE);
        spriteBatch.end();

    }

}
