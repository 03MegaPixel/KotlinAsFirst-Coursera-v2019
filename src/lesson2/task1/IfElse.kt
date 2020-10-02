@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.*


/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    if (age <= 0 || age >= 200) return "Ошибка"
    val text: String = when {
        //1 -- год 2, 3, 4 -- года 5, 6, 7, 8, 9, 10, 11, 12, и все остальные "-надцать",
        // а также 20 -- лет 21 -- год 22, 23, 24 -- года и т.д.
        age % 100 in 11..20 -> "лет"
        age % 10 == 1 -> "год"
        age % 10 in 2..4 -> "года"
        else -> "лет"
    }
    return "$age $text"
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val part1Km: Double = t1 * v1
    val part2Km: Double = t2 * v2
    val part3Km: Double = t3 * v3
    val halfWayKm: Double = (part1Km + part2Km + part3Km) / 2.0
    return when {
        part1Km >= halfWayKm -> halfWayKm / v1
        part1Km + part2Km >= halfWayKm -> t1 + (halfWayKm - part1Km) / v2
        else -> t1 + t2 + (halfWayKm - part1Km - part2Km) / v3
    }


}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    var rook1 = false
    var rook2 = false
    if (rookX1 == kingX || rookY1 == kingY) rook1 = true
    if (rookX2 == kingX || rookY2 == kingY) rook2 = true
    return when {
        rook1 && rook2 -> 3
        rook2 -> 2
        rook1 -> 1
        else -> 0
    }

}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    var rook = false
    var bishop = false
    if (rookX == kingX || rookY == kingY) rook = true
    if ((bishopX - bishopY == kingX - kingY) || (bishopX + bishopY == kingX + kingY)) bishop = true
    return when {
        rook && bishop -> 3
        bishop -> 2
        rook -> 1
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {

    val otherSide: Double
    val maxSide: Double = max(a, max(b, c))
    val minSide: Double = min(a, min(b, c))
    otherSide = a + b + c - maxSide - minSide
    return if (
        (a < b + c) && (a > b - c) &&
        (b < a + c) && (b > a - c) && //Проверка на существующий треугольник
        (c < a + b) && (c > a - b)
    )
        when {
            maxSide.pow(2) == minSide.pow(2) + otherSide.pow(2) -> 1
            //если Квадрат Найбольшей стороны(Гипотенуза) равен сумме квадратов двух других то это прямоуголый треугольник(1)
            maxSide.pow(2) > minSide.pow(2) + otherSide.pow(2) -> 2
            //если Квадрат Найбольшей стороны БОЛЬШЕ сумме квадратов двух других то это ТУПОУГОЛЬНЫЙ треугольник(2)
            else -> 0 //В других случаях это ОСТРОУГОЛЬНЫЙ треугольник(0)
        }
    else -1 //Проверка на существующий треугольник не пройдена(-1)


}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return if ((a > c && a > d) || (b < c && b < d)) -1
    else {
        val maxPoint = max(b, d)
        val minPoint = min(a, c)
        val fullLength = maxPoint - minPoint
        val abL = b - a
        val cdL = d - c
        abL + cdL - fullLength // Если результат < 0 -> Отрезки не пересекаются.Это можно использовать вместо  if ((a > c && a > d) || (b < c && b < d)) -1
    }
}
