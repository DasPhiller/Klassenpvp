package net.fabricmc.example.commands

import net.silkmc.silk.commands.command
import net.silkmc.silk.core.text.literalText
import net.silkmc.silk.core.text.sendText

val testCommand = command("test") {
    runs {
        source.player?.sendText(literalText("test"))
    }
    literal("literal") {
        runs {
            source.player?.sendText(literalText("test x2"))
        }
    }
    argument<String>("argument") { arg ->
        runs {
            source.player?.sendText(literalText(arg()))
        }
    }
}