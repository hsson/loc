package edu.chl.loc.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.loc.models.characters.AbstractCharacter;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

/**
 * Created by maxim on 15-04-28.
 * @author Maxim Goretskyy
 *
 * Revised by Alexander Håkansson
 * Revised by Alexander Karlsson
 */
public class CharacterView implements IView {
    private AbstractCharacter absCharacter;
    private Texture charTexture;
    private SpriteBatch spriteBatch;

    /**
     *
     * @param absCharacter the character you want to view
     * @param texture The texture you want this character to have
     */
    public CharacterView(AbstractCharacter absCharacter, Texture texture){
        this.absCharacter = absCharacter;
        this.charTexture = texture;
        this.spriteBatch = GameView.getSpriteBatch();

    }

    /**
     * Renders the texture of the character and the characters position
     */
    public void render(){
        spriteBatch.draw(charTexture, absCharacter.getPosition().getX() * GameView.GRID_SIZE,
                absCharacter.getPosition().getY() * GameView.GRID_SIZE);
    }

    @Override
    public void dispose() {
        charTexture.dispose();
        //SpriteBatch is disposed in GameView
    }
}
