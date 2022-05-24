package main.java.MainPage;

import java.util.ArrayList;

public class Tree {
    // class for structuring the tree for traversing the different pages to be displayed
    private Tree parent;
    private String nodeName;  // Invariant: this String should be unique within the tree nodes
    private ArrayList<Tree> subtrees;
    private ITreeNode toExecute;

    /** Usage:  
     * moving through the tree consists of creating the tree with the correct structure, and then keeping  a 
     * root variable managing the current stage of the program by containing the current Tree node being
     * executed
    */

    /**
     * Constructor for a Tree node object
     * You first create a Tree type node with its corresponding information which would be the root,
     * then create the possible subtrees and link them by modifying the parent and subtree attributes
     * 
     * Ideally, you'd have a variable to describe the current place in the tree were you are (such as a root
     * variable), and then have them connected through their parent and subtrees attributes. 
     * 
     * @param name      the name describing the code to be executed stored in this tree node, this should be
     *                  unique within the tree
     * @param toExecute the page object with its execute method which is called by the Tree's execute method
     */
    public Tree(Tree parent, String name, ITreeNode toExecute){
        this.parent = parent;  // for the root of a tree set to NULL
        this.nodeName = name;  // should not be null
        this.toExecute = toExecute;  // should not be null and be from a class implementing ITreeNode
        this.subtrees = new ArrayList<Tree>();  // tree has no subtrees by default
    }

    
    /**
     * @return false if the tree is not empty, or true if the tree is empty at the node that is calling the method
     */
    public boolean isEmpty(){

        if (this.nodeName == null || this.toExecute == null){
            return true;
        }
        return false;
    }


    /**
     * @return the size of the tree rooted at the given node which is calling this method
     * 0 if tree is empty, sum of subtree sizes + 1 if not
     */
    public int size(){

        if (this.isEmpty()){
            return 0;
        } else {
            int result = 1;
            for (Tree subTree : this.subtrees){
                result += subTree.size();
            } 

            return result;
        }
    }


    /**
     * execute the code to run the page for the current node
     */
    public void execute(){
        this.toExecute.execute();  // call the execution method of the object implementing the ITreeNode interface
    }


    /**
     * add a new child to the tree  (this is used for the buildup of the standard constant tree)
     * @param nodeName node String name for the subtree being added (linked)
     * @param toExecute ITreeNode object representing the page of the GUI to be run when in this state of the tree
     */
    public void addSub(String nodeName, ITreeNode toExecute){
        Tree subTree = new Tree(this, nodeName, toExecute);  // create new tree instance
        this.subtrees.add(subTree);  // link current tree and subtree
    }


    /**
     * remove a child from the list of child subtrees
     * @param subToRemove the specific object to remove from the calling tree's subtrees attribute
     */
    public void removeSub(String subNameToRemove){

        for (Tree subTree : this.subtrees){
            if (subTree.nodeName == subNameToRemove){
                this.subtrees.remove(subTree);  // remove the subtree matching in nodeName
            }
        }
    }


    /**
     * @return the arraylist of children of the Tree node from which this method is called
     */
    public ArrayList<Tree> getChildren(){
        return this.subtrees;
    }
}
