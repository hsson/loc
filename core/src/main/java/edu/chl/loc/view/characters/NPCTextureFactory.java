package edu.chl.loc.view.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import edu.chl.loc.models.characters.npc.AbstractNPC;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-05-04
 */
public class NPCTextureFactory {

    private static final Texture defaultNPCTexture = new Texture(Gdx.files.internal("player-sheet.png"));

    private NPCTextureFactory() {
        // No construction allowed
    }

    public static Texture build(AbstractNPC npc) {

        // This could determine which texture to return
        // based on attributes of the NPC. Such as the name.

        return defaultNPCTexture;
    }
}
