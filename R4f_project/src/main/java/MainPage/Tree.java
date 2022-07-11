package main.java.MainPage;

import java.util.ArrayList;

public class Tree{

    public enum TreeState {
        WELCOME, HOME, ACCOUNT, CALENDAR, ABOUT, SURVEY;
    }

    // class for structuring the tree for traversing the different pages to be displayed
    protected Tree parent;
    protected TreeState stateName;  // Invariant: this String should be unique within the tree nodes
    protected String currPageName;
    protected ArrayList<Tree> subtrees;
    

    /** Usage:  
     * moving through the tree consists of creating the tree with the correct structure, and then keeping  a 
     * root variable managing the current stage of the program by containing the current Tree node being
     * executed
     * Based on the state, the main function managing the tree will be given certain permissions and attributes to use
    */

    /**
     * Constructor for a Tree node object
     * You first create a Tree type node with its corresponding information which would be the root,
     * then create the possible subtrees and link them by modifying the parent and subtree attributes
     * 
     * Ideally, you'd have a variable to describe the current place in the tree were you are (such as a root
     * variable), and then have them connected through their parent and subtrees attributes. 
     * 
     * @param parent    the tree node representing the previous (parent) state of the program
     * @param state     the name describing the page being executed stored in this tree node, this should be
     *                  unique within the tree
     * @param pageName  the string name ending in .html containning the reference name of the page to load
     */
    public Tree(Tree parent, TreeState state, String pageName){
        this.parent = parent;  // for the root of a tree set to NULL
        this.stateName = state;  // should not be null
        this.subtrees = new ArrayList<Tree>();  // tree has no subtrees by default
        this.currPageName = pageName;
    }

    
    /**
     * @return false if the tree is not empty, or true if the tree is empty at the node that is calling the method
     */
    public boolean isEmpty(){

        if (this.stateName == null || this.currPageName == ""){
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
     * add a new child to the tree  (this is used for the buildup of the standard constant tree)
     * @param stateName node state name for the subtree being added (linked)
     * @param pageName  the string name ending in .html containning the reference name of the page to load
     * @return          the child Tree that just got created
     */
    public Tree addSub(TreeState stateName, String pageName){
        Tree subTree = new Tree(this, stateName, pageName);  // create new tree instance
        this.subtrees.add(subTree);  // link current tree and subtree
        return subTree;
    }


    /**
     * remove a child from the list of child subtrees
     * @param subToRemove the specific object to remove from the calling tree's subtrees attribute
     */
    public void removeSub(TreeState subState){

        for (Tree subTree : this.subtrees){
            if (subTree.stateName == subState){
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
