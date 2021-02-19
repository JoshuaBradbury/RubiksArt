package uk.co.newagedev.rubiksart.model.cube

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.getAlphaTestFace
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.getBetaTestFace
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.getRotatedOnceBetaTestFace
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.getRotatedThriceBetaTestFace
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.getRotatedTwiceBetaTestFace
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.getTestCube
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.modifyLeft
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.modifyRight

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RightMoveTests {

    @Test
    fun `rotate right clockwise once`() {
        val cube = getTestCube()

        cube.move(Move.RIGHT).apply {
            left shouldBeEqualTo getAlphaTestFace()
            right shouldBeEqualTo getRotatedOnceBetaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.WHITE, Colour.RED, Colour.ORANGE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.RED, Colour.BLUE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.WHITE, Colour.RED, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate right clockwise twice`() {
        val cube = getTestCube()

        cube.move(Move.RIGHT_2).apply {
            left shouldBeEqualTo getAlphaTestFace()
            right shouldBeEqualTo getRotatedTwiceBetaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.WHITE, Colour.RED, Colour.ORANGE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.BLUE, Colour.RED, Colour.YELLOW)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.ORANGE, Colour.RED, Colour.WHITE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate right counter clockwise`() {
        val cube = getTestCube()

        cube.move(Move.RIGHT_PRIME).apply {
            left shouldBeEqualTo getAlphaTestFace()
            right shouldBeEqualTo getRotatedThriceBetaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.WHITE, Colour.RED, Colour.ORANGE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.ORANGE, Colour.RED, Colour.WHITE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.BLUE, Colour.RED, Colour.YELLOW)
            )
        }
    }
}