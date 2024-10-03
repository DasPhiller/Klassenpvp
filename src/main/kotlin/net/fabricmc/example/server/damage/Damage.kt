package net.fabricmc.example.server.damage

import net.minecraft.server.network.ServerPlayerEntity
import net.silkmc.silk.core.task.mcCoroutineTask
import java.util.UUID
import kotlin.time.Duration.Companion.seconds

object Damage {

    private val isInCombat = mutableMapOf<UUID, Boolean>()
    var ServerPlayerEntity.isInCombat: Boolean
        get() = Damage.isInCombat[this.uuid] ?: false
        set(value) {
            Damage.isInCombat[this.uuid] = value
        }

    fun onDamage(player: ServerPlayerEntity) {
        if (player.isInCombat) return
        player.isInCombat = true
        mcCoroutineTask(sync = true, delay = 15.seconds) {
            player.isInCombat = false
        }
    }
}