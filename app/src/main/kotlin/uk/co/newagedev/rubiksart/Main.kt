package uk.co.newagedev.rubiksart

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.abs


fun main() {
//    getCubesFromImage("examples/mario/question_block.png", 6).windowed(6, 6).map {
//        println(it)
//    }

//    dither()

    analyze()
}

fun Long.toARGB(): List<Int> {
    return listOf(
        (this and 0xFF000000 shr 24).toInt(),
        (this and 0x00FF0000 shr 16).toInt(),
        (this and 0x0000FF00 shr 8).toInt(),
        (this and 0x000000FF).toInt()
    )
}

fun List<Int>.toColourInt(): Int {
    return ((component1() shl 24) + (component2() shl 16) + (component3() shl 8) + component4())
}

private fun closest(pixel: List<Int>, colours: List<List<Int>>): List<Int> {
    var closest = colours.first()

    for (colour in colours.drop(1)) {
        if (colour.mapIndexed { index, component -> abs(component - pixel[index]) }
                .sum() < closest.mapIndexed { index, component -> abs(component - pixel[index]) }.sum()) {
            closest = colour
        }
    }

    return closest
}

private fun analyze() {
    val bufferedImage = ImageIO.read(File("starry2.png"))

    val output = mutableListOf<Long>()
    for (y in 0 until bufferedImage.height) {
        for (x in 0 until bufferedImage.width) {
            output.add(bufferedImage.getRGB(x, y).toLong())
        }
    }

    val my = false

    val colours = if (my ) {
        listOf(
            0xFFFF7852,
            0xFF07DB86,
            0xFF03CBFE,
            0xFFD4D7E0,
            0xFFFFB264,
            0xFFF1E213,
        )
    } else {
        listOf(
            0xFFFF0000,
            0xFF00FF00,
            0xFF0000FF,
            0xFFFFFFFF,
            0xFFFFA500,
            0xFFFFFF00,
        )
    }

    val ditherSize = 2
    val counters = MutableList(ditherSize * ditherSize) { 0 }

    val ditheredColours = mutableListOf<List<Int>>()

    val ditherMap = mutableMapOf<List<Int>, List<List<Int>>>()

    while (counters.last() < 6) {
        val average = MutableList(4) { 0 }
        val cols = counters.map { colours[it].toARGB() }
        for (i in cols.indices) {
            for (j in 0 until 4) {
                average[j] += cols[i][j] / (ditherSize * ditherSize)
            }
        }
        if (average !in ditheredColours) {
            ditheredColours.add(average)
            ditherMap[average] = counters.map { colours[it].toARGB() }
        }

        counters[0] += 1

        for (digit in 0 until counters.size - 1) {
            if (counters[digit] == 6) {
                counters[digit + 1] += 1
                counters[digit] = 0
            }
        }
    }

    val pixels = output.toList()

    val blendDither = false

    val image =
        BufferedImage(
            bufferedImage.width * if (blendDither) 1 else ditherSize,
            bufferedImage.height * if (blendDither) 1 else ditherSize,
            BufferedImage.TYPE_INT_ARGB
        )

    if (blendDither) {
        for (y in 0 until bufferedImage.height) {
            for (x in 0 until bufferedImage.width) {
                val colour = closest(pixels[y * bufferedImage.width + x].toARGB(), ditheredColours)
                image.setRGB(x, y, colour.toColourInt())
            }

            if (y % 10 == 0) {
                println("$y / ${bufferedImage.height}")
            }
        }
    } else {
        for (y in 0 until bufferedImage.height * ditherSize step ditherSize) {
            for (x in 0 until bufferedImage.width * ditherSize step ditherSize) {
                val colour =
                    closest(pixels[y / ditherSize * bufferedImage.width + x / ditherSize].toARGB(), ditheredColours)
                val (c00, c10, c01, c11) = ditherMap[colour]!!
                image.setRGB(x, y, c00.toColourInt())
                image.setRGB(x + 1, y, c01.toColourInt())
                image.setRGB(x, y + 1, c10.toColourInt())
                image.setRGB(x + 1, y + 1, c11.toColourInt())
            }

            if (y % 10 == 0) {
                println("$y / ${bufferedImage.height * ditherSize}")
            }
        }
    }

    val file = File("dithered_starry.png")
    ImageIO.write(image, "png", file)
}

private fun dither() {
    val colours = listOf(
        0xFFFF0000.toInt(),
        0xFF00FF00.toInt(),
        0xFF0000FF.toInt(),
        0xFFFFFFFF.toInt(),
        0xFFFFA500.toInt(),
        0xFFFFFF00.toInt(),
    )

    val ditheringSize = 2

    val counters = MutableList(ditheringSize * ditheringSize) { 0 }

    val averages = mutableListOf<Int>()

    while (counters[counters.size - 1] < 6) {
        val image = BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB)

        val average = counters.map { colours[it] }.sum() / counters.size

        if (average !in averages) {
            averages.add(average)

            for (y in 0 until 1000) {
                for (x in 0 until 1000) {
                    image.setRGB(x, y, colours[counters[(y % ditheringSize) * ditheringSize + (x % ditheringSize)]])
                }
            }
            val file =
                File("dithering$ditheringSize$ditheringSize/${Integer.toHexString(average)}.png")
            ImageIO.write(image, "png", file)
        }

        counters[0] += 1

        for (digit in 0 until counters.size - 1) {
            if (counters[digit] == 6) {
                counters[digit + 1] += 1
                counters[digit] = 0
            }
        }
    }
}