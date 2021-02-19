package uk.co.newagedev.rubiksart.model.cube

/**
 * For simplicity the cube is represented always from the face with white as Up, Red as the front face.
 *
 * The faces are row major when looking at the face
 */

typealias Face = List<Colour>

class Cube internal constructor(
    val up: Face,
    val down: Face,
    val front: Face,
    val left: Face,
    val right: Face,
    val back: Face,
) {

    fun move(move: Move): Cube {
        return when (move) {
            Move.UP, Move.UP_2, Move.UP_PRIME -> rotateAroundUp(move.moveCount())
            Move.RIGHT, Move.RIGHT_2, Move.RIGHT_PRIME -> rotateAroundRight(move.moveCount())
            Move.LEFT, Move.LEFT_2, Move.LEFT_PRIME -> rotateAroundLeft(move.moveCount())
            Move.BACK, Move.BACK_2, Move.BACK_PRIME -> rotateAroundBack(move.moveCount())
            Move.FRONT, Move.FRONT_2, Move.FRONT_PRIME -> rotateAroundFront(move.moveCount())
            Move.DOWN, Move.DOWN_2, Move.DOWN_PRIME -> rotateAroundDown(move.moveCount())
        }
    }

    private fun rotateAroundUp(amount: Int): Cube {
        val faces = listOf(right, back, left, front).map { it.subList(0, 3) }
        return Cube(
            rotateFace(up, amount),
            down,
            faces[(3 + amount) % 4] + front.subList(3, 9),
            faces[(2 + amount) % 4] + left.subList(3, 9),
            faces[(0 + amount) % 4] + right.subList(3, 9),
            faces[(1 + amount) % 4] + back.subList(3, 9),
        )
    }

    private fun rotateAroundDown(amount: Int): Cube {
        val faces = listOf(front, left, back, right).map { it.subList(6, 9) }
        return Cube(
            up,
            rotateFace(down, amount),
            front.subList(0, 6) + faces[(0 + amount) % 4],
            left.subList(0, 6) + faces[(1 + amount) % 4],
            right.subList(0, 6) + faces[(3 + amount) % 4],
            back.subList(0, 6) + faces[(2 + amount) % 4],
        )
    }

    private fun rotateAroundBack(amount: Int): Cube {
        val faces = listOf(
            up.subList(0, 3),
            listOf(right[2], right[5], right[8]),
            listOf(down[8], down[7], down[6]),
            listOf(left[6], left[3], left[0]),
        )
        return Cube(
            faces[(0 + amount) % 4] + up.subList(3, 9),
            down.subList(0, 6) + faces[(2 + amount) % 4].reversed(),
            front,
            listOf(
                faces[(3 + amount) % 4][2],
                left[1],
                left[2],
                faces[(3 + amount) % 4][1],
                left[4],
                left[5],
                faces[(3 + amount) % 4][0],
                left[7],
                left[8],
            ),
            listOf(
                right[0],
                right[1],
                faces[(1 + amount) % 4][0],
                right[3],
                right[4],
                faces[(1 + amount) % 4][1],
                right[6],
                right[7],
                faces[(1 + amount) % 4][2],
            ),
            rotateFace(back, amount),
        )
    }

    private fun rotateAroundFront(amount: Int): Cube {
        val faces = listOf(
            listOf(left[8], left[5], left[2]),
            listOf(down[2], down[1], down[0]),
            listOf(right[0], right[3], right[6]),
            up.subList(6, 9),
        )
        return Cube(
            up.subList(0, 6) + faces[(3 + amount) % 4],
            faces[(1 + amount) % 4].reversed() + down.subList(3, 9),
            rotateFace(front, amount),
            listOf(
                left[0],
                left[1],
                faces[(0 + amount) % 4][2],
                left[3],
                left[4],
                faces[(0 + amount) % 4][1],
                left[6],
                left[7],
                faces[(0 + amount) % 4][0],
            ),
            listOf(
                faces[(2 + amount) % 4][0],
                right[1],
                right[2],
                faces[(2 + amount) % 4][1],
                right[4],
                right[5],
                faces[(2 + amount) % 4][2],
                right[7],
                right[8],
            ),
            back,
        )
    }

    private fun rotateAroundLeft(amount: Int): Cube {
        val faces = listOf(
            listOf(up[0], up[3], up[6]),
            listOf(back[8], back[5], back[2]),
            listOf(down[0], down[3], down[6]),
            listOf(front[0], front[3], front[6]),
        )
        return Cube(
            listOf(
                faces[(0 + amount) % 4][0],
                up[1],
                up[2],
                faces[(0 + amount) % 4][1],
                up[4],
                up[5],
                faces[(0 + amount) % 4][2],
                up[7],
                up[8],
            ),
            listOf(
                faces[(2 + amount) % 4][0],
                down[1],
                down[2],
                faces[(2 + amount) % 4][1],
                down[4],
                down[5],
                faces[(2 + amount) % 4][2],
                down[7],
                down[8],
            ),
            listOf(
                faces[(3 + amount) % 4][0],
                front[1],
                front[2],
                faces[(3 + amount) % 4][1],
                front[4],
                front[5],
                faces[(3 + amount) % 4][2],
                front[7],
                front[8],
            ),
            rotateFace(left, amount),
            right,
            listOf(
                back[0],
                back[1],
                faces[(1 + amount) % 4][2],
                back[3],
                back[4],
                faces[(1 + amount) % 4][1],
                back[6],
                back[7],
                faces[(1 + amount) % 4][0],
            ),
        )
    }

    private fun rotateAroundRight(amount: Int): Cube {
        val faces = listOf(
            listOf(front[2], front[5], front[8]),
            listOf(down[2], down[5], down[8]),
            listOf(back[6], back[3], back[0]),
            listOf(up[2], up[5], up[8]),
        )
        return Cube(
            listOf(
                up[0],
                up[1],
                faces[(3 + amount) % 4][0],
                up[3],
                up[4],
                faces[(3 + amount) % 4][1],
                up[6],
                up[7],
                faces[(3 + amount) % 4][2],
            ),
            listOf(
                down[0],
                down[1],
                faces[(1 + amount) % 4][0],
                down[3],
                down[4],
                faces[(1 + amount) % 4][1],
                down[6],
                down[7],
                faces[(1 + amount) % 4][2],
            ),
            listOf(
                front[0],
                front[1],
                faces[(0 + amount) % 4][0],
                front[3],
                front[4],
                faces[(0 + amount) % 4][1],
                front[6],
                front[7],
                faces[(0 + amount) % 4][2],
            ),
            left,
            rotateFace(right, amount),
            listOf(
                faces[(2 + amount) % 4][2],
                back[1],
                back[2],
                faces[(2 + amount) % 4][1],
                back[4],
                back[5],
                faces[(2 + amount) % 4][0],
                back[7],
                back[8],
            ),
        )
    }

    private fun rotateFace(list: Face, amount: Int): Face {
        val evenRot = listOf(6, 8, 2, 0)
        val oddRot = listOf(3, 7, 5, 1)

        return listOf(
            list[evenRot[(3 + amount) % 4]],
            list[oddRot[(3 + amount) % 4]],
            list[evenRot[(2 + amount) % 4]],
            list[oddRot[(0 + amount) % 4]],
            list[4],
            list[oddRot[(2 + amount) % 4]],
            list[evenRot[(0 + amount) % 4]],
            list[oddRot[(1 + amount) % 4]],
            list[evenRot[(1 + amount) % 4]],
        )
    }

    fun getFaceHeuristic(face: Face): Int {
        if (face.size != 9) return Integer.MIN_VALUE

        val faceToCheck = when (face[4]) {
            Colour.YELLOW -> down
            Colour.WHITE -> up
            Colour.GREEN -> left
            Colour.BLUE -> right
            Colour.ORANGE -> back
            Colour.RED -> front
        }

        return maxOf(
            getFaceDistance(face, faceToCheck),
            getFaceDistance(rotateFace(face, 1), faceToCheck),
            getFaceDistance(rotateFace(face, 2), faceToCheck),
            getFaceDistance(rotateFace(face, 3), faceToCheck),
        )
    }

    private fun getFaceDistance(face: Face, faceToCheck: Face): Int {
        return face.mapIndexed { index, colour -> if (faceToCheck[index] == colour) 1 else 0 }.sum()
    }

    fun print() {
        println("    ${up.subList(0, 3).joinToString("")}")
        println("    ${up.subList(3, 6).joinToString("")}")
        println("    ${up.subList(6, 9).joinToString("")}")
        println(
            "${left.subList(0, 3).joinToString("")} ${front.subList(0, 3).joinToString("")} ${
                right.subList(0, 3).joinToString("")
            } ${back.subList(0, 3).joinToString("")}"
        )
        println(
            "${left.subList(3, 6).joinToString("")} ${front.subList(3, 6).joinToString("")} ${
                right.subList(3, 6).joinToString("")
            } ${back.subList(3, 6).joinToString("")}"
        )
        println(
            "${left.subList(6, 9).joinToString("")} ${front.subList(6, 9).joinToString("")} ${
                right.subList(6, 9).joinToString("")
            } ${back.subList(6, 9).joinToString("")}"
        )
        println("    ${down.subList(0, 3).joinToString("")}")
        println("    ${down.subList(3, 6).joinToString("")}")
        println("    ${down.subList(6, 9).joinToString("")}")
    }

    companion object {

        fun new(): Cube {
            return Cube(
                up = List(9) { Colour.WHITE },
                down = List(9) { Colour.YELLOW },
                front = List(9) { Colour.RED },
                left = List(9) { Colour.GREEN },
                right = List(9) { Colour.BLUE },
                back = List(9) { Colour.ORANGE },
            )
        }

        fun scrambled(): Pair<List<Move>, Cube> {
            var cube = new()

            var lastMove: Move? = null
            val moves = List(20) {
                val move = if (lastMove != null) {
                    Move.randomNext(lastMove!!)
                } else {
                    Move.values().random()
                }

                lastMove = move
                move
            }

            for (move in moves) {
                cube = cube.move(move)
            }

            return moves to cube
        }
    }
}
