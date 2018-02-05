package com.mmotiy;

public class Application {

	public static void run() {
		Algorithm<FindFactorsInArrayParam> algorithm = new FindFactorsInArray();
		Integer[] temp = { 4, 7, 9, 3, 2, 10, 9, 13, 17, 14, 1 };
		FindFactorsInArrayParam param = new FindFactorsInArrayParam(temp, 14);
		Result<?> result = algorithm.start(param);
	}

	public static void main(String[] args) {
		Application.run();
	}
}
