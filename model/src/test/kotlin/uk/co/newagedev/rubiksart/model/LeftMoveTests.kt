package uk.co.newagedev.rubiksart.model

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import uk.co.newagedev.rubiksart.model.FaceTestData.getAlphaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getBetaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getRotatedOnceAlphaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getRotatedThriceAlphaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getRotatedTwiceAlphaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getTestCube
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyLeft
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyRight

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LeftMoveTests {

    @Test
    fun `rotate left clockwise once`() {
        val cube = getTestCube()

        cube.move(Move.LEFT).apply {
            right shouldBeEqualTo getBetaTestFace()
            left shouldBeEqualTo getRotatedOnceAlphaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.RED, Colour.BLUE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.BLUE, Colour.GREEN, Colour.YELLOW)
            )
            down shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.GREEN, Colour.BLUE)
            )
        }
    }

    @Test
    fun `rotate left clockwise twice`() {
        val cube = getTestCube()

        cube.move(Move.LEFT_2).apply {
            right shouldBeEqualTo getBetaTestFace()
            left shouldBeEqualTo getRotatedTwiceAlphaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.GREEN, Colour.BLUE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.RED, Colour.BLUE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.BLUE, Colour.GREEN, Colour.YELLOW)
            )
            down shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate left counter clockwise`() {
        val cube = getTestCube()

        cube.move(Move.LEFT_PRIME).apply {
            right shouldBeEqualTo getBetaTestFace()
            left shouldBeEqualTo getRotatedThriceAlphaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.GREEN, Colour.BLUE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.GREEN, Colour.BLUE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.RED, Colour.BLUE)
            )
        }
    }
}