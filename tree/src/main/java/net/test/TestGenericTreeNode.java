package net.test;

import net.tree.GenericTreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestGenericTreeNode {
    @Test
    public void TestNodeDataIsNullOnNewNodeCreation() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        Assert.assertNull(node.getData());
    }

    @Test
    public void TestNodeHasNonNullChildrenListOnNewNodeCreation() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        Assert.assertNotNull(node.getChildren());
    }
//
    @Test
    public void TestNodeHasZeroChildrenOnNewNodeCreation() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        Assert.assertEquals(node.getNumberOfChildren(), 0);
    }
//
    @Test
    public void TestNodeHasChildrenReturnsFalseOnNewNodeCreation() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        Assert.assertFalse(node.hasChildren());
    }
//
    @Test
    public void TestNodeDataIsNonNullWithParameterizedConstructor() {
        GenericTreeNode<String> node = new GenericTreeNode<String>("I haz data");
        Assert.assertNotNull(node.getData());
    }
//
    @Test
    public void TestNodeSetAndGetData() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        String data = "data";
        node.setData(data);
        Assert.assertEquals(node.getData(), data);
    }

    @Test
    public void TestNodeSetAndGetChildren() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        GenericTreeNode<String> child = new GenericTreeNode<String>();

        List<GenericTreeNode<String>> children = new ArrayList<GenericTreeNode<String>>();
        children.add(child);

        node.setChildren(children);
        Assert.assertEquals(node.getChildren(), children);
    }

    @Test
    public void TestNodeSetAndGetChildrenHasCorrectParent() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        GenericTreeNode<String> child = new GenericTreeNode<String>();

        List<GenericTreeNode<String>> children = new ArrayList<GenericTreeNode<String>>();
        children.add(child);

        node.setChildren(children);
        Assert.assertEquals(node.getChildren(), children);

        for(GenericTreeNode<String> childNode : children) {
            Assert.assertEquals(node, childNode.getParent());
        }
    }
//
    @Test
    public void TestNodeRemoveChildren() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        GenericTreeNode<String> child = new GenericTreeNode<String>();

        List<GenericTreeNode<String>> children = new ArrayList<GenericTreeNode<String>>();
        children.add(child);

        node.setChildren(children);
        node.removeChildren();
        Assert.assertEquals(node.getChildren().size(), 0);
    }
//
//    @Test
//    public void TestNodeAddChildHasCorrectParent() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>();
//        GenericTreeNode<String> child = new GenericTreeNode<String>();
//
//        node.addChild(child);
//        Assert.assertEquals(node, child.getParent());
//    }
//
//    @Test
//    public void TestNodeAddChildHasOneChild() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>();
//        GenericTreeNode<String> child = new GenericTreeNode<String>();
//
//        node.addChild(child);
//        Assert.assertEquals(node.getNumberOfChildren(), 1);
//    }
//
//    @Test
//    public void TestNodeAddChildHasChildrenIsTrue() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>();
//        GenericTreeNode<String> child = new GenericTreeNode<String>();
//
//        node.addChild(child);
//        Assert.assertTrue(node.hasChildren());
//    }

    @Test
    public void TestNodeAddAndGetChildAt() {
        GenericTreeNode<String> node = new GenericTreeNode<String>("root");
        GenericTreeNode<String> child1 = new GenericTreeNode<String>("child1");
        GenericTreeNode<String> child2 = new GenericTreeNode<String>("child2");

        node.addChild(child1);
        node.addChildAt(1, child2);

        Assert.assertEquals(node.getChildAt(1).getData(), child2.getData());
    }
//
//    @Test
//    public void TestNodeAddAndRemoveChildAt() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>("root");
//        GenericTreeNode<String> child1 = new GenericTreeNode<String>("child1");
//        GenericTreeNode<String> child2 = new GenericTreeNode<String>("child2");
//
//        node.addChild(child1);
//        node.addChildAt(1, child2);
//
//        node.removeChildAt(0);
//
//        Assert.assertEquals(node.getNumberOfChildren(), 1);
//    }
//
    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void TestNodeAddChildAtThrowsException() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        GenericTreeNode<String> child = new GenericTreeNode<String>();

        node.addChildAt(5, child);
    }
//
//    @Test(expected = java.lang.IndexOutOfBoundsException.class)
//    public void TestNodeRemoveChildAtThrowsException() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>();
//        node.removeChildAt(1);
//    }
//
//    @Test
//    public void TestNodeToString() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>();
//        node.setData("data");
//        Assert.assertEquals(node.toString(), "data");
//    }
//
//    @Test
//    public void TestNodeToStringVerboseNoChildren() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>();
//        node.setData("data");
//        Assert.assertEquals(node.toStringVerbose(), "data:[]");
//    }
//
//    @Test
//    public void TestNodeToStringVerboseOneChild() {
//        GenericTreeNode<String> node = new GenericTreeNode<String>();
//        node.setData("data");
//
//        GenericTreeNode<String> child = new GenericTreeNode<String>();
//        child.setData("child");
//
//        node.addChild(child);
//        Assert.assertEquals(node.toStringVerbose(), "data:[child]");
//    }
//
    @Test
    public void TestNodeToStringVerboseMoreThanOneChild() {
        GenericTreeNode<String> node = new GenericTreeNode<String>();
        node.setData("data");

        GenericTreeNode<String> child1 = new GenericTreeNode<String>();
        child1.setData("child1");

        GenericTreeNode<String> child2 = new GenericTreeNode<String>();
        child2.setData("child2");

        node.addChild(child1);
        node.addChild(child2);

        Assert.assertEquals(node.toStringVerbose(), "data:[child1, child2]");
    }
}
