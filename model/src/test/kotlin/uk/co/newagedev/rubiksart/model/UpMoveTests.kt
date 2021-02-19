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
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyTop

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpMoveTests {

    @Test
    fun `rotate up clockwise once`() {
        val cube = getTestCube()

        cube.move(Move.UP).apply {
            down shouldBeEqualTo getBetaTestFace()
            up shouldBeEqualTo getRotatedOnceAlphaTestFace()
            left shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.YELLOW, Colour.YELLOW, Colour.WHITE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.YELLOW, Colour.YELLOW, Colour.WHITE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.BLUE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.BLUE)
            )
        }
    }

    @Test
    fun `rotate up clockwise twice`() {
        val cube = getTestCube()

        cube.move(Move.UP_2).apply {
            down shouldBeEqualTo getBetaTestFace()
            up shouldBeEqualTo getRotatedTwiceAlphaTestFace()
            left shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.YELLOW, Colour.YELLOW, Colour.WHITE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.BLUE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.BLUE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.YELLOW, Colour.YELLOW, Colour.WHITE)
            )
        }
    }

    @Test
    fun `rotate up counter clockwise`() {
        val cube = getTestCube()

        cube.move(Move.UP_PRIME).apply {
            down shouldBeEqualTo getBetaTestFace()
            up shouldBeEqualTo getRotatedThriceAlphaTestFace()
            left shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.BLUE)
            )
            front shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.BLUE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.YELLOW, Colour.YELLOW, Colour.WHITE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.YELLOW, Colour.YELLOW, Colour.WHITE)
            )
        }
    }
}