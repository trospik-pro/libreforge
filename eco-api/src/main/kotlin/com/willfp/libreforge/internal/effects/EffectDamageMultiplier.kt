package com.willfp.libreforge.internal.effects

import com.willfp.eco.core.config.interfaces.JSONConfig
import com.willfp.libreforge.api.effects.ConfigViolation
import com.willfp.libreforge.api.effects.Effect
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

class EffectDamageMultiplier : Effect("damage_multiplier") {
    override fun onAnyDamage(
        attacker: Player,
        victim: LivingEntity,
        event: EntityDamageByEntityEvent,
        config: JSONConfig
    ) {
        if (!this.getFilter(config).matches(victim)) {
            return
        }

        event.damage *= config.getDouble("multiplier")
    }

    override fun validateConfig(config: JSONConfig): List<ConfigViolation> {
        val violations = mutableListOf<ConfigViolation>()

        config.getDoubleOrNull("multiplier")
            ?: violations.add(
                ConfigViolation(
                    "multiplier",
                    "You must specify the damage multiplier!"
                )
            )

        return violations
    }
}