package edu.chl.loc.map;

/**
 * A class representing layers on the map.
 *
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-06
 */
public class Layer implements ILayer {

    private final String name;

    /**
     * Create a new layer with the specified name. The name
     * will be used as identifier for the layer.
     *
     * @param name The name for the layer.
     */
    public Layer(final String name) {
        this.name = name;
    }

    /**
     * @inheritDoc
     */
    public String getName() {
        return name;
    }

    /**
     * Two layers are considered equal if they share the same name.
     * This is since the name is being used as the main identifier
     * for a layer.
     *
     * @param o The other object to compare with
     * @return True if the layers are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        Layer other = (Layer) o;
        return other.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        return 11*7*23 * name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}