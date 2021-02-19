package uk.co.newagedev.rubiksart.model

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import uk.co.newagedev.rubiksart.model.FaceTestData.getAlphaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getBetaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getRotatedOnceBetaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getRotatedThriceBetaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getRotatedTwiceBetaTestFace
import uk.co.newagedev.rubiksart.model.FaceTestData.getTestCube
import uk.co.newagedev.rubiksart.model.FaceTestData.modifyBottom

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DownMoveTests {

    @Test
    fun `rotate down clockwise once`() {
        val cube = getTestCube()

        cube.move(Move.DOWN).apply {
            up shouldBeEqualTo getAlphaTestFace()
            down shouldBeEqualTo getRotatedOnceBetaTestFace()
            front shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.YELLOW)
            )
            left shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.YELLOW)
            )
            right shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.BLUE, Colour.RED, Colour.ORANGE)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.BLUE, Colour.RED, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate down clockwise twice`() {
        val cube = getTestCube()

        cube.move(Move.DOWN_2).apply {
            up shouldBeEqualTo getAlphaTestFace()
            down shouldBeEqualTo getRotatedTwiceBetaTestFace()
            front shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.YELLOW)
            )
            left shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.BLUE, Colour.RED, Colour.ORANGE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.YELLOW)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.BLUE, Colour.RED, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate down counter clockwise`() {
        val cube = getTestCube()

        cube.move(Move.DOWN_PRIME).apply {
            up shouldBeEqualTo getAlphaTestFace()
            down shouldBeEqualTo getRotatedThriceBetaTestFace()
            front shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.BLUE, Colour.RED, Colour.ORANGE)
            )
            left shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.BLUE, Colour.RED, Colour.ORANGE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.YELLOW)
            )
            back shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.YELLOW)
            )
        }
    }
}