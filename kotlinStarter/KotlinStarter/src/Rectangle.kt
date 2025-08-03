import kotlin.math.PI
import kotlin.math.sqrt

data class Rectangle(val width: Float, val height: Float) : Shape() {
    val diagonal = sqrt(width * width + height * height)
    override val area = width * height
    override val circumference: Float
        get() = 2 * (width + height)
}

data class Circle(val radius: Float) : Shape() {
    override val area = radius * radius * PI.toFloat()
    override val circumference: Float
        get() = 2 * radius * PI.toFloat()
}

/**
 * we can also create an object/singleton class in kotlin as shown below:
 * The unit square class is a class which will only have 1 object all through out
 * the code and is a constant
 */

object unitSquare:Shape()
{
    override val area: Float=1f
    override val circumference: Float=1f
}

/*
interface Shape {
    val area: Float
    val circumference: Float
}
*/

fun printTypeOfShape(vararg shapes: Shape) {
    for(s in shapes)
    {
        val output=when(s)
        {
            is Circle->println("Yo,thats a circle")
            is Rectangle->println("Yo,thats a rectangle")
            is unitSquare->println("This is a unit square")
            else->println("not a s hape")
        }
    }
}

abstract class Shape {
    abstract val area: Float
    abstract val circumference: Float
}

fun main() {
    val rect1 = Rectangle(4f, 8f)
    println(rect1.height)
    println(rect1.width)

    val rect2 = Rectangle(4f, 8f)
    println(rect2.height)
    println(rect2.width)

    println(rect1 == rect2)//true if Rectangle is a data class and false otherwise

    val rect3 = rect1.copy(height = 10f)//.copy() is a feature of a data class
    println(rect3)//printed differently depending on the fact that whether the Rectangle is a data class or not
    val circle = Circle(5f)
    println("sum of areas of rect3 and circle -> ${sumAreas(rect1, circle)}")
    printTypeOfShape(circle,rect1)
}

fun maxArea(rect1: Rectangle, rect2: Rectangle): Float {
    return maxOf(rect1.area, rect2.area)
}

fun sumAreas(vararg shapes: Shape): Double {
    return shapes.sumOf { currentShape -> currentShape.area.toDouble() }
}
/*
open class is a class that can be inherited.foe example

open class Shape
{
var counter=0
open val area:Float=0f
open val circumference:Float=0f

fun inc()
    {
    counter++;
    }


}
 */

//what is a sealed class?
//what is a module?
//what is internal access modifier