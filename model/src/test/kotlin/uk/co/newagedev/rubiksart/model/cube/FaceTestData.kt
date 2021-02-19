package uk.co.newagedev.rubiksart.model.cube

object FaceTestData {
    fun getAlphaTestFace(): List<Colour> {
        return listOf(
            Colour.ORANGE, Colour.WHITE, Colour.BLUE,
            Colour.GREEN, Colour.YELLOW, Colour.RED,
            Colour.ORANGE, Colour.WHITE, Colour.YELLOW,
        )
    }

    fun getBetaTestFace(): List<Colour> {
        return listOf(
            Colour.YELLOW, Colour.YELLOW, Colour.WHITE,
            Colour.GREEN, Colour.GREEN, Colour.RED,
            Colour.BLUE, Colour.RED, Colour.ORANGE,
        )
    }

    fun getRotatedOnceBetaTestFace(): List<Colour> {
        return listOf(
            Colour.BLUE, Colour.GREEN, Colour.YELLOW,
            Colour.RED, Colour.GREEN, Colour.YELLOW,
            Colour.ORANGE, Colour.RED, Colour.WHITE,
        )
    }

    fun getRotatedTwiceBetaTestFace(): List<Colour> {
        return listOf(
            Colour.ORANGE, Colour.RED, Colour.BLUE,
            Colour.RED, Colour.GREEN, Colour.GREEN,
            Colour.WHITE, Colour.YELLOW, Colour.YELLOW,
        )
    }

    fun getRotatedThriceBetaTestFace(): List<Colour> {
        return listOf(
            Colour.WHITE, Colour.RED, Colour.ORANGE,
            Colour.YELLOW, Colour.GREEN, Colour.RED,
            Colour.YELLOW, Colour.GREEN, Colour.BLUE,
        )
    }

    fun getRotatedOnceAlphaTestFace(): List<Colour> {
        return listOf(
            Colour.ORANGE, Colour.GREEN, Colour.ORANGE,
            Colour.WHITE, Colour.YELLOW, Colour.WHITE,
            Colour.YELLOW, Colour.RED, Colour.BLUE,
        )
    }

    fun getRotatedTwiceAlphaTestFace(): List<Colour> {
        return listOf(
            Colour.YELLOW, Colour.WHITE, Colour.ORANGE,
            Colour.RED, Colour.YELLOW, Colour.GREEN,
            Colour.BLUE, Colour.WHITE, Colour.ORANGE,
        )
    }

    fun getRotatedThriceAlphaTestFace(): List<Colour> {
        return listOf(
            Colour.BLUE, Colour.RED, Colour.YELLOW,
            Colour.WHITE, Colour.YELLOW, Colour.WHITE,
            Colour.ORANGE, Colour.GREEN, Colour.ORANGE,
        )
    }

    fun getTestCube(): Cube {
        return Cube(
            up = getAlphaTestFace(),
            down = getBetaTestFace(),
            front = getBetaTestFace(),
            left = getAlphaTestFace(),
            right = getBetaTestFace(),
            back = getAlphaTestFace(),
        )
    }

    fun List<Colour>.modifyBottom(newColours: List<Colour>): List<Colour> {
        return subList(0, 6) + newColours
    }

    fun List<Colour>.modifyTop(newColours: List<Colour>): List<Colour> {
        return newColours + subList(3, 9)
    }

    fun List<Colour>.modifyLeft(newColours: List<Colour>): List<Colour> {
        return listOf(
            newColours[0],
            get(1),
            get(2),
            newColours[1],
            get(4),
            get(5),
            newColours[2],
            get(7),
            get(8),
        )
    }

    fun List<Colour>.modifyRight(newColours: List<Colour>): List<Colour> {
        return listOf(
            get(0),
            get(1),
            newColours[0],
            get(3),
            get(4),
            newColours[1],
            get(6),
            get(7),
            newColours[2],
        )
    }
}