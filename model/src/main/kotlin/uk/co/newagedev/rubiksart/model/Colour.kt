package uk.co.newagedev.rubiksart.model

enum class Colour {
    WHITE,
    RED,
    YELLOW,
    GREEN,
    BLUE,
    ORANGE;

    override fun toString(): String {
        return when (this) {
            WHITE -> "W"
            RED -> "R"
            YELLOW -> "Y"
            GREEN -> "G"
            BLUE -> "B"
            ORANGE -> "O"
        }
    }
}