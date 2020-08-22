/**
 * The IllegalNumResException class creates an instance of FullSceneException
 * object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 5 R09
 *
 */
public class FullSceneException extends Exception{
	public FullSceneException() {
		super("\nYou cannot add another scene!");
	}
}
