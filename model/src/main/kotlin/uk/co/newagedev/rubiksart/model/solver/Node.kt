package uk.co.newagedev.rubiksart.model.solver

import uk.co.newagedev.rubiksart.model.cube.Cube
import uk.co.newagedev.rubiksart.model.cube.Move

data class Node(
    val cube: Cube,
    val moves: List<Move>,
    val score: Int,
) : Comparable<Node> {

    override fun compareTo(other: Node): Int {
        return if (score == other.score) {
            moves.size.compareTo(other.moves.size)
        } else {
            other.score.compareTo(score)
        }
    }
}