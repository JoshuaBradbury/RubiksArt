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
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyBottom
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyLeft
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyRight
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyTop

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BackMoveTests {

    @Test
    fun `rotate back clockwise once`() {
        val cube = getTestCube()

        cube.move(Move.BACK).apply {
            front shouldBeEqualTo getBetaTestFace()
            back shouldBeEqualTo getRotatedOnceAlphaTestFace()
            left shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.BLUE, Colour.WHITE, Colour.ORANGE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.ORANGE, Colour.RED, Colour.BLUE)
            )
            up shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.WHITE, Colour.RED, Colour.ORANGE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate back clockwise twice`() {
        val cube = getTestCube()

        cube.move(Move.BACK_2).apply {
            front shouldBeEqualTo getBetaTestFace()
            back shouldBeEqualTo getRotatedTwiceAlphaTestFace()
            left shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.ORANGE, Colour.RED, Colour.WHITE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
            up shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.RED, Colour.BLUE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.BLUE, Colour.WHITE, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate back counter clockwise`() {
        val cube = getTestCube()

        cube.move(Move.BACK_PRIME).apply {
            front shouldBeEqualTo getBetaTestFace()
            back shouldBeEqualTo getRotatedThriceAlphaTestFace()
            left shouldBeEqualTo getAlphaTestFace().modifyLeft(
                listOf(Colour.BLUE, Colour.RED, Colour.ORANGE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyRight(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.BLUE)
            )
            up shouldBeEqualTo getAlphaTestFace().modifyTop(
                listOf(Colour.ORANGE, Colour.GREEN, Colour.ORANGE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.RED, Colour.WHITE)
            )
        }
    }
}