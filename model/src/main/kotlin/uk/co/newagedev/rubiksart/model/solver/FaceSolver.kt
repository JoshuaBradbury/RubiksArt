package uk.co.newagedev.rubiksart.model.solver

import uk.co.newagedev.rubiksart.model.cube.Cube
import uk.co.newagedev.rubiksart.model.cube.Face
import uk.co.newagedev.rubiksart.model.cube.Move
import java.util.*

object FaceSolver {

    fun solveForFace(face: Face): List<Move>? {
        val startCube = Cube.new()

        val heuristic = startCube.getFaceHeuristic(face)
        if (heuristic == 9) {
            return emptyList()
        }

        val nodes = PriorityQueue<Node>()

        for (move in Move.values()) {
            nodes.add(Node(startCube.move(move), listOf(move), heuristic))
        }

        while (nodes.isNotEmpty()) {
            val node = nodes.remove()

            if (node.score == 9) {
                return node.moves
            }

            if (node.moves.size == 20) {
                continue
            }

            for (move in Move.possibleNext(node.moves.last())) {
                val cube = node.cube.move(move)
                nodes.add(Node(cube, node.moves + listOf(move), cube.getFaceHeuristic(face)))
            }
        }

        return null
    }
}