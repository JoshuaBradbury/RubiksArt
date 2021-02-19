package uk.co.newagedev.rubiksart.model.art

import uk.co.newagedev.rubiksart.model.cube.Colour
import uk.co.newagedev.rubiksart.model.cube.Face
import uk.co.newagedev.rubiksart.model.cube.Move
import uk.co.newagedev.rubiksart.model.solver.FaceSolver.solveForFace
import java.awt.image.DataBufferByte
import java.io.File
import javax.imageio.ImageIO


object Art {

    fun loadImageData(path: String): List<Int> {
        val bufferedImage = ImageIO.read(File(path))

        val output = mutableListOf<Int>()
        for (y in 0 until bufferedImage.height) {
            for (x in 0 until bufferedImage.width) {
                output.add(bufferedImage.getRGB(x, y))
            }
        }

        return output.toList()
    }

    fun getCubesFromImage(path: String, cubeWidth: Int): List<List<Move>> {
        val data = loadImageData(path).map {
            when (it) {
                0xFFFF0000.toInt() -> Colour.RED
                0xFF00FF00.toInt() -> Colour.GREEN
                0xFF0000FF.toInt() -> Colour.BLUE
                0xFFFFFFFF.toInt() -> Colour.WHITE
                0xFFFFFF00.toInt() -> Colour.YELLOW
                0xFFFF6A00.toInt() -> Colour.ORANGE
                else -> throw IllegalStateException("Unexpected pixel colour ${Integer.toHexString(it)}")
            }
        }

        if (data.size % 9 != 0) {
            throw IllegalArgumentException("Image has incorrect pixel count, must be divisible by 9")
        }

        val width = cubeWidth * 3
        val height = data.size / width

        val faces = mutableListOf<Face>()

        for (x in 0 until width step 3) {
            for (y in 0 until height step 3) {
                faces.add(
                    data.subList(y * width + x, y * width + x + 3) +
                            data.subList((y + 1) * width + x, (y + 1) * width + x + 3) +
                            data.subList((y + 2) * width + x, (y + 2) * width + x + 3)
                )
            }
        }

        val moves = mutableListOf<List<Move>>()

        for (face in faces) {
            val solution = solveForFace(face) ?: throw IllegalStateException("Couldn't find solution for face $face")
            moves.add(solution)
        }

        return moves.toList()
    }
}