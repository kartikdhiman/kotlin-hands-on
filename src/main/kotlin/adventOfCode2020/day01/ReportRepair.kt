package adventOfCode2020.day01
import java.io.File

fun main(args: Array<String>) {
	val numbers = File("src/main/kotlin/adventOfCode2020/day01/report-repair.txt")
		.readLines()
		.map(String::toInt)

	println(numbers.findPairOfSum(2020)?.let { (a, b) -> a * b })
	println(numbers.findTripleOfSum(2020)?.let { (a, b, c) -> a * b * c})
}

fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int>? {
	val complements = associateBy { sum - it }
	return mapNotNull { num ->
		val complement = complements[num]
		if (complement != null) Pair(num, complement) else null
	}.firstOrNull()
}

fun List<Int>.findTripleOfSum(sum: Int): Triple<Int, Int, Int>? =
	firstNotNullOfOrNull { x ->
		findPairOfSum(sum - x)?.let { pair ->
			Triple(x, pair.first, pair.second)
		}
	}
