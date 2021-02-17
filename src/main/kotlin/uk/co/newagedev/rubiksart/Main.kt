package uk.co.newagedev.rubiksart

import uk.co.newagedev.rubiksart.model.Cube
import uk.co.newagedev.rubiksart.model.Move

fun main() {
    val cube = Cube.new()

    cube.move(Move.RIGHT_2)
        .move(Move.LEFT_2)
        .move(Move.UP_2)
        .move(Move.RIGHT_2)
        .move(Move.LEFT_2)
        .move(Move.DOWN_2)
        .move(Move.LEFT_2)
        .move(Move.RIGHT_2)
        .print()
}