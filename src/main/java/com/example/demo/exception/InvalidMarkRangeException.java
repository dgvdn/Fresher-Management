package com.example.demo.exception;

public class InvalidMarkRangeException extends RuntimeException {
	public InvalidMarkRangeException(int minMark, int maxMark) {
		super("Invalid mark range: minMark = " + minMark + ", maxMark = " + maxMark);
	}
}
