package edu.chl.loc.models.map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alexander Håkansson
 * @version 1.0.0
 * @since 2015-04-08
 */
public class LayerTest {

    ILayer layer1;
    ILayer layer2;
    ILayer layer3;

    @Before
    public void testSetup() {
        layer1 = new Layer("someName");
        layer2 = new Layer("someName");
        layer3 = new Layer("differentName");
    }

    @Test
    public void testLayerName() {
        Assert.assertEquals("Name is wrong", "someName", layer1.getName());
        Assert.assertEquals("Name is wrong", "differentName", layer3.getName());
    }

    @Test
    public void testLayerEquality() {
        Assert.assertEquals("The two layers should be equal", true, layer1.equals(layer2));
        Assert.assertNotSame("The two layers should not be equal", true, layer1.equals(layer3));
    }

    @Test
    public void testLayerToString() {
        Assert.assertEquals("toString should give name", layer1.toString(), layer1.getName());
    }

    @After
    public void testUnload() {
        layer1 = layer2 = layer3 = null;
    }

}
