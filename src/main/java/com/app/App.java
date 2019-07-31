package com.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {

		String dictionary = args[0];
		String lineStr = args[1];

		List<String> dictionaryList = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(dictionary))) {
			dictionaryList = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		List<String> lineStrList = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(lineStr))) {
			lineStrList = stream.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		// First line only reading from Second input file
		String valiDateStr = lineStrList.get(0);

		Set<String> matchlist = new HashSet<String>();

		for (String s1 : dictionaryList) {

			char first = s1.charAt(0);
			char last = s1.charAt(s1.length() - 1);

			for (int i = 0; i < valiDateStr.length(); i++) {

				if (valiDateStr.length() >= i + s1.length()) {

					if (first == valiDateStr.charAt(i) && last == valiDateStr.charAt(i + s1.length() - 1)) {

						String matchStr = valiDateStr.substring(i, i + s1.length());

						if (StringToASCII(s1) == StringToASCII(matchStr)) {

							System.out.println("Matched Words  " + matchStr);

							matchlist.add(s1);

						}
					}
				}
			}

		}

		System.out.println("Distinct Matched Words count " + matchlist.size());
	}

	public static long StringToASCII(final String str) {

		long sum = 0;
		for (int j = 0; j < str.length(); j++) {

			sum = sum + (int) str.charAt(j);

		}
		return sum;
	}
}
