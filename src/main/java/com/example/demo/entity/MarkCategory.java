package com.example.demo.entity;

public enum MarkCategory {
	UNSATISFACTORY("Unsatisfactory", 0, 50), NEEDS_IMPROVEMENT("Needs Improvement", 50, 60),
	SATISFACTORY("Satisfactory", 60, 70), GOOD("Good", 70, 80), EXCELLENT("Excellent", 80, 90),
	OUTSTANDING("Outstanding", 90, Integer.MAX_VALUE);

	private final String categoryName;
	private final int minRange;
	private final int maxRange;

	MarkCategory(String categoryName, int minRange, int maxRange) {
		this.categoryName = categoryName;
		this.minRange = minRange;
		this.maxRange = maxRange;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public boolean isInRange(float mark) {
		return mark >= minRange && mark < maxRange;
	}
}
