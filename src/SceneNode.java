/**
 * The SceneNode class creates an instance of SceneNode object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 5 R09
 *
 */
public class SceneNode {
	private String title;
	private String sceneDescription;
	private int sceneID;
	private SceneNode left;
	private SceneNode middle;
	private SceneNode right;
	private SceneNode parent;
	private static int numScenes = 1;

/**
 * Creates an instance of SceneNode object
 */
	public SceneNode() {
		left = null;
		middle = null;
		right = null;
	}

/**
 * Returns a string representation of the title variable.
 * @return
 * 		the string representation of title variable
 */
	public String getTitle() {
		return this.title;
	}

/**
 * Returns a string representation of the sceneDescription variable.
 * @return
 * 		the string representation of sceneDescription variable
 */
	public String getDescription() {
		return this.sceneDescription;
	}

/**
 * Returns the integer value of the sceneID variable.
 * @return
 * 		the integer value of sceneID variable
 */
	public int getID() {
		return this.sceneID;
	}

/**
 * Returns the reference of object left
 * @return
 * 		the reference of the object: left
 */
	public SceneNode getLeft() {
		return left;
	}
	
/**
 * Returns the reference of the SceneNode object middle
 * @return
 * 		the reference of the SceneNode object: middle
 */
	public SceneNode getMid() {
		return middle;
	}

/**
 * Returns the reference of the SceneNode object right
 * @return
 * 		the reference of the SceneNode object: right
 */
	public SceneNode getRight() {
		return right;
	}

/**
 * Sets the reference of variable left to sleft
 * @param sleft
 * 		the SceneNode object reference to be set to variable left
 */
	public void setLeft(SceneNode sleft) {
		left = sleft;
	}
	
/**
 * Sets the reference of variable middle to smid
 * @param smid
 * 		the SceneNode object reference to be set to variable middle
 */
	public void setMid(SceneNode smid) {
		middle = smid;
	}
	
/**
 * Sets the reference of variable right to sright
 * @param sright
 * 		the SceneNode object reference to be set to variable right
 */
	public void setRight(SceneNode sright) {
		right = sright;
	}
	
/**
 * Returns the SceneNode reference of variable parent
 * @return
 * 		the reference of variable parent
 */
	public SceneNode getParent() {
		return parent;
	}

/**
 * Returns the integer value of numScenes
 * @return
 * 		the integer value of numScenes
 */
	public static int getNumS() {
		return numScenes;
	}
	
/**
 * Creates an instance of SceneNode object with given parameters
 * @param title
 * 		the title of the to-be-created SceneNode object
 * @param sceneDescription
 * 		the sceneDescription of the to-be-created SceneNode object
 * @param sceneID
 * 		the sceneID of the to-be-created SceneNode object
 */
	public SceneNode(String title, String sceneDescription, int sceneID) {
		this.title = title;
		this.sceneDescription = sceneDescription;
		this.sceneID = sceneID;
		left = null;
		right = null;
		middle = null;
	}
	
/**
 * Sets the scene to the first leftmost available slot in the child nodes
 * @param scene
 * 		the SceneNode object to be added
 * @throws FullSceneException
 * 		thrown when the current node does not have any empty child nodes
 */
	public void addScene(SceneNode scene) throws FullSceneException{
		if(this.left == null) {
			this.left = scene;
			numScenes++;
			left.parent = this;
		}else if(this.middle == null) {
			this.middle = scene;
			numScenes++;
			middle.parent = this;
		}else if(this.right == null) {
			this.right = scene;
			numScenes++;
			right.parent = this;
		}else {
			throw new FullSceneException();
		}
	}

/**
 * Returns a boolean value determining whether a SceneNode is a leaf.
 * @return
 * 		a boolean value: True if the node is a leaf, false otherwise
 */
	public boolean isEnding() {
		return (left == null) ? true:false;
	}
	
/**
 * Returns a string representation of this SceneNode object
 */
	public String toString() {
		return this.getTitle() + " (#" + this.getID() +")";
	}
	
/**
 * Outputs the scene information
 */
	public void displayScene() {
		System.out.println("\n" + this.title + "\n" + this.sceneDescription 
		  + "\n");
		if(this.left != null) {
			System.out.println("A) " + this.left.title);
			if(this.middle != null) {
				System.out.println("B) " + this.middle.title);
				if(this.right != null) {
					System.out.println("C) " + this.right.title);
				}
			}
		}
	}
	
/**
 * Outputs all information about a scene, including sceneID, sceneDescription,
 * title and its child nodes.
 */
	public void displayFullScene() {
		System.out.print("\nScene ID #" + this.sceneID + "\nTitle: " + 
		  this.title + "\nScene: " + this.sceneDescription + "\nLeads to: ");
		if(this.left == null) {
			System.out.println(" NONE");
		}else {
			System.out.print(" '" + this.left.getTitle() + "' (#" + 
			  this.left.getID() + ")");
			if(this.middle != null) {
				System.out.print(", '" + this.middle.getTitle() + "' (#" + 
				  this.middle.getID() + ")");
				if(this.right != null) {
					System.out.print(", '" + this.right.getTitle() + "' (#" + 
					  this.right.getID() + ")");
				}
			}
		}
	}
}
