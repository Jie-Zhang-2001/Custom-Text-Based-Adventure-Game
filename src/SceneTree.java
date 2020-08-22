/**
 * The SceneTree class creates an instance of SceneTree object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 5 R09
 *
 */
public class SceneTree {
	private SceneNode root;
	private SceneNode cursor;
	private String removed;
	SceneNode x = new SceneNode();

/**
 * Creates an instance of SceneTree object
 */
	public SceneTree() {
		root = null;
		cursor = null;
	}

/**
 *Returns the string representation of the variable removed. 
 * @return
 * 		the string representation of variable removed.
 */
	public String getRemoved() {
		return removed;
	}

/**
 * Sets the reference of this root variable to root
 * @param root
 * 		the reference to be set to this root variable
 */
	public void setRoot(SceneNode root) {
		this.root = root;
	}

/**
 * Sets the reference of this cursor variable to cursor
 * @param cursor
 * 		the reference to be set to this cursor variable
 */
	public void setCursor(SceneNode cursor) {
		this.cursor = cursor;
	}

/**
 * Returns the reference of cursor variable
 * @return
 * 		the reference of cursor variable
 */
	public SceneNode getCursor() {
		return cursor;
	}

/**
 * Moves the cursor to the parent node
 * @throws NoSuchNodeException
 * 		thrown when the current node does not have a parent
 */
	public void moveCursorBackwards() throws NoSuchNodeException {
		if (cursor.getParent() != null) {
			cursor = cursor.getParent();
		} else {
			throw new NoSuchNodeException();
		}
	}

/**
 * Moves the cursor to the appropriate child node according to user input
 * @param option
 * 		the variable determining the child node to move the cursor to.
 * @throws NoSuchNodeException
 * 		thrown when the current node does not have such child
 */
	public void moveCursorForward(String option) throws NoSuchNodeException {
		option = option.toUpperCase();
		if (option.equals("A")) {
			if (cursor.getLeft() != null) {
				this.setCursor(cursor.getLeft());
			} else {
				throw new NoSuchNodeException();
			}
		}
		if (option.equals("B")) {
			if (cursor.getMid() != null) {
				cursor = cursor.getMid();
			} else {
				throw new NoSuchNodeException();
			}
		}
		if (option.equals("C")) {
			if (cursor.getRight() != null) {
				cursor = cursor.getRight();
			} else {
				throw new NoSuchNodeException();
			}
		}
	}

/**
 * Returns the reference of the root variable
 * @return
 * 		the reference of the root variable
 */
	public SceneNode getRoot() {
		return root;
	}

/**
 * Sets the reference of removed variable to x
 * @param x
 * 		the reference to be set to the variable removed
 */
	public void setRemoved(String x) {
		removed = x;
	}

/**
 * Removes the specified child from the tree
 * @param option
 * 		the string value dictating which child to be removed
 * @throws NoSuchNodeException
 * 		thrown when the current node does not have such child
 */
	public void removeScene(String option) throws NoSuchNodeException {
		option = option.toUpperCase();
		if (option.equals("A")) {
			if (cursor.getLeft() != null) {
				removed = "\n" + cursor.getLeft().getTitle() + " removed";
				if (cursor.getMid() != null) {
					if (cursor.getRight() != null) {
						cursor.setLeft(cursor.getMid());
						cursor.setMid(cursor.getRight());
						cursor.setRight(null);
					} else {
						cursor.setLeft(cursor.getMid());
						cursor.setMid(null);
					}
				} else {
					cursor.setLeft(null);
				}
			} else {
				throw new NoSuchNodeException();
			}
		}
		if (option.equals("B")) {
			if (cursor.getMid() != null) {
				removed = "\n" + cursor.getMid().getTitle() + " removed";
				if (cursor.getRight() != null) {
					cursor.setMid(cursor.getRight());
					cursor.setRight(null);
				} else {
					cursor.setMid(null);
				}
			} else {
				throw new NoSuchNodeException();
			}
		}
		if (option.equals("C")) {
			if (cursor.getRight() != null) {
				removed = "\n" + cursor.getRight().getTitle() + " removed";
				cursor.setRight(null);
			} else {
				throw new NoSuchNodeException();
			}
		}
		if(!option.equals("A") && !option.equals("B") && !option.equals("C")) 
		  {
			throw new NoSuchNodeException();
		}
	}

/**
 * Finds the SceneNode object in the tree with the specified sceneID value
 * @param root
 * 		the root of the tree, the beginning of the traversal.
 * @param ID
 * 		the specified ID to be found in the tree
 * @return
 * 		the SceneNode reference of the found node.
 */
	public SceneNode Find(SceneNode root, int ID) {
		if (root == null) {
			return null;
		}
		if (root.getID() == ID) {
			return root;
		}
		SceneNode found = null;
		if (root.getLeft() != null) {
			found = Find(root.getLeft(), ID);
			if (found != null) {
				return found;
			}
		}
		if (root.getMid() != null) {
			found = Find(root.getMid(), ID);
			if (found != null) {
				return found;
			}
		}
		if (root.getRight() != null) {
			found = Find(root.getRight(), ID);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

/**
 * 
 * @param ID
 * 		an integer specifying which node this SceneNode object should be moved 
 * 		under
 * @throws NoSuchNodeException
 * 		thrown when there does not exist a SceneNode with the supplied SceneID
 * @throws FullSceneException
 * 		thrown when the SceneNode specified have three children already
 */
	public void moveScene(int ID) throws NoSuchNodeException,
	  FullSceneException {
		SceneNode orgCursor = cursor.getParent();
		int orgID = cursor.getID();
		SceneNode target;
		target = Find(root, ID);
		if (target == null) {
			throw new NoSuchNodeException();
		} else {
			target.addScene(cursor);
		}
		cursor = orgCursor;
		  if(orgCursor.getLeft().getID() == orgID){ 
			  this.removeScene("A");
		  }else if(orgCursor.getMid().getID() == orgID){ 
			  this.removeScene("B"); 
		  }else if(orgCursor.getRight().getID() == orgID){
			  this.removeScene("C"); 
		  }
		  cursor = Find(root,orgID);
	}

/**
 * Returns a string representation of this SceneTree object
 */
	public String toString() {
		return toStringHelper(root, 1);
	}

/**
 * Returns the string representation of the path from root to cursor
 * @return
 * 		the string representation of the path from root to cursor
 */
	public String getPathFromRoot() {
		String path = cursor.getTitle();
		SceneNode parent = cursor;
		while (parent.getParent() != null) {
			path = parent.getParent().getTitle() + ", " + path;

			parent = parent.getParent();
		}
		return path;
	}

/**
 * The helper method to compose the string representation of this SceneTree
 * object
 * @param root
 * 		the root of the tree, the beginning of the traversal
 * @param space
 * 		the indentation to be added in front of each node depending on its depth
 * @return
 * 		a string representation of this SceneTree object
 */
	public String toStringHelper(SceneNode root, int space) {
		String tree = "";
		if (root == cursor) {
			tree += root.toString();
			tree += "* \n";
		} else {
			tree += root.toString();
			tree += "\n";
		}
		if (root.getLeft() != null) {
			for (int i = 0; i < space; i++) {
				tree += "	";
			}
			tree += "A) " + toStringHelper(root.getLeft(), space + 1);
		}
		if (root.getMid() != null) {
			for (int i = 0; i < space; i++) {
				tree += "	";
			}
			tree += "B) " + toStringHelper(root.getMid(), space + 1);

		}
		if (root.getRight() != null) {
			for (int i = 0; i < space; i++) {
				tree += "	";
			}
			tree += "C) " + toStringHelper(root.getRight(), space + 1);
		}
		return tree;
	}

/**
 * Creates an instance object of SceneNode and adds it to the current node with
 * supplied values 
 * @param title
 * 		the title of the to-be-added SceneNode object
 * @param sceneDescription
 * 		the sceneDescription of the to-be-added SceneNode object
 * @throws FullSceneException
 * 		thrown when the current node does not have any available child position
 */
	public void addNewNode(String title, String sceneDescription) throws 
	  FullSceneException {
		SceneNode sc = new SceneNode(title, sceneDescription, SceneNode.
		  getNumS() + 1);
		cursor.addScene(sc);
	}
}
