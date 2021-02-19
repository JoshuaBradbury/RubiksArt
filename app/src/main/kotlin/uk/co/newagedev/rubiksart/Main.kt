package uk.co.newagedev.rubiksart

import uk.co.newagedev.rubiksart.model.cube.Colour
import uk.co.newagedev.rubiksart.model.cube.Cube
import uk.co.newagedev.rubiksart.model.cube.Move

fun main() {
    Cube.new().move(Move.LEFT_2)
        .move(Move.UP_2)
        .print()

    val cube = Cube.new()
        .move(Move.RIGHT_2)
        .move(Move.LEFT_2)
        .move(Move.UP_2)
        .move(Move.RIGHT_2)
        .move(Move.LEFT_2)
        .move(Move.DOWN_2)
        .move(Move.LEFT_2)
        .move(Move.RIGHT_2)

    cube.print()

    println(
        cube.getFaceHeuristic(
            listOf(
                Colour.YELLOW,
                Colour.YELLOW,
                Colour.YELLOW,
                Colour.WHITE,
                Colour.WHITE,
                Colour.WHITE,
                Colour.YELLOW,
                Colour.YELLOW,
                Colour.YELLOW,
            )
        )
    )

    val (moves, scrambledCube) = Cube.scrambled()
    println(moves)
    scrambledCube.print()
}