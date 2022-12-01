import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads text from the given input txt file.
 */
fun readInputText(name: String): String = File("src", "$name.txt").readText()

/**
 * Reads lines from the given input txt file.
 */
fun readInputLines(name: String): List<String> = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')
