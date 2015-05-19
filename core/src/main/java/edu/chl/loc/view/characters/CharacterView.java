package edu.chl.loc.view.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.chl.loc.models.characters.AbstractCharacter;
import edu.chl.loc.models.characters.utilities.Direction;
import edu.chl.loc.view.core.GameView;
import edu.chl.loc.view.core.IView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maxim on 15-04-28.
 * @author Maxim Goretskyy
 *
 * Revised by Alexander Håkansson
 */
public class CharacterView implements IView {
    private AbstractCharacter absCharacter;
    private Texture charTexture;
    private SpriteBatch spriteBatch;

    private static final int CELL_W = 32;
    private static final int CELL_H = 64;

    private final Map<Direction, Animation> animations = new HashMap<Direction, Animation>();

    private Animation currentAnimation;
    private TextureRegion currentFrame;

    private float stateTime = 0f;

    /**
     *
     * @param absCharacter the character you want to view
     * @param texture The texture you want this character to have
     */
    public CharacterView(AbstractCharacter absCharacter, Texture texture){
        this.absCharacter = absCharacter;
        this.charTexture = texture;

        setupAnimations();
    }

    private void setupAnimations() {
        TextureRegion[][] tmp = TextureRegion.split(charTexture, CELL_W, CELL_H);
        animations.put(Direction.SOUTH, new Animation(0.25f, tmp[0][0], tmp[0][1], tmp[0][2], tmp[0][3]));
        animations.put(Direction.WEST,  new Animation(0.25f, tmp[1][0], tmp[1][1], tmp[1][2], tmp[1][3]));
        animations.put(Direction.EAST, new Animation(0.25f, tmp[2][0], tmp[2][1], tmp[2][2], tmp[2][3]));
        animations.put(Direction.NORTH, new Animation(0.25f, tmp[3][0], tmp[3][1], tmp[3][2], tmp[3][3]));

        currentAnimation = animations.get(Direction.SOUTH);
        currentFrame = currentAnimation.getKeyFrames()[0]; // First frame of animation
    }

    @Override
    public void render(float delta, SpriteBatch batch){
        stateTime += delta;

        currentAnimation = animations.get(absCharacter.getDirection());
        if (absCharacter.isMoving()) {
            currentFrame = currentAnimation.getKeyFrame(stateTime, true);
        } else {
            currentFrame = currentAnimation.getKeyFrame(0);
        }

        batch.draw(currentFrame, absCharacter.getPosition().getX() * GameView.GRID_SIZE,
                absCharacter.getPosition().getY() * GameView.GRID_SIZE);
    }

    @Override
    public void dispose() {
        charTexture.dispose();
        //SpriteBatch is disposed in GameView
    }
}
