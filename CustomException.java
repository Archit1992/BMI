package BMIException;

public class CustomException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1069338385097177924L;
	
	public CustomException(String msg){
		super(msg);
	}
	public CustomException(){
		super();
	}
	
}
