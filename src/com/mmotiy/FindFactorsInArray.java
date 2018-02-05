package com.mmotiy;

import static com.mmotiy.ConsoleUtil.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个整形数组，要求在O(n)时间复杂度内 找到给定一个整形的两个因子并输出
 * 
 * @author mmotiy
 *
 */
public class FindFactorsInArray implements Algorithm<FindFactorsInArrayParam> {
	// 标记因子的出现
	private Map<Integer, MapValue> map = new HashMap<Integer, MapValue>();

	@Override
	public Result<List<Map<String, Integer>>> start(FindFactorsInArrayParam d) {
		// 假设这里已经做完参数验证的工作了(0.0)
		collectFactors(map, d.target);
		return findAllFactors(d);
	}

	private void collectFactors(Map<Integer, MapValue> map, Integer target) {
		for (int i = 1, limit = target; i <= limit; i++) {
			if (target % i != 0) {// 判断i是不是target的因子
				continue;
			}
			// 是因子加入到标记中
			map.put(i, new MapValue(target / i));
		}
	}

	private Result<List<Map<String, Integer>>> findAllFactors(FindFactorsInArrayParam d) {
		List<Map<String, Integer>> ls = new ArrayList<Map<String, Integer>>();
		// 如果传入的是有序数组，使用快速查找的方法可以进一步降低时间复杂度。这里就偷懒了，假设是无序的。
		for (int i = d.arrays.length; i-- > 0;) {// 遍历整个给定数组集合
			// 待定的因子组合
			Integer alternative = d.arrays[i];
			// 目标值
			Integer target = d.target;
			if (target % alternative != 0) {// 判断alternative是不是target的因子
				continue;
			}
			// 取出与之对应的另外一个标记因子，将自己标记为已出现
			MapValue otherFactor = map.get(target / alternative);
			if (otherFactor.book == 0) {// 如果当前因子没有出现过，则标记book为1.表示出现
				otherFactor.book = 1;
			}
			// 取出当前标记因子，判断另外一个因子是不是已出现
			MapValue currentFactor = map.get(alternative);
			if (currentFactor.book == 1) {
				console("因子1:" + otherFactor.value + "因子2:" + currentFactor.value);
				Map<String, Integer> m = new HashMap<String, Integer>(2);
				m.put("firstFactor", otherFactor.value);
				m.put("secondFactor", currentFactor.value);
				ls.add(m);
				// 避免重复输出
				currentFactor.book++;
			}
		}
		return new Result<List<Map<String, Integer>>>(ls);
	}
}
