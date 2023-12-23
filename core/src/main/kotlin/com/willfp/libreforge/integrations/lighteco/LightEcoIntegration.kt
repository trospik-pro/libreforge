package com.willfp.libreforge.integrations.lighteco

import com.willfp.eco.core.EcoPlugin
import com.willfp.libreforge.effects.Effects
import com.willfp.libreforge.integrations.LoadableIntegration
import com.willfp.libreforge.integrations.lighteco.impl.EffectLightEcoGiveMoney
import com.willfp.libreforge.integrations.lighteco.impl.EffectLightEcoTakeMoney

object LightEcoIntegration : LoadableIntegration {
    override fun load(plugin: EcoPlugin) {
        Effects.register(EffectLightEcoGiveMoney)
        Effects.register(EffectLightEcoTakeMoney)
    }

    override fun getPluginName(): String {
        return "lighteco-bukkit"
    }
}