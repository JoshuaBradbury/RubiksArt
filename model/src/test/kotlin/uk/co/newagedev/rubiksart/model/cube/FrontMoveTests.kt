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
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.modifyBottom
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.modifyLeft
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.modifyRight
import uk.co.newagedev.rubiksart.model.cube.FaceTestData.modifyTop

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FrontMoveTests {

    @Test
    fun `rotate front clockwise once`() {
        val cube = getTestCube()

        cube.move(Move.FRONT).apply {
            back shouldBeEqualTo getAlphaTestFace()
            front shouldBeEqualTo getRotatedOnceBetaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.YELLOW, Colour.RED, Colour.BLUE)
            )
            left shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.YELLOW, Colour.YELLOW, Colour.WHITE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.ORANGE, Colour.WHITE, Colour.YELLOW)
            )
            down shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.BLUE, Colour.GREEN, Colour.YELLOW)
            )
        }
    }

    @Test
    fun `rotate front clockwise twice`() {
        val cube = getTestCube()

        cube.move(Move.FRONT_2).apply {
            back shouldBeEqualTo getAlphaTestFace()
            front shouldBeEqualTo getRotatedTwiceBetaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.WHITE, Colour.YELLOW, Colour.YELLOW)
            )
            left shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.BLUE, Colour.GREEN, Colour.YELLOW)
            )
            right shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.YELLOW, Colour.RED, Colour.BLUE)
            )
            down shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.YELLOW, Colour.WHITE, Colour.ORANGE)
            )
        }
    }

    @Test
    fun `rotate front counter clockwise`() {
        val cube = getTestCube()

        cube.move(Move.FRONT_PRIME).apply {
            back shouldBeEqualTo getAlphaTestFace()
            front shouldBeEqualTo getRotatedThriceBetaTestFace()
            up shouldBeEqualTo getAlphaTestFace().modifyBottom(
                listOf(Colour.YELLOW, Colour.GREEN, Colour.BLUE)
            )
            left shouldBeEqualTo getAlphaTestFace().modifyRight(
                listOf(Colour.YELLOW, Colour.WHITE, Colour.ORANGE)
            )
            right shouldBeEqualTo getBetaTestFace().modifyLeft(
                listOf(Colour.WHITE, Colour.YELLOW, Colour.YELLOW)
            )
            down shouldBeEqualTo getBetaTestFace().modifyTop(
                listOf(Colour.BLUE, Colour.RED, Colour.YELLOW)
            )
        }
    }
}