package com.github.supergluelib.lamp

import com.github.supergluelib.lamp.annotations.NotSelf
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.bukkit.BukkitCommandHandler
import revxrsal.commands.command.CommandActor
import revxrsal.commands.command.CommandParameter
import revxrsal.commands.exception.CommandErrorException

object LampManager {

    private lateinit var plugin: JavaPlugin
    private fun getPlugin() = runCatching { plugin }.getOrElse { throw NullPointerException("The wrapper must be setup first via LampManager.setup(Plugin)") }

    /**
     * Get the command handler for your plugin, registered with all of the wrappers extensions.
     * @param handler Your existing command handler if you have one, otherwise creates one for you
     * @return the same [BukkitCommandHandler] (for conciseness)
     */
    fun create(plugin: JavaPlugin, handler: BukkitCommandHandler = BukkitCommandHandler.create(plugin)): BukkitCommandHandler {
        this.plugin = plugin
        registerParameterValidators(handler)
        return handler
    }

    /**
     * @param condition Whether the parameter is valid, i.e. if this returns false, an error will be thrown.
     */
    private fun <T, A: Annotation> BukkitCommandHandler.registerAnnotationParameterValidator(
        constraintClass: Class<T>,
        annotationClass: Class<A>,
        errorMessage: String,
        condition: (CommandActor, CommandParameter, T) -> Boolean
    ): BukkitCommandHandler {
        registerParameterValidator(constraintClass) { value, param, actor ->
            if (param.hasAnnotation(annotationClass) && !condition.invoke(actor, param, value)) throw CommandErrorException(errorMessage)
        }
        return this
    }

    private fun registerParameterValidators(handler: BukkitCommandHandler) {
        handler.registerAnnotationParameterValidator(Player::class.java, NotSelf::class.java, "You cannot specify yourself") {
              actor, _, player -> actor.uniqueId != player.uniqueId
        }
    }


}