package caryhsu.nim.game;

public class NimNumberUtils {

	public static int[] toBinaryArray(int value) {
		char[] chars = Integer.toBinaryString(value).toCharArray();
		int[] result = new int[chars.length];
		for(int i = 0; i < chars.length; i++) {
			result[i] = Integer.parseInt("" + chars[i]);
		}
		return result;
	}
	
	public static int maxLength(int[]...arraies) {
		int size = 0;
		for(int[] array : arraies) {
			if (size < array.length) {
				size = array.length;
			}
		}
		return size;
	}

	public static int sumOfArraies(int[]...arraies) {
		int size = maxLength(arraies);
		int[] result = new int[size];
		for(int[] array : arraies) {
			for(int i = 0; i < array.length; i++) {
				int index1 = array.length - 1 - i;
				int value = array[index1];
				int index2 = size - 1 - i;
				result[index2] += value;
			}
		}
		for(int i = 0; i < result.length; i++) {
			result[i] = result[i] % 2;
		}
		return sum(result);
	}

	private static int sum(int[] values) {
		int sum = 0;
		for(int value : values) {
			sum += value;
		}
		return sum;
	}

}
