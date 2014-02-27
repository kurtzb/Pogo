package me.nrubin29.pogo.function;

import me.nrubin29.pogo.InvalidCodeException;
import me.nrubin29.pogo.gui.Console;
import me.nrubin29.pogo.lang.Block;

abstract class Function {

	private final String name;
	
	Function(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public abstract void run(Console console, Block b, String[] args) throws InvalidCodeException;
}