package uk.co.newagedev.rubiksart

import uk.co.newagedev.rubiksart.model.art.Art.getCubesFromImage

fun main() {
    getCubesFromImage("examples/mario/question_block.png", 6).windowed(6, 6).map {
        println(it)
    }
}