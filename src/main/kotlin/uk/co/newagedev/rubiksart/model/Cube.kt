package uk.co.newagedev.rubiksart.model

/**
 * For simplicity the cube is represented always from the face with white as Up, Red as the front face
 */

class Cube private constructor(
    val up: List<Colour>,
    val down: List<Colour>,
    val front: List<Colour>,
    val left: List<Colour>,
    val right: List<Colour>,
    val back: List<Colour>,
) {

    fun move(move: Move): Cube {
        return when (move) {
            Move.UP -> rotateFace(Face.UP, clockwise = true, amount = 1)
            Move.UP_2 -> rotateFace(Face.UP, clockwise = true, amount = 2)
            Move.UP_PRIME -> rotateFace(Face.UP, clockwise = false, amount = 1)

            Move.LEFT -> rotateFace(Face.LEFT, clockwise = true, amount = 1)
            Move.LEFT_2 -> rotateFace(Face.LEFT, clockwise = true, amount = 2)
            Move.LEFT_PRIME -> rotateFace(Face.LEFT, clockwise = false, amount = 1)

            Move.BACK -> rotateFace(Face.BACK, clockwise = true, amount = 1)
            Move.BACK_2 -> rotateFace(Face.BACK, clockwise = true, amount = 2)
            Move.BACK_PRIME -> rotateFace(Face.BACK, clockwise = false, amount = 1)

            Move.FRONT -> rotateFace(Face.FRONT, clockwise = true, amount = 1)
            Move.FRONT_2 -> rotateFace(Face.FRONT, clockwise = true, amount = 2)
            Move.FRONT_PRIME -> rotateFace(Face.FRONT, clockwise = false, amount = 1)

            Move.RIGHT -> rotateFace(Face.RIGHT, clockwise = true, amount = 1)
            Move.RIGHT_2 -> rotateFace(Face.RIGHT, clockwise = true, amount = 2)
            Move.RIGHT_PRIME -> rotateFace(Face.RIGHT, clockwise = false, amount = 1)

            Move.DOWN -> rotateFace(Face.DOWN, clockwise = true, amount = 1)
            Move.DOWN_2 -> rotateFace(Face.DOWN, clockwise = true, amount = 2)
            Move.DOWN_PRIME -> rotateFace(Face.DOWN, clockwise = false, amount = 1)
        }
    }

    private fun rotateFace(around: Face, clockwise: Boolean, amount: Int): Cube {
        val normalisedAmount = if (clockwise) amount else 4 - amount

        return when (around) {
            Face.UP -> rotateAroundUp(normalisedAmount)
            Face.RIGHT -> rotateAroundRight(normalisedAmount)
            Face.LEFT -> rotateAroundLeft(normalisedAmount)
            Face.BACK -> rotateAroundBack(normalisedAmount)
            Face.FRONT -> rotateAroundFront(normalisedAmount)
            Face.DOWN -> rotateAroundDown(normalisedAmount)
        }
    }

    private fun rotateAroundUp(amount: Int): Cube {
        val faces = listOf(right, back, left, front).map { it.subList(0, 3) }
        return Cube(
            rotateList(up, amount),
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
            rotateList(down, amount),
            front.subList(0, 6) + faces[(0 + amount) % 4],
            left.subList(0, 6) + faces[(1 + amount) % 4],
            right.subList(0, 6) + faces[(3 + amount) % 4],
            back.subList(0, 6) + faces[(2 + amount) % 4],
        )
    }

    private fun rotateAroundBack(amount: Int): Cube {
        val faces = listOf(
            listOf(up[2], up[1], up[0]),
            listOf(right[2], right[5], right[8]),
            listOf(down[8], down[7], down[6]),
            listOf(left[0], left[3], left[6]),
        )
        return Cube(
            faces[(0 + amount) % 4] + up.subList(3, 9),
            down.subList(0, 6) + faces[(2 + amount) % 4],
            front,
            listOf(
                faces[(3 + amount) % 4][0],
                left[1],
                left[2],
                faces[(3 + amount) % 4][1],
                left[4],
                left[5],
                faces[(3 + amount) % 4][2],
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
            rotateList(back, amount),
        )
    }

    private fun rotateAroundFront(amount: Int): Cube {
        val faces = listOf(
            listOf(left[2], left[5], left[8]),
            down.subList(0, 3),
            listOf(right[0], right[3], right[6]),
            up.subList(6, 9),
        )
        return Cube(
            up.subList(0, 6) + faces[(3 + amount) % 4],
            faces[(1 + amount) % 4] + down.subList(3, 9),
            rotateList(front, amount),
            listOf(
                left[0],
                left[1],
                faces[(0 + amount) % 4][0],
                left[3],
                left[4],
                faces[(0 + amount) % 4][1],
                left[6],
                left[7],
                faces[(0 + amount) % 4][2],
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
            rotateList(left, amount),
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
            rotateList(right, amount),
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

    private fun rotateList(list: List<Colour>, amount: Int): List<Colour> {
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
    }
}
