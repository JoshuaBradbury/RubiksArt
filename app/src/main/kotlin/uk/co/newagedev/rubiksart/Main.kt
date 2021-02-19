package uk.co.newagedev.rubiksart

import uk.co.newagedev.rubiksart.model.cube.Colour
import uk.co.newagedev.rubiksart.model.solver.FaceSolver.solveForFace

fun main() {
    println(
        solveForFace(
            listOf(
                Colour.WHITE, Colour.WHITE, Colour.WHITE,
                Colour.BLUE, Colour.WHITE, Colour.BLUE,
                Colour.BLUE, Colour.WHITE, Colour.BLUE,
            )
        )
    )
}