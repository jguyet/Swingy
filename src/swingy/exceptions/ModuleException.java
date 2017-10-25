package swingy.exceptions;

public class ModuleException extends Exception {

	/**
	 * SERIAL VERSION
	 */
	private static final long serialVersionUID = 1L;

	public ModuleException(String arg) {
		super("Module exception from argument '" + arg + "' please select 'console' or 'gui' module.");
	}
	
	public ModuleException() {
		super("Module exception argument arg[0] dosen't exist please select 'console' or 'gui' module.");
	}
}
