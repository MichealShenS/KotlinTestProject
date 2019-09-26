package com.ssq.kotlin_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /*var： var是一个可变变量，这是一个可以通过重新分配来更改为另一个值的变量。这种声明变量的方式和Java中声明变量的方式一样。
    val: val是一个只读变量，这种声明变量的方式相当于java中的final变量。一个val创建的时候必须初始化，因为以后不能被改变。*/

    //定义只读局部变量使用关键字 val 定义。只能为其赋值一次。
    val a: Int = 1 // 立即赋值
    val b = 2 // 自动推断出 `Int` 类型
    //可重新赋值的变量使用 var 关键字：
    var x = 5


    @BindView(R.id.text)
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        text = findViewById(R.id.text)
        ButterKnife.bind(this)
        text.setText("Hello ButterKnife Kotlin!")
//        println(printSum(9, 8))
//        println("b = $b,x = ${sum(x, b)}")
//        println("max of 4 and 52 is ${maxOf(4, 52)}")
//        forFun()
        whileFun();
    }

//    companion object { //我是静态块
//        /**
//        这里面定义的静态变量跟静态方法，类似java的：static
//         **/
//        @JvmStatic
//        fun main(args: Array<String>) {
//
//        }
//    }

    /**
     * 带有两个 Int 参数、返回 Int 的函数：
     */
//    fun sum(a: Int, b: Int): Int {
//        return a + b
//    }

    //将表达式作为函数体、返回值类型自动推断的函数：
    fun sum(a: Int, b: Int) = a + b

    //函数返回无意义的值(Unit可省略):
    fun printSum(a: Int, b: Int): Unit {
        println("sum of $a and $b is ${a + b}")
    }

    //条件表达式
//    fun maxOf(a: Int, b: Int) : Int{
//        if (a>b){
//            return a
//        } else{
//            return b
//        }
//    }

    //在 Kotlin 中，if 也可以用作表达式：
    fun maxOf(a: Int, b: Int) = if (a > b) a else b

    //当某个变量的值可以为 null 的时候，必须在声明处的类型后添加 ? 来标识该引用可为空。
    //使用返回可空值的函数:
    fun parseInt(string: String): Int? {
        return string.toIntOrNull()
    }

    fun printProduct(arg1: String, arg2: String) {
        val x = parseInt(arg1)
        val y = parseInt(arg2)
        // 直接使用 `x * y` 会导致编译错误，因为它们可能为 null
        if (x != null && y != null) {
            // 在空检测后，x 与 y 会自动转换为非空值（non-nullable）
            println(x * y)
        } else {
            println("$arg1 or $arg2 is not a number")
        }
    }

    //is 运算符检测一个表达式是否某类型的一个实例。
    // 如果一个不可变的局部变量或属性已经判断出为某类型，
    // 那么检测后的分支中可以直接当作该类型使用，无需显式转换：
    fun getStringLength(obj: Any): Int? {

        if (obj is String) return obj.length// `obj` 在该条件分支内自动转换成 `String`

        return null// 在离开类型检测分支后，`obj` 仍然是 `Any` 类型

//        if (obj is String) {
//            // `obj` 在该条件分支内自动转换成 `String`
//            return obj.length
//        } else {
//            // 在离开类型检测分支后，`obj` 仍然是 `Any` 类型
//            return null
//        }
    }

    //for循环
    fun forFun() {
        val items = listOf("apple", "banana", "kiwifruit");
//        for (item in items){
//            println(item)
//        }

        for (index in items.indices) println("item at $index is ${items[index]}")
    }

    //while 循环
    fun whileFun() {
        val items = listOf("apple", "banana", "kiwifruit")
        var index = 0
        while (index < items.size) {
            println("item at $index is ${items[index]}")
            index++
        }
    }

    //when 表达式
    fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a String"
            else -> "Unknown"
        }

    //使用区间（range）
    //使用 in 运算符来检测某个数字是否在指定区间内：
    fun isRange() {
        val x = 10
        val y = 9
        if (x in 1..y + 1) println("fits in range")
    }

    //检测某个数字是否在指定区间外:
    fun outRange() {
        val list = listOf("a", "b", "c");
        if (-1 !in 0..list.lastIndex) println("-1 is out of range")
        if (list.size !in list.indices) println("list size is out of valid list indices range, too")
    }


    fun iterFun() {
        //区间迭代:
        for (x in 0..5) print(x)
        //012345

        //或数列迭代：
        for (x in 1..10 step 2) print(x)
        println()
        for (x in 9 downTo 0 step 3) print(x)
    }

    //Collections
    fun collectionFun() {
        val items = listOf("apple", "banana", "kiwifruit")

        //对集合进行迭代:
        for (item in items) println(item)
        //使用 in 运算符来判断集合内是否包含某实例：
        when {
            "orange" in items -> println("juicy")
            "apple" in items -> println("apple is fine too")
        }

        //使用 lambda 表达式来过滤（filter）与映射（map）集合：
        val fruits = listOf("banana", "avocado", "apple", "kiwifruit");
        fruits.filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
    }
}
