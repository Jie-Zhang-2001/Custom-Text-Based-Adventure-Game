/**
 * The AdventureDesigner class creates an instance of AdventureDesigner object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 5 R09
 *
 */
import java.util.*;
public class AdventureDesigner {

/**
 * Runs the game with user inputs. Displays the scene and asks the user to 
 * select an option and prints the scene for that option.
 * @param x
 * 		The SceneTree object to be played
 */
	public static void playGame(SceneTree x) {
		String option;
		SceneNode cursor = x.getCursor();
		SceneTree playStory = x;
		playStory.setCursor(playStory.getRoot());
		Scanner stdin = new Scanner(System.in);
		System.out.print("\nNow beginning game...\n");
		do {
			try {
				playStory.getCursor().displayScene();
				System.out.print("\nPlease enter an option: ");
				option = stdin.nextLine();
				switch(option.toUpperCase()) {
				case "A":
					playStory.moveCursorForward("A");
					break;
				case "B":
					playStory.moveCursorForward("B");
					break;
				case "C":
					playStory.moveCursorForward("C");
					break;
				default:
					System.out.println("Enter a valid choice!");
					break;
				}
			
			}catch(NoSuchNodeException ex1) {
				System.out.println(ex1.getMessage());
			}
		}while(!playStory.getCursor().isEnding());
		playStory.getCursor().displayScene();
		x.setCursor(cursor);
		System.out.println("The End");
		System.out.println("\nReturning back to creation mode...");
	}
	
/**
 * Acts as a test to test the methods in SceneTree and SceneNode class.
 * @param args
 */
	public static void main(String[] args) {
		String title, scene,choice;
		int pos;
		SceneTree story = new SceneTree();
		Scanner stdin = new Scanner(System.in);
		boolean quit = false;
		System.out.println("Creating a story...");
		System.out.print("\nPlease enter a title: ");
		title = stdin.nextLine();
		System.out.print("Please enter a scene: ");
		scene = stdin.nextLine();
		SceneNode root = new SceneNode(title,scene,1);
		story.setRoot(root);
		story.setCursor(root);
		System.out.println("\nScene #" + SceneNode.getNumS() + " added.");
		do {
			try {
				System.out.println("\nA) Add Scene");
				System.out.println("R) Remove Scene");
				System.out.println("S) Show Current Scene");
				System.out.println("P) Print Adventure Tree");
				System.out.println("B) Go Back A Scene");
				System.out.println("F) Go Forward A Scene");
				System.out.println("G) Play Game");
				System.out.println("N) Print Path To Cursor");
				System.out.println("M) Move scene");
				System.out.println("Q) Quit");
				System.out.print("\nPlease enter a selection: ");
				choice = stdin.next();
				switch(choice.toUpperCase()) {
				case "A":
					stdin.nextLine();
					System.out.print("\nPlease enter a title: ");
					title = stdin.nextLine();
					System.out.print("Please enter a scene: ");
					scene = stdin.nextLine();
					story.addNewNode(title, scene);
					System.out.println("\nScene #" + SceneNode.getNumS() 
					  + " added.");
					break;
				case "R":
					stdin.nextLine();
					System.out.print("\nPlease enter an option: ");
					choice = stdin.nextLine();
					story.removeScene(choice);
					System.out.println(story.getRemoved());
					story.setRemoved("");
					break;
				case "S":
					story.getCursor().displayFullScene();
					System.out.println();
					break;
				case "P":
					System.out.print("\n" + story.toString());
					break;
				case "B":
					story.moveCursorBackwards();
					System.out.println("\nSuccessfully moved back to " 
					  + story.getCursor().getTitle());
					break;
				case "F":
					System.out.print("\nWhich option do you wish to go to: ");
					choice = stdin.next();
					story.moveCursorForward(choice);
					System.out.println("\nSucessfully moved to " 
					  + story.getCursor().getTitle());
					break;
				case "G":
					playGame(story);
					break;
				case "N":
					System.out.println(story.getPathFromRoot());
					break;
				case "M":
					System.out.print("\nMove current scene to: ");
					pos = stdin.nextInt();
					story.moveScene(pos);
					System.out.println("\nSuccessfully moved scene.");
					break;
				case "Q":
					quit = true;
					System.out.println("\nProgram terminating normally...");
					break;
				default:
					System.out.println("\nEnter a valid choice!");
					break;
				}
			}catch(NoSuchNodeException ex1) {
				System.out.println(ex1.getMessage());
			}catch(FullSceneException ex2) {
				System.out.println(ex2.getMessage());
			}
		}while(!quit);
		stdin.close();
	}
}
