package net.sourceforge.plantuml.command;

import java.util.List;

import net.sourceforge.plantuml.classdiagram.command.CommandHideShowByGender;
import net.sourceforge.plantuml.classdiagram.command.CommandHideShowByVisibility;
import net.sourceforge.plantuml.sequencediagram.command.CommandSkin;
import net.sourceforge.plantuml.statediagram.command.CommandHideEmptyDescription;
import net.sourceforge.plantuml.style.CommandStyleImport;
import net.sourceforge.plantuml.style.CommandStyleMultilinesCSS;

public final class CommonCommands {

	private CommonCommands() {
	}

	static public void addCommonCommands1(List<Command> cmds) {
		addTitleCommands(cmds);
		addCommonCommands2(cmds);
		addCommonHides(cmds);
	}

	static public void addCommonCommands2(List<Command> cmds) {
		cmds.add(CommandNope.ME);
		cmds.add(CommandPragma.ME);
		cmds.add(CommandAssumeTransparent.ME);

		cmds.add(CommandSkinParam.ME);
		cmds.add(CommandSkinParamMultilines.ME);
		cmds.add(CommandSkin.ME);
		cmds.add(CommandMinwidth.ME);
		cmds.add(CommandPage.ME);
		cmds.add(CommandRotate.ME);
		cmds.add(CommandScale.ME);
		cmds.add(CommandScaleWidthAndHeight.ME);
		cmds.add(CommandScaleWidthOrHeight.ME);
		cmds.add(CommandScaleMaxWidth.ME);
		cmds.add(CommandScaleMaxHeight.ME);
		cmds.add(CommandScaleMaxWidthAndHeight.ME);
		cmds.add(CommandAffineTransform.ME);
		cmds.add(CommandAffineTransformMultiline.ME);
		final CommandFactorySprite factorySpriteCommand = new CommandFactorySprite();
		cmds.add(factorySpriteCommand.createMultiLine(false));
		cmds.add(factorySpriteCommand.createSingleLine());
		cmds.add(CommandSpriteSvg.ME);
		cmds.add(CommandSpriteFile.ME);
		cmds.add(CommandSpriteSvgMultiline.ME);

		cmds.add(CommandStyleMultilinesCSS.ME);
		cmds.add(CommandStyleImport.ME);

	}

	static public void addCommonHides(List<Command> cmds) {
		cmds.add(CommandHideEmptyDescription.ME);
		cmds.add(CommandHideShowByVisibility.ME);
		cmds.add(CommandHideShowByGender.ME);
	}

	static public void addTitleCommands(List<Command> cmds) {
		cmds.add(CommandTitle.ME);
		cmds.add(CommandMainframe.ME);
		cmds.add(CommandCaption.ME);
		cmds.add(CommandMultilinesCaption.ME);
		cmds.add(CommandMultilinesTitle.ME);
		cmds.add(CommandMultilinesLegend.ME);
		cmds.add(CommandLegend.ME);

		cmds.add(CommandFooter.ME);
		cmds.add(CommandMultilinesFooter.ME);

		cmds.add(CommandHeader.ME);
		cmds.add(CommandMultilinesHeader.ME);
	}

}
