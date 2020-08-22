/**
 * The IllegalNumResException class creates an instance of NoSuchNodeException
 * object.
 * 
 * @author Jie Zhang
 *		e-mail:jie.zhang.2@stonybrook.edu
 *		Stony Brook ID: 112645894
 *		CSE214 HW 5 R09
 *
 */
public class NoSuchNodeException extends Exception {
	public NoSuchNodeException() {
		super("\nThat option does not exist!");
	}
}
