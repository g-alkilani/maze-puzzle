package maze;

import org.junit.Assert;
import org.junit.Test;

public class UnionFindStructureTest {

    @Test
    public void testUnion() {
        UnionFindStructure structure = new UnionFindStructure(35);
        structure.union(0, 10);
        structure.union(10, 20);
        structure.union(20, 30);

        Assert.assertTrue(structure.inOneSet(0, 20));
        Assert.assertTrue(structure.inOneSet(0, 30));
        Assert.assertEquals(10, structure.root(0));
        Assert.assertEquals(10, structure.root(10));
        Assert.assertEquals(10, structure.root(20));
    }

}
