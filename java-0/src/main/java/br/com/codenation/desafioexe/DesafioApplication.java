package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;


public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List<Integer> numList = new ArrayList<>();
		numList.add(0);
		numList.add(1);
		for(int num = 1; numList.get(num) < 350; num++) {
			numList.add(numList.get(num) + numList.get(num-1));
		}
		return numList;
	}


	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}