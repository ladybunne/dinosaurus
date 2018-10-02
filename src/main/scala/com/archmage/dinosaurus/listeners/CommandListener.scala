package com.archmage.dinosaurus.listeners

import com.archmage.dinosaurus.components.dinosaurus.ResponseLogic
import com.archmage.dinosaurus.globals.{Constants, ExceptionWrapper}
import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent

import scala.util.matching.Regex

class CommandListener extends IListener[MessageReceivedEvent] {
	val command: Regex = Constants.commandRegex.r

	override def handle(event: MessageReceivedEvent): Unit = {
		ExceptionWrapper.wrap(event, () => {
			event.getMessage.getContent match {
				case command(name, args) => {
					name match {
						case "today" => ResponseLogic.eventsToday(event.getChannel)
						case _ => event.getChannel.sendMessage(s"Command: `$name`; Args: `$args`")
					}
				}
				case _ => ()
			}
		})
	}
}