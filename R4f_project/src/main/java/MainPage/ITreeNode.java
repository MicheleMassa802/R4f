package main.java.MainPage;

import java.util.ArrayList;

import javax.swing.tree.TreeNode;

public interface ITreeNode {
    // interface for the information and actions a tree node must be able to do and contain

    // execute the code to run the page for the current node
    // every single page class in this package must implement this method, which depending on the 
    void execute();

}
