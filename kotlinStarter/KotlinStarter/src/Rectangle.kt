import kotlin.math.sqrt

class Rectangle(val width: Float, val height: Float) {
    val diagonal = sqrt(width * width + height * height)
    val area = width * height
}

fun main() {
    val rect1 = Rectangle(4f, 8f)
    println(rect1.height)
    println(rect1.width)

    val rect2 = Rectangle(4f, 8f)
    println(rect2.height)
    println(rect2.width)

    println(rect1 == rect2)
}

fun maxArea(rect1: Rectangle, rect2: Rectangle): Float {
    return maxOf(rect1.area, rect2.area)
}