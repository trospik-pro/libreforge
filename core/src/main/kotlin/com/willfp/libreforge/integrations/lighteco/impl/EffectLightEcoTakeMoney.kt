package com.willfp.libreforge.integrations.lighteco.impl

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.core.integrations.economy.balance
import com.willfp.libreforge.NoCompileData
import com.willfp.libreforge.arguments
import com.willfp.libreforge.effects.Effect
import com.willfp.libreforge.getDoubleFromExpression
import com.willfp.libreforge.integrations.lighteco.LightEcoIntegration
import com.willfp.libreforge.triggers.TriggerData
import com.willfp.libreforge.triggers.TriggerParameter
import dev.xhyrom.lighteco.api.LightEcoProvider
import java.math.BigDecimal

object EffectLightEcoTakeMoney : Effect<NoCompileData>("lighteco_take_money") {
    override val parameters = setOf(
        TriggerParameter.PLAYER
    )

    override val arguments = arguments {
        require("currency", "You must specify the name of currency")
        require("amount", "You must specify the amount of money to give!")
    }

    override fun onTrigger(config: Config, data: TriggerData, compileData: NoCompileData): Boolean {
        val player = data.player ?: return false
        val user = LightEcoProvider.get().userManager.getUser(player.uniqueId) ?: return false
        val currency = LightEcoProvider.get().currencyManager.getCurrency(config.getString("currency")) ?: return false

        user.withdraw(currency, BigDecimal.valueOf(config.getDoubleFromExpression("amount", data)))

        return true
    }
}
