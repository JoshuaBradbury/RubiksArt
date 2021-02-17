package uk.co.newagedev.rubiksart.model

enum class Move {
    UP,
    UP_2,
    UP_PRIME,

    LEFT,
    LEFT_2,
    LEFT_PRIME,

    FRONT,
    FRONT_2,
    FRONT_PRIME,

    RIGHT,
    RIGHT_2,
    RIGHT_PRIME,

    BACK,
    BACK_2,
    BACK_PRIME,

    DOWN,
    DOWN_2,
    DOWN_PRIME;

    override fun toString(): String {
        return when (this) {
            UP -> "U "
            UP_2 -> "U2"
            UP_PRIME -> "U'"

            LEFT -> "L "
            LEFT_2 -> "L2"
            LEFT_PRIME -> "L'"

            FRONT -> "F "
            FRONT_2 -> "F2"
            FRONT_PRIME -> "F'"

            RIGHT -> "R "
            RIGHT_2 -> "R2"
            RIGHT_PRIME -> "R'"

            BACK -> "B "
            BACK_2 -> "B2"
            BACK_PRIME -> "B'"

            DOWN -> "D "
            DOWN_2 -> "D2"
            DOWN_PRIME -> "D'"
        }
    }
}